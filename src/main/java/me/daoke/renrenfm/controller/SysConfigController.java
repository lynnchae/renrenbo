package me.daoke.renrenfm.controller;

import me.daoke.renrenfm.common.model.CommonJsonResult;
import me.daoke.renrenfm.common.util.ConstantsUtil;
import me.daoke.renrenfm.common.util.JsonMapper;
import me.daoke.renrenfm.entity.SysConfig;
import me.daoke.renrenfm.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统信息配置
 * @author liyanqin
 * @date 2015/10/12
 */
@Controller
@RequestMapping(value="/sysConfig")
public class SysConfigController {

    /***系统参数配置service层对象*/
    @Autowired
    private ISysConfigService sysConfigService;

    /**
     *跳转到道路参数信息配置界面
     */
    @RequestMapping("/getIndex")
    public String getRoadConfigIndexPage(){
        return "sysConfig/checkConfig";
    }


    /**
     * 获取APP审核的系统配置参数
     * @return
     */
    @RequestMapping(value="/getCkeckConfig")
    @ResponseBody
    public String getCkeckConfig(){
        try{
            SysConfig config = sysConfigService.queryConfirmConfig(ConstantsUtil.SYSCONFIG_IS_ONCHECK);
            if(config != null){
                return JsonMapper.toJson(config,true);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 更新路况指标
     * @param isOnCheck
     *        是否在审核中
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/updateCheckConfig")
    public CommonJsonResult updateCheckConfig(@RequestParam int isOnCheck){
        CommonJsonResult commonJsonResult = new CommonJsonResult();
        commonJsonResult.setCommonJsonResult(ConstantsUtil.ERRORCODE_FAIL,"更新失败");
        try{
            //执行跟新操作
            Map<String,Object> params = new HashMap<String, Object>();
            params.put("flag",ConstantsUtil.SYSCONFIG_IS_ONCHECK);
            params.put("valOne",isOnCheck);
            int num = sysConfigService.updateConfirmConfigInfo(params);
            if(num > 0){
                Map map = new HashMap<String,Object>();
                map.put("valOne",isOnCheck);
                commonJsonResult.setCommonJsonResult(ConstantsUtil.ERRORCODE_OK, map);
                return commonJsonResult;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return commonJsonResult;
    }
}
