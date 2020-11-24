import {NetworkRequest} from '../api'

export default{
  /**
   * @returns {Promise<Object>} 
  */
  async getUserList(){
    const res = await NetworkRequest({
      url:'/allEmployee',
      method:'get'
    })
    return res.data;
  },

  /**
   * @param {Object} data 用户数据   employeeId:工号
   *                                isAccountLocked: 锁定:1  解锁:0
   * @returns {Promise<Object>}
  */
  async lockOrUnlockEmployee(data){
    const res = await NetworkRequest({
      url:'/lockOrUnlockEmployee',
      method:'put',
      throttle:true,
      data
    })
    return res.data;
  },

  /**
   * @param {Object} data 用户数据   employeeId:工号
   * @returns {Promise<Object>} 
  */
  async employeeRolelist(data){
    const res = await NetworkRequest({
      url:'/employeeRolelist',
      method:'get',
      data
    })
    return res.data;
  }
}