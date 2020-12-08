import {NetworkRequest} from '../api'

export default{
  /**
   * 
   * @returns {Promise<Object>} 
  */
  async dataRequest(){
    const res = await NetworkRequest({
      url:'/addressbook',
      method:'get',
    })
    return res.data;
  },

  /**
   * 
   * @returns {Promise<Object}
   */
  async departmentRequest(){
    const res = await NetworkRequest({
      url:'/getAllDepartmentName',
      method:'get',
    })
    return res.data;
  },

  /**
   *  @param integer data 分页数据 currentPageNum 当前页
   *                              pageSize 每页显示的页码数量
   *                               
   *  @returns {Promise<Object>} 
   */

   async paginationRequest(data){
     const res = await NetworkRequest({
       url:'/addressbookbypage',
       method:'get',
       data
     })
     return res.data;
   }
}