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
}