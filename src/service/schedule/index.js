import {NetworkRequest} from '../api'

export default{
  /**
   * 获取个人日程
   * @param {Object} data 用户数据   
   *                                
   * @returns {Promise<Object>} 
  */
  async getSelfSchedule(){
    const res = await NetworkRequest({
      url:'/findSelfSchedule',
      method:'get',
      // throttle:true,
    })
    return res.data;
  },
  /**
   * 获取个人的公司日程
   * @param {Object} data 用户数据   
   *                                
   * @returns {Promise<Object>} 
  */
 async getCompanySchedule(){
  const res = await NetworkRequest({
    url:'/findCompanySchedule',
    method:'get',
    // throttle:true,
  })
  return res.data;
  },
  /**
   * 通过id查找日程(个人)
   * @param {Object} data 用户数据   scheduleId:日程id
   *                                
   * @returns {Promise<Object>} 
  */
 async SelfSchedule(data){
  const res = await NetworkRequest({
    url:'/Self/findScheduleByScheduleId',
    method:'get',
    // throttle:true,
    data
  })
  return res.data;
  },
  /**
   * 通过id查找日程(公司)
   * @param {Object} data 用户数据   scheduleId:日程id
   *                                
   * @returns {Promise<Object>} 
  */
 async CompanySchedule(data){
  const res = await NetworkRequest({
    url:'/Company/findScheduleByScheduleId',
    method:'get',
    // throttle:true,
    data
  })
  return res.data;
  },
  /**
   * 新建个人日程
   * @param {Object} data 用户数据   content:内容,startTime:开始时间,endTime:结束时间,location:地点
   *                                
   * @returns {Promise<Object>} 
  */
 async addSelfSchedule(data){
  const res = await NetworkRequest({
    url:'/SelfSchedule',
    method:'post',
    // throttle:true,
    data
  })
  return res.data;
  },
  /**
   * 修改个人日程
   * @param {Object} data 用户数据   content:内容,startTime:开始时间,endTime:结束时间,location:地点,scheduleId:日程id
   *                                
   * @returns {Promise<Object>} 
  */
 async updateSelfSchedule(data){
  const res = await NetworkRequest({
    url:'/SelfSchedule',
    method:'put',
    // throttle:true,
    data
  })
  return res.data;
  },
  /**
   * 删除个人日程
   * @param {Object} data 用户数据   scheduleId:日程id
   *                                
   * @returns {Promise<Object>} 
  */
 async deleteSelfSchedule(data){
  const res = await NetworkRequest({
    url:'/SelfSchedule',
    method:'delete',
    // throttle:true,
    data
  })
  return res.data;
  },
  /**
   * 新建公司日程
   * @param {Object} data 用户数据   content:内容,startTime:开始时间,endTime:结束时间,location:地点,leader:创建人,joiner:参与人
   *                                
   * @returns {Promise<Object>} 
  */
 async addCompanySchedule(data){
  const res = await NetworkRequest({
    url:'/CompanySchedule',
    method:'post',
    // throttle:true,
    data
  })
  return res.data;
  },
  /**
   * 修改公司日程
   * @param {Object} data 用户数据   content:内容,startTime:开始时间,endTime:结束时间,location:地点,leader:创建人,joiner:参与人,scheduleId:日程id
   *                                
   * @returns {Promise<Object>} 
  */
 async updateCompanySchedule(data){
  const res = await NetworkRequest({
    url:'/CompanySchedule',
    method:'put',
    // throttle:true,
    data
  })
  return res.data;
  },
  /**
   * 删除公司日程
   * @param {Object} data 用户数据   schduleId:日程id
   *                                
   * @returns {Promise<Object>} 
  */
 async deleteCompanySchedule(data){
  const res = await NetworkRequest({
    url:'/CompanySchedule',
    method:'delete',
    // throttle:true,
    data
  })
  return res.data;
  },
  
}