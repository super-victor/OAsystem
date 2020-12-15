import {NetworkRequest} from '../api'

export default{
    
   /**
   * @param {Object} data 修改未审批会议 endtime 结束时间
   *                                    meetingid 会议id
   *                                    meetingroomid 会议室id
   *                                    name 会议名称
   *                                    peoplenum 参会人数
   *                                    remark 备注
   *                                    startTime 开始时间
   *                               
   * @returns {Promise<Object>} 
  */
 async updateOrderMeeting(data){
    const res = await NetworkRequest({
      url:'/updateOrderMeeting',
      method:'put',
      data
    })
  },

    /**
   * @param {Object} data 删除未审批会议 meetingid 会议id
   *                               
   * @returns {Promise<Object>} 
  */
 async deleteOrderMeeting(data){
    const res = await NetworkRequest({
      url:'/cancleApproveMeeting',
      method:'delete',
      data
    })
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
}
