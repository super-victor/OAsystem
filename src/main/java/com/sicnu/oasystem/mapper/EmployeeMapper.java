package com.sicnu.oasystem.mapper;

import com.sicnu.oasystem.pojo.Employee;
import com.sicnu.oasystem.pojo.Menu;
import com.sicnu.oasystem.pojo.Role;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @InterfaceName EmployeeMapper
 * @Description 操作employee表的mapper
 * @Author JohnTang
 * @LastChangeDate 2020/11/4 23:06
 * @Version v1.0
 */


@Mapper
public interface EmployeeMapper {

    int insertEmployee(Employee employee);

    int deleteEmployee(int employeeId);

    /**
     * @MethodName findEmployeeByUsername
     * @Param String username
     * @Description 找出员工by员工用户名
     * @Return Employee
     * @LastChangeDate 2020/11/5
     * @Version v1.0
     */
    Employee findEmployeeByUsername(String username);


    /**
     * @MethodName findEmployeeByEmployeeId
     * @Param int employeeId
     * @Description 找出员工by员工id
     * @Author JohnTang
     * @Return
     * @LastChangeDate 2020/11/6
     */
    Employee findEmployeeByEmployeeId(int employeeId);

    /**
     * @MethodName findRolesByEmployeeId
     * @Param int employeeId
     * @Description 找出所有权限列表by员工id
     * @Author JohnTang
     * @Return List<Role>
     * @LastChangeDate 2020/11/5
     */
    List<Role> findRolesByEmployeeId(int employeeId);

    /**
     * @MethodName updatePartlyEmployeeByEmployeeId
     * @Param int employeeId, String phone, String email, String homeAddress
     * @Description 更新员工部分信息by员工id
     * @Author JohnTang
     * @Return int
     * @LastChangeDate 2020/11/6
     */
    int updatePartlyEmployeeByEmployeeId(int employeeId, String phone, String email, String homeAddress);

    /**
     * @MethodName getAllEmployee
     * @param
     * @Description 获取所有员工的数据及对应部门数据
     * @Author pan
     * @Return java.util.List<com.sicnu.oasystem.pojo.Employee>
     * @LastChangeDate 2020/11/9
     */
    List<Employee> getAllEmployee();

    /**
     * @MethodName findAllEmployee
     * @param
     * @Description 获取所有员工数据
     * @Author JohnTang
     * @Return java.util.List<com.sicnu.oasystem.pojo.Employee>
     * @LastChangeDate 2020/11/25
     */
    List<Employee> findAllEmployee();

    /**
     * @MethodName getEmployeeBypage
     * @param currentPageNum
     * @param pageSize
     * @Description 按页获取员工数据
     * @Author pan
     * @Return java.util.List<com.sicnu.oasystem.pojo.Employee>
     * @LastChangeDate 2020/11/9
     */
    List<Employee> getEmployeeBypage(Integer currentPageNum,Integer pageSize);

    /**
     * @MethodName getemployeenumbers
     * @param
     * @Description 获取所有员工的数量
     * @Author pan
     * @Return java.lang.Integer
     * @LastChangeDate 2020/11/9
     */

    Integer getemployeenumbers();

    /**
     * @MethodName getEmployeeBycondition
     * @param name
     * @param sex
     * @Description 通过名字和性别获取员工
     * @Author pan
     * @Return java.util.List<com.sicnu.oasystem.pojo.Employee>
     * @LastChangeDate 2020/11/9
     */
    List<Employee>getEmployeeByCondition(String name,String sex);

    /**
     * @MethodName findRoleByEmployeeIdAndRoleId
     * @param employeeId
     * @param roleId
     * @Description 判断用户是否拥有该角色
     * @Author JohnTang
     * @Return java.util.List<com.sicnu.oasystem.pojo.Employee>
     * @LastChangeDate 2020/11/18
     * @return
     */
    Role findRoleByEmployeeIdAndRoleId(Integer employeeId, Integer roleId);

    /**
     * @MethodName findEmployeesByRoleId
     * @param roleId
     * @Description 找出所有拥有该角色的员工
     * @Author JohnTang
     * @Return java.util.List<com.sicnu.oasystem.pojo.Employee>
     * @LastChangeDate 2020/11/18
     */
    List<Employee> findEmployeesByRoleId(Integer roleId);

    /**
     * @MethodName findRoleIdsByUrlPattern
     * @param urlPattern
     * @Description 根据url模式找出需要的角色
     * @Author JohnTang
     * @Return java.util.List<java.lang.String>
     * @LastChangeDate 2020/11/22
     */
    List<String> findRoleIdsByUrlPattern(String urlPattern);

    /**
     * @MethodName findCodesByEmployeeId
     * @param employeeId
     * @Description 根据员工id找出所有的code
     * @Author JohnTang
     * @Return java.util.List<java.lang.String>
     * @LastChangeDate 2020/11/25
     */
    List<String> findCodesByEmployeeId(Integer employeeId);

