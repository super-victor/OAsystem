package com.sicnu.oasystem.service.document;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.mapper.DepartmentMapper;
import com.sicnu.oasystem.mapper.DocumentMapper;
import com.sicnu.oasystem.mapper.EmployeeMapper;
import com.sicnu.oasystem.pojo.DataSeeAbleA;
import com.sicnu.oasystem.pojo.Department;
import com.sicnu.oasystem.pojo.SendFile;
import com.sicnu.oasystem.pojo.limit.EmployeeLimitA;
import com.sicnu.oasystem.service.message.MessageService;
import com.sicnu.oasystem.util.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.util.*;

/**
 * @ClassName DocumentServiceImpl
 * @Description 添加描述
 * @Author JohnTang
 * @LastChangeDate 2020/11/12 15:06
 * @Version v1.0
 */

@Service
public class DocumentServiceImpl implements DocumentService {

    @Resource
    DocumentMapper documentMapper;

    @Resource
    EmployeeMapper employeeMapper;

    @Resource
    LogUtil logUtil;

    @Resource
    MessageService messageService;

    @Resource
    DepartmentMapper departmentMapper;

    @Resource
    FileUtil fileUtil;

    /**
     * @MethodName getAllCensors
     * @Description 获取所有的审查人
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/15
     */
    @Override
    public BackFrontMessage getAllCensors() {
        // 返回有审查人接口的权限的员工列表
        return new BackFrontMessage(200, "获取成功", documentMapper.gethaveCheckAbilityEmployList());
    }

    /**
     * @MethodName getSelfUnCheckDocument
     * @Description 审查人获取自己需要审查的发文列表
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/1
     */
    @Override
    public BackFrontMessage getSelfInCheckDocument() {
        return new BackFrontMessage(200,"获取成功",documentMapper.findSendFileByEmployeeIdAndStatus(UserAuthenticationUtils.getCurrentUserFromSecurityContext().getEmployeeId(), 0));
    }

    @Override
    public BackFrontMessage getSelfDraftBoxDocument() {
        return new BackFrontMessage(200,"获取成功",documentMapper.findSendFileByEmployeeIdAndStatus(UserAuthenticationUtils.getCurrentUserFromSecurityContext().getEmployeeId(), -1));
    }

    @Override
    public BackFrontMessage getSelfUnPassDocument() {
        return new BackFrontMessage(200,"获取成功",documentMapper.findSendFileByEmployeeIdAndStatus(UserAuthenticationUtils.getCurrentUserFromSecurityContext().getEmployeeId(), -2));
    }

    @Override
    public BackFrontMessage deleteDocument(Integer sendfileId) {
        // 先删除附件文件夹
        fileUtil.deleteLocalFile("/"+sendfileId.toString());
        logUtil.deleteInfo("删除发文"+sendfileId+"成功");
        documentMapper.deleteSendFile(sendfileId);
        return new BackFrontMessage(200,"删除成功",null);
    }

    @Override
    public BackFrontMessage deleteDocumentAnnex(int sendfileId,String annexUrl) {
        // 校验是否为本人的发文
        SendFile sendFile = documentMapper.findSendFileByEmployeeIdAndSendfileId(UserAuthenticationUtils.getCurrentUserFromSecurityContext().getEmployeeId(),sendfileId);
        if (sendFile == null){
            return new BackFrontMessage(500,"删除失败3",null);
        }

        // 只有这几种状态的发文才能修改
        if (!(sendFile.getStatus() == -3||sendFile.getStatus() == -1||sendFile.getStatus() == -2)){
            return new BackFrontMessage(500,"删除失败6",null);
        }

        // 删除文件系统对应的文件
        if (!fileUtil.deleteLocalFile(annexUrl)) {
            return new BackFrontMessage(500,"删除失败7",null);
        }

        if (documentMapper.deleteDocumentAnnexByannexUrl(annexUrl) == 1) {
            logUtil.deleteInfo("删除发文"+sendfileId+"的附件url="+annexUrl+"成功");
            return new BackFrontMessage(200,"删除成功",null);
        } else {
            return new BackFrontMessage(500,"删除失败",null);
        }

    }

