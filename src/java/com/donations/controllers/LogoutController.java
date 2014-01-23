/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donations.controllers;

import com.donations.services.LoginService;
import com.donations.services.SessionService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author user
 */
@Controller
public class LogoutController {

    @RequestMapping(value = "/logout")
    public String doLogout(SessionService sessionService, HttpServletRequest request, LoginService loginService) {


        sessionService.init(request);
        loginService.setService(sessionService);
        if (loginService.isLoggedIn()) {

            sessionService.closeSession();
            return "redirect:home";
        }

        return "redirect:home";
    }
}
