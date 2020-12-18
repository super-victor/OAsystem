import {NetworkRequest} from '../api'

export default{
  /**
   * 获取名片分类
   * @param {Object} data 用户数据   username:用户名，password:密码
   *                                
   * @returns {Promise<Object>} 
  */
  async requestCardFile(){
    const res = await NetworkRequest({
      url:'/CardHolder',
      method:'get',
      postHeaderType:'application/x-www-form-urlencoded',
      // throttle:true,
    })
    return res.data;
  },

  /**
   * 添加名片夹分类
   * @param {Object} data 名片夹分类   name:分类名
   * 
   * @returns {Promise<Object>} 
  */
  async addCardFile(data) {
    const res = await NetworkRequest({
        url:'/CardHolder',
        method:'post',
        // throttle:true,
        data
    });
    return res.data;
  },

  /**
   * 删除名片夹分类
   * @param {Object} data 名片夹分类   cardHolderId :分类id
   * 
   * @returns {Promise<Object>} 
  */
  async deleteCardFile(data) {
    const res = await NetworkRequest({
        url:'/CardHolder',
        method:'delete',
        // throttle:true,
        data
    });
    return res.data;
  },

  /**
   * 修改名片夹分类
   * @param {Object} data 名片夹分类   cardHolderId :分类id，name:分类名
   *                                   
   * @returns {Promise<Object>} 
  */
  async updateCardFile(data) {
    const res = await NetworkRequest({
        url:'/CardHolder',
        method:'put',
        // throttle:true,
        data
    });
    return res.data;
  },

  /**
   * 请求某一名片夹分类的所有名片
   * @param {Object} data 名片夹分类   cardHolderId :分类id
   * 
   * @returns {Promise<Object>} 
  */
  async requestCard(data) {
    const res = await NetworkRequest({
        url:'/Card',
        method:'get',
        postHeaderType:'multipart/form-data',
        // throttle:true,
        data
      })
      return res.data;
  },
  /**
   * 新建名片
   * @param {Object} data 名片夹分类   address:地址，cardHolderId:,company:公司，department：部门，name：姓名，phone：电话，position：职位，email：邮箱
   * 
   * @returns {Promise<Object>} 
  */
  async addCard(data) {
    const res = await NetworkRequest({
      url:'/Card',
      method:'post',
      // throttle:true,
      data
    })
    return res.data;
  },
  /**
   * 导入名片
   * @param {Object} data 名片夹分类   cardId:名片id，cardHolderId:名片夹id
   * 
   * @returns {Promise<Object>} 
  */
  async importCard(data) {
    const res = await NetworkRequest({
      url:'/shareCard',
      method:'post',
      // throttle:true,
      data
    })
    return res.data;
  },
  /**
   * 修改名片
   * @param {Object} data 名片夹分类   address:地址，cardHolderId:,company:公司，department：部门，name：姓名，phone：电话，position：职位，email：邮箱
   * 
   * @returns {Promise<Object>} 
  */
  async updateCard(data) {
    const res = await NetworkRequest({
      url:'/Card',
      method:'put',
      // throttle:true,
      data
    })
    return res.data;
  },
  /**
   * 删除名片
   * @param {Object} data 名片夹分类   cardId:名片id
   * 
   * @returns {Promise<Object>} 
  */
  async deleteCard(data) {
    const res = await NetworkRequest({
      url:'/Card',
      method:'delete',
      // throttle:true,
      data
    })
    return res.data;
  },
}