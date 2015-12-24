package me.daoke.renrenfm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by huangwenjing on 2015/7/25.
 */
@Controller
@RequestMapping("/page")
public class PageController {

    @RequestMapping("/{index}")
    public String page(@PathVariable("index")String index){
        return index;
    }

}
