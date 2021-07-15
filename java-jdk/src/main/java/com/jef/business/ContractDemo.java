package com.jef.business;

import com.jef.entity.RentContractVo;
import com.jef.util.LogicUtils;

import java.math.BigDecimal;

/**
 * @author Jef
 * @date 2018/9/28 17:20
 */
public class ContractDemo {
    public static void main(String[] args) {

    }

    public static void test() {
        RentContractVo rent  = new RentContractVo();
        rent.setIsBreach(1);
        rent.setBillingCycle(0);
        rent.setBreachDay(2);
        rent.setCalStandard(new BigDecimal(3.00));
        rent.setFreeType(0);
        rent.setImmunity(2);


        RentContractVo vo = new RentContractVo();
        vo.setIsBreach(1);
        vo.setBillingCycle(2);
        vo.setBreachDay(2);
        vo.setCalStandard(new BigDecimal(4.00));
        vo.setFreeType(0);
        vo.setImmunity(2);
        System.out.println(appendContractBreach(rent, vo, rent));
    }

    public static String appendContractBreach(RentContractVo rent, RentContractVo vo, RentContractVo rentBreachVo) {
        StringBuilder sb = new StringBuilder();
        if(rent.getIsBreach()!=vo.getIsBreach()){
            if(vo.getIsBreach()==1){
                sb.append("不计算违约金").append("→").append("计算违约金；");
                // 新增
                sb.append("计算周期按").append(getBillingCycleShow(vo.getBillingCycle())).append(",");
                String unit = "%";
                if (vo.getBillingCycle() != 0) {
                    unit = "元";
                }
                // 新增
                sb.append("在应收日后").append(vo.getBreachDay()).append("天开始计算违约金,")
                        .append("计算标准按").append(vo.getCalStandard().toString()).append(unit).append(",豁免按");
                if(vo.getFreeType()==0){
                    sb.append("天(").append(vo.getImmunity()).append("天);");
                }else{
                    sb.append("月;");
                }
            }else{
                sb.append("计算违约金").append("→").append("不计算违约金；");
            }
        }else if(rent.getIsBreach()==1){
            // 新增
            if(rentBreachVo.getBillingCycle()!=vo.getBillingCycle()) {
                sb.append("计算周期按").append(getBillingCycleShow(rent.getBillingCycle()))
                        .append("→")
                        .append(getBillingCycleShow(vo.getBillingCycle())).append(";");
            }

            // 新增
            if(rentBreachVo.getBreachDay()!=vo.getBreachDay()){
                sb.append("在应收日后");
                sb.append(rentBreachVo.getBreachDay()).append("天开始计算违约金");
                sb.append("→");
                sb.append(vo.getBreachDay()).append("天开始计算违约金;");
            }
            String unitBefore = "%";
            if (rentBreachVo.getBillingCycle() != 0) {
                unitBefore = "元";
            }
            String unitAfter = "%";
            if (vo.getBillingCycle() != 0) {
                unitAfter = "元";
            }
            if(rentBreachVo.getCalStandard().compareTo(vo.getCalStandard())!=0){
                sb.append("计算标准按");
                sb.append(rentBreachVo.getCalStandard()).append(unitBefore);
                sb.append("→");
                sb.append(vo.getCalStandard()).append(unitAfter).append(";");
            }
            if(rentBreachVo.getFreeType()!=vo.getFreeType()){
                sb.append("豁免按");
                if(vo.getFreeType()==0){
                    sb.append("月→天(").append(vo.getImmunity()).append("天);");
                }else{
                    sb.append("天→月;");
                }
            }
            if(rentBreachVo.getFreeType() == 0 && vo.getFreeType() == 0 && rentBreachVo.getImmunity() != vo.getImmunity()){
                sb.append("豁免天数按").append(rentBreachVo.getImmunity()).append("天→").append(vo.getImmunity()).append("天;");
            }
        }
        return sb.toString();
    }

    /**
     * 违约金计算周期中文
     * @author Jef
     * @date 20180813
     */
    public static String getBillingCycleShow(Integer billingCycle) {
        if (LogicUtils.isNull(billingCycle)) {
            return "";
        }
        String billingCycleStr = "";
        if (billingCycle == 0) {
            billingCycleStr = "日";
        } else if (billingCycle == 1) {
            billingCycleStr = "月";
        } if (billingCycle == 2) {
            billingCycleStr = "年";
        }
        return billingCycleStr;
    }
}