    @Override
    public BackFrontMessage uploadDocumentAnnex(Integer sendfileId, MultipartFile file) {
        // 校验是否为本人的发文
        SendFile sendFile = documentMapper.findSendFileByEmployeeIdAndSendfileId(UserAuthenticationUtils.getCurrentUserFromSecurityContext().getEmployeeId(),sendfileId);
        if (sendFile == null){
            return new BackFrontMessage(500,"上传失败3",null);
        }

        // 只有这几种状态的发文才能修改
        if (!(sendFile.getStatus() == -3||sendFile.getStatus() == -1||sendFile.getStatus() == -2)){
            return new BackFrontMessage(500,"上传失败6",null);
        }

        //校验文件不为空
        if (file == null || file.isEmpty()) {
            return new BackFrontMessage(500,"上传失败1",null);
        }

        String fileUrl;
        // 限制文件大小为10M
        int maxSizeLimit = 1024*1024*10;
        try {
            fileUrl = fileUtil.saveFileInNewDirectory(file,"/"+sendfileId.toString(), maxSizeLimit);
        } catch (IOException e) {
            e.printStackTrace();
            return new BackFrontMessage(500,"上传失败2",null);
        } catch (OverMaxFileSizeLimitException e) {
            e.printStackTrace();
            return new BackFrontMessage(500,"文件超出大小限制",maxSizeLimit);
        }

        // 如果一个用户在空白发文中上传了附件，则自动存入草稿箱
        if (sendFile.getStatus() == -3) {
            documentMapper.updateStatusInSendFile(sendfileId,-1);
        }
        // 更新发文列表的附件url列表
        if (documentMapper.insertDocumentAnnex(sendfileId,fileUrl) == 1){
            return new BackFrontMessage(200,"上传成功",fileUrl);
        } else {
            return new BackFrontMessage(500,"上传失败4",null);
        }

    }

    @Override
    public BackFrontMessage getDocumentDetail(Integer sendfileId) {
        // todo 权限控制 发文详情是返回的所有状态的发文复用接口
        SendFile sendFile = documentMapper.findSendFileBySendfileId(sendfileId);
        if (sendFile != null) {
            sendFile.setAccessEmployeeIdList(documentMapper.findAccessEmployeesBySendfileId(sendfileId));
            sendFile.setAnnuxUrlList(documentMapper.findAnnexUrlsBySendfileId(sendfileId));
            sendFile.setSenderDetail(employeeMapper.findEmployeeByEmployeeId(sendFile.getSenderId()));
            if(sendFile.getCensorId() != null) {
                sendFile.setCensorDetail(employeeMapper.findEmployeeByEmployeeId(sendFile.getCensorId()));
            }
        }
        return new BackFrontMessage(200, "获取成功", sendFile);
    }


    @Override
    public BackFrontMessage createDocument() {
        SendFile sendFile = documentMapper.findEmptySendFileByEmployeeId(UserAuthenticationUtils.getCurrentUserFromSecurityContext().getEmployeeId());
        if (sendFile == null) {
            sendFile = new SendFile();
            sendFile.setSenderId(UserAuthenticationUtils.getCurrentUserFromSecurityContext().getEmployeeId());
            sendFile.setStatus(-3);
            if (documentMapper.insertSendFile(sendFile) == 0) {
                return new BackFrontMessage(500,"添加失败",null);
            }
        }
        return new BackFrontMessage(200,"添加成功",sendFile.getSendfileId());
    }


