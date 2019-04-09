// miniprogram/pages/editUser/editUserDetails.js
const app = getApp();
Page({
  /**
   * 页面的初始数据
   */

  data: {
    userId: '',
    // 字数限制
    current: 0,
    max: 300,
    startDate: '2018-12-01',
    deadline:'2018-12-01',
    dataFromMyList: {},
    index1:0,
    index2:0,
    index3:0,
    category: ['拼车', '竞赛','电影', '学习', '其他'],
  },

  // 文本框字数限制
  limit: function (e) {
    var value = e.detail.value;
    var length = parseInt(value.length);

    if (length > this.data.noteMaxLen) {
      return;
    }

    this.setData({
      current: length
    });
  },

  listenerPickerSelected: function (e) {
    //改变index值，通过setData()方法重绘界面
    this.setData({
      index1: e.detail.value
    });
  },

  /**
   * 监听日期picker选择器
   */
  listenerStartDatePickerSelected: function (e) {
    this.setData({
      startDate: e.detail.value
    })
  },

  /**
   * 监听日期picker选择器
   */
  listenerDeadlinePickerSelected: function (e) {
    this.setData({
      deadline: e.detail.value
    })
  },

  switchChange: function (e) {
    if (e.detail.value) {
      this.setData({ sex: '男' });
    } else {
      this.setData({ sex: '女' });
    }
  },

  formSubmit: function (e) {
    if (e.detail.value.title.length == 0) {
      wx.showToast({
        title: '标题不能为空',
        icon: 'fail',
      })
    } else if (e.detail.value.content.length == 0) {
      wx.showToast({
        title: '需求内容不能为空',
        icon: 'fail',
      })
    } else if (e.detail.value.limitNo.length == 0) {
      wx.showToast({
        title: '人数限制不能为空',
        icon: 'fail',
      })
    } else if (e.detail.value.qq.length == 0 && e.detail.value.weChat.length == 0 && e.detail.value.phoneNo.length == 0) {
      wx.showModal({
        content: 'qq,微信，手机号至少填写一个',
        showCancel:false,
        icon: 'fail',
      })
    } else if (e.detail.value.phoneNo.length != 11){
      wx.showToast({
        title: '手机号有误',
        icon: 'fail',
      })
    }
    else {
        var data = { "categoryId": this.data.index1, "userId": this.data.userId, "title": e.detail.value.title, "content": e.detail.value.content, "startTime": e.detail.value.startTime, "deadline": e.detail.value.deadline, "phoneNo": e.detail.value.phoneNo, "qq": e.detail.value.qq, "weChat": e.detail.value.weChat, "limitNo": e.detail.value.limitNo };
        wx.showModal({
          title: '',
          content: '确定添加吗？',
          icon: 'success',
          duration: 2000,
          success:function(res) {
            if(res.confirm) {
              wx.request({
                url: 'http://localhost:8080/needsManagement/addNeeds',
                method: 'POST',
                data: JSON.stringify(data),
                header: {
                  'content-type': 'application/json' // 默认值
                },
                success: (res) => {
                  if (res.data.isSuccess) {
                    wx.showToast({
                      title: '成功',
                      icon: 'success',
                      duration: 2000,
                    })
                    console.log("新增需求信息成功");
                  }
                  //success: function(res) {
                    //if (res.confirm) {
                      //wx.navigateTo({
                        //url: '../myList/myList'
                      //})
                    //}
                    //else {
                      //wx.navigateTo({
                        //url: '../myList/myList'
                      //})
                    //}
                  //}
                  else {
                    wx.showToast({
                      title: '失败',
                      icon:'fail',
                      duration:5000
                    })
                    //wx.navigateTo({
                      //url: '../myList/myList'
                    //})
                  }
                }
              })
            }
          }
        })
    }
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let that = this;
    that.setData({
      userId: app.globalData.userId,
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})