import {NetworkRequest} from '../api'

export default{
  /**
   * 获取所有职工信息
   * @returns {Promise<Object>} 
  */
  async getUserList(){
    const res = await NetworkRequest({
      url:'/allEmployee',
      method:'get'
    })
    return res.data;
  },

  /**
   * 锁定或者解锁职工账户
   * @param {Object} data 用户数据   employeeId:工号
   *                                isAccountLocked: 锁定:1  解锁:0
   * @returns {Promise<Object>}
  */
  async lockOrUnlockEmployee(data){
    const res = await NetworkRequest({
      url:'/lockOrUnlockEmployee',
      method:'put',
      throttle:true,
      data
    })
    return res.data;
  },

  /**
   * 获取指定职工的角色列表
   * @param {Object} data 用户数据   employeeId:工号
   * @returns {Promise<Object>} 
  */
  async getEmployeeRolelist(data){
    const res = await NetworkRequest({
      url:'/employeeRolelist',
      method:'get',
      data
    })
    return res.data;
  },

  /**
   * 获取所有角色和角色对应职工列表
   * @returns {Promise<Object>} 
  */
  async getAllRolesWithEmployeeList(){
    const res = await NetworkRequest({
      url:'/allRolesWithEmployeeList',
      method:'get',
    })
    return res.data;
  },

  /**
   * 更新职工拥有的角色列表
   * @param {Object} data 用户数据   employeeId:工号
   *                                roleIdList:角色数组
   * @returns {Promise<Object>} 
  */
  async putEmployeeRolelist(data){
    const res = await NetworkRequest({
      url:'/employeeRolelist',
      method:'put',
      data
    })
    return res.data;
  },

  /**
   * 获取指定角色下所有功能的codeList
   * @param {Object} data 用户数据   roleId:角色号
   * @returns {Promise<Object>} 
  */
  async getMenuCodeList(data){
    const res = await NetworkRequest({
      url:'/menuCodeList',
      method:'get',
      data
    })
    return res.data;
  },

  /**
   * 通过codelist更新指定角色下所有功能
   * @param {Object} data 用户数据   roleId:角色号
   *                                codeList:功能数组
   * @returns {Promise<Object>} 
  */
  async putRoleMenulist(data){
    const res = await NetworkRequest({
      url:'/roleMenulist',
      method:'put',
      data
    })
    return res.data;
  },

  /**
   * 更新指定角色下所有用户
   * @param {Object} data 用户数据   roleId:角色号
   *                                employeeIdList:职工号数组
   * @returns {Promise<Object>} 
  */
  async putRoleEmployeelist(data){
    const res = await NetworkRequest({
      url:'/roleEmployeelist',
      method:'put',
      data
    })
    return res.data;
  },

  /**
   * 添加角色
   * @param {Object} data 用户数据   roleName:角色名称
   * @returns {Promise<Object>} 
  */
  async addRole(data){
    const res = await NetworkRequest({
      url:'/role',
      method:'post',
      data
    })
    return res.data;
  },

  /**
   * 删除角色
   * @param {Object} data 用户数据   roleId:角色id
   * @returns {Promise<Object>} 
  */
  async deleteRole(data){
    const res = await NetworkRequest({
      url:'/role',
      method:'delete',
      data,
      throttle:true
    })
    return res.data;
  },
  
  /**
   * 删除职工
   * @param {Object} data 用户数据   employeeId:职工号
   * @returns {Promise<Object>} 
  */
  async deleteRole(data){
    const res = await NetworkRequest({
      url:'/employee',
      method:'delete',
      data,
      throttle:true
    })
    return res.data;
  },

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
   * 添加部门
   * @param {Object} data 用户数据   name:部门名称
   *                                phone:部门电话
   * @returns {Promise<Object>} 
  */
  async addDepartment(data){
    const res = await NetworkRequest({
      url:'/department',
      method:'post',
      data
    })
    return res.data;
  },

  /**
   * 删除部门
   * @param {Object} data 用户数据   departmentId:部门id
   * @returns {Promise<Object>} 
  */
  async deleteDepartment(data){
    const res = await NetworkRequest({
      url:'/department',
      method:'delete',
      data
    })
    return res.data;
  },

  /**
   * 修改部门
   * @param {Object} data 用户数据   departmentId:部门id
   *                                name:部门名称
   *                                phone:部门电话
   * @returns {Promise<Object>} 
  */
  async updateDepartment(data){
    const res = await NetworkRequest({
      url:'/department',
      method:'put',
      data
    })
    return res.data;
  },

  /**
   * 添加职工
   * @param {Object} data 用户数据   name:姓名
   *                                sex:性别,
   *                                phone:电话号码,
   *                                email:电子邮箱,
   *                                idCard:身份证号,
   *                                departmentName:部门,
   *                                position:职位,
   *                                homeAddress:家庭住址
   * @returns {Promise<Object>} 
  */
  async addEmployee(data){
    const res = await NetworkRequest({
      url:'/employee',
      method:'post',
      data
    })
    return res.data;
  },

  /**
   * 更新指定角色下所有用户
   * @param {Object} data 用户数据   employeeIdList:职工列表
   *                                roleId:角色号
   * @returns {Promise<Object>} 
  */
  async updateRoleEmployeelist(data){
    const res = await NetworkRequest({
      url:'/roleEmployeelist',
      method:'put',
      data
    })
    return res.data;
  },
}