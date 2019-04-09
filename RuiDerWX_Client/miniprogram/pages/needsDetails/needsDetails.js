// miniprogram/pages/needsDetails/needsDetails.js
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
      item:{},
      userId: '',
      evaluateList:{},
  },

  evaluate:function(e) {
    console.log(e.currentTarget.dataset.categoryId)
    if(this.data.userId == '') {
      wx.showModal({
        title: '',
        content: '是否允许登录',
        success: function (res) {
          if (res.confirm) {
            wx.navigateTo({
              url: '../userPage/userPage',
            })
          }
        }
      })
    }
    else {
      wx.navigateTo({
        url: '../evaluatePage/evaluate?data=' + JSON.stringify(e.currentTarget.dataset.item) + '&categoryId=' + JSON.stringify(e.currentTarget.dataset.id)
      })  
    }
  },

  editNeeds: function (e) {
    wx.request({
      url: 'http://localhost:8080/needsManagement/getNeedsDetailsById?id=' + e.currentTarget.dataset.id, //仅为示例，并非真实的接口地址
      method: 'GET',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: (res) => {
        if (res.data.isSuccess) {
          wx.navigateTo({
            url: '../editNeeds/editNeeds?data=' + JSON.stringify(res.data.data),
          })
        }
        else {
          wx.showToast({
            title: '',
            content: '失败',
            duration: 3000
          })
        }
      }
    })
  },

  deleteNeeds: function (e) {
    wx.showModal({
      title: '',
      content: '确认删除吗？',
      success: function (res) {
        if (res.confirm) {
          wx.request({
            url: 'http://localhost:8080/needsManagement/deleteNeeds/' + e.currentTarget.dataset.id, //仅为示例，并非真实的接口地址
            method: 'GET',
            header: {
              'content-type': 'application/json' // 默认值
            },
            success: (res) => {
              if (res.data.isSuccess) {
                console.log("删除需求信息成功");
                wx.showModal({
                  title: '',
                  content: '删除成功',
                  showCancel: false,
                  confirmColor: '#333ccc',
                  duration: 1000,
                  success: function (res) {
                    if (res.confirm) {
                      wx.request({
                        url: 'http://localhost:8080/needsManagement/getNeedsByUserId?userId=5', //仅为示例，并非真实的接口地址
                        method: 'GET',
                        header: {
                          'content-type': 'application/json' // 默认值
                        },
                        success: (ress) => {
                          if (ress.data.isSuccess) {
                            console.log("获取用户需求信息成功");
                            wx.redirectTo({
                              url: '../newNeedsManager/newNeedsManager?data=' + JSON.stringify(ress.data.data),    //?data=' + JSON.stringify(res.data),
                            })
                          }
                        }
                      })
                    }
                  }
                })

              } else {
                console.log("删除需求信息失败");
                wx.showToast({
                  title: '失败',
                  icon: 'fail',
                  duration: 2000
                })
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
    var item = JSON.parse(options.data);
    console.log(item);
    item.deadline = item.deadline.substring(0, 10);
    item.startTime = item.startTime.substring(0, 10);
    console.log(item);

    wx.request({
      url:'http://localhost:8080/MessageManage/getMessageByNeedsIdAndCategoryId?needsId=' + item.id + '&categoryId=2',
      method: 'GET',
      header: {
        'content-type': 'application/json' 
      },
      success: (res) => {
        console.log(res.data.data)
        that.setData({
          evaluateList: res.data.data,
        })
      }
    });
    that.setData({
      item: item,
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
    let that = this
    wx.showNavigationBarLoading()
    setTimeout(function () {
      wx.request({
        url: 'http://localhost:8080/MessageManage/getMessageByNeedsIdAndCategoryId?needsId=' + that.data.item.id + '&categoryId=2',
        method: 'GET',
        header: {
          'content-type': 'application/json'
        },
        success: (res) => {
          console.log(res.data.data)
          that.setData({
            evaluateList: res.data.data,
          })
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