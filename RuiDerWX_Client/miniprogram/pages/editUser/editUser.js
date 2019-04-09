// miniprogram/pages/editUser/editUserDetails.js
const app = getApp();
Page({
  /**
   * 页面的初始数据
   */

  data: {
    userId: app.globalData.userId,
    id:{},
    dataFromMyList:{},
    sex: {} ,
  },

  switchChange: function (e) {
    if (e.detail.value) {
      this.setData({ sex: '男' });
    } else {
      this.setData({ sex: '女' });
    }
  },

  formSubmit:function(e) {
    var data = { "id": this.data.id, "userName": app.globalData.userInfo.nickName, "sex": app.globalData.userInfo.gender, "phone": e.detail.value.mobilePhone, "autograph": e.detail.value.autograph, "headerUrl": e.detail.value.headerUrl, "realName": e.detail.value.realName, "age": e.detail.value.age };
    wx.showModal({
      title: '',
      content: '确认提交吗？',
      icon:'success',
      duration:3000,
      success:function(res) {
        if(res.confirm) {
          wx.request({
            url: 'http://localhost:8080/userInfo/editUserDetails',
            method: 'POST',
            data: JSON.stringify(data),
            header: {
              'content-type': 'application/json' // 默认值
            },
            success: (res) => {
              if (res.data.isSuccess) {
                wx.showToast({
                  title: '成功',
                  icon:'success',
                  duration:2000,
                })
                console.log("修改用户信息成功");
                //success: function(res) {
                  //if (res.confirm) {
                    //wx.navigateTo({
                      //url: '../myList/myList'
                    //})
                  //}
                //}
              }
              else {
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
    var data1 = JSON.parse(options.data);
    that.setData({
      id:data1.id,
      dataFromMyList: data1,
      sex: data1.sex,
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