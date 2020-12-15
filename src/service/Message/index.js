import {NetworkRequest} from '../api'

export default{
  /**
   * 获取所有消息
   * @param {Object} 
   *                                
   * @returns {Promise<Object>} 
  */
  async getMessage(){
    const res = await NetworkRequest({
      url:'/message',
      method:'get',
      // throttle:true,
    })
    return res.data;
  },
  /**
   * 获取所有未读消息
   * @param {Object} 
   *                                
   * @returns {Promise<Object>} 
  */
 async getMessageNotRead(){
    const res = await NetworkRequest({
      url:'/messageNotRead',
      method:'get',
      // throttle:true,
    })
    return res.data;
  },
  /**
   * 消息已读
   * @param {Object} messageId:消息id
   *                                
   * @returns {Promise<Object>} 
  */
 async readMessage(data){
    const res = await NetworkRequest({
      url:'/readMessage',
      method:'put',
      data
    })
    return res.data;
  },
}