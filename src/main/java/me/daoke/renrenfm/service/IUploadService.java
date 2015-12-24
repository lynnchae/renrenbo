package me.daoke.renrenfm.service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by liyanqin on 2015/8/5.
 */
public interface IUploadService {

    /**
     * 图片上传
     * @param request
     * @return
     */
    public String uploadPic(HttpServletRequest request);
}
