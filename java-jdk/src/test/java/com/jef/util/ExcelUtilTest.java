package com.jef.util;

import com.jef.constant.BasicConstant;
import com.jef.entity.User;
import com.jef.poi.excel.ExcelUtil;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
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
        List<User> users = PropertyUtil.transToMap(fields, list);
        util.exportExcel2007("用户导出", columnNames, users, new FileOutputStream("E:/test.xls"), ExportExcelUtil.EXCEL_FILE_2003);
    }

    @Test
    public void twoTest() throws Exception {
        ExportExcelUtil<List<String>> util = new ExportExcelUtil<>();
        List<List<String>> lists = Lists.newArrayList();
        List<String> list = Lists.newArrayList();
        list.add(BasicConstant.USER_NAME);
        list.add("18390220001");
        list.add("1");
        list.add("2018-10-27");
        lists.add(list);
        String[] columnNames = {"姓名", "手机号", "年龄", "创建时间"};
        util.exportExcel2007("用户导出", columnNames, lists, new FileOutputStream("E:/test.xls"), ExportExcelUtil.EXCEL_FILE_2003);
    }

    @Test
    public void testGetExcelInsertSQL() throws IOException {
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("男", "0");
        mapParams.put("女", "1");
        ExcelUtil.getExcelInsertSQL("E:/download/sap_customer/sap_customer.xlsx", "t_sap_pushcustomerdata",
                "FID, FECID, FNumber, FName, FLinkPhone, FTaxNumxl, FAddress, FSimpleName, FEmail, FSex", "testECID", mapParams);
    }


}
