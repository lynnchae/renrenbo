package me.daoke.renrenfm.controller;

import me.daoke.renrenfm.common.model.CommonJsonResult;
import me.daoke.renrenfm.common.util.ConstantsUtil;
import me.daoke.renrenfm.common.util.JsonMapper;
import me.daoke.renrenfm.entity.Version;
import me.daoke.renrenfm.service.IUserInfoService;
import me.daoke.renrenfm.service.IVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 系统操作controller
 * @author zhuosh
 * @date 2015/8/3
 */
@Controller
@RequestMapping(value="/sys")
public class SystemController {

    /**
     * 系统操作Service
     */
    @Autowired
    private IVersionService versionService;

    /**
     * 登录用户Service
     */
    @Autowired
    private IUserInfoService userInfoService;


    /**
     * 跳转到登录界面
     *
     * @return
     */
    @RequestMapping(value = "/login")
    public String getLogin() {
        return "login";
    }


    @RequestMapping(value = "/toLogin")
    public String toLogin() {
        return "index";
    }


    @RequestMapping(value = "/index")
    public String getIndex() {
        return "index";
    }

    @RequestMapping(value = "/calendar")
    public String getcalendar() {
        return "calendar";
    }

    /**
     * 跳转到最新版本信息界面
     */
    @RequestMapping("/getVersion")
    public String getVersion() {
        return "versionUpdate/versionUpdate";
    }

    /**
     * 获取最新的FM的版本信息
     *
     * @return
     */
    @RequestMapping(value = "/getVersionFm")
    @ResponseBody
    public String getVersionFm(@RequestParam String appKey) {
        try {
            Version version = versionService.getVersionFm(appKey);
            Map map = new HashMap<String, Object>();
            if ("0".equals(appKey)) {
                map.put("number", version.getIosNumber());
                map.put("versionUrl", version.getIosUpdateUrl());
                map.put("remark", version.getRemark());
                map.put("isUpToDate", version.getIsUpToDate());
                map.put("isForcedUpDate", version.getIsForcedUpDate());
            }
            if ("1".equals(appKey)) {
                map.put("number", version.getAndroidNumber());
                map.put("versionUrl", version.getAndroidUpdateUrl());
                map.put("remark", version.getRemark());
                map.put("isUpToDate", version.getIsUpToDate());
                map.put("isForcedUpDate", version.getIsForcedUpDate());
            }
            if (map != null) {
                return JsonMapper.toJson(map, true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取人人播的最新版本信息
     *
     * @return
     */
    @RequestMapping(value = "/getVersionRrb")
    @ResponseBody
    public String getVersionRrm(@RequestParam String appKey) {
        try {
            Version version = versionService.getVersionRrb(appKey);
            Map map = new HashMap<String, Object>();
            if ("0".equals(appKey)) {
                map.put("number", version.getIosNumber());
                map.put("versionUrl", version.getIosUpdateUrl());
                map.put("remark", version.getRemark());
                map.put("isUpToDate", version.getIsUpToDate());
                map.put("isForcedUpDate", version.getIsForcedUpDate());
            }
            if ("1".equals(appKey)) {
                map.put("number", version.getAndroidNumber());
                map.put("versionUrl", version.getAndroidUpdateUrl());
                map.put("remark", version.getRemark());
                map.put("isUpToDate", version.getIsUpToDate());
                map.put("isForcedUpDate", version.getIsForcedUpDate());
            }
            if (map != null) {
                return JsonMapper.toJson(map, true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 更新版本信息
     *
     * @param type           AAP类型
     * @param number         版本号
     * @param appKey         app系统类型
     * @param versionUrl     更新的Url
     * @param remark         备注信息
     * @param isUpToDate     是否是最新版本
     * @param isForcedUpDate 是否强制更新
     * @return
     */
    @RequestMapping("/updateVersion")
    @ResponseBody
    public CommonJsonResult updateVersion(@RequestParam String type, @RequestParam String number, @RequestParam String appKey, @RequestParam String versionUrl,
                                          @RequestParam String remark, @RequestParam Integer isUpToDate, @RequestParam Integer isForcedUpDate) {
        CommonJsonResult commonJsonResult = new CommonJsonResult();
        commonJsonResult.setCommonJsonResult(ConstantsUtil.ERRORCODE_FAIL, "更新失败");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("remark", remark);
        params.put("isUpToDate", isUpToDate);
        params.put("isForcedUpDate", isForcedUpDate);
        params.put("appKey", appKey);
        int num = 0;
        try {
            //执行更新操作
            if ("fm".equals(type)) {
                //IOS
                if ("0".equals(appKey)) {
                    params.put("iosNumber", number);
                    params.put("iosUpdateUrl", versionUrl);
                }
                //Android
                if ("1".equals(appKey)) {
                    params.put("androidNumber", number);
                    params.put("androidUpdateUrl", versionUrl);
                }
                num = versionService.updateVersionFm(params);
            }
            if ("rrb".equals(type)) {
                //IOS
                if ("0".equals(appKey)) {
                    params.put("iosNumber", number);
                    params.put("iosUpdateUrl", versionUrl);
                }
                //Android
                if ("1".equals(appKey)) {
                    params.put("androidNumber", number);
                    params.put("androidUpdateUrl", versionUrl);
                }
                num = versionService.updateVersionRrb(params);
            }
            //如果更新成功，则返回更新以后的数据
            if (num > 0) {
                Map map = new HashMap<String, Object>();
                map.put("number", number);
                map.put("versionUrl", versionUrl);
                map.put("remark", remark);
                map.put("isUpToDate", isUpToDate);
                map.put("isForcedUpDate", isForcedUpDate);
                commonJsonResult.setCommonJsonResult(ConstantsUtil.ERRORCODE_OK, map);
                return commonJsonResult;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return commonJsonResult;
    }

    /**
     *
     * @param userName 登录用户姓名
     * @param userPassword  登录用户密码
     * @return
     */
    @RequestMapping("/loginCheck")
    @ResponseBody
    public String loginCheck(HttpSession session ,@RequestParam String userName,@RequestParam String userPassword) {
        boolean flag = false;
        try{
            flag = userInfoService.queryUserInfo(session, userName, userPassword);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return flag + "";
    }

}