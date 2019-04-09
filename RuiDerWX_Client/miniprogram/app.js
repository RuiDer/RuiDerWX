//app.js
App({
  globalData: {
    userInfo: null,
    openId: null,
    userId:null
  },

  onLaunch: function () {
    var that = this;
    // 展示本地存储能力
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)
    // 登录
    wx.login({
      success: function (res) {
        var code = res.code;//登录凭证
        if (code) {
          //2、调用获取用户信息接口
          wx.getUserInfo({
            success: function (res) {
              console.log({ encryptedData: res.encryptedData, iv: res.iv, code: code })
              //3.请求自己的服务器，解密用户信息 获取unionId等加密信息
              wx.request({
                url: 'http://localhost:8080/userInfo/IfAuthorizationEd',
                method: 'get',
                header: {
                  "Content-Type": "applciation/json"
                },
                data: { encryptedData: res.encryptedData, iv: res.iv, code: code },
                success: function (data) {
                  console.log(data);
                  //4.解密成功后 获取自己服务器返回的结果
                  if (data.data.isSuccess) {
                    console.log(that.globalData.userInfo.nickName)
                    wx.showModal({
                      title: '',
                      content: '确定授权登录吗？',
                      icon: 'success',
                      duration: 2000,
                      success: function (res) {
                        //this.globalData.openId = data.data.data.openid
                        if (res.confirm) {
                          that.globalData.openId = data.data.data.openid;
                            wx.request({
                              url: 'http://localhost:8080/userInfo/addUserInfoIfNoSaved',//自己的服务接口地址
                              method: 'POST',
                              header: {
                                'content-type': 'application/json' // 默认值
                              },
                              data: JSON.stringify({
                                "openId": data.data.data.openid, "nickName": that.globalData.userInfo.nickName, "image": that.globalData.userInfo.avatarUrl, "headUrl": that.globalData.userInfo.country + '+_+' + that.globalData.userInfo.province + '+_+' + that.globalData.userInfo.city, "sex": that.globalData.userInfo.gender
                              }),
                              success: function (res) {
                                if (res.data.isSuccess) {
                                  console.log("获取用户数据:"+res)
                                  console.log("用户数据保存或者查询成功,userId:" + res.data.data)
                                  that.globalData.userId = res.data.data;
                                  this.setData({
                                    userId: res.data.data,
                                  })
                                }
                                else {
                                  console.log("用户数据保存或者查询失败:" + res.data.data.message)
                                }
                              }

                            })
                        }
                      }
                    })
                  } else {
                    console.log('解密失败')
                  }
                },
                fail: function () {
                  console.log('系统错误')
                }
              })
            },
            fail: function () {
              wx.showLoading({
                title: '',
                mask: true,
                success: function (res) { },
                fail: function (res) { },
                complete: function (res) { },
              })
            }
          })
        } else {
          console.log('获取用户登录态失败！' + r.errMsg)
        }
      },
    })
    // 获取用户信息
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
          wx.getUserInfo({
            success: res => {
              // 可以将 res 发送给后台解码出 unionId
              this.globalData.userInfo = res.userInfo 
              // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
              // 所以此处加入 callback 以防止这种情况
              if (this.userInfoReadyCallback) {
                this.userInfoReadyCallback(res)
              }
            }
          })
        }
      }
    })
  },

})