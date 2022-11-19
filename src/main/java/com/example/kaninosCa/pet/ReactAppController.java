package com.example.kaninosCa.pet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
//controller needed to allow the fronted to refresh pages without crashing
@Controller
public class ReactAppController {

    @RequestMapping(value = { "/", "/{x:[\\w\\-]+}", "/{x:^(?!api$).*$}/*/{y:[\\w\\-]+}","/error"  })
    public String getIndex(HttpServletRequest request) {
        return "/index.html";
    }


}
