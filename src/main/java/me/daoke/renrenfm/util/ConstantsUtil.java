package me.daoke.renrenfm.util;

import java.util.HashMap;
import java.util.Map;

public class ConstantsUtil {

    //数据推送
    public static String androidMasterSecret = "d3c381ebe8193a5c74b1a3b7";
    public static String anddroidJpushAppKey = "801b258d66f1f423724c361a";
    public static String iosMasterSecret = "d3c381ebe8193a5c74b1a3b7";
    public static String iosJpushAppKey = "801b258d66f1f423724c361a";


	//public static final Log logger = LogFactory.getLog(UserController.class);
	public static ConstantsUtil obj;
	
	public static String appKey = "1220182788";
	public static String secret = "559D34DBA31C5780B9F059A4BA2210386DED04AC";
	
	public static String iOSppKey = "184269830";
	public static String iOSSecret = "931E498698AB2D9B1D93F419E572D2ACCA981488";

    public static String ACCOUNTSID="aaf98fda4351e36201438a4c7c961049";
    public static String TOKEN="c0bde57aa2a64528938dec0b56eb0410";
    public static String APPID="aaf98f894aec8568014af11c4212025c";

	/*public ConstantsUtil(String string) {
         logger.info(string +"  自动装配");
		obj = this;
	}
	*/

    /**
     * 接单
     */
    public static final String ACCEPT = "accept";

    /**
     * 签单
     */
    public static final String SIGN = "sign";

    /**
     * 取件
     */
    public static final String GET = "get";

    /**
     * 收货
     */
    public static final String RECIEVE = "recieve";

    /**
     * 待接超时
     */
    public static final String ACCEPTOVERTIME = "acceptOverTime";

    /**
     * 发件人取消
     */
    public static final String SENDERCANCEL = "senderCancel";

    /**
     * 车主取消
     */
    public static final String DELIVERCANCEL = "deliverCancel";

    /**
     * 未签发货单
     */
    public static final String SENDWITHOUTSIGN = "sendWithoutSign";

    /**
     * 未签收货单
     */
    public static final String RECIEVEWITHOUTSIGN = "recieveWithoutSign";

    /**
     * 未签收货单
     */
    public static final String CONFIRMREGRETORDER = "confirmRegretOrder";



    /**
     * Http状态码
     */
    public class HttpStatusCode{
        //成功
        public static final int OK =200;

    }


    /**
	 * 键值对 Spring启动的时候注入
	 */

/*	public Map currTypes;

	@SuppressWarnings("rawtypes")
	public Map getCurrTypes() {
		return currTypes;
	}

	@SuppressWarnings("rawtypes")
	public void setCurrTypes(Map currTypes) {
		this.currTypes = currTypes;
	}*/
	
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
	
	/**
	 * 语境内部错误
	 */
	public static final String ERRORCODE_APPKEY_ERROR = "ME01002";
	public static final String ERRORCODE_SING_ERROR = "ME01019";
	public static final String ERRORCODE_INNER_ERROR = "ME18001";
	public static final String ERRORCODE_INNER_JSON_ERROR = "ME18024";
	public static final String ERRORCODE_INNER_PARAMETERS_ERROR = "ME18040";
	
