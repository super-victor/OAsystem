package com.sicnu.oasystem.service.admin;

import com.sicnu.oasystem.json.BackFrontMessage;

import java.util.List;

/**
 * @InterfaceName SuperAdminService
 * @Description 添加描述
 * @Author JohnTang
 * @LastChangeDate 2020/11/26 16:43
 * @Version v1.0
 */

public interface SuperAdminService {

    BackFrontMessage getAllAdmin();

    BackFrontMessage updateAdminList(List<Integer> employeeIdList);

    BackFrontMessage lockAdmin(int employeeId, Integer isAccountLocked);
}
