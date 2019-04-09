//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    userId: '',
  },

  otherText: function(e) {
    wx.request({
      url: 'http://localhost:8080/needsManagement/getNeedsByCategoryId?categoryId=5', //仅为示例，并非真实的接口地址
      method: 'GET',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: (res) => {
        if (res.data.isSuccess) {
          console.log("获取用户需求信息成功");
          wx.navigateTo({
            url: '../newNeedsManager/newNeedsManager?data=' + JSON.stringify(res.data.data),    //?data=' + JSON.stringify(res.data),
          })
        } else {
          console.log("获取用户需求信息失败");
          wx.navigateTo({
            url: '../main/main'
          })
        }
      }
    })
  },
  carpoolingText: function (e) {
    wx.request({
      url: 'http://localhost:8080/needsManagement/getNeedsByCategoryId?categoryId=1', //仅为示例，并非真实的接口地址
      method: 'GET',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: (res) => {
        if (res.data.isSuccess) {
          console.log("获取用户需求信息成功");
          wx.navigateTo({
            url: '../newNeedsManager/newNeedsManager?data=' + JSON.stringify(res.data.data),    //?data=' + JSON.stringify(res.data),
          })
        } else {
          console.log("获取用户需求信息失败");
          wx.navigateTo({
            url: '../main/main'
          })
        }
      }
    })
  },

  filmText: function (e) {
    wx.request({
      url: 'http://localhost:8080/needsManagement/getNeedsByCategoryId?categoryId=3', //仅为示例，并非真实的接口地址
      method: 'GET',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: (res) => {
        if (res.data.isSuccess) {
          console.log("获取用户需求信息成功");
          wx.navigateTo({
            url: '../newNeedsManager/newNeedsManager?data=' + JSON.stringify(res.data.data),    //?data=' + JSON.stringify(res.data),
          })
        } else {
          console.log("获取用户需求信息失败");
          wx.navigateTo({
            url: '../main/main'
          })
        }
      }
    })
  },

  studyText: function (e) {
    wx.request({
      url: 'http://localhost:8080/needsManagement/getNeedsByCategoryId?categoryId=4', //仅为示例，并非真实的接口地址
      method: 'GET',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: (res) => {
        if (res.data.isSuccess) {
          console.log("获取用户需求信息成功");
          wx.navigateTo({
            url: '../newNeedsManager/newNeedsManager?data=' + JSON.stringify(res.data.data),    //?data=' + JSON.stringify(res.data),
          })
        } else {
          console.log("获取用户需求信息失败");
          wx.navigateTo({
            url: '../main/main'
          })
        }
      }
    })
  },

  competitionText: function (e) {
    wx.request({
      url: 'http://localhost:8080/needsManagement/getNeedsByCategoryId?categoryId=2', //仅为示例，并非真实的接口地址
      method: 'GET',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: (res) => {
        if (res.data.isSuccess) {
          console.log("获取用户需求信息成功");
          wx.navigateTo({
            url: '../newNeedsManager/newNeedsManager?data=' + JSON.stringify(res.data.data),    //?data=' + JSON.stringify(res.data),
          })
        } else {
          console.log("获取用户需求信息失败");
          wx.navigateTo({
            url: '../main/main'
          })
        }
      }
    })
  },
  
  onLoad: function () {
    let that = this;
    that.setData({
      userId: app.globalData.userId,
    })
  },

  onReady: function () {

  },

})