// miniprogram/pages/evaluatePage/evaluate.js
const app = getApp();
Page({
  /**
   * 页面的初始数据
   */
  data: {
    current: 0,
    userId: app.globalData.userId,
    categoryId:'',
    item:{}
  },

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

  formSubmit: function (e) {
    if (e.detail.value.content.length == 0){
      wx.showToast({
        title: '内容不能为空',
        icon: 'fail',
      })
    }
    else{
      if(this.data.item.needsId==null){   //评论或者申请理由
        var data = { "categoryId": this.data.categoryId, "userId": this.data.userId, "content": e.detail.value.content, "needsId": this.data.item.id};
        wx.showModal({
          title: '',
          content: '确定发表吗？',
          icon: 'success',
          duration: 2000,
          success: function (res) {
            if (res.confirm) {
              wx.request({
                url: 'http://localhost:8080/MessageManage/addEvaluate',
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
                    console.log("新增评论信息成功");
                  }
                  else {
                    wx.showToast({
                      title: '失败',
                      icon: 'fail',
                      duration: 5000
                    })
                  }
                }
              })
            }
          }
        })
      }
      else {   //回复
        var data = { "categoryId": this.data.categoryId, "userId": this.data.userId, "content": e.detail.value.content, "needsId": this.data.item.needsId, "messageId": this.data.item.id, "masterId":this.data.item.userId};
        wx.request({
          url: 'http://localhost:8080/MessageManage/addReply',
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
              console.log("新增回复信息成功");
            }
            else{
              wx.showToast({
                title: '失败',
                icon: 'fail',
                duration: 2000,
              })
            }
          },
          fail: (res) =>{
            wx.showToast({
              title: '失败',
              icon: 'fail',
              duration: 2000,
            })
          }
        })
      }
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
    var item = JSON.parse(options.data);
    var categoryId = JSON.parse(options.categoryId);
    console.log(item);
    console.log(categoryId);
    item.deadline = item.deadline.substring(0, 10);
    item.startTime = item.startTime.substring(0, 10);
    console.log(item);
    that.setData({
      item: item,
      categoryId: categoryId,
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