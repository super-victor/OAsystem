import {NetworkRequest} from '../api'

export default{
  /**
   * @param {Object} data 用户数据   
   *                                
   * @returns {Promise<Object>} 
  */
  async getSelfSchedule(){
    const res = await NetworkRequest({
      url:'/findSelfSchedule',
      method:'get',
      throttle:true,
    })
    return res.data;
  },
  /**
   * @param {Object} data 用户数据   
   *                                
   * @returns {Promise<Object>} 
  */
 async getCompanySchedule(){
  const res = await NetworkRequest({
    url:'/findCompanySchedule',
    method:'get',
    throttle:true,
  })
  return res.data;
  },
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
  },
  /**
   * @param {Object} data 用户数据   content:内容,startTime:开始时间,endTime:结束时间,location:地点
   *                                
   * @returns {Promise<Object>} 
  */
 async addSelfSchedule(data){
  const res = await NetworkRequest({
    url:'/SelfSchedule',
    method:'post',
    throttle:true,
    data
  })
  return res.data;
  },
  
}