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
      url:'/CardHolder',
      method:'get',
      postHeaderType:'multipart/form-data',
      throttle:true,
    })
    return res.data;
  },

  /**
   * @param {Object} data 名片夹分类   name:分类名
   * @returns {Promise<Object>} 
  */
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

  /**
   * @param {Object} data 名片夹分类   cardHolderClassfyId :分类id
   * @returns {Promise<Object>} 
  */
  // 删除名片夹分类
  async deleteCardFile(data) {
    const res = await NetworkRequest({
        url:'/CardHolderClassfy',
        method:'delete',
        postHeaderType:'www-form',
        throttle:true,
        data
    });
    return res.data;
  },

  /**
   * @param {Object} data 名片夹分类   cardHolderClassfyId :分类id
   *                                   name:分类名
   * @returns {Promise<Object>} 
  */
  // 修改名片夹分类
  async updateCardFile(data) {
    const res = await NetworkRequest({
        url:'/CardHolderClassfy',
        method:'put',
        postHeaderType:'www-form',
        throttle:true,
        data
    });
    return res.data;
  },

  /**
   * @param {Object} data 名片夹分类   cardHolderClassfyId :分类id
   * @returns {Promise<Object>} 
  */
  // 请求某一名片夹分类的所有名片
  async requestCard(data) {
    const res = await NetworkRequest({
        url:'/findEmployeeCardHolderByCardHolderClassfyId',
        method:'get',
        postHeaderType:'multipart/form-data',
        throttle:true,
        data
      })
      return res.data;
  },
  // 新建名片
  async addCard(data) {
    const res = await NetworkRequest({
      url:'/CardHolder',
      method:'post',
      postHeaderType:'application/json;charset=UTF-8',
      throttle:true,
      data
    })
    return res.data;
  },
  // 导入名片
  async importCard(data) {
    const res = await NetworkRequest({
      url:'/EmployeeCardHolder',
      method:'post',
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
  },
  async test() {
    const res = await NetworkRequest({
      url:'/findEmployeeCardHolderByEmployeeId',
      method: 'get',
      postHeaderType:'multipart/form-data',
      throttle:true
    }) 
    return res.data;
  }
}