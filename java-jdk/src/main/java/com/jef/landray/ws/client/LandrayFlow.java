package com.jef.landray.ws.client;

import com.jef.entity.FileVo;
import com.jef.entity.WorkFlowVo;
import com.jef.landray.flow.AttachmentForm;
import com.jef.landray.flow.IKmReviewWebserviceService;
import com.jef.landray.flow.IKmReviewWebserviceServiceProxy;
import com.jef.landray.flow.IKmReviewWebserviceServiceServiceLocator;
import com.jef.landray.flow.KmReviewParamterForm;
import com.jef.util.DateTimeUtil;
import com.jef.util.LogicUtils;
import com.jef.util.StringUtils;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author Administrator
 * @date 2022/6/14
 */
public class LandrayFlow {

    private static final Logger logger = LoggerFactory.getLogger(LandrayFlow.class);

    private static final String APP_NAME = "kdWojiacloud";
    private static final String APP_FILE_KEY = "wjy_rentfile_key";

    public static void main(String[] args) throws Exception {

        IKmReviewWebserviceServiceServiceLocator krLocator = new IKmReviewWebserviceServiceServiceLocator();
        KmReviewParamterForm form = new KmReviewParamterForm();
        // 文档模板id
        form.setFdTemplateId("131eb0cfd7db55e6980e9ce4985a1387");
        // 文档标题
        form.setDocSubject("物料采购申请单");
// 流程发起人
        form.setDocCreator("{\"PersonNo\": \"00012\"}");
        // 文档关键字
        form.setFdKeyword("[\"物料\", \"采购\"]");
        // 流程表单
        String formValues = "{\"fd_2eddbf023c8292\":\"张三\", \"fd_2edd2f83f68242\":\"咨询部\", \"fd_2edd2fa69f6fc6\":\"\", \"fd_2eddbf09f9bc96\":\"2011-10-26\", \"fd_2edd2fb18e7f90\":{\"fd_2edd2fb18e7f90.fd_2eddbef4da4688\":[\"555555\",\"777777\"], \"fd_2edd2fb18e7f90.fd_2edd2fc8001062\":[\"444444\",\"666666\"], \"fd_2edd2fb18e7f90.fdId\":[\"1332472122898ac618f3e22460cab595\",\"13324721228b50c184d82c44ceca5301\"]}}";
        form.setFormValues(formValues);
        // 流程参数
        String flowParam = "{auditNode:\"请审核\", futureNodeId:\"N7\", changeNodeHandlers:[\"N7:1183b0b84ee4f581bba001c47a78b2d9;131d019fbac792eab0f0a684c8a8d0ec\"]}";
        form.setFlowParam(flowParam);
//		List<AttachmentForm> attForms = createAllAtts();
//		//form.getAttachmentForms().addAll(attForms);
//		form.setAttachmentForms((AttachmentForm[])attForms.toArray());
        String result = krLocator.getIKmReviewWebserviceServicePort().addReview(form);
        System.out.println("result=" + JSONObject.fromObject(result));
        StringBuffer token = new StringBuffer(DateTimeUtil.formatDate(new Date(), DateTimeUtil.DATETIME_FORMAT3))
                .append("testUserName").append(DateTimeUtil.formatDate(DateTimeUtil.addHours(new Date(), 2), DateTimeUtil.DATETIME_FORMAT3))
                .append(UUID.randomUUID().toString().replaceAll("-", ""));

        String landrayUrl = "http://teip.shumyip.com.hk:8091/km/review/km_review_main/kmReviewMain.do?method=view&fdId=" + result
                + "&token=" + token;
        System.out.println("landrayUrl=" + JSONObject.fromObject(landrayUrl));

    }

    public String addReview(WorkFlowVo vo, Map<String, Object> map) throws Exception {
        String appUrl = (String) map.get("appUrl");
        StringBuffer kmUrl = new StringBuffer().append(appUrl).append("/sys/webservice/kmReviewWebserviceService");
        IKmReviewWebserviceService kmSer = new IKmReviewWebserviceServiceProxy(kmUrl.toString());
        KmReviewParamterForm form = createForm(vo, map);
        logger.info("setDocSubject addReview form={}", JSONObject.fromObject(form));
        String responseID = kmSer.addReview(form);
        logger.info("LandrayFlow addReview responseID={}", responseID);
        return responseID;
    }

