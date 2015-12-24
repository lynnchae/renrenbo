package me.daoke.renrenfm.service.impl;

import me.daoke.renrenfm.common.util.ConstantsUtil;
import me.daoke.renrenfm.common.util.JsonMapper;
import me.daoke.renrenfm.service.IUploadService;
import me.daoke.renrenfm.util.ParameterUtil;
import me.daoke.renrenfm.util.SHASignature;
import net.sf.json.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 文件上传
 * Created by liyanqin on 2015/8/5.
 */
@Service
public class UploadServiceImpl implements IUploadService {

    /***
     * 文件上传路径
     */
    @Value("#{apiConfig[saveFile]}")
    private String saveFile;

    /**
     * 图片上传
     * @param request
     * @return
     */
    @Override
    public String uploadPic(HttpServletRequest request){
        String pic = null;
        if (request instanceof MultipartHttpServletRequest) {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Iterator<String> nameList = multiRequest.getFileNames();
            while (nameList.hasNext()) {
                MultipartFile file = multiRequest.getFile(nameList.next());
                if (file.getSize() > 0) {
                    //读取文件内容
                    try {
                        String result = this.saveFile(file, saveFile);
                        if (result != null && !"".equals(result)) {
                            JSONObject jsonObject = JSONObject.fromObject(result);
                            if ("0".equals(jsonObject.getString("ERRORCODE"))) {
                                pic = jsonObject.getJSONObject("RESULT").getString("url");
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return pic;
    }


    private  String saveFile(MultipartFile file, String saveFile) throws Exception{
        String result = "";
        Map<String,String> param = new HashMap<String, String>(3);
        String length = file.getSize() + "";
        String filename = file.getOriginalFilename();
        String fileType = filename.substring(filename.indexOf(".") + 1);

        param.put("appKey", ConstantsUtil.getAppKey("ios"));
        param.put("secret",ConstantsUtil.getSecret("ios"));
        param.put("length", length);
        param.put("fileType",fileType);
        String sign = SHASignature.sign(ParameterUtil.getSignData(param));
        param.remove("secret");
        param.put("sign",sign);
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod(saveFile);
        Part[] parts = new Part[param.size() + 1];
        int i = 0;
        for (Iterator<String> iter = param.keySet().iterator(); iter.hasNext();) {
            String paramname = iter.next();
            String paramvalue = (String) param.get(paramname);
            StringPart part = new StringPart(paramname, paramvalue, "utf-8");
            parts[i] = part;
            i++;
        }
        ByteArrayPartSource arrayPartSource = new ByteArrayPartSource(file.getOriginalFilename(), file.getBytes());
        FilePart part = new FilePart("imgFileName", arrayPartSource);
        parts[i] = part;
        post.setRequestEntity(new MultipartRequestEntity(parts, post.getParams()));

        int statusCode = client.executeMethod(post);
        if (HttpStatus.SC_OK == statusCode) {
            result = post.getResponseBodyAsString();
        } else {
            Map<String,Object> map = new HashMap<String, Object>(1);
            map.put(ConstantsUtil.ERRORCODE_FAIL,"fail");
            result = JsonMapper.toJson(map, true);
        }
        return result;
    }
}