    /**
     * @param title
     * @param content
     * @param remark
     * @MethodName pdateNotPassDocument
     * @Description 修改发文(草稿箱, 未通过)
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/15
     */
    @Override
    public BackFrontMessage updateUncheckDocument(Integer sendfileId,String type,String subject, String title, String content, String remark, Integer censorId, String urgent, Integer isPublic, List<Integer> accessEmployeeIdList) {
        // 校验是否为本人的发文
        SendFile sendFile = documentMapper.findSendFileByEmployeeIdAndSendfileId(UserAuthenticationUtils.getCurrentUserFromSecurityContext().getEmployeeId(),sendfileId);
        if (sendFile == null ){
            return new BackFrontMessage(500,"添加失败5",null);
        }

        // 只有这几种状态的发文才能修改
        if (!(sendFile.getStatus() == -3||sendFile.getStatus() == -1||sendFile.getStatus() == -2)){
            return new BackFrontMessage(500,"添加失败6",null);
        }

        // 验证参数
        // 验证传来审稿人id
        if (censorId != null && documentMapper.ishaveCheckAbility(censorId) == 0) {
            return new BackFrontMessage(500,"添加失败1",null);
        }

        // 修改发文属性
        sendFile.setStatus(-1);
        sendFile.setSubject(subject);
        sendFile.setType(type);
        sendFile.setIsPublic(isPublic);
        sendFile.setTitle(title);
        sendFile.setContent(content);
        sendFile.setUrgent(urgent);
        sendFile.setRemark(remark);
        if (censorId != null) {
            sendFile.setCensorId(censorId);
        }
        documentMapper.updateSendFileBySendFile(sendFile);


        // 如果修改了公开字段 删除所有权限控制
        if (isPublic == 1) {
            documentMapper.deleteDocumentAcess(sendfileId);
        } else {
            List<Integer> primaryAccessEmployeeIdList = documentMapper.findEmployeeIdsInDocumentAcess(sendfileId);

            ListUtil.compare(primaryAccessEmployeeIdList,accessEmployeeIdList);

            //删除减少的部分
            if (primaryAccessEmployeeIdList != null && !primaryAccessEmployeeIdList.isEmpty()) {
                for (Integer employeeId : primaryAccessEmployeeIdList) {
                    documentMapper.deleteDocumentAcessBySendfileIdAndEmployeeId(sendfileId,employeeId);
                }
            }

            //添加增加的部分
            if (accessEmployeeIdList != null && !accessEmployeeIdList.isEmpty()) {
                for (Integer employeeId : accessEmployeeIdList) {
                    documentMapper.insertDocumentAcess(sendfileId,employeeId);
                }
            }
        }
        logUtil.insertInfo("添加发文"+sendfileId+"成功");
        return new BackFrontMessage(200,"修改成功",null);

    }

    /**
     * @param sendfileId
     * @MethodName commitDocument
     * @Description 提交审核
     * @Author JohnTang
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/12/1
     */
    @Override
    public BackFrontMessage commitDocument(Integer sendfileId) {
        // 校验是否为本人的发文
        SendFile sendFile = documentMapper.findSendFileByEmployeeIdAndSendfileId(UserAuthenticationUtils.getCurrentUserFromSecurityContext().getEmployeeId(),sendfileId);
        if (sendFile == null){
            return new BackFrontMessage(500,"上传失败3",null);
        }

        if (documentMapper.updateStatusInSendFile(sendfileId, 0) == 1) {
            messageService.send(sendFile.getCensorId(),DataUtil.MESSAGE_TYPE_INFO,DataUtil.MESSAGE_TITLE_POSTREVIEW,"有一份新的发文等待您审核");
            return new BackFrontMessage(200,"提交成功",null);
        }else {
            return new BackFrontMessage(500,"提交失败",null);
        }

    }

