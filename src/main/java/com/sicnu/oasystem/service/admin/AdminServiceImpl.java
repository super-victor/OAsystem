package com.sicnu.oasystem.service.admin;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.mapper.CardHolderMapper;
import com.sicnu.oasystem.mapper.EmployeeMapper;
import com.sicnu.oasystem.pojo.*;
import com.sicnu.oasystem.service.message.MessageService;
import com.sicnu.oasystem.util.DataUtil;
import com.sicnu.oasystem.util.ListUtil;
import com.sicnu.oasystem.util.LogUtil;
import com.sicnu.oasystem.util.UserAuthenticationUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.mail.MailMessage;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName AdminServiceImpl
 * @Description 添加描述
 * @Author JohnTang
 * @LastChangeDate 2020/11/22 11:09
 * @Version v1.0
 */

@Slf4j
@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    EmployeeMapper employeeMapper;

    @Resource
    CardHolderMapper cardHolderMapper;

    @Resource
    LogUtil logUtil;

    @Resource
    MessageService messageService;

    @Override
    public BackFrontMessage getAllEmployee() {
        return new BackFrontMessage(200,"获取成功",employeeMapper.findAllEmployeeExceptA());
    }

    @Override
    public BackFrontMessage batchEmployeeByExcel(MultipartFile multipartFile) {
        if (multipartFile == null) {
            return new BackFrontMessage(500,"上传失败",null);
        }
        FileInputStream fileInputStream ;

        try {
            fileInputStream = (FileInputStream)multipartFile.getInputStream();
            XSSFWorkbook xssfWorkbook;
            if (multipartFile.getOriginalFilename().endsWith("xlsx")) {
                xssfWorkbook = new XSSFWorkbook(fileInputStream);
            } else {
                return new BackFrontMessage(500, "文件类型不正确", null);
            }

            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
            List<Employee> employeeList = new ArrayList<>();
            for (Row row : xssfSheet) {
                if (row.getRowNum() == 0) {
                    // 第一行忽略
                    continue;
                }
                Employee employee = new Employee();
                employee.setName(row.getCell(0).toString());
                employee.setPhone(row.getCell(1).toString());
                employee.setEmail(row.getCell(2).toString());
                employee.setIdCard(row.getCell(3).toString());
                employee.setSex(row.getCell(4).toString());
                employee.setDepartmentName(row.getCell(5).toString());
                employee.setPosition(row.getCell(6).toString());
                employee.setHomeAddress(row.getCell(7).toString());
                employeeList.add(employee);
            }

            for (Employee employee:employeeList) {
                employeeMapper.insertEmployee(employee);
            }

            return new BackFrontMessage(200,"添加成功",null);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new BackFrontMessage(500,"添加失败",null);
    }

    @Override
    public BackFrontMessage addEmployee(Employee employee) {
        employee.setUsername(employee.getEmail());
        employee.setPassword("123456");
        String birthdayString = employee.getIdCard().substring(6,14);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date birthday;
        try {
            birthday = simpleDateFormat.parse(birthdayString);
        } catch (ParseException e) {
            e.printStackTrace();
            return new BackFrontMessage(500,"添加失败",null);
        }
        employee.setBirthday(birthday);
        int flag;
        try {
            flag = employeeMapper.insertEmployee(employee);
        }catch (DuplicateKeyException e) {
            e.printStackTrace();
            flag = 0;
        }catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            flag = 0;
        }catch (Exception e){
            e.printStackTrace();
            flag = 0;
        }
        if (flag != 0) {
            logUtil.insertInfo("添加用户"+employee.getEmployeeId()+"成功");
            messageService.send(employee.getEmployeeId(), DataUtil.MESSAGE_TYPE_SUCCESS,"注册成功", "欢迎使用协同办公系统，使用中如果有任何Bug请联系管理员!");
            CardHolder cardHolder = new CardHolder();
            cardHolder.setOwnerId(employee.getEmployeeId());
            cardHolder.setName("默认名片夹");
            cardHolderMapper.insertCardHolderByCardHolderId(cardHolder);
            return new BackFrontMessage(200, "添加成功", null);
        } else {
            return new BackFrontMessage(500, "添加失败", null);
        }
    }

    @Override
    public BackFrontMessage deleteEmployee(int employeeId) {
        //确保不是拥有管理员和超级管理员用户
        // unfinish
        //先将用户有关的所有表都清空完
        if (employeeMapper.deleteEmployee(employeeId) != 0) {
            logUtil.deleteInfo("删除用户"+employeeId+"成功");
            return new BackFrontMessage(200,"删除成功",null);
        } else {
            return new BackFrontMessage(500,"删除失败",null);
        }
    }

    @Override
    public BackFrontMessage addRole(String name) {
        Role role = new Role();
        role.setName(name);

        int flag;
        try {
            flag = employeeMapper.insertRole(role);
        }catch (DuplicateKeyException e) {
            flag = 0;
        }catch (DataIntegrityViolationException e) {
            flag = 0;
        }catch (Exception e){
            flag = 0;
        }

        if (flag != 0) {
            logUtil.insertInfo("添加角色成功，"+"角色信息为："+role.toString());
            return new BackFrontMessage(200, "添加成功", null);
        } else {
            return new BackFrontMessage(500, "添加失败", null);
        }
    }

    @Override
    public BackFrontMessage deleteRole(int roleId) {
        int flag;
        try {
            flag = employeeMapper.deleteRole(roleId);
        }catch (Exception e){
            flag = 0;
        }

        if (flag != 0) {
            logUtil.deleteInfo("删除角色"+roleId+"成功");
            return new BackFrontMessage(200, "删除成功", null);
        } else {
            return new BackFrontMessage(500, "删除失败", null);
        }
    }

    @Override
    public BackFrontMessage getAllMenus() {
        return new BackFrontMessage(200, "获取成功", employeeMapper.findAllMenus());
    }

    @Override
    public BackFrontMessage getMenuCodeList(Integer roleId) {
        return new BackFrontMessage(200,"获取成功",employeeMapper.findCodesByRoleId(roleId));
    }

    @Override
    public BackFrontMessage updateRoleMenulist(Integer roleId, List<String> codeList) {

        // 判断roleId是否为超级管理员和管理员
        if (roleId == 1 || roleId == 2) {
            return new BackFrontMessage(500,"修改失败",null);
        }

        List<String> primaryCodeList = employeeMapper.findCodesByRoleId(roleId);

        ListUtil.compare(primaryCodeList, codeList);

        for (String code : primaryCodeList) {
            employeeMapper.deletefromRoleMenuByCode(roleId, code);
        }

        for (String code : codeList) {
            employeeMapper.insertIntoRoleMenuByCode(roleId, code);
        }

        logUtil.updateInfo("更新角色"+roleId+"角色功能对应关系成功");
        return new BackFrontMessage(200,"修改成功",null);
    }

    @Override
    public BackFrontMessage getAllRolesWithEmployeeList() {
        return new BackFrontMessage(200,"获取成功", employeeMapper.findAllRolesWithEmpolyeeList());
    }

    @Override
    public BackFrontMessage updateRoleEmployeelist(Integer roleId, List<Integer> employeeIdList) {
        if (roleId == 1 || roleId == 2) {
            return new BackFrontMessage(500,"修改失败",null);
        }
        List<Employee> primaryEmployeeList = employeeMapper.findEmployeesByRoleId(roleId);
        List<Integer> primaryEmployeeIdList = new ArrayList<>(primaryEmployeeList.size());

        for (Employee employee: primaryEmployeeList) {
            primaryEmployeeIdList.add(employee.getEmployeeId());
        }

        ListUtil.compare(primaryEmployeeIdList,employeeIdList);

        for (Integer employeeId : primaryEmployeeIdList) {
            employeeMapper.deletefromEmployeeRole(employeeId,roleId);
        }

        for (Integer employeeId : employeeIdList) {
            employeeMapper.insertIntoEmployeeRole(employeeId,roleId);
        }
        logUtil.updateInfo("更新角色"+roleId+"角色职工对应关系成功");
        return new BackFrontMessage(200,"修改成功",null);
    }

    @Override
    public int addMenuInRole(Integer menuId, Integer roleId) {
        int flag;
        try {
            flag = employeeMapper.insertIntoRoleMenu(menuId,roleId);
        }catch (DuplicateKeyException e) {

            log.warn("用户id为"+ UserAuthenticationUtils.getCurrentUserFromSecurityContext().getEmployeeId() +"插入了一个重复键 roleId为"+roleId+"menuId为"+menuId);
            flag = 0;
        }catch (DataIntegrityViolationException e) {
            log.warn("用户id为"+ UserAuthenticationUtils.getCurrentUserFromSecurityContext().getEmployeeId() +"插入了一个重复键 roleId为"+roleId+"menuId为"+menuId);
            flag = 0;
        }
        logUtil.insertInfo("添加角色"+roleId+"功能"+menuId+"对应关系成功");
        return flag;
    }

    @Override
    public int deleteMenuInRole(Integer menuId, Integer roleId) {
        logUtil.insertInfo("删除角色"+roleId+"功能"+menuId+"对应关系成功");
        return employeeMapper.deletefromRoleMenu(menuId, roleId);
    }

    @Override
    public int addRoleInEmployee(Integer employeeId, Integer roleId) {
        int flag;
        try {
            flag = employeeMapper.insertIntoEmployeeRole(1,1);
        }catch (DuplicateKeyException e) {
            log.warn("用户id为"+employeeId+"插入了一个重复键 roleId为"+roleId);
            flag = 0;
        }catch (DataIntegrityViolationException e) {
            log.warn("用户id为"+employeeId+"插入了一个违反约束完整性的键 roleId为"+roleId);
            flag = 0;
        }
        logUtil.insertInfo("添加角色"+roleId+"职工"+employeeId+"对应关系成功");
        return flag;
    }

    @Override
    public int deleteRoleInEmployee(Integer employeeId, Integer roleId) {
        logUtil.deleteInfo("删除角色"+roleId+"职工"+employeeId+"对应关系成功");
        return employeeMapper.deletefromEmployeeRole(employeeId, roleId);
    }

    @Override
    public BackFrontMessage lockOrUnlockEmployee(Integer employeeId, Integer isAccountLocked) {
        //判断是否为超级管理员，管理员
        if (employeeMapper.updateIsAccountLockedInEmployee(employeeId, isAccountLocked) == 1) {
            if (isAccountLocked == 1) {
                logUtil.updateInfo("锁定用户"+employeeId+"成功");
            }else {
                logUtil.updateInfo("解锁用户"+employeeId+"成功");
            }
            return new BackFrontMessage(200, "修改成功",null);
        } else {
            return new BackFrontMessage(500,"修改失败",null);
        }
    }

    @Override
    public BackFrontMessage getEmployeeRole(Integer employeeId) {
        Map<String, List<Role>> map = new HashMap<>(2);
        map.put("haveRoleList", employeeMapper.findRolesByEmployeeId(employeeId));
        map.put("nothaveRoleList", employeeMapper.findAllRolesExceptA());
        return new BackFrontMessage(200, "获取成功",map);
    }

    @Override
    public BackFrontMessage updateEmployeeRolelist(Integer employeeId, List<Integer> roleIdList) {
        // 判断是否为超级管理员，管理员 判断角色列表中有没有1,2

        List<Role> primaryRoleList = employeeMapper.findRolesByEmployeeId(employeeId);
        List<Integer> primaryRoleIdList = new ArrayList<>(primaryRoleList.size());

        for (Role role : primaryRoleList) {
            primaryRoleIdList.add(role.getRoleId());
        }

        ListUtil.compare(primaryRoleIdList,roleIdList);

        for (Integer roleId : primaryRoleIdList) {
            employeeMapper.deletefromEmployeeRole(employeeId,roleId);
        }
        for (Integer roleId :roleIdList) {
            employeeMapper.insertIntoEmployeeRole(employeeId,roleId);
        }
        logUtil.updateInfo("更新职工"+employeeId+"的角色列表成功");
        return new BackFrontMessage(200,"修改成功",null);
    }

    @Override
    public BackFrontMessage copyRole(Integer copyroleId, String newName) {
        if (copyroleId == 1 || copyroleId == 2) {
            return new BackFrontMessage(500,"复制失败1",null);
        }
        Role copyrole = employeeMapper.findRoleByRoleId(copyroleId);
        if (copyrole == null) {
            return new BackFrontMessage(500,"复制失败2",null);
        }
        Role newRole = new Role();
        newRole.setName(newName);
        if (employeeMapper.insertRole(newRole) != 0) {
            List<Menu> copyRoleMenuList = employeeMapper.findMenusByRoleId(copyroleId);
            for (Menu menu : copyRoleMenuList) {
                employeeMapper.insertIntoRoleMenu(menu.getMenuId(), newRole.getRoleId());
            }
            logUtil.insertInfo("复制角色"+copyroleId+"为新的角色"+newRole.getRoleId()+"成功");
            return new BackFrontMessage(200,"复制成功",null);
        } else {
            return new BackFrontMessage(500,"复制失败",null);
        }

    }


}
