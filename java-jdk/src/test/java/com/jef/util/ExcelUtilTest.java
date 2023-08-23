package com.jef.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jef.constant.BasicConstant;
import com.jef.constant.ExcelConstants;
import com.jef.entity.User;
import org.junit.Test;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jef
 * @date 2018/11/2 19:13
 */
public class ExcelUtilTest {

    @Test
    public void exportTest() throws Exception {
        ExportExcelUtil<User> util = new ExportExcelUtil<User>();
        // 准备数据
        List<User> list = new ArrayList<>();
        list.add(new User(BasicConstant.USER_NAME, "18390220001",1, new Date()));
        String[] columnNames = { "姓名", "手机号", "年龄", "创建时间" };
        String[] fields = {"name", "phone", "age", "createTime"};
        util.exportExcel2007("用户导出", columnNames, fields, list, new FileOutputStream("E:/test.xls"), ExportExcelUtil.EXCEL_FILE_2003);
    }

    @Test
    public void exportTestTwo() throws Exception {
        ExportExcelUtil<User> util = new ExportExcelUtil<User>();
        // 准备数据
        List<User> list = new ArrayList<>();
        list.add(new User(BasicConstant.USER_NAME, "18390220001", 1, new Date()));
        String[] columnNames = {"姓名", "手机号", "年龄", "创建时间"};
        String[] fields = {"name", "phone", "age", "createTime"};
        List<Map<String, Object>> usersMap = PropertyUtil.transToMap(fields, list);
        util.exportExcel2007WithMap("用户导出", columnNames, fields, usersMap, new FileOutputStream("E:/test.xls"), ExportExcelUtil.EXCEL_FILE_2003, new HashMap<String, String>());
    }

    @Test
    public void exportTestThree() throws Exception {
        ExportExcelUtil<User> util = new ExportExcelUtil<User>();
        // 准备数据
        String content = ExcelConstants.LIST_CONTENT;
        JSONArray dataArray = JSONObject.parseArray(content);
        List<Map<String, Object>> dataMap = new ArrayList<>();
        for (int i = 0; i < dataArray.size(); i++) {
            dataMap.add((Map<String, Object>) dataArray.get(i));
        }
        Map<String, String> mapParams = new HashMap<>();

        mapParams.put("riskType11", "赌博");
        mapParams.put("riskType13", "疑似洗钱");
        mapParams.put("riskType01", "境内移机");
        mapParams.put("riskType03", "境外移机");
        mapParams.put("riskType14", "欺诈（盗刷）");
        mapParams.put("riskType16", "欺诈（套现）");
        mapParams.put("riskType17", "欺诈（商户合谋）");
        mapParams.put("riskType09", "欺诈（账户盗用）");
        mapParams.put("riskType10", "欺诈（其他）");
        mapParams.put("riskType02", "大额");
        mapParams.put("riskType04", "跨地区交易");
        mapParams.put("riskType05", "小微磁条卡交易");
        mapParams.put("riskType06", "夜间交易");
        mapParams.put("riskType07", "风险地区交易");
        mapParams.put("riskType08", "返回码统计");
        mapParams.put("riskType12", "赌博/洗单");
        mapParams.put("riskType15", "赌博/线上交易");
        mapParams.put("riskType21", "调单");
        mapParams.put("riskType22", "司法调查");
        mapParams.put("riskType23", "外部可疑交易");
        mapParams.put("riskType24", "银联延迟清算");
        mapParams.put("riskType99", "其他");


        mapParams.put("routeRelation01", "交易地区码");
        mapParams.put("routeRelation02", "商户");
        mapParams.put("routeRelation", "通用");

        mapParams.put("eventType01", "进件");
        mapParams.put("eventType02", "交易");
        mapParams.put("eventType03", "提现");
        mapParams.put("eventType04", "代付");
        mapParams.put("eventType05", "业务变更");
        mapParams.put("eventType06", "代理商进件");
        mapParams.put("eventType07", "渠道风险事件");

        mapParams.put("ruleType01", "拦截");
        mapParams.put("ruleType03", "实时分析");
        mapParams.put("ruleType06", "离线分析");

        mapParams.put("productposp", "大POS");
        mapParams.put("productmpos", "立刷");
        mapParams.put("productcommon", "通用");

        mapParams.put("busiTypecommon", "通用");
        mapParams.put("busiType1001", "POS+");
        mapParams.put("busiType8001", "外接码付");
        mapParams.put("busiType2000", "小POS");

        String[] columnNames = {"规则编码",
                "规则类型", "风险类型", "参数", "规则描述",
                "事件类型",
                "产品类型",
                "业务类型"};
        String[] fields = {"ruleCode", "ruleType", "riskType", "param", "ruleDesc", "eventType", "product", "busiType"};
        util.exportExcel2007WithMap("规则导出", columnNames, fields, dataMap, new FileOutputStream("E:/test.xls"), ExportExcelUtil.EXCEL_FILE_2003, mapParams);
    }
    

}
