import {NetworkRequest} from '../api'

export default{
  /**
   * @param {Object} data 用户数据   username:用户名
   *                                password:密码
   * @returns {Promise<Object>} 
  */
  async loginRequest(data){
    const res = await NetworkRequest({
      url:'/login',
      method:'post',
      postHeaderType:'multipart/form-data',
      data
    })
    return res;
  }
}