    /**
     * @MethodName findCodesByRoleId
     * @param roleId
     * @Description 根据角色id找出所有的code
     * @Author JohnTang
     * @Return java.util.List<java.lang.String>
     * @LastChangeDate 2020/11/25
     */
    List<String> findCodesByRoleId(Integer roleId);

    /**
     * @MethodName findAllMenus
     * @param
     * @Description 找出所有的功能列表
     * @Author JohnTang
     * @Return java.util.List<com.sicnu.oasystem.pojo.Menu>
     * @LastChangeDate 2020/11/25
     */
    List<Menu> findAllMenus();

    /**
     * @MethodName findAllRoles
     * @param
     * @Description 找出所有的角色
     * @Author JohnTang
     * @Return java.util.List<com.sicnu.oasystem.pojo.Role>
     * @LastChangeDate 2020/11/25
     */
    List<Role> findAllRoles();

    /**
     * @MethodName findAllRolesWithEmpolyeeList
     * @param
     * @Description 找出所有的角色以及角色对应的拥有角色的员工
     * @Author JohnTang
     * @Return java.util.List<com.sicnu.oasystem.pojo.Role>
     * @LastChangeDate 2020/11/25
     */
    List<Role> findAllRolesWithEmpolyeeList();

    /**
     * @MethodName insertRoleIntoEmployeeRole
     * @param employeeId
     * @param roleId
     * @Description 将角色和职工的对应关系添加进职工角色表
     * @Author JohnTang
     * @Return int
     * @LastChangeDate 2020/11/25
     */
    int insertIntoEmployeeRole(Integer employeeId, Integer roleId);

    /**
     * @MethodName deletefromEmployeeRole
     * @param employeeId
     * @param roleId
     * @Description 删除职工和角色的对应关系
     * @Author JohnTang
     * @Return int
     * @LastChangeDate 2020/11/25
     */
    int deletefromEmployeeRole(Integer employeeId, Integer roleId);

    /**
     * @MethodName insertIntoRoleMenu
     * @param menuId
     * @param roleId
     * @Description 添加角色和功能的对应关系
     * @Author JohnTang
     * @Return int
     * @LastChangeDate 2020/11/25
     */
    int insertIntoRoleMenu(Integer menuId, Integer roleId);

    /**
     * @MethodName deletefromRoleMenu
     * @param menuId
     * @param roleId
     * @Description 删除角色和功能的对应关系
     * @Author JohnTang
     * @Return int
     * @LastChangeDate 2020/11/25
     */
    int deletefromRoleMenu(Integer menuId, Integer roleId);

    /**
     * @MethodName deletefromRoleMenuByCode
     * @param roleId
     * @param code
     * @Description 根据code删除角色和功能的对应关系
     * @Author JohnTang
     * @Return int
     * @LastChangeDate 2020/11/25
     */
    int deletefromRoleMenuByCode(Integer roleId, String code);

    /**
     * @MethodName insertIntoRoleMenuByCode
     * @param roleId
     * @param code 根据code添加角色和功能的对应关系
     * @Description
     * @Author JohnTang
     * @Return int
     * @LastChangeDate 2020/11/25
     */
    int insertIntoRoleMenuByCode(Integer roleId, String code);

    /**
     * @MethodName updateIsAccountLockedInEmployee
     * @param employeeId
     * @param isAccountLocked
     * @Description 更新角色账户锁定的状态
     * @Author JohnTang
     * @Return int
     * @LastChangeDate 2020/11/25
     */
    int updateIsAccountLockedInEmployee(Integer employeeId,Integer isAccountLocked);

    /**
     * @MethodName findAllEmployeeExcept12
     * @param
     * @Description 找出除了管理员和超级管理员的用户
     * @Author JohnTang
     * @Return java.util.List<com.sicnu.oasystem.pojo.Employee>
     * @LastChangeDate 2020/11/26
     */
    List<Employee> findAllEmployeeExceptA();

    /**
     * @MethodName findAllAdmin
     * @param
     * @Description 找出所有的管理员
     * @Author JohnTang
     * @Return java.util.List<com.sicnu.oasystem.pojo.Employee>
     * @LastChangeDate 2020/11/26
     */
    List<Employee> findAllAdmin();

    /**
     * @MethodName addAdmin
     * @param employeeId
     * @Description 给用户添加管理员角色
     * @Author JohnTang
     * @Return int
     * @LastChangeDate 2020/11/26
     */
    int addAdmin(int employeeId);
    
    /**
     * @MethodName deleteAdmin 
     * @param employeeId 
     * @Description 添加描述
     * @Author JohnTang
     * @Return int
     * @LastChangeDate 2020/11/27
     */
    int deleteAdmin(int employeeId);

    int insertRole(String roleName);

    int deleteRole(int roleId);
}
