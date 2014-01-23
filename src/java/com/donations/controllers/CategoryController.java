/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donations.controllers;

import com.donations.model.Category;
import com.donations.model.Donation;
import com.donations.model.User;
import com.donations.services.CategoryService;
import com.donations.services.DonationService;
import com.donations.services.LoginService;
import com.donations.services.SessionService;
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
public class CategoryController {

    @RequestMapping(method = RequestMethod.GET, value = "/category")
    public String category(HttpServletRequest request, HttpServletResponse response, CategoryService categoryService, ModelMap model, SessionService sessionService, LoginService loginService, DonationService donationService) {

        User loggedUser = null;
        sessionService.init(request);
        loginService.setService(sessionService);
        if (loginService.isLoggedIn()) {
            loggedUser = (User) sessionService.getAttribute("user");
            sessionService.setAttribute("user", loggedUser);
            sessionService.removeDonationAttributes();
            if (request.getParameterNames().hasMoreElements()) {
                String categoryId = request.getParameter("categoryId");
                Category category = categoryService.findCategoryById(Integer.parseInt(categoryId));
                if (categoryService.deleteCategory(category)) {
                    model.addAttribute("Action", "Successfully Deleted");
                } else {
                    model.addAttribute("Action", "Deletion failed!");
                }
                List<Category> allCategories = categoryService.getAllCategories();
                model.addAttribute("allCategories", allCategories);
                model.addAttribute("superValue", categoryId);
                model.addAttribute("loggeduserName", loggedUser.getFirstName());
                return "category";
            } else {
                List<Category> allCategories = categoryService.getAllCategories();
                model.addAttribute("allCategories", allCategories);
                model.addAttribute("loggeduserName", loggedUser.getFirstName());
                return "category";

            }
        } else {
            List<Donation> allDonation = donationService.getAllDonations();
            model.addAttribute("allDonation", allDonation);
            return "login";
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/createcategory")
    public String createCategoryForm(HttpServletRequest request, HttpServletResponse response, ModelMap model, CategoryService categoryService, SessionService sessionService, LoginService loginService, DonationService donationService) {


        User loggedUser = null;
        sessionService.init(request);
        loginService.setService(sessionService);
        if (loginService.isLoggedIn()) {
            loggedUser = (User) sessionService.getAttribute("user");
            sessionService.setAttribute("user", loggedUser);




            Category category = new Category();
            model.addAttribute("createCategory", category);
            model.addAttribute("loggeduserName", loggedUser.getFirstName());
            return "createcategory";
        } else {
            List<Donation> allDonation = donationService.getAllDonations();
            model.addAttribute("allDonation", allDonation);
            return "home";
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/createcategory")
    public String doCreateCategory(@ModelAttribute(value = "createCategory") Category category, DonationService donationService, HttpServletRequest request, SessionService sessionService, LoginService loginService, BindingResult result, CategoryService categoryService, ModelMap model,
            @RequestParam(value = "name", required = false) String name) {

        User loggedUser = null;
        sessionService.init(request);
        loginService.setService(sessionService);
        if (loginService.isLoggedIn()) {
            loggedUser = (User) sessionService.getAttribute("user");
            sessionService.setAttribute("user", loggedUser);


            category.setName(name);
            List<Category> allCategories = null;
            if (categoryService.addCategory(category)) {
                allCategories = categoryService.getAllCategories();
                model.addAttribute("Action", "Successfully Created");
                model.addAttribute("allCategories", allCategories);
            } else {
                model.addAttribute("LabelCreate", "Creation failed!");
            }
            model.addAttribute("loggeduserName", loggedUser.getFirstName());
            model.addAttribute("allCategories", allCategories);

            return "category";

        } else {
            List<Donation> allDonation = donationService.getAllDonations();
            model.addAttribute("allDonation", allDonation);
            return "home";
        }
    }

    @RequestMapping(value = "/updatecategory", method = RequestMethod.GET)
    public String showUpdateCategoryForm(HttpServletRequest request, ModelMap model, CategoryService categoryService, SessionService sessionService, LoginService loginService, DonationService donationService) {


        User loggedUser = null;
        sessionService.init(request);
        loginService.setService(sessionService);
        if (loginService.isLoggedIn()) {
            loggedUser = (User) sessionService.getAttribute("user");
            sessionService.setAttribute("user", loggedUser);



            if (request.getParameterNames().hasMoreElements()) {
                Category category = new Category();
                String categoryId = request.getParameter("categoryId");
                category.setId(Integer.parseInt(categoryId));
                model.addAttribute("updateCategoryForm", category);
                model.addAttribute("idToDelete", categoryId);

                Category currentCategory = categoryService.findCategoryById(Integer.parseInt(categoryId));

                model.addAttribute("currentName", currentCategory.getName());
                model.addAttribute("loggeduserName", loggedUser.getFirstName());

                return "updatecategory";
            } else {
                Category category = new Category();
                model.addAttribute("updateCategoryForm", category);
                return "updatecategory";
            }


        } else {
            List<Donation> allDonation = donationService.getAllDonations();
            model.addAttribute("allDonation", allDonation);
            return "home";
        }
    }

    @RequestMapping(value = "/updatecategory", method = RequestMethod.POST)
    public String doUpdateCategoryForm(HttpServletRequest request, @ModelAttribute(value = "updateCategoryForm") Category category, BindingResult result, CategoryService categoryService, ModelMap model, DonationService donationService, SessionService sessionService, LoginService loginService,
            @RequestParam(value = "name", required = false) String name) {

        User loggedUser = null;
        sessionService.init(request);
        loginService.setService(sessionService);
        if (loginService.isLoggedIn()) {
            loggedUser = (User) sessionService.getAttribute("user");
            sessionService.setAttribute("user", loggedUser);

            category.setName(name);

            model.addAttribute("loggeduserName", loggedUser.getFirstName());
            if (categoryService.updateCategory(category)) {
                model.addAttribute("Action", "Successfully Updated");
            } else {
                model.addAttribute("LabelCreate", "Update failed!");
            }
            List<Category> allCategories = categoryService.getAllCategories();
            model.addAttribute("allCategories", allCategories);
            return "category";

        } else {
            List<Donation> allDonation = donationService.getAllDonations();
            model.addAttribute("allDonation", allDonation);
            return "home";
        }
    }
}
