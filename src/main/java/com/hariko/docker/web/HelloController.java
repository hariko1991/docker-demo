package com.hariko.docker.web;

import com.hariko.docker.factory.HarikoFactoryBean;
import com.hariko.docker.factory.entity.HarikoBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class HelloController {
    @Resource
    private HarikoFactoryBean harikoBean;

    @RequestMapping("/hello")
    @ResponseBody
    public String toTest() {
        return "Hello World";
    }

    @RequestMapping("/")
    public String index(ModelMap map) {
        map.addAttribute("host", "http://www.baidu.com/");
        return "index";
    }

}