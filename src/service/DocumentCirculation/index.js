import {NetworkRequest} from '../api'

export default{
  /**
   * 获取所有部门
   * @returns {Promise<Object>} 
  */
  async getAllDepartment(){
    const res = await NetworkRequest({
      url:'/allDepartment',
      method:'get'
    })
    return res.data;
  },

  /**
   * 获取所有职工信息
   * @returns {Promise<Object>} 
  */
  async getUserList(){
    const res = await NetworkRequest({
      url:'/document/allEmployees',
      method:'get'
    })
    return res.data;
  },

  /**
   * 上传文件
   * @param {Object} data 用户数据   multipartFile:文件
   *                                sendfileId:发文id
   * @returns {Promise<Object>} 
  */
  async uploadFile(data){
    const res = await NetworkRequest({
      url:'/DocumentAnnex',
      method:'post',
      data,
      postHeaderType:'multipart/form-data',
      fileType:true
    })
    return res.data;
  },

  /**
   * 删除文件
   * @param {Object} data 用户数据   annexUrl:文件url
   *                                sendfileId:发文id
   * @returns {Promise<Object>} 
  */
  async deleteFile(data){
    const res = await NetworkRequest({
      url:'/DocumentAnnex',
      method:'delete',
      data
    })
    return res.data;
  },

  /**
   * 获取或创建空白发文
   * @returns {Promise<Object>} 
  */
  async emptyDocument(){
    const res = await NetworkRequest({
      url:'/emptydocument',
      method:'get',
    })
    return res.data;
  },

  /**
   * 获取审查人
   * @returns {Promise<Object>} 
  */
  async getAllCensor(){
    const res = await NetworkRequest({
      url:'/allCensor',
      method:'get',
    })
    return res.data;
  },

  /**
   * 获取草稿箱
   * @returns {Promise<Object>} 
  */
  async getDraftbox(){
    const res = await NetworkRequest({
      url:'/draftbox',
      method:'get',
    })
    return res.data;
  },

  /**
   * 修改发文
   * @param {Object} data 用户数据   accessEmployeeIdList:接收者列表
   *                                censorId:审查人id
   *                                content:正文
   *                                isPublic:是否公开
   *                                remark:备注
   *                                sendfileId:发文id
   *                                title:标题
   *                                type:公文种类
   *                                urgent:紧急程度
   * @returns {Promise<Object>} 
  */
  async updateDocument(data){
    const res = await NetworkRequest({
      url:'/document',
      method:'put',
      data
    })
    return res.data;
  },

  /**
   * 获取发文详情
   * @returns {Promise<Object>} 
  */
  async getDocument(data){
    const res = await NetworkRequest({
      url:'/document',
      method:'get',
      data
    })
    return res.data;
  },

  /**
   * 提交发文审稿
   * @param {Object} data 用户数据   sendfileId:发文id
   * @returns {Promise<Object>} 
  */
  async commitDocument(data){
    const res = await NetworkRequest({
      url:'/commitDocument',
      method:'put',
      data
    })
    return res.data;
  },

  /**
   * 获取自己正在审核的发文
   * @returns {Promise<Object>} 
  */
  async getDocumentInCheck(data){
    const res = await NetworkRequest({
      url:'/documentInCheck',
      method:'get',
      data
    })
    return res.data;
  },

  /**
   * 删除公文
   * @param {Object} data 用户数据   sendfileId:发文id
   * @returns {Promise<Object>}
  */
  async deleteDocument(data){
    const res = await NetworkRequest({
      url:'/document',
      method:'delete',
      data,
      throttle:true,
    })
    return res.data;
  },

  /**
   * 审查者获取自己的审查发文列表
   * @returns {Promise<Object>}
  */
  async getNeedCheckDocument(){
    const res = await NetworkRequest({
      url:'/needCheckDocument',
      method:'get'
    })
    return res.data;
  },

  /**
   * 取消审核
   * @param {Object} data 用户数据   sendfileId:发文id
   * @returns {Promise<Object>}
  */
  async cancelCheck(data){
    const res = await NetworkRequest({
      url:'/cancelCheck',
      method:'put',
      data,
      throttle:true,
    })
    return res.data;
  },

  /**
   * 审查发文
   * @param {Object} data 用户数据   sendfileId:发文id
   *                                comment:审稿意见
   *                                ispassed:是否通过
   * @returns {Promise<Object>}
  */
  async checkDocument(data){
    const res = await NetworkRequest({
      url:'/checkDocument',
      method:'put',
      data,
      throttle:true,
    })
    return res.data;
  },

  /**
   * 获取未通过的发文
   * @returns {Promise<Object>}
  */
  async getDocumementNotPassed(){
    const res = await NetworkRequest({
      url:'/documementNotPassed',
      method:'get'
    })
    return res.data;
  },

  /**
   * 将审查未通过的设置成草稿箱
   * @param {Object} data 用户数据   sendfileId:发文id
   * @returns {Promise<Object>}
  */
  async putDocumentNotPassIntoDraftbox(data){
    const res = await NetworkRequest({
      url:'/putDocumentNotPassIntoDraftbox',
      method:'put',
      data,
      throttle:true,
    })
    return res.data;
  },

  /**
   * 获取所有已发布的发文
   * @returns {Promise<Object>}
  */
  async getAllPublishDocument(){
    const res = await NetworkRequest({
      url:'/allPublishDocument',
      method:'get'
    })
    return res.data;
  },
}