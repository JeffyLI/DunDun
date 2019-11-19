package com.jeffy.dundun.cloud.common;




public class Validate {
    /**
     * 参数空校验
     * @param paras 参数
     * @return 存在空值：false，否则：true
     */
    public static boolean paraNullValidate(Object ... paras){
        for(Object para:paras){
            if(para!=null){
                if (null==para.toString()||"".equals(para.toString())) {
                    return false;
                }
            }else {
                return false;
            }
        }
        return true;
    }
}