    /**
     * 创建文档及流程数据
     */
    private KmReviewParamterForm createForm(WorkFlowVo vo, Map<String, Object> map) throws Exception {
        KmReviewParamterForm form = new KmReviewParamterForm();
        // 文档模板id
        form.setFdTemplateId(vo.getFormCodeId());
        // 文档标题
        form.setDocSubject(vo.getTitle());
        // 流程表单
        form.setFormValues(vo.getTabloid());
        // 流程状态(10起草状态，20发起状态)
        form.setDocStatus("20");
        // 流程发起人
        JSONObject perObj = new JSONObject();
        perObj.put("LoginName", vo.getApprover());
        form.setDocCreator(perObj.toString());
        // 文档关键字	，只能通过这个实现字段切换了，不能通过Form对象添加字段的方式
        String fdKeyword = "[\"租赁\", \"合同\", \"";
        fdKeyword += vo.getTitle() + "\"]";
        form.setFdKeyword(fdKeyword);
        // 加入附件前查看一下完整请求信息
        List<FileVo> fileList = (ArrayList<FileVo>) map.get("fileList");
        //手动调流程参数为空字符串会报错 所以这里加上空对象 2019-08-31 janker
        form.setFlowParam(new JSONObject().toString());
        //如果文件列表为空，表示没有附件上传
        if (LogicUtils.isNotNullAndEmpty(fileList)) {
            AttachmentForm[] attForms = new AttachmentForm[fileList.size()];
            for (int i = 0; i < fileList.size(); i++) {
                FileVo file = fileList.get(i);
                if (StringUtils.isEmpty(file.getNumber())) {
                    file.setNumber(file.getCname() + "." + file.getCext());
                }
                attForms[i] = createAtt(file.getNumber(), file.getUrl());
            }
            form.setAttachmentForms(attForms);
        }
        return form;
    }


    /**
     * 创建附件对象
     */
    static AttachmentForm createAtt(String fileName, String fileUrl) throws Exception {
        AttachmentForm attForm = new AttachmentForm();
        attForm.setFdFileName(fileName);
        // 设置附件关键字，表单模式下为附件控件的id
        attForm.setFdKey(APP_FILE_KEY);
        byte[] data = file2bytes(fileUrl);
        attForm.setFdAttachment(data);
        return attForm;
    }

    /**
     * 将文件转换为字节编码
     */
    static byte[] file2bytes(String fileUrl) throws Exception {

        InputStream in = new FileInputStream(fileUrl);
        byte[] data = new byte[in.available()];
        try {
            in.read(data);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
            }
        }
        return data;
    }

    /**
     * 返回退回人处继续审批
     *
     * @param vo
     * @param map
     * @return java.lang.String
     * @author Jef
     * @date 2022/6/7
     */
    public String approveProcess(WorkFlowVo vo, Map<String, Object> map) throws Exception {
        String appUrl = (String) map.get("appUrl");
        StringBuffer kmUrl = new StringBuffer().append(appUrl).append("/sys/webservice/kmReviewWebserviceService");
        IKmReviewWebserviceService kmSer = new IKmReviewWebserviceServiceProxy(kmUrl.toString());
        JSONObject perObj = new JSONObject();
        perObj.put("LoginName", vo.getApprover());
        KmReviewParamterForm form = createForm(vo, map);
        // 继承原来的第三方流程信息
        form.setFdId(vo.getWfID());
        JSONObject flowParamJSONObject = new JSONObject();
        flowParamJSONObject.put("operationType", "drafter_submit");
        flowParamJSONObject.put("auditNote", "继续审批");
        form.setFlowParam(flowParamJSONObject.toString());
        logger.info("setDocSubject approveProcess form={}", JSONObject.fromObject(form));
        String responseID = kmSer.approveProcess(form);
        logger.info("LandrayFlow approveProcess responseID={}", responseID);
        return responseID;
    }

}