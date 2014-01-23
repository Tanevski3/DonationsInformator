/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donations.controllers;

import com.donations.model.Account;
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
public class AccountController {

    @RequestMapping(method = RequestMethod.POST, value = "/addAccount")
    protected String addAccount(HttpServletRequest request, SessionService sessionService, LoginService loginService, @RequestParam(value = "account") String _account) {


        sessionService.init(request);
        loginService.setService(sessionService);

        List<Account> accounts = new ArrayList<Account>();
        if (sessionService.getAttribute("accounts") != null) {
            accounts = (ArrayList<Account>) sessionService.getAttribute("accounts");
        }
        accounts.add(new Account(_account));
        sessionService.setAttribute("accounts", accounts);
        return "redirect:createdonation";
    }
}
