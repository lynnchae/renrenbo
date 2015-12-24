package me.daoke.renrenfm.controller;

import com.alibaba.fastjson.JSON;
import me.daoke.renrenfm.common.page.JqgridPageList;
import me.daoke.renrenfm.common.util.AbStrUtil;
import me.daoke.renrenfm.common.util.JsonMapper;
import me.daoke.renrenfm.service.IPrizeBoxService;
import me.daoke.renrenfm.service.IPrizeInfoService;
import me.daoke.renrenfm.service.IUploadService;
import me.daoke.renrenfm.vo.PrizeBox;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
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
 * 箱子controller层
 * Created by liyanqin on 2015/8/1.
 */
@Controller
@RequestMapping("/box")
public class PrizeBoxController {

    private final Logger logger = Logger.getLogger(this.getClass());

    /**箱子**/
    @Autowired
    private IPrizeBoxService prizeBoxService;

    /**奖品**/
    @Autowired
    private IPrizeInfoService prizeInfoService;

    /**文件上传**/
    @Autowired
    private IUploadService uploadService;
    /**
     * 首次请求
     * @return
     */
    @RequestMapping(value="/boxIndex")
    public ModelAndView prizeInfoIndex(){
        return new ModelAndView("box/prizeBox");
    }

    @ResponseBody
    @RequestMapping(value="/getBoxList")
    public String getBoxList(HttpServletRequest request,HttpServletResponse response, @RequestParam String rows,@RequestParam String page,@RequestParam(value = "name",required = false) String name){
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
            List<PrizeBox> data = prizeBoxService.getBoxList(pageList,name);
            if(data != null && data.size() > 0){
                for(PrizeBox box : data){
                    logger.info("boxInfo:" + box);
                    Map<String,Object> prizeMap = new HashMap<String, Object>();
                    prizeMap.put("id",box.getId());
                    prizeMap.put("boxName",box.getBoxName());
                    prizeMap.put("boxCode",box.getBoxCode());
                    prizeMap.put("boxImg",box.getBoxImg());
                    prizeMap.put("denseNum",box.getDenseNum());
                    prizeMap.put("createTime",format.format(box.getCreateTime()));
                    resultList.add(prizeMap);
                }
            }
            pageList.setTotal(pageList.getRecords(), Integer.parseInt(rows));
            pageList.setRows(resultList);
            str = JSON.toJSONString(pageList);
            logger.info("查询盒子信息返回的json格式：" + str);
        }catch (Exception e){
            e.printStackTrace();
        }
        return str;
    }

    @ResponseBody
    @RequestMapping(value="/getPrizeInBox")
    public String getPrizeInBox(@RequestParam int boxId, @RequestParam String rows,@RequestParam String page){
        String str = "";
        try {
            JqgridPageList<Map<String,Object>> pageList = new JqgridPageList<Map<String,Object>>();
            List<Map<String,Object>> resultList = new ArrayList<Map<String, Object>>();
            List<Map<String,Object>> data = prizeInfoService.getPrizeInfoInBox(boxId);
            if(data != null && data.size() > 0){
                for(Map<String,Object> info : data){
                    Map<String,Object> prizeMap = new HashMap<String, Object>();
                    prizeMap.put("id",info.get("id"));//映射关系的ID
                    prizeMap.put("prizeName",info.get("name"));
                    prizeMap.put("prizeIcon",info.get("icon"));
                    prizeMap.put("prizeDenseNum",info.get("denseNum"));
                    prizeMap.put("prizeCode", info.get("prizeCode"));
                    prizeMap.put("percent", info.get("percent"));
                    resultList.add(prizeMap);
                }
            }
            pageList.setRecords(resultList.size());
            pageList.setTotal(pageList.getRecords(), Integer.parseInt(rows));
            pageList.setPage(1);

            pageList.setRows(resultList);
//            str = JsonMapper.toJson(pageList, true);
            logger.info("查询盒子里面的礼物");
            str = JSONObject.fromObject(pageList).toString();
            logger.info("盒子里面的礼物，json数据" + str);
        }catch (Exception e){
            e.printStackTrace();
        }
        return str;
    }


    @ResponseBody
    @RequestMapping(value="/deleteBox")
    public String deleteBox(@RequestParam String boxIDs){
        String str = "";
        boolean flag = false;
        try {
            String boxCode = prizeBoxService.isExistPrize(boxIDs);
            if(boxCode != null && !"".equals(boxCode)){
                str = boxCode;
            }else{
                flag = prizeBoxService.deletePrizeBox(boxIDs);
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
     * @param boxID
     * @param boxName
     * @param money
     * @return
     */
    @ResponseBody
    @RequestMapping("/editBoxInfo")
    public String editBoxInfo(HttpServletRequest request,@RequestParam int boxID,@RequestParam String boxName,@RequestParam float money,
                                @RequestParam String createTime,@RequestParam String icon){
        boolean flag = false;
        try {
            PrizeBox box = prizeBoxService.queryBoxByID(boxID);
            if(box != null){
                String boxCode = box.getBoxCode();
                //上传图片，并返回图片路径
                String picUrl = uploadService.uploadPic(request);
                Map<String,Object> params = new HashMap<String, Object>();
                params.put("boxID",boxID);
                params.put("boxName",boxName);
                if(picUrl != null){//修改图片
                    params.put("boxImg", picUrl);
                }else{//未修改图片
                    params.put("boxImg", icon);
                }
                params.put("boxCode", boxCode);
                params.put("denseNum", money);
                params.put("createTime", createTime);
                flag = prizeBoxService.editBoxInfo(params);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return String.valueOf(flag);
    }

    /**
     * 判断当前奖品的编码是否存在
     * @param boxCode
     * @return
     */
    @ResponseBody
    @RequestMapping("/judgeBoxCodeIsExist")
    public String judgeBoxCodeIsExist(@RequestParam String boxCode){
        boolean flag = prizeBoxService.judgeBoxCodeIsExist(boxCode);
        return String.valueOf(flag);
    }

    /**
     * 添加奖品信息
     * @param request
     * @param boxName
     * @param money
     * @return
     */
    @ResponseBody
    @RequestMapping("/addBoxInfo")
    public String addBoxInfo(HttpServletRequest request,@RequestParam String boxName,@RequestParam float money){
        boolean flag = false;
        try {
            //上传图片，并返回图片路径
            String icon = uploadService.uploadPic(request);
            Map<String,Object> params = new HashMap<String, Object>();
            params.put("boxName",boxName);
            if(icon != null){
                params.put("boxImg", icon);
            }
            //生成箱子编码
            String boxCode = "B"+ AbStrUtil.getCode()+AbStrUtil.getCharAndNum(3);
            //判断编码是否已被使用
            while (prizeBoxService.judgeBoxCodeIsExist(boxCode)){
                boxCode = "B"+ AbStrUtil.getCode()+AbStrUtil.getCharAndNum(3);
            }
            params.put("denseNum", money);
            params.put("boxCode", boxCode);
            flag = prizeBoxService.addBoxInfo(params);
        }catch (Exception e){
            e.printStackTrace();
        }
        return String.valueOf(flag);
    }

    /**
     * 首次请求
     * @return
     */
    @RequestMapping(value="/prizeBoxMappingIndex")
    public ModelAndView editPrizeInBox(@RequestParam int boxId){
        PrizeBox box = new PrizeBox();
        try {
            box = prizeBoxService.queryBoxByID(boxId);
        }catch (Exception e){

        }
        return new ModelAndView("box/prizeBoxMapping","result",box);
    }

    /**
     * 查询不在箱子中的奖品
     * @param boxCode
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/getPrizeNotInBox")
    public String getPrizeNotInBox(@RequestParam String boxCode){
        String str = "";
        try {
            List<Map<String,Object>> resultList = new ArrayList<Map<String, Object>>();
            List<Map<String,Object>> data = prizeInfoService.getPrizeInfoNotInBox(boxCode);
            if(data != null && data.size() > 0){
                for(Map<String,Object> prizeInfo : data){
                    Map<String,Object> prizeMap = new HashMap<String, Object>();
                    prizeMap.put("id",prizeInfo.get("id"));
                    prizeMap.put("prizeName",prizeInfo.get("name"));
                    prizeMap.put("prizeIcon",prizeInfo.get("icon"));
                    prizeMap.put("prizeDenseNum",prizeInfo.get("denseNum"));
                    prizeMap.put("prizeCode", prizeInfo.get("prizeCode"));
                    resultList.add(prizeMap);
                }
            }
            str = JsonMapper.toJson(resultList, true);
        }catch (Exception e){
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 删除箱子中的奖品
     * @param mappingIds
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/deletePrizeInBox")
    public String deletePrizeInBox(@RequestParam String mappingIds){
        boolean flag = false;
        try {
            flag = prizeBoxService.deletePrizeInBox(mappingIds);
        }catch (Exception e){
            e.printStackTrace();
        }
        return String.valueOf(flag);
    }

    /**
     * 修改百分比
     * @param id
     *          映射关系的ID
     * @param percent
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/editPrizePercent")
    public String editPrizePercent(@RequestParam int id,@RequestParam float percent){
        boolean flag = false;
        try {
            flag = prizeBoxService.editPrizePercent(id,percent);
        }catch (Exception e){
            e.printStackTrace();
        }
        return String.valueOf(flag);
    }

    /**
     * 往箱子中添加奖品
     * @param boxCode
     * @param prizeIDs
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/addPrize2Box")
    public String addPrize2Box(@RequestParam String boxCode,@RequestParam String prizeIDs){
        boolean flag = false;
        try {
            flag = prizeBoxService.addPrize2Box(boxCode, prizeIDs);
        }catch (Exception e){
            e.printStackTrace();

        }
        return String.valueOf(flag);
    }
}

