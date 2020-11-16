import {NetworkRequest} from '../api'

export default{
  /**
   * @param {Object} data 用户数据   username:用户名
   *                                password:密码
   * @returns {Promise<Object>} 
  */
 // 获取名片夹分类
  async requestCardFile(){
    const res = await NetworkRequest({
      url:'/CardHolderClassfy',
      method:'get',
      postHeaderType:'multipart/form-data',
      throttle:true,
    })
    return res.data;
  },
  // 添加名片夹分类
  async addCardFile(data) {
    const res = await NetworkRequest({
        url:'/CardHolderClassfy',
        method:'post',
        postHeaderType:'multipart/form-data',
        throttle:true,
        data
    });
    return res.data;
  },
  // 请求某一名片夹分类的所有名片
  async requestCard(data) {
    const res = await NetworkRequest({
        url:'/CardHolderByCardHolderClassfyId',
        method:'get',
        postHeaderType:'multipart/form-data',
        throttle:true,
        data
      })
      return res.data;
  },
  // 请求自己的名片
  async requestAnimationFrame() {
      const res = await NetworkRequest({
          url: '',
          method: 'get',
          postHeaderType:'multipart/form-data',
          throttle:true
      })
      return res.data;
  }
}