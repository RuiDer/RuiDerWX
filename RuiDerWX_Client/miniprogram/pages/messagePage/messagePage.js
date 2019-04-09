// miniprogram/pages/messagePage/messagePage.js
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
      allNoWatchedNo:'',
      applyNo:'',
      evaluateNo:'',
      overTimeNo:'',
      overNumberNo:'',
      userId: ''
  },

  getMessageList:function(e) {
    wx.navigateTo({
      url: '../applyMessageList/applyMessageList?categoryId=' + JSON.stringify(e.currentTarget.dataset.id) + '&isReply=' + JSON.stringify(e.currentTarget.dataset.isreply),
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
      wx.request({
        url: 'http://localhost:8080/MessageManage/getNoWatchedMessageNo?userId='+this.data.userId,
        method:'GET',
        header: {
          'content-type': 'application/json' // 默认值
        },
        success: (res) => {
        if (res.data.isSuccess) {
          that.setData({
            allNoWatchedNo: res.data.data.allNoWatchedNo,
            applyNo: res.data.data.applyNo,
            evaluateNo: res.data.data.evaluateNo,
            overTimeNo: res.data.data.overTimeNo,
            overNumberNo: res.data.data.overNumberNo,
            userId:app.globalData.userId,
          })
          console.log(that.data.allNoWatchedNo);
        }
      }
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
    let that = this;
    wx.showNavigationBarLoading()
    setTimeout(function() {
      wx.request({
        url: 'http://localhost:8080/MessageManage/getNoWatchedMessageNo?userId=' + that.data.userId,
        method: 'GET',
        header: {
          'content-type': 'application/json' // 默认值
        },
        success: (res) => {
          if (res.data.isSuccess) {
            that.setData({
              allNoWatchedNo: res.data.data.allNoWatchedNo,
              applyNo: res.data.data.applyNo,
              evaluateNo: res.data.data.evaluateNo,
              overTimeNo: res.data.data.overTimeNo,
              overNumberNo: res.data.data.overNumberNo
            })
            console.log(that.data.allNoWatchedNo);
          }
        }
      })
      wx.hideNavigationBarLoading()
      wx.stopPullDownRefresh()
    }, 1500);
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