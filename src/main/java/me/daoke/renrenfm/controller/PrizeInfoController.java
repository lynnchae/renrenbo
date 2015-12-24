package me.daoke.renrenfm.controller;

import me.daoke.renrenfm.common.page.JqgridPageList;
import me.daoke.renrenfm.common.util.AbStrUtil;
import me.daoke.renrenfm.common.util.JsonMapper;
import me.daoke.renrenfm.service.IPrizeInfoService;
import me.daoke.renrenfm.service.IUploadService;
import me.daoke.renrenfm.vo.PrizeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 实体礼物controller层
 * Created by liyanqin on 2015/8/1.
 */
@Controller
@RequestMapping(value="/prize",produces="text/plain;charset=UTF-8")
public class PrizeInfoController {

    @Autowired
    private IPrizeInfoService prizeInfoService;

    /**文件上传**/
    @Autowired
    private IUploadService uploadService;

    /**
     * 首次请求
     * @return
     */
    @RequestMapping(value="/prizeIndex")
    public ModelAndView prizeInfoIndex(){
        return new ModelAndView("prize/prizeInfo");
    }

    @ResponseBody
    @RequestMapping(value="/getPrizeInfoList")
    public String getPrizeInfoList(HttpServletRequest request,HttpServletResponse response, @RequestParam String rows,@RequestParam String page,@RequestParam(value = "name",required = false) String name){
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
            List<PrizeInfo> data = prizeInfoService.getPrizeInfoList(pageList, name);
            if(data != null && data.size() > 0){
                for(PrizeInfo prizeInfo : data){
                    Map<String,Object> prizeMap = new HashMap<String, Object>();
                    prizeMap.put("id",prizeInfo.getId());
                    prizeMap.put("name",prizeInfo.getName());
                    prizeMap.put("icon",prizeInfo.getIcon());
//                    prizeMap.put("denseNum",prizeInfo.getDenseNum()/100.0);
                    prizeMap.put("description",prizeInfo.getDescription());
                    prizeMap.put("collectWealthVal",prizeInfo.getCollectWealthVal());
                    prizeMap.put("giveWealthVal",prizeInfo.getGiveWealthVal());
                    prizeMap.put("prizeCode", prizeInfo.getPrizeCode());
                    prizeMap.put("createTime",format.format(prizeInfo.getCreateTime()));
                    resultList.add(prizeMap);
                }
            }
            pageList.setTotal(pageList.getRecords(), Integer.parseInt(rows));
            pageList.setRows(resultList);
            str = JsonMapper.toJson(pageList, true);
        }catch (Exception e){
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 删除奖品
     * @param prizeIDs
     * @return
     */
    @ResponseBody
    @RequestMapping("/deletePrizeInfo")
    public String deletePrizeInfo(@RequestParam String prizeIDs){
        String str = "";
        boolean flag = false;
        try {
            String prizeCode = prizeInfoService.isExistInBox(prizeIDs);
            if(prizeCode != null && !"".equals(prizeCode)){
                str = prizeCode;
            }else{
                flag = prizeInfoService.deletePrizeInfo(prizeIDs);
                str = String.valueOf(flag);
            }
        }catch (Exception e){
            e.printStackTrace();
            str = String.valueOf(flag);
        }
        return str;
    }

    /**
     * 修改奖品信息
     * @param request
     * @param prizeID
     * @param prizeName
     * @param description
     * @param collectWealthVal
     * @param giveWealthVal
     * @return
     */
    @ResponseBody
    @RequestMapping("/editPrizeInfo")
    public String editPrizeInfo(HttpServletRequest request,@RequestParam int prizeID,@RequestParam String prizeName,
                                @RequestParam String description,@RequestParam int collectWealthVal,@RequestParam int giveWealthVal,
                                @RequestParam String createTime,@RequestParam String icon){
        boolean flag = false;
        try {
            PrizeInfo prizeInfo = prizeInfoService.getPrizeById(prizeID);
            if(prizeInfo != null){
                //查询编码
                String prizeCode = prizeInfo.getPrizeCode();
                //上传图片，并返回图片路径
                String picUrl = uploadService.uploadPic(request);
                Map<String,Object> params = new HashMap<String, Object>();
                params.put("prizeID",prizeID);
                params.put("name",prizeName);
                if(picUrl != null){//修改图片
                    params.put("icon", picUrl);
                }else{//未修改图片
                    params.put("icon", icon);
                }
//            params.put("denseNum", money*100);
                params.put("description", description);
                params.put("collectWealthVal", collectWealthVal);
                params.put("giveWealthVal", giveWealthVal);
                params.put("prizeCode", prizeCode);
                params.put("createTime", createTime);
                flag = prizeInfoService.editPrizeInfo(params);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return String.valueOf(flag);
    }

    /**
     * 判断当前奖品的编码是否存在
     * @param prizeCode
     * @return
     */
    @ResponseBody
    @RequestMapping("/judgeCodeIsExist")
    public String judgeCodeIsExist(@RequestParam String prizeCode){
        boolean flag = prizeInfoService.judgeCodeIsExist(prizeCode);
        return String.valueOf(flag);
    }

    /**
     * 添加奖品信息
     * @param request
     * @param prizeName
     * @param description
     * @param collectWealthVal
     * @param giveWealthVal
     * @return
     */
    @ResponseBody
    @RequestMapping("/addPrizeInfo")
    public String addPrizeInfo(HttpServletRequest request,@RequestParam String prizeName,@RequestParam String description,@RequestParam int collectWealthVal,@RequestParam int giveWealthVal){
        boolean flag = false;
        try {
            //上传图片，并返回图片路径
            String icon = uploadService.uploadPic(request);
            Map<String,Object> params = new HashMap<String, Object>();
            params.put("name",prizeName);
            if(icon != null){
                params.put("icon", icon);
            }
//            params.put("denseNum", money*100);
            //生成奖品编码
            String prizeCode = "P"+AbStrUtil.getCode()+AbStrUtil.getCharAndNum(3);
            //判断编码是否已被使用
            while(prizeInfoService.judgeCodeIsExist(prizeCode)){
                prizeCode = "P"+AbStrUtil.getCode()+AbStrUtil.getCharAndNum(3);
            }
            params.put("description", description);
            params.put("collectWealthVal", collectWealthVal);
            params.put("giveWealthVal", giveWealthVal);
            params.put("prizeCode", prizeCode);
            flag = prizeInfoService.addPrizeInfo(params);
        }catch (Exception e){
            e.printStackTrace();
        }
        return String.valueOf(flag);
    }


}
