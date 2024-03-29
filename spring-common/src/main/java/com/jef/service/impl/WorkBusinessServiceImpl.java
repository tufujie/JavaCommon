package com.jef.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.jef.dao.IWorkBusinessDao;
import com.jef.service.IWorkBusinessService;
import com.jef.util.ExcelUtil;
import com.jef.util.REIDIdentifier;
import com.jef.util.StringUtils;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 用户服务
 *
 * @author Jef
 * @create 2018/5/15 19:21
 */
@Service(value = "userService")
public class WorkBusinessServiceImpl implements IWorkBusinessService {
    @Autowired
    private IWorkBusinessDao workBusinessDao;


    @Override
    public void getUserForHandler() {
        List<Map<String, Object>> userMapList = workBusinessDao.getAllUser();
        System.out.println("工作业务：取到的第一个用户名" + userMapList.get(0).get("name"));
    }

    /**
     * 获取excel形成的SQL
     *
     * @author Jef
     * @date 2022/1/6
     */
    @Override
    public void getExcelInsertSQL(String fileUrl, String tableName, String columnNames, String ecID, Map<String, String> mapParams) throws Exception {
        // 创建Excel，读取文件内容
        File file = new File(fileUrl);
        XSSFWorkbook workbook = new XSSFWorkbook(FileUtils.openInputStream(file));
        // 两种方式读取工作表
        // Sheet sheet=workbook.getSheet("Sheet0");
        Sheet sheet = workbook.getSheetAt(0);
        //获取sheet中最后一行行号
        int lastRowNum = sheet.getLastRowNum();
        StringBuilder sb = new StringBuilder();
        sb.append("insert into ").append(tableName);

        sb.append("(").append(columnNames).append(") values\n");
        for (int i = 1; i <= lastRowNum; i++) {
            Row row = sheet.getRow(i);
            //获取当前行最后单元格列号
            int lastCellNum = row.getLastCellNum();
            String tempStr = "('" + REIDIdentifier.randomEOID() + "',";
            if (StringUtils.isNotEmpty(ecID)) {
                tempStr += "'" + ecID + "',";
            }
            for (int j = 0; j < lastCellNum; j++) {
                Cell cell = row.getCell(j);
                String value = ExcelUtil.getValueFromCell(cell);
                if (mapParams.containsKey(value)) {
                    value = mapParams.get(value);
                }
                tempStr += "'" + value + "'";
                if (j < lastCellNum - 1) {
                    tempStr += ",";
                }
            }
            sb.append(tempStr).append(")");
            if (i < lastRowNum) {
                sb.append(",");
            } else {
                sb.append(";");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    @Override
    public void getUpdateContractTypeSQL() throws Exception {
        List<Map<String, Object>> contractList = workBusinessDao.getContract();
        List<Map<String, Object>> contractTypeList = workBusinessDao.getContractType();
        List<Map<String, Object>> disbursementTypeList = workBusinessDao.getDisbursementType();
        // 创建Excel，读取文件内容
        File file = new File("D:/download/lushangcontracttype.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(FileUtils.openInputStream(file));
        // 两种方式读取工作表
        // Sheet sheet=workbook.getSheet("Sheet0");
        Sheet sheet = workbook.getSheetAt(0);
        //获取sheet中最后一行行号
        int lastRowNum = sheet.getLastRowNum();
        // 合并过单元格的字段
        String projectName = "", contractTypeDisbursementType = "";
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= lastRowNum; i++) {
            String contractTypeName = "", disbursementType = "", contractNumber = "";
            Row row = sheet.getRow(i);
            //获取当前行最后单元格列号
            int lastCellNum = row.getLastCellNum();
            for (int j = 0; j < lastCellNum; j++) {
                Cell cell = row.getCell(j);
                String value = ExcelUtil.getValueFromCell(cell);
                if (j == 0) {
                    if (StringUtils.isNotEmpty(value)) {
                        projectName = value;
                    }
                } else if (j == 1) {
                    if (StringUtils.isNotEmpty(value)) {
                        contractTypeDisbursementType = value;
                    }
                    List<String> contractTypeDisbursementTypeList = StringUtils.getListFromString(contractTypeDisbursementType, "-");
                    contractTypeName = contractTypeDisbursementTypeList.get(0);
                    if (contractTypeDisbursementTypeList.size() == 2) {
                        disbursementType = contractTypeDisbursementTypeList.get(1);
                    }
                } else if (j == 2) {
                    contractNumber = value;
                }
            }
            String finalProjectName = projectName;
            String finalContractNumber = contractNumber;
            List<Map<String, Object>> pickContractList = contractList.stream().filter(obj -> finalProjectName.equals(obj.get("projectName"))
                    && finalContractNumber.equals(obj.get("number"))).collect(Collectors.toList());
            String finalContractTypeName = contractTypeName;
            List<Map<String, Object>> pickContractTypeList = contractTypeList.stream().filter(obj -> finalProjectName.equals(obj.get("projectName"))
                    && finalContractTypeName.equals(obj.get("name"))).collect(Collectors.toList());
            if (pickContractTypeList.isEmpty()) {
                contractTypeName = contractTypeName.replace("合同", "");
                String finalContractTypeName1 = contractTypeName;
                pickContractTypeList = contractTypeList.stream().filter(obj -> finalProjectName.equals(obj.get("projectName"))
                        && finalContractTypeName1.equals(obj.get("name"))).collect(Collectors.toList());
            }
            if (pickContractList.isEmpty()) {
                throw new Exception("未找到合同数据，合同编码为：" + contractNumber + "，请检查");
            }
            if (pickContractTypeList.isEmpty()) {
                throw new Exception("未找到合同类型数据，合同编码为：" + contractNumber + "，请检查");
            }
            List<Map<String, Object>> pickDisbursementTypeList = Lists.newArrayList();
            if (StringUtils.isNotEmpty(disbursementType)) {
                String finalDisbursementType = disbursementType;
                pickDisbursementTypeList = disbursementTypeList.stream().filter(obj -> finalProjectName.equals(obj.get("projectName"))
                        && finalDisbursementType.equals(obj.get("name"))).collect(Collectors.toList());
                if (pickDisbursementTypeList.isEmpty()) {
                    throw new Exception("未找到支出合同类型数据，合同编码为：" + contractNumber + "，请检查");
                }
            }
            for (Map<String, Object> contractMap : pickContractList) {
                Map<String, Object> contractTypeMap = pickContractTypeList.get(0);
                String typeID = (String) contractTypeMap.get("id"), contractID = (String) contractMap.get("id");
                sb.append("update t_rt_rentcontract set FTypeID ='" + typeID + "' where FID = '" + contractID + "' and FIsDelete = 0;\n");
                if (StringUtils.isNotEmpty(disbursementType)) {
                    Map<String, Object> disbursementTypeMap = pickDisbursementTypeList.get(0);
                    String disbursementTypeID = (String) disbursementTypeMap.get("id");
                    sb.append("update t_rt_rentcontractextention set FDisbursementTypeId ='" + disbursementTypeID + "' where FRentContractID = '" + contractID + "' and FIsDelete = 0;\n");
                }
            }
        }
        System.out.println(sb);
    }

    @Override
    public void testGetUpdateRoomBuildingTypeSQL() throws Exception {
        List<Map<String, Object>> roomList = workBusinessDao.getRoom();
        List<Map<String, Object>> buidlingList = workBusinessDao.getBuidling();
        List<Map<String, Object>> buildingUnitList = workBusinessDao.getBuildingUnit();
        List<Map<String, Object>> floorList = workBusinessDao.getFloor();
        // 创建Excel，读取文件内容
        File file = new File("D:/download/roomBuildingUpdate.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(FileUtils.openInputStream(file));
        // 两种方式读取工作表
        // Sheet sheet=workbook.getSheet("Sheet0");
        Sheet sheet = workbook.getSheetAt(0);
        //获取sheet中最后一行行号
        int lastRowNum = sheet.getLastRowNum();
        // 合并过单元格的字段
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= lastRowNum; i++) {
            String roomID = "", buidlingName = "", buldUnitName = "", floorName = "", number = "";
            Row row = sheet.getRow(i);
            //获取当前行最后单元格列号
            int lastCellNum = row.getLastCellNum();
            for (int j = 0; j < lastCellNum; j++) {
                Cell cell = row.getCell(j);
                String value = ExcelUtil.getValueFromCell(cell);
                if (j == 0) {
                    roomID = value;
                } else if (j == 1) {
                    buidlingName = value;
                } else if (j == 2) {
                    buldUnitName = value;
                } else if (j == 3) {
                    floorName = value;
                } else if (j == 4) {
                    number = value;
                }
            }
            String finalRoomID = roomID;
            Map<String, Object> room = roomList.stream().filter(obj -> obj.get("id").equals(finalRoomID)).collect(Collectors.toList()).get(0);
            String finalBuidlingName = buidlingName;
            Map<String, Object> buidling = buidlingList.stream().filter(obj -> obj.get("name").equals(finalBuidlingName)).collect(Collectors.toList()).get(0);
            String buildingID = (String) buidling.get("id");
            String finalBuldUnitName = buldUnitName;
            Map<String, Object> buidUnit = buildingUnitList.stream().filter(obj -> obj.get("name").equals(finalBuldUnitName) && buildingID.equals(obj.get("buildingID"))).collect(Collectors.toList()).get(0);
            String buildUnitID = (String) buidUnit.get("id");
            String finalFloorName = floorName;
            List<Map<String, Object>> floorListTemp = floorList.stream().filter(obj -> obj.get("name").equals(finalFloorName) && buildingID.equals(obj.get("buildingID"))).collect(Collectors.toList());
            String floorID = "";
            if (floorListTemp.size() > 0) {
                floorID = (String) floorListTemp.get(0).get("id");
            } else {
                System.out.println(roomID + "楼层异常");
            }
            sb.append("update t_pc_room set FNumber = '" + number + "', FBuildingID = '" + buildingID + "', FBuildingName = '" + buidlingName + "', FBuildUnitID = '" + buildUnitID + "', FBuildUnitName = '" + buldUnitName + "', FFloorID ='" + floorID + "', FFloor = '" + floorName + "' where FID = '" + roomID + "' and FIsDelete = 0;\n");
        }
        System.out.println(sb);
    }

    @Override
    public void testGetUpdateDemanPoolSQL() throws Exception {
        List<Map<String, Object>> demandPoolList = workBusinessDao.getDemandPool();
        // 创建Excel，读取文件内容
        File file = new File("D:/download/demandPool.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(FileUtils.openInputStream(file));
        // 两种方式读取工作表
        // Sheet sheet=workbook.getSheet("Sheet0");
        Sheet sheet = workbook.getSheetAt(0);
        //获取sheet中最后一行行号
        int lastRowNum = sheet.getLastRowNum();
        // 合并过单元格的字段
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= lastRowNum; i++) {
            Row row = sheet.getRow(i);
            Cell cell = row.getCell(0);
            String number = ExcelUtil.getValueFromCell(cell);
            cell = row.getCell(1);
            String dateField3 = ExcelUtil.getValueFromCell(cell);
            String finalNumber = number;
            List<Map<String, Object>> demandPoolPick = demandPoolList.stream().filter(obj -> finalNumber.equals(obj.get("number"))).collect(Collectors.toList());
            if (demandPoolPick.size() > 0) {
                String id = (String) demandPoolPick.get(0).get("id");
                sb.append("update t_inv_demandpoolextension2 set FDateField3 = '" + dateField3 + "' where FID = '" + id + "' and FIsDelete = 0;\n");
            } else {
                System.out.println("客户异常" + number);
            }
        }
        System.out.println(sb);
    }

    /**
     * 获取excel形成的SQL
     *
     * @author Jef
     * @date 2022/1/6
     */
    @Override
    public void getExcelInsertSQLOfBranchBank(String fileUrl, String tableName, String columnNames) throws Exception {
        // 创建Excel，读取文件内容
        File file = new File(fileUrl);
        XSSFWorkbook workbook = new XSSFWorkbook(FileUtils.openInputStream(file));
        // 两种方式读取工作表
        // Sheet sheet=workbook.getSheet("Sheet0");
        Sheet sheet = workbook.getSheetAt(0);
        //获取sheet中最后一行行号
        int lastRowNum = sheet.getLastRowNum();
        StringBuilder sb = new StringBuilder();
        List<Map<String, Object>> rootBankList = workBusinessDao.getAllRootBank();
        int maxLine = 10000;
        File fileDown = new File("E://download" + UUID.randomUUID() + ".sql");
        FileWriter fWriter = null;
        fileDown.createNewFile();
        fWriter = new FileWriter(fileDown);
        int notPickCount = 0;
        for (int i = 1; i <= lastRowNum; i++) {
            if ((i - 1) % maxLine == 0) {
                sb.append("insert into ").append(tableName);
                sb.append("(").append(columnNames).append(") values\n");
            }
            String tempStr = "(";
            Row row = sheet.getRow(i);
            //获取当前行最后单元格列号
            int lastCellNum = row.getLastCellNum();

            Cell cellOne = row.getCell(0);
            Cell cellTwo = row.getCell(1);
            Cell cellThree = row.getCell(2);
            String branchBankCode = ExcelUtil.getValueFromCell(cellOne);
            String rootBankCode = ExcelUtil.getValueFromCell(cellTwo);
            String branchBankName = ExcelUtil.getValueFromCell(cellThree);
            Map<String, String> bankNameMap = new HashMap<>();
            bankNameMap.put("建行", "建设银行");
            bankNameMap.put("招行", "招商银行");
            bankNameMap.put("工行", "工商银行");
            bankNameMap.put("花旗银行（中国）有限公司", "花旗银行有限公司");
            bankNameMap.put("中国农业发展银行", "农业银行");
            String finalRootBankCode = "0" + rootBankCode;
            String branchBankNameAll = branchBankName;
            for (String key : bankNameMap.keySet()) {
                if (branchBankName.contains(key)) {
                    branchBankNameAll = branchBankName.replace(key, bankNameMap.get(key));
                    break;
                }
            }
            String bankName = branchBankNameAll.substring(0, branchBankNameAll.indexOf("银行") + 2);
            List<Map<String, Object>> pickBankList = rootBankList.stream().filter(obj -> bankName.contains(obj.get("bank_name").toString())).collect(Collectors.toList());
            if (pickBankList.isEmpty()) {
                notPickCount++;
                System.out.println("未找到银行编码，银行编码为：" + finalRootBankCode + "，分行名称为：" + branchBankName + "，请检查");
            } else {
                finalRootBankCode = (String) pickBankList.get(0).get("bank_code");
            }
            tempStr += "'" + branchBankCode + "',";
            tempStr += "'" + finalRootBankCode + "',";
            tempStr += "'" + branchBankName + "'";
            sb.append(tempStr).append(")");
            if (i % maxLine == 0 || i >= lastRowNum) {
                sb.append(";");
            } else {
                sb.append(",");
            }
            sb.append("\n");
        }
        fWriter.append(sb);
        fWriter.flush();
        System.out.println("没有匹配的银行数为：" + notPickCount);
    }

    @Override
    public void getExcelToJson(String fileUrl, int levelType) throws Exception {
        // 创建Excel，读取文件内容
        File file = new File(fileUrl);
        XSSFWorkbook workbook = new XSSFWorkbook(FileUtils.openInputStream(file));
        // 两种方式读取工作表
        // Sheet sheet=workbook.getSheet("Sheet0");
        Sheet sheet = workbook.getSheetAt(0);
        //获取sheet中最后一行行号
        int lastRowNum = sheet.getLastRowNum();
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        int maxLine = 10000;
        File fileDown = new File("E://download" + UUID.randomUUID() + ".sql");
        FileWriter fWriter = null;
        fileDown.createNewFile();
        fWriter = new FileWriter(fileDown);
        boolean parent = false;
        Map<String, Object> map = new HashMap<>();
        String parentKey = "";
        // 第1行为表头
        // 支持第1列和第2列为字段值
        // 表头：字段 字段名 长度 必填 说明
        for (int i = 1; i <= lastRowNum; i++) {
            Row row = sheet.getRow(i);
            Cell cellKey = row.getCell(0);
            Cell cellSub = row.getCell(0);
            Cell cellKeyName = row.getCell(1);
            Cell cellType = row.getCell(2);
            Cell cellComment = row.getCell(4);
            if (levelType == 2) {
                // 支持字段中有子字段，第1列和第2列是字段 后面是 字段名 长度 必填 说明
                cellSub = row.getCell(1);
                cellKeyName = row.getCell(2);
                cellType = row.getCell(3);
                cellComment = row.getCell(5);
            }
            String key = ExcelUtil.getValueFromCell(cellKey);
            String subkey = ExcelUtil.getValueFromCell(cellSub);
            String keyName = ExcelUtil.getValueFromCell(cellKeyName);
            String type = ExcelUtil.getValueFromCell(cellType);
            String comment = ExcelUtil.getValueFromCell(cellComment);
            /*comment = comment.replaceAll(" ", "");
            comment = comment.replaceAll("/", "");
            comment = comment.replaceAll(":", "");*/
            if (type.contains("string") || type.contains("String")) {
            }
            String value = keyName + comment;
            boolean thisIsParent = type == null || "".equals(type);
            boolean commonAppend = false;
            if (parent) {
                map.put(subkey, value);
            } else if (!thisIsParent) {
                commonAppend = true;
            }
            if ((key != null && !"".equals(key) && type != null && !"".equals(type)) || i == lastRowNum) {
                parent = false;
                if (!map.isEmpty()) {
                    key = parentKey;
                    value = JSONObject.toJSONString(map);
                    commonAppend = true;
                }
            }
            if (commonAppend) {
                sb.append(key).append(":'").append(value).append("',").append("\n");
            }
            if (thisIsParent) {
                parent = true;
                parentKey = key;
                map = new HashMap<>();
            }
        }
        sb.append("}");
        fWriter.append(sb);
        fWriter.flush();
    }

}
