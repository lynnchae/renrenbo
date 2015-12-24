package me.daoke.renrenfm.controller;

import me.daoke.renrenfm.common.model.CommonJsonResult;
import me.daoke.renrenfm.common.util.ConstantsUtil;
import me.daoke.renrenfm.common.util.JsonMapper;
import me.daoke.renrenfm.entity.RoadConditionPercent;
import me.daoke.renrenfm.service.IRoadConditionPercentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 道路参数信息配置
 * @author zhuosh
 * @date 2015/8/8
 */
@Controller
@RequestMapping(value="/roadConditionPercent")
public class RoadConditionPercentController {

    /***道路参数配置service层对象*/
    @Autowired
    private IRoadConditionPercentService roadConditionPercentService;

    /**
     *跳转到道路参数信息配置界面
     */
    @RequestMapping("/getIndex")
    public String getRoadConfigIndexPage(){
        return "sysConfig/roadConfig";
    }


    @RequestMapping(value="/getConfigInfo")
    @ResponseBody
    public String getConfigInfo(){
        try{
            RoadConditionPercent roadConditionPercent = roadConditionPercentService.queryConfigInfo();
            if(roadConditionPercent != null){
                return JsonMapper.toJson(roadConditionPercent,true);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 更新路况指标
     * @param crowPercent
     *        拥堵指标
     * @param slowPercent
     *         缓行指标
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/updateConfigInfo")
    public CommonJsonResult updateConfigInfo(@RequestParam Float slowPercent,@RequestParam Float crowPercent){
        CommonJsonResult commonJsonResult = new CommonJsonResult();
        commonJsonResult.setCommonJsonResult(ConstantsUtil.ERRORCODE_FAIL,"更新失败");
        try{
            if(slowPercent == null || slowPercent == 0){
                commonJsonResult.setCommonJsonResult(ConstantsUtil.ERRORCODE_FAIL,"输入合法的缓行指标");
                return commonJsonResult;
            }

            if(crowPercent == null || crowPercent == 0){
                commonJsonResult.setCommonJsonResult(ConstantsUtil.ERRORCODE_FAIL,"输入合法的拥堵指标");
                return commonJsonResult;
            }

            //执行跟新操作
            int num = roadConditionPercentService.updateInfo(slowPercent.floatValue(),crowPercent.floatValue());
            if(num > 0){
                Map map = new HashMap<String,Object>();
                map.put("slowPercent",slowPercent.toString());
                map.put("crowPercent",crowPercent.toString());
                commonJsonResult.setCommonJsonResult(ConstantsUtil.ERRORCODE_OK, map);
                return commonJsonResult;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return commonJsonResult;
    }
}
