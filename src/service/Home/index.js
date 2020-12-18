import {NetworkRequest} from '../api'

export default{
  /**
   * 个人的数据统计
   * @returns {Promise<Object>}
  */
  async getPersonalInfo(){
    const res = await NetworkRequest({
      url:'/data/person',
      method:'get'
    })
    return res.data;
  },

  /**
   * 获取职工未开始日程数目
   * @returns {Promise<Object>}
  */
  async getScheduleCount(){
    const res = await NetworkRequest({
      url:'/findScheduleCount',
      method:'get'
    })
    return res.data;
  },

  /**
   * 获取职工最近未开始日程
   * @returns {Promise<Object>}
  */
  async getSchedule(){
    const res = await NetworkRequest({
      url:'/findSchedule',
      method:'get'
    })
    return res.data;
  },
}