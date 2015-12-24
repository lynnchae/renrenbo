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
 * 活动管理
 * @author liyanqin
 * @date 2015/10/13
 */
@Controller
@RequestMapping(value="/activity")
public class ActivityController {


    /**
     *跳转到道路参数信息配置界面
     */
    @RequestMapping("/getIndex")
    public String getRoadConfigIndexPage(){
        return "activity/createActivity";
    }


}
