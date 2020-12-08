import {NetworkRequest} from '../api'

export default{
 
  /**
   *                               
   *  @returns {Promise<Object>} 
   */

   async getEquipmentRequest(){
     const res = await NetworkRequest({
       url:'/getallEquipment',
       method:'get',
      
     })
     return res.data;
   },

   /**
   * @param {Object} data 设备信息 equipmentId 设备id   
   *                               
   * @returns {Promise<Object>} 
  */
 async deleteEquipmentRequest(data){
   const res = await NetworkRequest({
     url:'/deleteEuipment',
     method:'delete',
     data
   })
   
 },

   /**
    * @param {*} data 修改设备 equipmentId 设备id
    *                         equipmentClassifyId 设备类别id
    *                         meetingRoomId 会议室id
    *                         name 设备名
    *                         isMaintain 是否维修
    *                         remark 备注
    *                         num 数量
    */    
   async updateEquipmentRequest(data){
    const res = await NetworkRequest({
      url:'/updateEquipment',
      method:'put',
      data
    })
    
  },
   /**
   * @param {Object} data 添加设备 equipmentClassifyId 设备类别id
   *                               meetingRoomId 会议室id
   *                               name 设备名
   *                               isMaintain 是否维修
   *                               remark 备注
   *                               num 数量
   * @returns {Promise<Object>} 
  */
 async insertEquipmentRequest(data){
  const res = await NetworkRequest({
    url:'/addEquipment',
    method:'post',
    data
  })
  
},
 /**
   * @returns {Promise<Object>} 
  */
 async getAllMeetingRoom(data){
  const res = await NetworkRequest({
    url:'/getAllMeetingRoom',
    method:'get',
    data
  })
  return res.data;
  
},
 /**
   * @returns {Promise<Object>} 
  */
 async getAllEquipmentClassify(){
  const res = await NetworkRequest({
    url:'/getAllEquipmentClassify',
    method:'get',
    
  })
  return res.data;
  
},
 /**
  * 
  *  @returns {Promise<Object>}
  */
 async getAllMeetings(){
   const res = await NetworkRequest({
     url:'/getAllMeetings',
     method:'get',
   })
  return res.data;
},

 /**
  * 
  *  @returns {Promise<Object>}
  */
 async getAllMeetingRoom(){
   const res = await NetworkRequest({
     url:'/getAllMeetingRoom',
     method:'get'
   })
   return res.data;
 },

 /**
  * @param {Object} data 预约会议   employeeid 职工id
   *                               meetingroomId 会议室id
   *                               name 会议名称
   *                               peoplenum 参会人数
   *                               startTime 开始时间
   *                               endtime 结束时间
   *                               remark 备注
  * @returns {Promise<Object>}
  */
 async addOrderMeeting(data){
   const res = await NetworkRequest({
     url:'/addOrderMeeting',
      method:'post',
      data
   })
 },

 /**
  * 
  * @param {Object}  data 查询某天的会议安排 date 日期
  *                                        meetingroomid 会议室号
  * 
  * @returns {Promise<Object>}
  */
 async getAllMeetingTimeByRoomAndTime(data){
    const res = await NetworkRequest({
      url:'/getAllMeetingTimeByRoomAndTime',
      method:'post',
      data
    })
    return res.data;
 },

 /**
  * 
  * @returns {Promise<Object>}
  */
 async getAllMeetingRoomByStorey(){
   const res = await NetworkRequest({
     url:'/getAllMeetingRoomByStorey',
     method:'get'
   })
   return res.data;
 },

 /**
  * 
  * @returns {Promise<Object>}
  */
 async getNotApprovedMeeting(){
   const res = await NetworkRequest({
     url:'/getNotApprovedMeeting',
     method:'get'
   })
   return res.data;
 },

 /**
  * @param {Object} data 会议审批 appoinmentstatus 审批通过与否
  *                               meetingid 会议号
  * 
  * @returns {Promise<Object>}
  */
 async approveMeeting(data){
   const res = await NetworkRequest({
     url:'/approveMeeting',
     method:'put',
     data
   })
   return res.data;
 }
}