package me.daoke.renrenfm.controller.demo;

import com.alibaba.fastjson.JSONObject;
import me.daoke.renrenfm.common.page.JqgridPageList;
import me.daoke.renrenfm.common.util.JsonMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by zhuosh on 2015/8/2.
 */
@Controller
@RequestMapping(value="/author")
public class AuthController {


    /**
     *
     * @return
     */
    @RequestMapping(value="allAuthor.do")
    public String  getAnchorIndex(){
        return "demo";
    }


    /**
     *
     * @return
     */
    @RequestMapping(value = "/getAuthorList.do")
    @ResponseBody
    public String  getAllAnchor(HttpServletRequest request,String page,String rows){
        HttpSession session  = request.getSession();
        List<Author> list = (List<Author>)session.getAttribute("data");
        if(list == null){
            list = Data.initAllDate();
            session.setAttribute("data",list);
        }

        JqgridPageList<Author> pageList = new JqgridPageList<Author>();
        if(page == null || "".equals(page)){
            page = "1";
        }
        if(rows == null || "".equals(rows)){
            rows = "10";
        }


        pageList.setPage(Integer.parseInt(page));
        //pageList.setTotal(Integer.parseInt(total));
        pageList.setRecords(list.size());
        pageList.setTotal(pageList.getRecords(), Integer.parseInt(rows));
        pageList.setRows(list.subList((pageList.getPage() - 1) * pageList.getTotal(),
                pageList.getPage() * Integer.parseInt(rows)));

        String str = "";
        try {
            str = JsonMapper.toJson(pageList, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;

    }

    @RequestMapping(value="/forward.do")
    public String forward(){
        return "1";
    }
}
