package me.daoke.renrenfm.controller;

import me.daoke.renrenfm.common.model.CommonJsonResult;
import me.daoke.renrenfm.common.util.AbStrUtil;
import me.daoke.renrenfm.common.util.ConstantsUtil;
import me.daoke.renrenfm.entity.SysNotice;
import me.daoke.renrenfm.service.ISysNoticeService;
import me.daoke.renrenfm.service.IUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 系统公告controller层
 * @author zhuosh
 * @date 2015/8/10
 */
@Controller
@RequestMapping(value="/sysNotice")
public class SysNoticeController {


    /***系统公告service层对象*/
    @Autowired
    private ISysNoticeService sysNoticeService;

    /**文件上传**/
    @Autowired
    private IUploadService uploadService;

    @RequestMapping(value="/getIndex")
    public String getSysNoticeIndex(){
        return "/sysConfig/sysNotice";
    }


    /**
     *根据ID查询公告信息
     *@param remark
     *        主键ID
     * @return
     */
    @RequestMapping(value="/getNoticeByRemark")
    @ResponseBody
    public CommonJsonResult getNoticeByID(Integer remark){
        CommonJsonResult commonJsonResult = new CommonJsonResult();
        try{
            SysNotice sysNotice = sysNoticeService.querySysNoticeByRemark(remark);
            commonJsonResult.setCommonJsonResult(ConstantsUtil.ERRORCODE_OK,sysNotice);
        }catch (Exception e){
            commonJsonResult.setCommonJsonResult(ConstantsUtil.ERRORCODE_FAIL,"查询失败");
            e.getMessage();
        }
        return commonJsonResult;
    }


    /**
     * 如果没有做新增操作
     * 更新指定的公告模块
     * @param sysNotice
     *         公告内容
     * @return
     */
    @RequestMapping(value="/setSysNoticeByMark")
    @ResponseBody
    public CommonJsonResult setSysNoticeByMark(HttpServletRequest request,SysNotice sysNotice){
        CommonJsonResult commonJsonResult = new CommonJsonResult();
        try{
            if(sysNotice == null){
                commonJsonResult.setCommonJsonResult(ConstantsUtil.ERRORCODE_FAIL, "请填写相关的信息");
                return commonJsonResult;
            }

            //上传图片
            String picUrl = uploadService.uploadPic(request);
            if(!AbStrUtil.isEmpty(picUrl)){
                sysNotice.setIcon(picUrl);
            }

            //新增或是修改
            int num = sysNoticeService.setSysNoticeInfo(sysNotice);

            if(num == 1){
                //查询该公告的最新内容
                SysNotice newSysNotice = sysNoticeService.querySysNoticeByRemark(sysNotice.getRemark());
                commonJsonResult.setCommonJsonResult(ConstantsUtil.ERRORCODE_OK,newSysNotice);
            }else{
                commonJsonResult.setCommonJsonResult(ConstantsUtil.ERRORCODE_FAIL,"更新失败");
            }


        }catch (Exception e){
            e.printStackTrace();
            commonJsonResult.setCommonJsonResult(ConstantsUtil.ERRORCODE_FAIL,"");
        }

        return commonJsonResult;
    }

}
