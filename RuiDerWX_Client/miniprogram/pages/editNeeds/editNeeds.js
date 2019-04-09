// miniprogram/pages/editUser/editUserDetails.js
const app = getApp();
Page({
  /**
   * 页面的初始数据
   */

  data: {
    userId: app.globalData.userId,
    // 字数限制
    current: 0,
    max: 300,
    startDate: '2018-12-01',
    deadline: '2018-12-01',
    dataFromMyList: {},
    index1: 0,
    index2: 0,
    index3: 0,
    category: ['拼车', '竞赛', '电影', '学习', '其他'],
    dataFromEditList: {}
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
    var data = {"id":this.data.dataFromEditList.id, "categoryId": this.data.index1+1, "userId": 1, "title": e.detail.value.title, "content": e.detail.value.content, "startTime": e.detail.value.startTime, "deadline": e.detail.value.deadline, "phoneNo": e.detail.value.phoneNo, "qq": e.detail.value.qq, "weChat": e.detail.value.weChat, "limitNo": e.detail.value.limitNo };
    wx.showModal({
      title: '',
      content: '确定修改吗？',
      icon: 'success',
      duration: 2000,
      success: function (res) {
        if (res.confirm) {
          wx.request({
            url: 'http://localhost:8080/needsManagement/editNeeds',
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
                console.log("修改需求信息成功");
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
                  icon: 'fail',
                  duration: 5000
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
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let that = this;
    that.setData({
      userId: app.globalData.userId,
    })
    that.setData({
      dataFromEditList: JSON.parse(options.data),
      deadline: JSON.parse(options.data).deadline.substring(0, 10),
      startDate: JSON.parse(options.data).startTime.substring(0, 10)
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