    @Override
    public BackFrontMessage checkDocument(Integer sendfileId, int ispassed, String comment) {

        SendFile sendFile = documentMapper.findSendFileBySendfileId(sendfileId);
        if (sendFile == null || sendFile.getCensorId() != UserAuthenticationUtils.getCurrentUserFromSecurityContext().getEmployeeId()) {
            return new BackFrontMessage(500,"修改失败1",null);
        }

        Integer status;
        if (ispassed == 1) {
            messageService.send(sendFile.getSenderId(),DataUtil.MESSAGE_TYPE_SUCCESS,DataUtil.MESSAGE_TITLE_POSTREVIEW,"恭喜您,标题为: "+sendFile.getTitle()+" 的发文已通过审核,请前往发文详情查看！");
            // 通知所有发布范围的人
            List<EmployeeLimitA> employeeLimitAList = documentMapper.findAccessEmployeesBySendfileId(sendfileId);
            if ( employeeLimitAList != null) {
                for (EmployeeLimitA employee : employeeLimitAList) {
                    messageService.send(employee.getEmployeeId(),DataUtil.MESSAGE_TYPE_INFO,"公文发布","与你有关的公文已发布，请前往发文详情查看！");
                }
            }
            status = 1;
        }else {
            messageService.send(sendFile.getSenderId(),DataUtil.MESSAGE_TYPE_INFO,DataUtil.MESSAGE_TITLE_POSTREVIEW,"很遗憾,标题为: "+sendFile.getTitle()+" 的发文没有通过审核,请前往发文详情查看并修改！");
            status = -2;
        }

        if (documentMapper.updateStatusAndCommentInSendFile(sendfileId,status,comment) != 0) {
            logUtil.updateInfo("修改发文"+sendfileId+"成功");
            return new BackFrontMessage(200,"审查成功",null);
        } else {
            return new BackFrontMessage(500,"审查失败",null);
        }
    }

    @Override
    public BackFrontMessage cancelCheck(Integer sendfileId) {
        SendFile sendFile = documentMapper.findSendFileBySendfileId(sendfileId);
        if (sendFile == null || sendFile.getSenderId() != UserAuthenticationUtils.getCurrentUserFromSecurityContext().getEmployeeId()) {
            return new BackFrontMessage(500,"修改失败1",null);
        }

        if (documentMapper.updateStatusInSendFile(sendfileId,-1) != 0 ){
            return new BackFrontMessage(200,"修改成功",null);
        } else {
            return new BackFrontMessage(500,"修改失败2",null);
        }

    }

    @Override
    public BackFrontMessage needCheckDocument() {

        List<SendFile> sendFileList = documentMapper.findSendFileNeedCheck(UserAuthenticationUtils.getCurrentUserFromSecurityContext().getEmployeeId());
        if (sendFileList != null) {
            for (SendFile sendFile : sendFileList) {
                sendFile.setAccessEmployeeIdList(documentMapper.findAccessEmployeesBySendfileId(sendFile.getSendfileId()));
                sendFile.setAnnuxUrlList(documentMapper.findAnnexUrlsBySendfileId(sendFile.getSendfileId()));
                sendFile.setSenderDetail(employeeMapper.findEmployeeByEmployeeId(sendFile.getSenderId()));
                if(sendFile.getCensorId() != null) {
                    sendFile.setCensorDetail(employeeMapper.findEmployeeByEmployeeId(sendFile.getCensorId()));
                }
            }
        }
        return new BackFrontMessage(200,"获取成功",sendFileList);
    }

    @Override
    public BackFrontMessage getAllPublishDocument() {
        List<SendFile> sendFileList = documentMapper.findAllPublishSendFile();
        if (sendFileList != null) {
            for (SendFile sendFile : sendFileList) {
                sendFile.setAccessEmployeeIdList(documentMapper.findAccessEmployeesBySendfileId(sendFile.getSendfileId()));
                sendFile.setAnnuxUrlList(documentMapper.findAnnexUrlsBySendfileId(sendFile.getSendfileId()));
                sendFile.setSenderDetail(employeeMapper.findEmployeeByEmployeeId(sendFile.getSenderId()));
                if(sendFile.getCensorId() != null) {
                    sendFile.setCensorDetail(employeeMapper.findEmployeeByEmployeeId(sendFile.getCensorId()));
                }
            }
        }
        return new BackFrontMessage(200,"获取成功",sendFileList);
    }

