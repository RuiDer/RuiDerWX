const app = getApp();
Page({
  data: {
    userId: '',
    isAll:0,
    category: ['拼车', '竞赛', '电影', '学习', '其他'],
    length:{},
    threeArray: '', //模拟将后台获取到的数组对象数据按照一行3个的单元数据的格式切割成新的数组对象（简单的说：比如获取到数组是9个元素，切分成，3个元素一组的子数组）
  },

  editNeeds:function(e) {
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
            duration:3000
          })
        }
      }
    })
  },

  deleteNeeds:function(e) {
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
                  content:'删除成功',
                  showCancel:false,
                  confirmColor: '#333ccc',
                  duration: 1000,
                  success: function (res) {
                    if (res.confirm) {
                      wx.request({
                        url: 'http://localhost:8080/needsManagement/getNeedsByUserId?userId=1', //仅为示例，并非真实的接口地址
                        method: 'GET',
                        header: {
                          'content-type': 'application/json' // 默认值
                        },
                        success: (ress) => {
                          if (ress.data.isSuccess) {
                            console.log("获取用户需求信息成功");
                            wx.redirectTo({
                              url: '../user/userNeedsManager?data=' + JSON.stringify(ress.data.data),    //?data=' + JSON.stringify(res.data),
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

flush:function(e) {
  wx.request({
    url: 'http://localhost:8080/needsManagement/getNeedsByUserId?userId=1', //仅为示例，并非真实的接口地址
    method: 'GET',
    header: {
      'content-type': 'application/json' // 默认值
    },
    success: (res) => {
      if (res.data.isSuccess) {
        console.log("获取用户需求信息成功");
        wx.navigateTo({
          url: '../user/userNeedsManager?data=' + JSON.stringify(res.data.data),    //?data=' + JSON.stringify(res.data),
        })
      } else {
        console.log("获取用户需求信息失败");
        wx.redirectTo({
          url: '../user/userNeedsManage'
        })
      }
    }
  })
},
  onLoad: function (options) {
    let that = this;
    that.setData({
      userId: app.globalData.userId,
    })
    let threeArray = [];
    if (JSON.parse(options.data).length == 0) {
      wx.showModal({
        title:'',
        content:'空空如也～～',
        showCancel:false,
        confirmColor: '#333ccc',
        duration:1000,
        success:function(res) {
          if(res.confirm) {
            wx.redirectTo({
              url:'../userPage/userPage'
            })
          }
        }
      }) 
    }
    else {
      // 使用for循环将原数据切分成新的数组
      var array = JSON.parse(options.data);
      console.log(array);
      for (let i = 0, len = array.length; i < len; i += 1) {
        array[i].deadline = array[i].deadline.substring(0, 10);
        array[i].startTime = array[i].startTime.substring(0, 10);
        threeArray.push(array.slice(i, i + 1));     
      }
      
      console.log(threeArray);
      that.setData({
        threeArray: threeArray,
        length: JSON.parse(options.data).length,
        isAll: options.isAll,
      })
    }
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

  },
})
