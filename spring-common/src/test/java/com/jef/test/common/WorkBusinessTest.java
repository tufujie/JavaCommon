package com.jef.test.common;

import com.jef.common.context.SpringContextHolder;
import com.jef.service.impl.WorkBusinessServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 工作业务处理
 *
 * @author Jef
 * @date 2022/1/15
 */
public class WorkBusinessTest extends BaseTest {
    @Override
    public void init() throws Exception {

    }

    /**
     * 工作业务获取用户信息
     * 模拟service调用
     *
     * @date 2022/01/15
     */
    @Test
    public void testGetUserForHandler() throws Exception {
        initMybatis("mapper/*Mapper.xml");
        TestBeanUtil.addBean(WorkBusinessServiceImpl.class.getSimpleName(), WorkBusinessServiceImpl.class);
        WorkBusinessServiceImpl workBusinessService = SpringContextHolder.getBean(WorkBusinessServiceImpl.class.getSimpleName());

        workBusinessService.getUserForHandler();

    }

    @Test
    public void testGetExcelInsertSQL() throws Exception {
        Map<String, String> mapParams = new HashMap<>();
        mapParams.put("男", "0");
        mapParams.put("女", "1");
        initMybatis("mapper/*Mapper.xml");
        TestBeanUtil.addBean(WorkBusinessServiceImpl.class.getSimpleName(), WorkBusinessServiceImpl.class);
        WorkBusinessServiceImpl workBusinessService = SpringContextHolder.getBean(WorkBusinessServiceImpl.class.getSimpleName());
        workBusinessService.getExcelInsertSQL("E:/download/sap_customer/sap_customer.xlsx", "t_sap_pushcustomerdata",
                "FID, FECID, FNumber, FName, FLinkPhone, FTaxNumxl, FAddress, FSimpleName, FEmail, FSex", "testECID", mapParams);
    }

    /**
     * 工作业务获取用户信息
     * 模拟service调用
     *
     * @date 2022/01/15
     */
    @Test
    public void testGetUpdateContractTypeSQL() throws Exception {
        initMybatis("mapper/*Mapper.xml");
        TestBeanUtil.addBean(WorkBusinessServiceImpl.class.getSimpleName(), WorkBusinessServiceImpl.class);
        WorkBusinessServiceImpl workBusinessService = SpringContextHolder.getBean(WorkBusinessServiceImpl.class.getSimpleName());

        workBusinessService.getUpdateContractTypeSQL();

    }

    /**
     * 工作业务获取用户信息
     * 模拟service调用
     *
     * @date 2022/01/15
     */
    @Test
    public void testGetUpdateRoomBuildingTypeSQL() throws Exception {
        initMybatis("mapper/*Mapper.xml");
        TestBeanUtil.addBean(WorkBusinessServiceImpl.class.getSimpleName(), WorkBusinessServiceImpl.class);
        WorkBusinessServiceImpl workBusinessService = SpringContextHolder.getBean(WorkBusinessServiceImpl.class.getSimpleName());

        workBusinessService.testGetUpdateRoomBuildingTypeSQL();

    }

    /**
     * 工作业务获取用户信息
     * 模拟service调用
     *
     * @date 2022/01/15
     */
    @Test
    public void testGetUpdateDemanPoolSQL() throws Exception {
        initMybatis("mapper/*Mapper.xml");
        TestBeanUtil.addBean(WorkBusinessServiceImpl.class.getSimpleName(), WorkBusinessServiceImpl.class);
        WorkBusinessServiceImpl workBusinessService = SpringContextHolder.getBean(WorkBusinessServiceImpl.class.getSimpleName());

        workBusinessService.testGetUpdateDemanPoolSQL();

    }

    @Test
    public void testGetExcelInsertSQLOfBranchBank() throws Exception {
        Map<String, String> mapParams = new HashMap<>();
        initMybatis("mapper/*Mapper.xml");
        TestBeanUtil.addBean(WorkBusinessServiceImpl.class.getSimpleName(), WorkBusinessServiceImpl.class);
        WorkBusinessServiceImpl workBusinessService = SpringContextHolder.getBean(WorkBusinessServiceImpl.class.getSimpleName());
        workBusinessService.getExcelInsertSQLOfBranchBank("E:/Desktop/branch_bank.xlsx", "branch_bank",
                "bank_code, root_bank_code, branch_bank_name");
    }


}