    @Override
    public BackFrontMessage getSelfPublishDocument(){
        List<SendFile> sendFileList = documentMapper.findSelfPublishSendFile(UserAuthenticationUtils.getCurrentUserFromSecurityContext().getEmployeeId());
        if (sendFileList != null) {
            for (SendFile sendFile : sendFileList) {
                sendFile.setAccessEmployeeIdList(documentMapper.findAccessEmployeesBySendfileId(sendFile.getSendfileId()));
                sendFile.setAnnuxUrlList(documentMapper.findAnnexUrlsBySendfileId(sendFile.getSendfileId()));
                sendFile.setSenderDetail(UserAuthenticationUtils.getCurrentUserFromSecurityContext());
                if(sendFile.getCensorId() != null) {
                    sendFile.setCensorDetail(employeeMapper.findEmployeeByEmployeeId(sendFile.getCensorId()));
                }
            }
        }
        return new BackFrontMessage(200,"获取成功",sendFileList);
    }

    @Override
    public BackFrontMessage putDocumentNotPassIntoDraftbox(int sendfileId) {

        SendFile sendFile = documentMapper.findSendFileBySendfileId(sendfileId);
        if (sendFile == null || sendFile.getSenderId() != UserAuthenticationUtils.getCurrentUserFromSecurityContext().getEmployeeId()) {
            return new BackFrontMessage(500,"修改失败1",null);
        }

        if (documentMapper.updateStatusInSendFile(sendfileId, -1) != 0) {
            return new BackFrontMessage(200,"修改成功",null);
        } else {
            return new BackFrontMessage(500,"修改失败",null);
        }
    }

