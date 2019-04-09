// miniprogram/pages/applyMessageList/applyMessageList.js
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userId: app.globalData.userId,
    messageList:{},
    categoryId:'',
    isReply:''
  },

  approveOrRefuse: function(e) {
    wx.request({
      url: 'http://localhost:8080/MessageManage/setIsApproved?isApproved=' + e.currentTarget.dataset.isapproved + '&id=' + e.currentTarget.dataset.id,
      method:'GET',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: (res) => {
        if (res.data.isSuccess) {
          wx.showToast({
            title: 'OK',
            icon:'success'
          })
        }
        else {
          wx.showToast({
            title: '失败',
            icon: 'fail'
          })
        }
      },
      fail: (res) => {
        wx.showToast({
          title: '失败',
          icon: 'fail'
        })
      }
    })
  },

  reply:function(e) {
    console.log(e.currentTarget.dataset.item),
    wx.navigateTo({
      url: '../evaluatePage/evaluate?data=' + JSON.stringify(e.currentTarget.dataset.item) + '&categoryId=' + JSON.stringify(e.currentTarget.dataset.categoryid),
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
      var categoryId = JSON.parse(options.categoryId);
      var isReply = JSON.parse(options.isReply);
      wx.request({
        url: 'http://localhost:8080/MessageManage/getMessageList?userId=' + that.data.userId + '&categoryId=' + categoryId + '&isReply=' + isReply,
        method:'GET',
        header: {
          'content-type': 'application/json' // 默认值
        },
        success: (res) => {
          if (res.data.isSuccess) {
            var array = res.data.data;
            for (let i = 0, len = array.length; i < len; i += 1) {
              array[i].deadline = array[i].deadline.substring(0, 10);
              array[i].startTime = array[i].startTime.substring(0, 10);
            }
            that.setData({
              messageList: array,
              categoryId:categoryId,
              isReply:isReply
            })
            console.log(that.data.messageList);
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
    setTimeout(function () {
      wx.request({
        url: 'http://localhost:8080/MessageManage/getMessageList?userId=' + that.data.userId + '&categoryId=' + that.data.categoryId + '&isReply=' + that.data.isReply,
        method: 'GET',
        header: {
          'content-type': 'application/json' // 默认值
        },
        success: (res) => {
          if (res.data.isSuccess) {
            var array = res.data.data;
            for (let i = 0, len = array.length; i < len; i += 1) {
              array[i].deadline = array[i].deadline.substring(0, 10);
              array[i].startTime = array[i].startTime.substring(0, 10);
            }
            that.setData({
              messageList: array,
            })
            console.log(that.data.messageList);
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