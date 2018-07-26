package com.gzf.util;

/**
 * Created by yzq on 2018/4/2.
 * 租金计算
 */
public class PayRentUtil {

    public PayRentUtil() {

    }
    //月租金

    /**
     *
     * @param areaUse
     * @param securityNum
     * @param securityArea
     * @param securityOutRatio
     * @param securityInRatio
     * @return
     */
    public Double monthStarandRent(double areaUse,int securityNum,
                                   double securityArea,double securityOutRatio,
                                   double securityInRatio){
        double starandRent = 0.0;
        starandRent = (areaUse-(securityNum*securityArea))*securityOutRatio+(securityNum*securityArea)*securityInRatio;
        return CommonUtil.formatDouble2(starandRent);
    }

}
