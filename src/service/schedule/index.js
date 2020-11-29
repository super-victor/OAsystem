import {NetworkRequest} from '../api'

export default{
  /**
   * @param {Object} data 用户数据   scheduleId:日程id
   *                                
   * @returns {Promise<Object>} 
  */
  async getSchedule(data){
    const res = await NetworkRequest({
      url:'/findScheduleByScheduleId',
      method:'get',
      throttle:true,
      data
    })
    return res.data;
  }
}