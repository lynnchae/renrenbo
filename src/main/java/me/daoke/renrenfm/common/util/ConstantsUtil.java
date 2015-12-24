package me.daoke.renrenfm.common.util;

/**
 * 返回结果的常量标志服
 * @author zhuosh
 * @time 20104/4/7
 */
public class ConstantsUtil {

    /**
     * Http状态码
     */
    public class HttpStatusCode {
        //成功
        public static final int OK = 200;

    }

    /**
     *  成功code和描述
      */
    public static final String ERRORCODE_OK = "0";

    /***
     * 失败的Code的描述
     */
    public static final String ERRORCODE_FAIL = "1";

    /*系统配置*/
    public static final String SYSCONFIG_IS_ONCHECK = "isOnCheck";

    public static String appKey = "616515395";
    public static String secret = "B22DC8437E554347729108C66B7BB9A6699F81B1";

    public static String iOSppKey = "3987311677";
    public static String iOSSecret = "2F0BAD21824948423AC6E49CF91C845619E9BD19";

    public static String getAppKey(String appKey) {
        if ("iOS".equalsIgnoreCase(appKey)) {
            return ConstantsUtil.iOSppKey;
        } else if ("Android".equalsIgnoreCase(appKey)) {
            return ConstantsUtil.appKey;
        } else {
            return ConstantsUtil.appKey;
        }
    }

    public static String getSecret(String appKey) {
        if ("iOS".equalsIgnoreCase(appKey)) {
            return ConstantsUtil.iOSSecret;
        } else if ("Android".equalsIgnoreCase(appKey)) {
            return ConstantsUtil.secret;
        } else {
            return ConstantsUtil.secret;
        }
    }

}