    @Override
    public Map<String, Object> getSystemDocumentSeeAbleData() {
        // 总的发布公文
        Map<String,Object> map = new HashMap<>(16);

        List<DataSeeAbleA> dataSeeAbleAList = documentMapper.findDateAndCountAboutPublishDocuments();
        List<DataSeeAbleA> resultSeeAbleList = new ArrayList<>();
        Date endDate = new Date();
        Date startDate = DateUtil.subDay(endDate,14);
        // 如果查出来的数据不为空
        if (dataSeeAbleAList != null && !dataSeeAbleAList.isEmpty()) {
            for (DataSeeAbleA dataSeeAbleA : dataSeeAbleAList) {
                while (true) {
                    if (DateUtil.isSameDay(startDate, dataSeeAbleA.getRecordDate())) {
                        resultSeeAbleList.add(dataSeeAbleA);
                        startDate = DateUtil.addDay(startDate,1);
                        break;
                    }
                    resultSeeAbleList.add(new DataSeeAbleA(startDate, 0));
                    startDate = DateUtil.addDay(startDate,1);
                }
            }
            while (true) {
                if(DateUtil.isSameDay(resultSeeAbleList.get(resultSeeAbleList.size() - 1).getRecordDate(),endDate)) {
                    break;
                }
                resultSeeAbleList.add(new DataSeeAbleA(startDate, 0));
                startDate = DateUtil.addDay(startDate,1);
            }
        } else {
            // 如果查出来的数据为空
            for (int i = 0; i < 15; i++) {
                resultSeeAbleList.add(new DataSeeAbleA(startDate, 0));
                startDate = DateUtil.addDay(startDate,1);
            }
        }

        map.put("totalPublishDocument",resultSeeAbleList);

        // 各部门发布公文
        List<Department> departments = departmentMapper.findAllDepartment();
        Map<String,List<DataSeeAbleA>> map1 = new HashMap<>();
        for (Department department:departments) {
            dataSeeAbleAList = documentMapper.findDateAndCountAboutPublishDocumentsByDepartmentName(department.getName());
            resultSeeAbleList = new ArrayList<>();
            startDate = DateUtil.subDay(endDate,14);
            // 如果查出来的数据不为空
            if (dataSeeAbleAList != null && !dataSeeAbleAList.isEmpty()) {
                for (DataSeeAbleA dataSeeAbleA : dataSeeAbleAList) {
                    while (true) {
                        if (DateUtil.isSameDay(startDate, dataSeeAbleA.getRecordDate())) {
                            resultSeeAbleList.add(dataSeeAbleA);
                            startDate = DateUtil.addDay(startDate,1);
                            break;
                        }
                        resultSeeAbleList.add(new DataSeeAbleA(startDate, 0));
                        startDate = DateUtil.addDay(startDate,1);
                    }
                }
                while (true) {
                    if(DateUtil.isSameDay(resultSeeAbleList.get(resultSeeAbleList.size() - 1).getRecordDate(),endDate)) {
                        break;
                    }
                    resultSeeAbleList.add(new DataSeeAbleA(startDate, 0));
                    startDate = DateUtil.addDay(startDate,1);
                }
            } else {
                // 如果查出来的数据为空
                for (int i = 0; i < 15; i++) {
                    resultSeeAbleList.add(new DataSeeAbleA(startDate, 0));
                    startDate = DateUtil.addDay(startDate,1);
                }
            }

            map1.put(department.getName(),resultSeeAbleList);
        }

        map.put("depertmentPublishDocument",map1);

        // 草稿箱

        dataSeeAbleAList = documentMapper.findDateAndCountAboutDraftBoxDocuments();
        resultSeeAbleList = new ArrayList<>();
        startDate = DateUtil.subDay(endDate,14);
        // 如果查出来的数据不为空
        if (dataSeeAbleAList != null && !dataSeeAbleAList.isEmpty()) {
            for (DataSeeAbleA dataSeeAbleA : dataSeeAbleAList) {
                while (true) {
                    if (DateUtil.isSameDay(startDate, dataSeeAbleA.getRecordDate())) {
                        resultSeeAbleList.add(dataSeeAbleA);
                        startDate = DateUtil.addDay(startDate,1);
                        break;
                    }
                    resultSeeAbleList.add(new DataSeeAbleA(startDate, 0));
                    startDate = DateUtil.addDay(startDate,1);
                }
            }
            while (true) {
                if(DateUtil.isSameDay(resultSeeAbleList.get(resultSeeAbleList.size() - 1).getRecordDate(),endDate)) {
                    break;
                }
                resultSeeAbleList.add(new DataSeeAbleA(startDate, 0));
                startDate = DateUtil.addDay(startDate,1);
            }
        } else {
            // 如果查出来的数据为空
            for (int i = 0; i < 15; i++) {
                resultSeeAbleList.add(new DataSeeAbleA(startDate, 0));
                startDate = DateUtil.addDay(startDate,1);
            }
        }

        map.put("totalDraftBoxDocument",resultSeeAbleList);

        // 各部门发布公文
        departments = departmentMapper.findAllDepartment();
        map1 = new HashMap<>();
        for (Department department:departments) {
            dataSeeAbleAList = documentMapper.findDateAndCountAboutDraftBoxDocumentsByDepartmentName(department.getName());
            resultSeeAbleList = new ArrayList<>();
            startDate = DateUtil.subDay(endDate,14);
            // 如果查出来的数据不为空
            if (dataSeeAbleAList != null && !dataSeeAbleAList.isEmpty()) {
                for (DataSeeAbleA dataSeeAbleA : dataSeeAbleAList) {
                    while (true) {
                        if (DateUtil.isSameDay(startDate, dataSeeAbleA.getRecordDate())) {
                            resultSeeAbleList.add(dataSeeAbleA);
                            startDate = DateUtil.addDay(startDate,1);
                            break;
                        }
                        resultSeeAbleList.add(new DataSeeAbleA(startDate, 0));
                        startDate = DateUtil.addDay(startDate,1);
                    }
                }
                while (true) {
                    if(DateUtil.isSameDay(resultSeeAbleList.get(resultSeeAbleList.size() - 1).getRecordDate(),endDate)) {
                        break;
                    }
                    resultSeeAbleList.add(new DataSeeAbleA(startDate, 0));
                    startDate = DateUtil.addDay(startDate,1);
                }
            } else {
                // 如果查出来的数据为空
                for (int i = 0; i < 15; i++) {
                    resultSeeAbleList.add(new DataSeeAbleA(startDate, 0));
                    startDate = DateUtil.addDay(startDate,1);
                }
            }

            map1.put(department.getName(),resultSeeAbleList);
        }

        map.put("depertmentDraftBoxDocument",map1);

        return map;
    }

