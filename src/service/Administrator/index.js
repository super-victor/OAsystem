import {NetworkRequest} from '../api'

export default{
  /**获取所有管理员信息
   * @returns {Promise<Object>} 
  */
  async getAllAdmin(){
    const res = await NetworkRequest({
      url:'/alladmin',
      method:'get'
    })
    return res.data;
  },

  /**添加管理员
   * @param {Object} data 用户数据   employeeIdList:选中人员列表
   * @returns {Promise<Object>} 
  */
  async editAdmin(data){
    const res = await NetworkRequest({
      url:'/admin',
      method:'post',
      data
    })
    return res.data;
  },
  
  /**
   * 锁定或者解锁管理员账户
   * @param {Object} data 用户数据   employeeId:工号
   *                                isAccountLocked: 锁定:1  解锁:0
   * @returns {Promise<Object>}
  */
  async lockOrUnlockAdmin(data){
    const res = await NetworkRequest({
      url:'/lockOrUnlockAdmin',
      method:'put',
      throttle:true,
      data
    })
    return res.data;
  },

  /**
   * 获取所有的日志信息
   * @returns {Promise<Object>}
  */
  async getAllLogs(){
    const res = await NetworkRequest({
      url:'/getAllLogs',
      method:'get'
    })
    return res.data;
  },

  /**
   * 获取系统总访问量和当天访问量
   * @returns {Promise<Object>}
  */
  async getSystemViews(){
    const res = await NetworkRequest({
      url:'/data/systemViews',
      method:'get'
    })
    return res.data;
  },

  /**
   * 获取系统总的数据统计
   * @returns {Promise<Object>}
  */
  async getSystemInfo(){
    const res = await NetworkRequest({
      url:'/data/system',
      method:'get'
    })
    return res.data;
  },
}