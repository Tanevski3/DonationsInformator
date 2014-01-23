/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donations.controllers;

import com.donations.model.Phone;
import com.donations.services.LoginService;
import com.donations.services.SessionService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author user
 */
@Controller
public class PhoneController {
    
    @RequestMapping(method=RequestMethod.POST,value = "/addPhone")
    protected String addPhone(HttpServletRequest request, SessionService sessionService,LoginService loginService,
                            @RequestParam(value="phone")String _phone) {
        sessionService.init(request);
        loginService.setService(sessionService);
        
        List<Phone> phones=new ArrayList<Phone>();
        if (sessionService.getAttribute("phones")!=null) {
            phones=(ArrayList<Phone>)sessionService.getAttribute("phones");
        }
        phones.add(new Phone(_phone));
        sessionService.setAttribute("phones", phones);
        return "redirect:createdonation";
    }
    
}
