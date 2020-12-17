import {
  NetworkRequest
} from '../api'

export default {


  /**
   * @param {Object} data 删除未审批会议 meetingid 会议id
   *                               
   * @returns {Promise<Object>} 
   */
  async deleteOrderMeeting(data) {
    const res = await NetworkRequest({
      url: '/cancleApproveMeeting',
      method: 'delete',
      data
    })
  },

  /**
   * 
   *  @returns {Promise<Object>}
   */
  async getAllMeetings() {
    const res = await NetworkRequest({
      url: '/getAllMeetings',
      method: 'get',
    })
    return res.data;
  },
}