    @Override
    public Map<String, List> getSelfDocumentSeeAbleData() {
        Map<String,List> map = new HashMap<>(16);

        List<DataSeeAbleA> dataSeeAbleAList = documentMapper.findDateAndCountAboutSelfPublishDocuments(UserAuthenticationUtils.getCurrentUserFromSecurityContext().getEmployeeId());
        List<DataSeeAbleA> resultSeeAbleList = new ArrayList<>();
        Date endDate = new Date();
        Date startDate = DateUtil.subDay(endDate,14);
        // 如果查出来的数据不为空
        if (dataSeeAbleAList != null && !dataSeeAbleAList.isEmpty()) {
            for (DataSeeAbleA dataSeeAbleA : dataSeeAbleAList) {
                while (true) {
                    if (DateUtil.isSameDay(startDate, dataSeeAbleA.getRecordDate())) {
                        resultSeeAbleList.add(dataSeeAbleA);
                        startDate = DateUtil.addDay(startDate,1);
                        break;
                    }
                    resultSeeAbleList.add(new DataSeeAbleA(startDate, 0));
                    startDate = DateUtil.addDay(startDate,1);
                }
            }
            while (true) {
                if(DateUtil.isSameDay(resultSeeAbleList.get(resultSeeAbleList.size() - 1).getRecordDate(),endDate)) {
                    break;
                }
                resultSeeAbleList.add(new DataSeeAbleA(startDate, 0));
                startDate = DateUtil.addDay(startDate,1);
            }
        } else {
            // 如果查出来的数据为空
            for (int i = 0; i < 15; i++) {
                resultSeeAbleList.add(new DataSeeAbleA(startDate, 0));
                startDate = DateUtil.addDay(startDate,1);
            }
        }

        map.put("selfPublishDocument",resultSeeAbleList);

        dataSeeAbleAList = documentMapper.findDateAndCountAboutSelfDraftBoxDocuments(UserAuthenticationUtils.getCurrentUserFromSecurityContext().getEmployeeId());
        resultSeeAbleList = new ArrayList<>();

        startDate = DateUtil.subDay(endDate,14);
        // 如果查出来的数据不为空
        if (dataSeeAbleAList != null && !dataSeeAbleAList.isEmpty()) {
            for (DataSeeAbleA dataSeeAbleA : dataSeeAbleAList) {
                while (true) {
                    if (DateUtil.isSameDay(startDate, dataSeeAbleA.getRecordDate())) {
                        resultSeeAbleList.add(dataSeeAbleA);
                        startDate = DateUtil.addDay(startDate,1);
                        break;
                    }
                    resultSeeAbleList.add(new DataSeeAbleA(startDate, 0));
                    startDate = DateUtil.addDay(startDate,1);
                }
            }
            while (true) {
                if(DateUtil.isSameDay(resultSeeAbleList.get(resultSeeAbleList.size() - 1).getRecordDate(),endDate)) {
                    break;
                }
                resultSeeAbleList.add(new DataSeeAbleA(startDate, 0));
                startDate = DateUtil.addDay(startDate,1);
            }
        } else {
            // 如果查出来的数据为空
            for (int i = 0; i < 15; i++) {
                resultSeeAbleList.add(new DataSeeAbleA(startDate, 0));
                startDate = DateUtil.addDay(startDate,1);
            }
        }

        map.put("selfDraftBoxDocument",resultSeeAbleList);
        return map;
    }


}
