package com.spring.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/** 主页
 * Created by Ranj on 2017/7/13.
 */


@Controller
public class IndexController {

    @RequestMapping("/main")
    public String main(){
        return "main";
    }

    @RequestMapping("/index")
    public String index(){
<<<<<<< HEAD
        long a=1L;
        return "index";
    }


=======
        return "index";
    }
>>>>>>> e5f5b84... 测试git第一次上传
}
