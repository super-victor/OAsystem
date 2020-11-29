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
      postHeaderType:'application/x-www-form-urlencoded',
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
        url:'/CardHolder',
        method:'post',
        throttle:true,
        data
    });
    return res.data;
  },

  /**
   * @param {Object} data 名片夹分类   cardHolderId :分类id
   * @returns {Promise<Object>} 
  */
  // 删除名片夹分类
  async deleteCardFile(data) {
    const res = await NetworkRequest({
        url:'/CardHolder',
        method:'delete',
        throttle:true,
        data
    });
    return res.data;
  },

  /**
   * @param {Object} data 名片夹分类   cardHolderId :分类id
   *                                   name:分类名
   * @returns {Promise<Object>} 
  */
  // 修改名片夹分类
  async updateCardFile(data) {
    const res = await NetworkRequest({
        url:'/CardHolder',
        method:'put',
        throttle:true,
        data
    });
    return res.data;
  },

  /**
   * @param {Object} data 名片夹分类   cardHolderId :分类id
   * @returns {Promise<Object>} 
  */
  // 请求某一名片夹分类的所有名片
  async requestCard(data) {
    const res = await NetworkRequest({
        url:'/Card',
        method:'get',
        postHeaderType:'multipart/form-data',
        throttle:true,
        data
      })
      return res.data;
  },
  /**
   * @param {Object} data 名片夹分类   address:地址，cardHolderId:,company:公司，department：部门，name：姓名，phone：电话，position：职位，email：邮箱
   * 
   * @returns {Promise<Object>} 
  */
  // 新建名片
  async addCard(data) {
    const res = await NetworkRequest({
      url:'/Card',
      method:'post',
      throttle:true,
      data
    })
    return res.data;
  },
  // 导入名片
  async importCard(data) {
    const res = await NetworkRequest({
      url:'/shareCard',
      method:'post',
      throttle:true,
      data
    })
    return res.data;
  },
  // 修改名片
  async updateCard(data) {
    const res = await NetworkRequest({
      url:'/Card',
      method:'put',
      throttle:true,
      data
    })
    return res.data;
  },
  // 删除名片
  async deleteCard(data) {
    const res = await NetworkRequest({
      url:'/Card',
      method:'delete',
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