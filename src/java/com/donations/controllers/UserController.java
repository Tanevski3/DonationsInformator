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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author user
 */
@Controller
public class UserController {

    @RequestMapping(method = RequestMethod.GET, value = "/user")
    public String user(@RequestParam(value = "from", required = false) Integer from, HttpServletRequest request, HttpServletResponse response, UserService userService, ModelMap model, SessionService sessionService, LoginService loginService, DonationService donationService) {

        User loggedUser = null;
        sessionService.init(request);
        loginService.setService(sessionService);
        List<User> allUsers = userService.getAllUsers();
        if (from==null) {
            from=10;
        }
        if (from < 10) {
            from = 10;
        }
        if (loginService.isLoggedIn()) {
            loggedUser = (User) sessionService.getAttribute("user");

            sessionService.setAttribute("user", loggedUser);
            sessionService.removeDonationAttributes();

            if (request.getParameterNames().hasMoreElements()) {
                model.addAttribute("from", from);
                model.addAttribute("allUsers", allUsers);
                model.addAttribute("loggeduserName", loggedUser.getFirstName());
                return "user";
            } else {
                model.addAttribute("from", 10);
                model.addAttribute("allUsers", allUsers);
                model.addAttribute("loggeduserName", loggedUser.getFirstName());
                return "user";

            }
        } else {
            return "login";
        }
    }

    @RequestMapping(value = "/createuser", method = RequestMethod.GET)
    public String showCreateUserForm(HttpServletRequest request, ModelMap model, SessionService sessionService, LoginService loginService, DonationService donationService) {


        User loggedUser = null;
        sessionService.init(request);
        loginService.setService(sessionService);
        if (loginService.isLoggedIn()) {
            loggedUser = (User) sessionService.getAttribute("user");

            sessionService.setAttribute("user", loggedUser);



            User user = new User();
            model.addAttribute("createUserForm", user);
            model.addAttribute("loggeduserName", loggedUser.getFirstName());
            return "createuser";

        } else {
            List<Donation> allDonation = donationService.getAllDonations();
            model.addAttribute("from", 4);
            model.addAttribute("allDonation", allDonation);
            return "home";
        }
    }

    @RequestMapping(value = "/updateuser", method = RequestMethod.GET)
    public String showUpdateUserForm(HttpServletRequest request, ModelMap model, UserService userService, SessionService sessionService, LoginService loginService, DonationService donationService) {

        User loggedUser = null;
        sessionService.init(request);
        loginService.setService(sessionService);
        if (loginService.isLoggedIn()) {
            loggedUser = (User) sessionService.getAttribute("user");

            sessionService.setAttribute("user", loggedUser);



            if (request.getParameterNames().hasMoreElements()) {
                User user = new User();
                String theid = request.getParameter("userId");
                user.setId(Integer.parseInt(theid));
                model.addAttribute("updateUserForm", user);
                model.addAttribute("idToDelete", theid);



                User currentUser = userService.getUserById(Integer.parseInt(theid));
                model.addAttribute("currentId", currentUser.getId());
                model.addAttribute("currentFirstName", currentUser.getFirstName());
                model.addAttribute("currentLastName", currentUser.getLastName());
                model.addAttribute("currentUsername", currentUser.getUsername());
                model.addAttribute("currentPassword", currentUser.getPassword());
                if (currentUser.getIsActive()) {
                    model.addAttribute("currentIsActive", "checked");
                } else {
                    model.addAttribute("currentIsActive", "");
                }
                model.addAttribute("currentEmail", currentUser.getEmail());
                model.addAttribute("loggeduserName", loggedUser.getFirstName());

                return "updateuser";
            } else {
                User user = new User();
                model.addAttribute("updateUserForm", user);
                return "updateuser";
            }


        } else {
            List<Donation> allDonation = donationService.getAllDonations();
            model.addAttribute("allDonation", allDonation);
            return "home";
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/createuser")
    public String doCreateDonationForm(@ModelAttribute("createUserForm") User user, BindingResult bindingResults,
            @RequestParam(value = "firstName", required = false) String first_name,
            @RequestParam(value = "lastName", required = false) String last_name,
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "isActive", required = false,defaultValue = "false") Boolean is_active,
            @RequestParam(value = "email", required = false) String email,
            ModelMap model, SessionService sessionService, LoginService loginService, DonationService donationService,
            HttpServletRequest request,
            HttpServletResponse response,
            UserService userService) {

        User loggedUser = null;
        sessionService.init(request);
        loginService.setService(sessionService);
        if (loginService.isLoggedIn()) {
            loggedUser = (User) sessionService.getAttribute("user");

            sessionService.setAttribute("user", loggedUser);


            user.setFirstName(first_name);
            user.setLastName(last_name);
            user.setUsername(username);
            user.setPassword(userService.getMD5(password));
            user.setIsActive(is_active);
            user.setEmail(email);
            userService.addUser(user);
            List<User> allUsers = userService.getAllUsers();
            model.addAttribute("Action", "Created Succesfully");
            model.addAttribute("from", 10);
            model.addAttribute("loggeduserName", loggedUser.getFirstName());
            model.addAttribute("allUsers", allUsers);
            return "user";

        } else {
            List<Donation> allDonation = donationService.getAllDonations();
            model.addAttribute("allDonation", allDonation);
            return "home";
        }
    }

    @RequestMapping(value = "/updateuser", method = RequestMethod.POST)
    public String doUpdateUser(@ModelAttribute(value = "updateUserForm") User user, BindingResult bindingResults, UserService userService,
            @RequestParam(value = "firstName", required = false) String first_name,
            @RequestParam(value = "lastName", required = false) String last_name,
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "isActive", required = false,defaultValue = "false") String is_active,
            @RequestParam(value = "email", required = false) String email,
            ModelMap model, HttpServletRequest request, SessionService sessionService, LoginService loginService, DonationService donationService, HttpServletResponse response) {


        User loggedUser = null;
        sessionService.init(request);
        loginService.setService(sessionService);
        if (loginService.isLoggedIn()) {
            loggedUser = (User) sessionService.getAttribute("user");

            sessionService.setAttribute("user", loggedUser);


            user.setFirstName(first_name);
            user.setLastName(last_name);
            user.setUsername(username);
            user.setPassword(userService.getMD5(password));
            user.setEmail(email);
            user.setIsActive(Boolean.parseBoolean(is_active));

            userService.updateUser(user);
            List<User> allUsers = userService.getAllUsers();


            model.addAttribute("Action", "Updated Succesfully ");
            model.addAttribute("from",10);
            model.addAttribute("loggeduserName", loggedUser.getFirstName());
            model.addAttribute("allUsers", allUsers);
            return "user";
        } else {
            List<Donation> allDonation = donationService.getAllDonations();
            model.addAttribute("allDonation", allDonation);
            return "home";
        }
    }
}