    /**
     * 本系统错误码
     */
	public static final String ERRORCODE_AGENT_ERROR = "ME01001";
	public static final String ERRORCODE_SERVICETYPE_ERROR = "ME01002";
	public static final String ERRORCODE_SIGN_ERROR = "ME01019";
	public static final String ERRORCODE_JSON_ERROR = "ME01006";                                                       
	public static final String ERRORCODE_SERVICE_ERROR = "ME01001";                                                    
	public static final String ERRORCODE_NOTNAME_ERROR = "ME19003";                                                    
	public static final String ERRORCODE_NOUSER_ERROR = "ME19004";                                                     
	public static final String ERRORCODE_CREATE_ORDER_ERROR = "ME19005";                                               
	public static final String ERRORCODE_PARAMETERS_ERROR = "ME01023";                                          
	public static final String ERRORCODE_ACCEPT_ORDER__ERROR = "ME19007";
	public static final String ERRORCODE_PAY_ORDER_ERROR = "ME19008";
	public static final String ERRORCODE_EMPTY_ERROR = "ME19009";
	public static final String ERRORCODE_UPDATE_ORDER_ERROR = "ME19010";
	public static final String ERRORCODE_USER_NAME_EXIST_ERROR = "ME19011";
	public static final String ERRORCODE_USER_NOT_EXIST_ERROR = "ME19012";
	public static final String ERRORCODE_USER_EXIST_ERROR = "ME19013";
    public static final String ERRORCODE_PERMISSIONS_QUOTA_ERROR = "Permissions or quota check failed";
    public static final String ERRORCODE_SUCCESS = "0";
    public static final String ERRORCODE_ACCOUNT_NOTEXISTS = "ME02001";
    public static final String ERRORCODE_ACCOUNT_EXITS = "ME02002";

    public static final String ERRORCODE_VERICODE = "ME22007";
    public static final String RESULT_VERICODE = "The verificationCode is error!";

    public static final String ERRORCODE_OUTTIME = "ME30001";
    public static final String RESULT_OUTTIME = " order out of 24hours ";

    /**
     * 本系统错误说明
     */
    public static final String RESULT_AGENT_ERROR = "agent is errror!";
    public static final String RESULT_SERVICETYPE_ERROR = "servicetype is error!";
    public static final String RESULT_SIGN_ERROR = "sign is error!";
	public static final String RESULT_JSON_ERROR = "Json is error!";
	public static final String RESULT_SERVICE_ERROR = "Service is error!";
	public static final String RESULT_NOTNAME_ERROR = "User's name is null!";
	public static final String RESULT_NOUSER_ERROR = "The user information is empty!";
	public static final String RESULT_CREATE_ORDER_ERROR = "Create order fail!";
	public static final String RESULT_PARAMETERS_ERROR = "Input param is error!";
	public static final String RESULT_ACCEPT_ORDER_ERROR = "The accept order fail!";
	public static final String RESULT_PAY_ORDER_ERROR = "Pay order fail!";
	public static final String RESULT_EMPTY_ERROR = "The content is empty!";
	public static final String RESULT_UPDATE_ORDER_ERROR = "The update order fail!";
	public static final String RESULT_USER_NAME_EXIST_ERROR = "The username has exist!";
	public static final String RESULT_USER_EXIST_ERROR = "The user has exist!";
	public static final String ERRORCODE_RESULT_SERVICE_ERROR = "{\"ERRORCODE\": \"19002\",\"RESULT\": \"Service is error!\"}";
    public static final String RESULT_ACCOUNT_NOTEXISTS = "accountID is not exists";
    public static final String RESULT_ACCOUNT_EXISTS = "user name is already exists";

	/**
	 * 语境内部错误说明
	 */
	public static final String RESULT_APPKEY_ERROR = "appKey error!";
	public static final String RESULT_SING_ERROR = "sign is error!";
	public static final String RESULT_INNER_ERROR = "server is error!";
	public static final String RESULT_INNER_JSON_ERROR = "json is error!";
	public static final String RESULT_INNER_PARAMETERS_ERROR = "parameters is error!";
	
	/**
	 * 返回常量
	 */
	public static final String ERRORCODE  ="ERRORCODE";
	public static final String RESULT  ="RESULT";

    /***
     * 失败的Code的描述
     */
    public static final String ERRORCODE_FAIL = "ME00001";
    public static final String RESULT_FAIL = "fail";


    /***
     * 发货单  账户余额不足
     */
    public static final String ERRORCODE_BALANCE_LOW = "ME00002";
    public static final String RESULT_BALANCE_LOW = "user balance is not enough";

    public static final String ERRORCODE_COMPLETED = "ME00009";
    public static final String RESULT_COMPLETED = "mission completed";

    /**
     * 验证码模板
     */
    public static final String VERIFY_MESSAGE ="帮忙拉的短信验证码为[code]，请在30分钟内输入有效！";

