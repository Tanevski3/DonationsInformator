/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donations.controllers;

import com.donations.model.Donation;
import com.donations.model.User;
import com.donations.services.DonationService;
import com.donations.services.LoginService;
import com.donations.services.SessionService;
import com.donations.services.UserService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author user
 */
@Controller
public class LoginController {


    @RequestMapping(value = "/login")
    public String showLoginForm(ModelMap model) {

        return "login";
    }

    @RequestMapping(value = "/donation")
    public String doLogin(HttpServletRequest request, HttpServletResponse response,
            @RequestParam("username") String username, @RequestParam("password") String password,
            UserService userService, ModelMap model, DonationService donationService, SessionService sessionService, LoginService loginService) 
    {
        User loggedUser;
        loggedUser = userService.findUser(username, userService.getMD5(password));
        if (loggedUser == null) {

            model.addAttribute("NOTLOGED", "Incorrect Username or Password");
            return "login";

        } else {
            List<Donation> allDonation = donationService.getAllOrderedDonations("title asc");
            model.addAttribute("allDonation", allDonation);
            model.addAttribute("from", 10);
            sessionService.init(request);
            loginService.setService(sessionService);
            if (loginService.isLoggedIn()) {
                List<Donation> allDonations = donationService.getAllOrderedDonations("title asc");
                model.addAttribute("allDonations", allDonations);
                model.addAttribute("loggeduserName", loggedUser.getFirstName());
                model.addAttribute("from", 10);
                return "donation";
            }
            sessionService.setAttribute("user", loggedUser);
            List<Donation> allDonations = donationService.getAllOrderedDonations("title asc");
            model.addAttribute("allDonations", allDonations);
            model.addAttribute("loggeduserName", loggedUser.getFirstName());
            model.addAttribute("from", 10);
            return "donation";

        }



    }
}
