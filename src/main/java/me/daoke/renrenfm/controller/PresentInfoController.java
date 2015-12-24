package me.daoke.renrenfm.controller;


import me.daoke.renrenfm.common.page.JqgridPageList;
import me.daoke.renrenfm.common.util.JsonMapper;
import me.daoke.renrenfm.service.IPresentInfoService;
import me.daoke.renrenfm.service.IUploadService;
import me.daoke.renrenfm.util.AbStrUtil;
import me.daoke.renrenfm.vo.PresentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 虚礼礼物controller
 * Created by liyanqin on 2015/8/1.
 */
@Controller
@RequestMapping(value="/present")
public class PresentInfoController {

    @Autowired
    private IPresentInfoService presentInfoService;

    /**文件上传**/
    @Autowired
    private IUploadService uploadService;

    /**
     * 首次请求
     * @return
     */
    @RequestMapping(value="/presentIndex")
    public ModelAndView presentInfoIndex(){
        return new ModelAndView("present/presentInfo");
    }

    /**
     *获取列表
     * @param rows
     * @param page
     * @return
     */
    @ResponseBody
    @RequestMapping("/getPresentInfoList")
    public String getPresentInfoList(@RequestParam String rows,@RequestParam String page,@RequestParam(value = "type",required = false) Integer type,
                                     @RequestParam(value = "name",required = false) String name,@RequestParam(value = "appearType",required = false) Integer appearType){
        String str = "";
        try {
            JqgridPageList<Map<String,Object>> pageList = new JqgridPageList<Map<String,Object>>();
            if(page == null || "".equals(page)){
                page = "1";
            }
            if(rows == null || "".equals(rows)){
                rows = "10";
            }
            pageList.setPage(Integer.parseInt(page));
            pageList.setRecords(Integer.parseInt(rows));
            List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if(type == null){
                type = -1;
            }
            if(appearType == null){
                appearType = -1;
            }
            List<PresentInfo> data = presentInfoService.getPresentInfoList(pageList,type,name,appearType);
            if(data != null && data.size() > 0){
                for (PresentInfo present : data){
                    Map<String,Object> presentMap = new HashMap<String, Object>();
                    presentMap.put("id",present.getId());
                    presentMap.put("name",present.getName());
                    presentMap.put("icon",present.getIcon());
                    presentMap.put("type",present.getType());
                    presentMap.put("denseNum",present.getDenseNum());
                    presentMap.put("diamond",present.getDiamond());
                    presentMap.put("popularityVal",present.getPopularityVal());
                    presentMap.put("frequencyNum",present.getFrequencyNum());
                    presentMap.put("appearType",present.getAppearType());
                    presentMap.put("createTime",format.format(present.getCreateTime()));
                    resultList.add(presentMap);
                }
            }
            pageList.setTotal(pageList.getRecords(), Integer.parseInt(rows));
            pageList.setRows(resultList);
            str = JsonMapper.toJson(pageList,true);
        }catch (Exception e){
            e.printStackTrace();
        }
        return str;
    }

    @ResponseBody
    @RequestMapping("/editPresentInfo")
    public String editPresentInfo(HttpServletRequest request,@RequestParam int presentID,@RequestParam String presentName,@RequestParam int type,@RequestParam int denseNum,@RequestParam int popularityVal,
                                  @RequestParam int frequencyNum,@RequestParam int appearType,@RequestParam int diamond,@RequestParam String createTime,@RequestParam String icon){
        boolean flag = false;
        try {
            String picUrl = uploadService.uploadPic(request);
            Map<String,Object> params = new HashMap<String, Object>();
            params.put("presentID",presentID);
            if(!AbStrUtil.isEmpty(presentName)){
                params.put("name",presentName);
            }
            if(!AbStrUtil.isEmpty(picUrl)){
                params.put("icon", picUrl);
            }else {
                params.put("icon", icon);
            }
            params.put("type", type);
            params.put("denseNum", denseNum);
            params.put("frequencyNum", frequencyNum);
            params.put("popularityVal", popularityVal);
            params.put("appearType", appearType);
            params.put("diamond", diamond);
            params.put("createTime", createTime);
            flag = presentInfoService.editPresentInfo(params);
        }catch (Exception e){
            e.printStackTrace();
        }
        return String.valueOf(flag);
    }

    @ResponseBody
    @RequestMapping("/deletePresentInfo")
    public String deletePresentInfo(@RequestParam String presentIDs){
        boolean flag = presentInfoService.deletePresentInfo(presentIDs);
        return String.valueOf(flag);
    }

    @ResponseBody
    @RequestMapping("/addPresentInfo")
    public String addPresentInfo(HttpServletRequest request,@RequestParam String presentName,@RequestParam int type,@RequestParam int denseNum,@RequestParam int diamond,@RequestParam int popularityVal,@RequestParam int frequencyNum,@RequestParam int appearType){
        boolean flag = false;
        try {
            String icon = uploadService.uploadPic(request);
            Map<String,Object> params = new HashMap<String, Object>();
            params.put("name",presentName);
            params.put("icon", icon);
            params.put("type", type);
            params.put("denseNum", denseNum);
            params.put("diamond", diamond);
            params.put("frequencyNum", frequencyNum);
            params.put("popularityVal", popularityVal);
            params.put("appearType", appearType);

            flag = presentInfoService.addPresentInfo(params);
        }catch (Exception e){
            e.printStackTrace();
        }
        return String.valueOf(flag);
    }
}