   //系统级错误提示
    public static  Map<String,String> ERRORCODE_BUSINESS_PARAM = new HashMap<String, String>();
    //业务错误提示
    public static  Map<String,String> ERRORCODE_SYSTEM_PARAM = new HashMap<String, String>();

    static {
        ERRORCODE_BUSINESS_PARAM.put("ME01002","不是正确的appKey");
        ERRORCODE_BUSINESS_PARAM.put("ME01006","错误的json格式数据");
        ERRORCODE_BUSINESS_PARAM.put("ME01024","并且需要提交数据");
        ERRORCODE_BUSINESS_PARAM.put("ME01025","请求失败");
        ERRORCODE_BUSINESS_PARAM.put("ME01026","系统正忙");
        ERRORCODE_BUSINESS_PARAM.put("ME18001","参数错误");
        ERRORCODE_BUSINESS_PARAM.put("ME18002","用户名已存在");
        ERRORCODE_BUSINESS_PARAM.put("ME18003","用户从未新浪授权过");
        ERRORCODE_BUSINESS_PARAM.put("ME18004","需要优化设计方式");
        ERRORCODE_BUSINESS_PARAM.put("ME18005","输入的参数不存在");
        ERRORCODE_BUSINESS_PARAM.put("ME18006","IMEI未绑定");
        ERRORCODE_BUSINESS_PARAM.put("ME18007","sina授权令牌已失效");
        ERRORCODE_BUSINESS_PARAM.put("ME18008","没有权限");
        ERRORCODE_BUSINESS_PARAM.put("ME18009","默认配置不存在");
        ERRORCODE_BUSINESS_PARAM.put("ME18010","该第三方账户不存在");
        ERRORCODE_BUSINESS_PARAM.put("ME18011","该第三方账户已存在");
        ERRORCODE_BUSINESS_PARAM.put("ME18012","不是语镜的accountID");
        ERRORCODE_BUSINESS_PARAM.put("ME18013","该用户不能被删除");
        ERRORCODE_BUSINESS_PARAM.put("ME18014","this user hasn't this group");
        ERRORCODE_BUSINESS_PARAM.put("ME18015","改账户没有资金信息");
        ERRORCODE_BUSINESS_PARAM.put("ME18016","没有道客密码");
        ERRORCODE_BUSINESS_PARAM.put("ME18017","余额不足");
        ERRORCODE_BUSINESS_PARAM.put("ME18018","该用户没有余额");
        ERRORCODE_BUSINESS_PARAM.put("ME18030","IMEI已存在");
        ERRORCODE_BUSINESS_PARAM.put("ME18031","IMEI不存在");
        ERRORCODE_BUSINESS_PARAM.put("ME18032","非法IMEI");
        ERRORCODE_BUSINESS_PARAM.put("ME18035","密码不匹配");
        ERRORCODE_BUSINESS_PARAM.put("ME18036","theuserhasn'tpaidthedeposit");
        ERRORCODE_BUSINESS_PARAM.put("ME18037","余额不足");
        ERRORCODE_BUSINESS_PARAM.put("ME18038","还未到提款时间");
        ERRORCODE_BUSINESS_PARAM.put("ME18039","WECODE不存在");
        ERRORCODE_BUSINESS_PARAM.put("ME18040","WECODE已过期");
        ERRORCODE_BUSINESS_PARAM.put("ME18041","用户没有申请退出合同");
        ERRORCODE_BUSINESS_PARAM.put("ME18044","奖励类型不存在");
        ERRORCODE_BUSINESS_PARAM.put("ME18045","无用的奖励类型");
        ERRORCODE_BUSINESS_PARAM.put("ME18046","存储类型不合法");
        ERRORCODE_BUSINESS_PARAM.put("ME18047","无效的存储类型");
        ERRORCODE_BUSINESS_PARAM.put("ME18048","输入存款金额不匹配给定的存款金额");
        ERRORCODE_BUSINESS_PARAM.put("ME18049","the user hast he same rewards type");
        ERRORCODE_BUSINESS_PARAM.put("ME18050","WECODE已失效");
        ERRORCODE_BUSINESS_PARAM.put("ME18051","business ID no texist!");
        ERRORCODE_BUSINESS_PARAM.put("ME18052","分组已存在");
        ERRORCODE_BUSINESS_PARAM.put("ME18053","accountID已存在");
        ERRORCODE_BUSINESS_PARAM.put("ME18054","分组ID不存在");
        ERRORCODE_BUSINESS_PARAM.put("ME18055","无效的分组ID");
        ERRORCODE_BUSINESS_PARAM.put("ME18059","IMEI未绑定");
        ERRORCODE_BUSINESS_PARAM.put("ME18060","accountID is not in me.daoke.renrenfm.service!");
        ERRORCODE_BUSINESS_PARAM.put("ME18061","用户名不存在");
        ERRORCODE_BUSINESS_PARAM.put("ME18063","密码不匹配");
        ERRORCODE_BUSINESS_PARAM.put("ME18064","用已在这个分组");
        ERRORCODE_BUSINESS_PARAM.put("ME18065","该用户没有权限");
        ERRORCODE_BUSINESS_PARAM.put("ME18066","该用户没有权限（权限过期）");
        ERRORCODE_BUSINESS_PARAM.put("ME18067","IMEI已经被使用");
        ERRORCODE_BUSINESS_PARAM.put("ME18068","user mobile hasn't authorization!");
        ERRORCODE_BUSINESS_PARAM.put("ME18069","redirect url don't match!");
        ERRORCODE_BUSINESS_PARAM.put("ME18070","授权码不存在");
        ERRORCODE_BUSINESS_PARAM.put("ME18071","授权码已过期");
        ERRORCODE_BUSINESS_PARAM.put("ME18072","刷新令牌不存在");
        ERRORCODE_BUSINESS_PARAM.put("ME18073","刷新令牌已过期");
        ERRORCODE_BUSINESS_PARAM.put("ME18074","appkey不存在");
        ERRORCODE_BUSINESS_PARAM.put("ME18075","不允许创建分组");
        ERRORCODE_BUSINESS_PARAM.put("ME18076","开发者详情等待审核");
        ERRORCODE_BUSINESS_PARAM.put("ME18078","开发者详情审核通过");
        ERRORCODE_BUSINESS_PARAM.put("ME18079","开发者没有注册");
        ERRORCODE_BUSINESS_PARAM.put("ME18080","网站不存在");
        ERRORCODE_BUSINESS_PARAM.put("ME18081","用户必须在线");
        ERRORCODE_BUSINESS_PARAM.put("ME18083","IMEI不匹配当前appKey");
        ERRORCODE_BUSINESS_PARAM.put("ME18084","app不能使用");
        ERRORCODE_BUSINESS_PARAM.put("ME18085","开发者尚未被验证");
        ERRORCODE_BUSINESS_PARAM.put("ME18090","返回执行还需要等几秒");
        ERRORCODE_BUSINESS_PARAM.put("ME18091","用户未设置+键的操作");
        ERRORCODE_BUSINESS_PARAM.put("ME18092","当前临时频道不是直播模式");
        ERRORCODE_BUSINESS_PARAM.put("ME18094","该用户没有分享坐标");
        ERRORCODE_BUSINESS_PARAM.put("ME18095","有多个相同的分享坐标点");
        ERRORCODE_BUSINESS_PARAM.put("ME18097","分享坐标超时");
        ERRORCODE_BUSINESS_PARAM.put("ME18098","用户的分享坐标必须大于0");
        ERRORCODE_BUSINESS_PARAM.put("ME18099","用户的等级超过范围");
        ERRORCODE_BUSINESS_PARAM.put("ME18101","没有使用");
        ERRORCODE_BUSINESS_PARAM.put("ME18102","不允许再调用直播模式");
        ERRORCODE_BUSINESS_PARAM.put("ME18103","不允许调用结束直播模式");
        ERRORCODE_BUSINESS_PARAM.put("ME18104","当前用户申请的频道已经被审核通过了");
        ERRORCODE_BUSINESS_PARAM.put("ME18105","当前用户重复提交相同的数据");
        ERRORCODE_BUSINESS_PARAM.put("ME18106","当前用户的密频道已经达到最大值,");
        ERRORCODE_BUSINESS_PARAM.put("ME18107","唯一码错误");
        ERRORCODE_BUSINESS_PARAM.put("ME18108","需要先删除一些无效的");
        ERRORCODE_BUSINESS_PARAM.put("ME18109","当前频道编码错误");
        ERRORCODE_BUSINESS_PARAM.put("ME18110","点在路上定位失败");
        ERRORCODE_BUSINESS_PARAM.put("ME18301","之前从未之前从未关注过");
        ERRORCODE_BUSINESS_PARAM.put("ME18302","禁止关注自己创建的微频道");
        ERRORCODE_BUSINESS_PARAM.put("ME18303","不需要再关注");
        ERRORCODE_BUSINESS_PARAM.put("ME18304","请不要继续操作取消关注");
        ERRORCODE_BUSINESS_PARAM.put("ME18305","密频道不能管理自己");
        ERRORCODE_BUSINESS_PARAM.put("ME18306","当前不是密频道管理员");
        ERRORCODE_BUSINESS_PARAM.put("ME18503","对多组imei号，输出错误号");
        ERRORCODE_BUSINESS_PARAM.put("ME18504","该帐号已存在");
        ERRORCODE_BUSINESS_PARAM.put("ME18801","当前状态不允许预入库操作");
        ERRORCODE_BUSINESS_PARAM.put("ME18802","没有收到开机信息");
        ERRORCODE_BUSINESS_PARAM.put("ME18803","没有接受到GPS信息");

        ERRORCODE_SYSTEM_PARAM.put("ME01020","访问mysql数据库失败");
        ERRORCODE_SYSTEM_PARAM.put("ME01021","访问redis失败");
        ERRORCODE_SYSTEM_PARAM.put("ME01022","内部错误");
        ERRORCODE_SYSTEM_PARAM.put("ME01023","参数错误");
        ERRORCODE_SYSTEM_PARAM.put("ME18100","原因是用户本次的谢尔值已经发放过了.属于重复获取");
        ERRORCODE_SYSTEM_PARAM.put("ME18096","原因是用户本次的谢尔值已经领取过了.属于重复获取");
        ERRORCODE_SYSTEM_PARAM.put("ME18082","this appKey has no business info!");
        ERRORCODE_SYSTEM_PARAM.put("ME18502","this user apply with drawamount less than ten");
        ERRORCODE_SYSTEM_PARAM.put("ME18057","this applicant hasn't delete righ");
        ERRORCODE_SYSTEM_PARAM.put("ME18058","this delete AccountID is not group member");
        ERRORCODE_SYSTEM_PARAM.put("ME18056","this apply AccountID is not group member!");
        ERRORCODE_SYSTEM_PARAM.put("ME18033","no account of withdrawing");
        ERRORCODE_SYSTEM_PARAM.put("ME18034","no deposit password");
        ERRORCODE_SYSTEM_PARAM.put("ME18042","the user hasn't applied exchanging mirrtalk");
        ERRORCODE_SYSTEM_PARAM.put("ME18043","the IMEI :%s is unusable");
        ERRORCODE_SYSTEM_PARAM.put("ME18093","this administrator has no business group!");
        ERRORCODE_SYSTEM_PARAM.put("ME18087","client AppKey is no texist!");
        ERRORCODE_SYSTEM_PARAM.put("ME18088","client AppKey is unusable!");
        ERRORCODE_SYSTEM_PARAM.put("ME18019","deposit type is valid,not allow to change it");
        ERRORCODE_SYSTEM_PARAM.put("ME18020","no right to withdraw the deposit");
        ERRORCODE_SYSTEM_PARAM.put("ME18021","no right to exchange the WEME");
        ERRORCODE_SYSTEM_PARAM.put("ME18025","delete too many members");
        ERRORCODE_SYSTEM_PARAM.put("ME18062","username is not in me.daoke.renrenfm.service!");
    }





}
