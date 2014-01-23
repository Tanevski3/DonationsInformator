/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donations.controllers;

import com.donations.model.Donation;
import com.donations.model.Type;
import com.donations.model.User;
import com.donations.services.DonationService;
import com.donations.services.LoginService;
import com.donations.services.SessionService;
import com.donations.services.TypeService;
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
public class TypeController {

    @RequestMapping(method = RequestMethod.GET, value = "/type")
    public String type(HttpServletRequest request, HttpServletResponse response, TypeService typeService, ModelMap model, SessionService sessionService, LoginService loginService, DonationService donationService) {


        User loggedUser = null;
        sessionService.init(request);
        loginService.setService(sessionService);
        if (loginService.isLoggedIn()) {
            loggedUser = (User) sessionService.getAttribute("user");

            sessionService.setAttribute("user", loggedUser);
            sessionService.removeDonationAttributes();

            if (request.getParameterNames().hasMoreElements()) {
                String theid = request.getParameter("typeId");
                Type type = typeService.findTypeById(Integer.parseInt(theid));
                if (typeService.deleteType(type)) {
                    model.addAttribute("Action", "Successfully Deleted");
                } else {
                    model.addAttribute("Action", "Deletion failed!");
                }
                List<Type> allTypes = typeService.getAllTypes();
                model.addAttribute("allTypes", allTypes);
                model.addAttribute("loggeduserName", loggedUser.getFirstName());
                model.addAttribute("superValue", theid);
                return "type";
            } else {
                List<Type> allTypes = typeService.getAllTypes();
                model.addAttribute("allTypes", allTypes);
                model.addAttribute("loggeduserName", loggedUser.getFirstName());
                return "type";

            }
        } else {
            List<Donation> allDonation = donationService.getAllDonations();
            model.addAttribute("allDonation", allDonation);
            return "login";
        }
    }

    @RequestMapping(value = "/createtype", method = RequestMethod.GET)
    public String showCreateTypeForm(HttpServletRequest request, ModelMap model, TypeService typeService, SessionService sessionService, LoginService loginService, DonationService donationService) {

        User loggedUser = null;
        sessionService.init(request);
        loginService.setService(sessionService);
        if (loginService.isLoggedIn()) {
            loggedUser = (User) sessionService.getAttribute("user");

            sessionService.setAttribute("user", loggedUser);


            Type type = new Type();
            model.addAttribute("myCommand", type);
            model.addAttribute("loggeduserName", loggedUser.getFirstName());
            return "createtype";

        } else {
            List<Donation> allDonation = donationService.getAllDonations();
            model.addAttribute("allDonation", allDonation);
            return "home";
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/createtype")
    public String doCreateTypeForm(@ModelAttribute(value = "myCommand") Type type, BindingResult result, HttpServletRequest request, SessionService sessionService, LoginService loginService, DonationService donationService, TypeService typeService, ModelMap model, @RequestParam(value = "name", required = false) String name) {

        User loggedUser = null;
        sessionService.init(request);
        loginService.setService(sessionService);
        if (loginService.isLoggedIn()) {
            loggedUser = (User) sessionService.getAttribute("user");

            sessionService.setAttribute("user", loggedUser);
            type.setName(name);
            if (typeService.addType(type)) {
                model.addAttribute("Action", "Successfully Created");
            } else {
                model.addAttribute("LabelCreate", "Creation failed!");
            }

            List<Type> allTypes = typeService.getAllTypes();

            model.addAttribute("loggeduserName", loggedUser.getFirstName());
            model.addAttribute("allTypes", allTypes);
            return "type";

        } else {
            List<Donation> allDonation = donationService.getAllDonations();
            model.addAttribute("allDonation", allDonation);
            return "home";
        }
    }

    @RequestMapping(value = "/updatetype", method = RequestMethod.GET)
    public String showUpdateTypeForm(HttpServletRequest request, ModelMap model, TypeService typeService, SessionService sessionService, LoginService loginService, DonationService donationService) {


        User loggedUser = null;
        sessionService.init(request);
        loginService.setService(sessionService);
        if (loginService.isLoggedIn()) {
            loggedUser = (User) sessionService.getAttribute("user");

            sessionService.setAttribute("user", loggedUser);



            if (request.getParameterNames().hasMoreElements()) {
                Type type = new Type();
                String theid = request.getParameter("typeId");
                type.setId(Integer.parseInt(theid));
                model.addAttribute("updateTypeForm", type);
                model.addAttribute("idToDelete", theid);
                Type currentType = typeService.findTypeById(Integer.parseInt(theid));

                model.addAttribute("currentName", currentType.getName());
                model.addAttribute("loggeduserName", loggedUser.getFirstName());

                return "updatetype";
            } else {
                Type type = new Type();
                model.addAttribute("updateTypeForm", type);
                return "updatetype";
            }


        } else {
            List<Donation> allDonation = donationService.getAllDonations();
            model.addAttribute("allDonation", allDonation);
            return "home";
        }
    }

    @RequestMapping(value = "/updatetype", method = RequestMethod.POST)
    public String doUpdateTypeForm(HttpServletRequest request, @ModelAttribute(value = "updateTypeForm") Type type, BindingResult result, TypeService typeService, ModelMap model, @RequestParam(value = "name", required = false) String name) {

        type.setName(name);
        if (typeService.updateType(type)) {
                model.addAttribute("Action", "Successfully Updated");
            } else {
                model.addAttribute("Action", "Update failed!");
            }
        List<Type> allTypes = typeService.getAllTypes();
        model.addAttribute("allTypes", allTypes);
        return "type";
    }
}