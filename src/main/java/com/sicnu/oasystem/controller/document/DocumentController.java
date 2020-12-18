package com.sicnu.oasystem.controller.document;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.service.department.DepartmentService;
import com.sicnu.oasystem.service.document.DocumentService;
import com.sicnu.oasystem.service.employee.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DocumentController
 * @Description 收发文控制器
 * @Author JohnTang
 * @LastChangeDate 2020/11/12 11:01
 * @Version v1.0
 */

@Api(tags = "公文流转")
@RestController
public class DocumentController {

    @Resource
    DocumentService documentService;

    @Resource
    EmployeeService employeeService;

    @Resource
    DepartmentService departmentService;

    @ApiOperation("获取所有部门")
    @GetMapping("/document/allDepartment")
    BackFrontMessage getAllDepartment(){
        return departmentService.getAllDepartment();
    }

    @ApiOperation("获取所有员工")
    @GetMapping("/document/allEmployees")
    public BackFrontMessage getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @ApiOperation("获取所有的审查人")
    @GetMapping("/allCensor")
    public BackFrontMessage getAllCensor(){
        return documentService.getAllCensors();
    }

    @ApiOperation("上传发文附件")
    @PostMapping("/DocumentAnnex")
    public BackFrontMessage uploadDocumentAnnex(@RequestParam Integer sendfileId, @RequestParam MultipartFile multipartFile){
        return documentService.uploadDocumentAnnex(sendfileId, multipartFile);
    }

    @ApiOperation("删除发文附件")
    @DeleteMapping("/DocumentAnnex")
    public BackFrontMessage deleteDocumentAnnex(@RequestParam Integer sendfileId, @RequestParam @NotNull String annexUrl) {
        return documentService.deleteDocumentAnnex(sendfileId, annexUrl);
    }

    @ApiOperation("获取或创建空白发文")
    @GetMapping("/emptydocument")
    public BackFrontMessage getemptydocument() {
        return documentService.createDocument();
    }

    @ApiOperation("修改发文")
    @PutMapping("/document")
    public BackFrontMessage updateDocument(@RequestParam Integer sendfileId, @RequestParam @Size(max = 20) String subject, @RequestParam @Size(max = 20) String type, @RequestParam String target, @RequestParam @Size(max = 20) String title, @RequestParam String content, @RequestParam @Size(max = 100) String remark, @RequestParam Integer censorId, @RequestParam @Size(max = 20) String urgent, @RequestParam Integer isPublic, @RequestParam(required = false) List<Integer> accessEmployeeIdList) {
        if (accessEmployeeIdList == null) {
            accessEmployeeIdList = new ArrayList<>();
        }
        return documentService.updateUncheckDocument(sendfileId, type, target,subject, title, content, remark, censorId, urgent, isPublic, accessEmployeeIdList);
    }

    @ApiOperation("提交发文审稿")
    @PutMapping("/commitDocument")
    public BackFrontMessage commitDocument(@RequestParam Integer sendfileId){
        return documentService.commitDocument(sendfileId);
    }

    @ApiOperation("获取草稿箱")
    @GetMapping("/draftbox")
    public BackFrontMessage getSelfDraftBox(){
        return documentService.getSelfDraftBoxDocument();
    }

    @ApiOperation("获取自己正在审核的发文")
    @GetMapping("/documentInCheck")
    public BackFrontMessage getDocumentInCheck(){
        return documentService.getSelfInCheckDocument();
    }

    @ApiOperation("获取未通过的发文")
    @GetMapping("/documementNotPassed")
    public BackFrontMessage getDocumentNotPassed(){
        return documentService.getSelfUnPassDocument();
    }

    @ApiOperation("获取发文详情")
    @GetMapping("/document")
    public BackFrontMessage getDocumentDetail(@RequestParam Integer sendfileId){
        return documentService.getDocumentDetail(sendfileId);
    }

    @ApiOperation("删除公文")
    @DeleteMapping("/document")
    public BackFrontMessage deleteDocument(@RequestParam Integer sendfileId){
        return documentService.deleteDocument(sendfileId);
    }

    @ApiOperation("审查发文")
    @PutMapping("/checkDocument")
    public BackFrontMessage checkDocument(@RequestParam Integer sendfileId,@RequestParam int ispassed,@RequestParam String comment) {
        return documentService.checkDocument(sendfileId,ispassed,comment);
    }

    @ApiOperation("取消审核")
    @PutMapping("cancelCheck")
    public BackFrontMessage cancelCheck(@RequestParam Integer sendfileId) {
        return documentService.cancelCheck(sendfileId);
    }

    @ApiOperation("审查者获取自己的审查发文列表")
    @GetMapping("/needCheckDocument")
    public BackFrontMessage needCheckDocument(){
        return documentService.needCheckDocument();
    }

    @ApiOperation("获取所有已发布的发文")
    @GetMapping("/allPublishDocument")
    public BackFrontMessage getAllPublishDocument(){
        return documentService.getAllPublishDocument();
    }

    @ApiOperation("获取自己已发布的公文")
    @GetMapping("/selfPublishDocument")
    public BackFrontMessage getSelfPublishDocument(){
        return documentService.getSelfPublishDocument();
    }

    @ApiOperation("将审查未通过的设置成草稿箱")
    @PutMapping("/putDocumentNotPassIntoDraftbox")
    public BackFrontMessage putDocumentNotPassIntoDraftbox(int sendfileId) {
        return documentService.putDocumentNotPassIntoDraftbox(sendfileId);
    }

    //复用

    @ApiOperation("在新建拟稿界面修改发文")
    @PutMapping("/a/document")
    public BackFrontMessage aupdateDocument(@RequestParam Integer sendfileId, @RequestParam @Size(max = 20) String subject, @RequestParam @Size(max = 20) String type,@RequestParam String target, @RequestParam @Size(max = 20) String title, @RequestParam String content, @RequestParam @Size(max = 100) String remark, @RequestParam Integer censorId, @RequestParam @Size(max = 20) String urgent, @RequestParam Integer isPublic, @RequestParam(required = false) List<Integer> accessEmployeeIdList) {
        if (accessEmployeeIdList == null) {
            accessEmployeeIdList = new ArrayList<>();
        }
        return documentService.updateUncheckDocument(sendfileId, type, target,subject, title, content, remark, censorId, urgent, isPublic, accessEmployeeIdList);
    }

    @ApiOperation("在新建拟稿界面获取所有审查人")
    @GetMapping("/a/allCensor")
    public BackFrontMessage agetAllCensor(){
        return documentService.getAllCensors();
    }

    @ApiOperation("在新建拟稿界面上传发文附件")
    @PostMapping("/a/DocumentAnnex")
    public BackFrontMessage auploadDocumentAnnex(@RequestParam Integer sendfileId, @RequestParam MultipartFile multipartFile){
        return documentService.uploadDocumentAnnex(sendfileId, multipartFile);
    }

    @ApiOperation("在新建拟稿界面删除发文附件")
    @DeleteMapping("/a/DocumentAnnex")
    public BackFrontMessage bdeleteDocumentAnnex(@RequestParam Integer sendfileId, @RequestParam @NotNull String annexUrl) {
        return documentService.deleteDocumentAnnex(sendfileId, annexUrl);
    }

    @ApiOperation("在拟稿审核界面获取发文详情")
    @GetMapping("/b/document")
    public BackFrontMessage bgetDocumentDetail(@RequestParam Integer sendfileId){
        return documentService.getDocumentDetail(sendfileId);
    }




}
