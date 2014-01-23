package com.donations.controllers;

import com.donations.model.Account;
import com.donations.model.Category;
import com.donations.model.Donation;
import com.donations.model.Phone;
import com.donations.model.Type;
import com.donations.model.User;
import com.donations.services.AccountService;
import com.donations.services.CategoryService;
import com.donations.services.DonationService;
import com.donations.services.LoginService;
import com.donations.services.PhoneService;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author user
 */
@Controller
public class DonationController {

    @RequestMapping(method = RequestMethod.GET, value = "/donation")
    public String donation(@RequestParam(value = "from", required = false) String from, HttpServletRequest request, HttpServletResponse response, DonationService donationService, ModelMap model, SessionService sessionService, LoginService loginService) {

        User loggedUser = null;
        sessionService.init(request);
        loginService.setService(sessionService);

        String order = request.getParameter("order");
        String donationId = request.getParameter("donationId");
        List<Donation> allDonations = donationService.getAllOrderedDonations(order);

        Integer fromDonation = 8;
        if (from == null || from.isEmpty()) {
            fromDonation = 8;
        } else {
            try {
                fromDonation = Integer.parseInt(from);
            } catch (Exception ex) {
                fromDonation = 8;
            }
            if (fromDonation < 8) {
                fromDonation = 8;
            }
        }

        if (order == null) {
            order = "title asc";
        }

        if (loginService.isLoggedIn()) {
            loggedUser = (User) sessionService.getAttribute("user");
            sessionService.setAttribute("user", loggedUser);
            sessionService.removeDonationAttributes();
            if (request.getParameterNames().hasMoreElements()) {

                if (donationId != null) {
                    Donation donation = donationService.findDonationbyId(Integer.parseInt(donationId));
                    donationService.deleteDonation(donation);
                    model.addAttribute("Action", "Deleted Succesfully");
                }

                List<Donation> allDonations1 = donationService.getAllOrderedDonations(order);
                model.addAttribute("from", fromDonation);
                model.addAttribute("allDonations", allDonations1);
                model.addAttribute("loggeduserName", loggedUser.getFirstName());
                return "donation";
            } else {
                List<Donation> allDonations2 = donationService.getAllOrderedDonations(order);
                model.addAttribute("from", 8);
                model.addAttribute("allDonations", allDonations2);
                model.addAttribute("loggeduserName", loggedUser.getFirstName());
                return "donation";
            }
        } else {
            return "login";
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/createdonation")
    public String createDonationForm(HttpServletRequest request, HttpServletResponse response, ModelMap model, CategoryService categoryService, TypeService typeService, DonationService donationService, SessionService sessionService, LoginService loginService) {


        User loggedUser = null;
        sessionService.init(request);
        loginService.setService(sessionService);
        if (loginService.isLoggedIn()) {
            loggedUser = (User) sessionService.getAttribute("user");
            sessionService.setAttribute("user", loggedUser);
            List<Category> categories = categoryService.getAllCategories();
            List<Type> types = typeService.getAllTypes();
            Donation donation = new Donation();
            model.addAttribute("imageLocation", sessionService.getAttribute("imageLocation"));
            model.addAttribute("allCategories", categories);
            model.addAttribute("allTypes", types);
            model.addAttribute("createDonation", donation);
            model.addAttribute("loggeduserName", loggedUser.getFirstName());
            if (sessionService.getAttribute("imageLocation") != null) {
                model.addAttribute("imageLocation", sessionService.getAttribute("imageLocation").toString());
            }
            if (sessionService.getAttribute("titleHidden") != null) {
                model.addAttribute("title", sessionService.getAttribute("titleHidden").toString());
            }
            if (sessionService.getAttribute("descriptionHidden") != null) {
                model.addAttribute("description", sessionService.getAttribute("descriptionHidden").toString());
            }
            if (sessionService.getAttribute("priorityHidden") != null) {
                model.addAttribute("priority", sessionService.getAttribute("priorityHidden").toString());
            }
            if (sessionService.getAttribute("donationUrlHidden") != null) {
                model.addAttribute("donationUrl", sessionService.getAttribute("donationUrlHidden").toString());
            }
            if (sessionService.getAttribute("endDateHidden") != null) {
                model.addAttribute("endDate", sessionService.getAttribute("endDateHidden").toString());
            }
            if (sessionService.getAttribute("categoryHidden") != null) {
                model.addAttribute("selectedCategory", sessionService.getAttribute("categoryHidden").toString());
            }
            if (sessionService.getAttribute("typeHidden") != null) {
                model.addAttribute("selectedType", sessionService.getAttribute("typeHidden").toString());
            }

            return "createdonation";
        } else {
            List<Donation> allDonation = donationService.getAllDonations();
            model.addAttribute("allDonation", allDonation);
            return "home";
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/createdonation")
    public String doCreateDonationForm(@ModelAttribute("createDonation") Donation donation, BindingResult bindingResults,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "donationUrl", required = false) String donationUrl,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "imageSource", required = false) String imageSource,
            @RequestParam(value = "endDate", required = false) String endDate,
            @RequestParam(value = "priority", required = false) String priority,
            @RequestParam(value = "user.id", required = false) String userId,
            @RequestParam(value = "category.id", required = false) String categoryId,
            @RequestParam(value = "type.id", required = false) String typeId,
            @RequestParam(value = "published", required = false, defaultValue = "false") Boolean published,
            @RequestParam(value="phones")String[] phones1,
            ModelMap model,
            HttpServletRequest request,
            HttpServletResponse response,
            DonationService donationService,
            SessionService sessionService,
            LoginService loginService,
            PhoneService phoneService,
            AccountService accountService) {


        String[] goodle=request.getParameterValues("phones");
        String asd=request.getParameter("phones");
        User loggedUser = null;
        sessionService.init(request);
        loginService.setService(sessionService);
        if (loginService.isLoggedIn()) {
            loggedUser = (User) sessionService.getAttribute("user");
            sessionService.setAttribute("user", loggedUser);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            donation.setTitle(title);
            donation.setDescription(description);
            donation.setDonationUrl(donationUrl);
            donation.setPublished(published);
            donation.setImageSource(sessionService.getAttribute("imageLocation").toString().substring(13));
            // do file upload specific process
            try {
                donation.setStartDate(new Date());
                donation.setEndDate(format.parse(endDate));
            } catch (ParseException ex) {
                ex.getCause();
            }

            donation.setPriority(Integer.parseInt(priority));
            donation.setUser(loggedUser);
            Category currentCategory = donation.getCategory();
            currentCategory.setId(Integer.parseInt(categoryId));
            Type currentType = donation.getType();
            currentType.setId(Integer.parseInt(typeId));
            
            List<Phone> phones = new ArrayList<Phone>();

            if (sessionService.getAttribute("phones") != null) {
                phones = (ArrayList<Phone>) sessionService.getAttribute("phones");
                donation.setPhones(phones);
            }
            for (Phone phone : phones) {
                phone.setDonation(donation);
                phoneService.addPhone(phone);
            }

            List<Account> accounts = new ArrayList<Account>();
            if (sessionService.getAttribute("accounts") != null) {
                accounts = (ArrayList<Account>) sessionService.getAttribute("accounts");
                donation.setAccounts(accounts);
            }
            for (Account account : accounts) {
                account.setDonation(donation);
                accountService.addAccount(account);
            }

            donationService.addDonation(donation);


            List<Donation> allDonations = donationService.getAllDonations();
            model.addAttribute("from", 8);
            model.addAttribute("Action", "Succesfully Created");
            model.addAttribute("loggeduserName", loggedUser.getFirstName());
            model.addAttribute("allDonations", allDonations);
            sessionService.removeDonationAttributes();
            return "donation";
            /*}*/

        } else {
            List<Donation> allDonation = donationService.getAllDonations();
            model.addAttribute("allDonation", allDonation);
            return "home";
        }
    }

    @RequestMapping(value = "/updatedonation", method = RequestMethod.GET)
    public String showUpdateDonationForm(AccountService accountService, PhoneService phoneService, HttpServletRequest request, ModelMap model, DonationService donationService, TypeService typeService, CategoryService categoryService, SessionService sessionService, LoginService loginService) {


        String path = "/data/images/";
        User loggedUser = null;
        sessionService.init(request);
        loginService.setService(sessionService);
        if (loginService.isLoggedIn()) {
            loggedUser = (User) sessionService.getAttribute("user");
            sessionService.setAttribute("user", loggedUser);

            if (request.getParameterNames().hasMoreElements()) {
                Donation donation = new Donation();
                String donationId = request.getParameter("donationId");
                donation.setId(Integer.parseInt(donationId));
                Donation currentDonation = donationService.findDonationbyId(Integer.parseInt(donationId));
                List<Category> categories = categoryService.getAllCategories();
                List<Type> types = typeService.getAllTypes();
                model.addAttribute("allCategories", categories);
                model.addAttribute("allTypes", types);


                if (sessionService.getAttribute("imageLocation") != null) {
                    model.addAttribute("imageLocation", sessionService.getAttribute("imageLocation").toString());
                }
                if (sessionService.getAttribute("descriptionHidden") != null) {
                    model.addAttribute("description", sessionService.getAttribute("descriptionHidden").toString());
                }
                if (sessionService.getAttribute("priorityHidden") != null) {
                    model.addAttribute("priority", sessionService.getAttribute("priorityHidden").toString());
                }
                if (sessionService.getAttribute("donationUrlHidden") != null) {
                    model.addAttribute("donationUrl", sessionService.getAttribute("donationUrlHidden").toString());
                }
                if (sessionService.getAttribute("endDateHidden") != null) {
                    model.addAttribute("endDate", sessionService.getAttribute("endDateHidden").toString());
                }
                if (sessionService.getAttribute("categoryHidden") != null) {
                    model.addAttribute("selectedCategory", sessionService.getAttribute("categoryHidden").toString());
                }
                if (sessionService.getAttribute("typeHidden") != null) {
                    model.addAttribute("selectedType", sessionService.getAttribute("typeHidden").toString());
                }




                String phone1 = request.getParameter("phone");
                String account1 = request.getParameter("account");
                model.addAttribute("donationPhone", currentDonation.getPhones().isEmpty() ? "NO PHONE" : currentDonation.getPhones().get(currentDonation.getPhones().size() - 1).getNumber());
                model.addAttribute("donationAccount", currentDonation.getAccounts().isEmpty() ? "NO ACCOUNT" : currentDonation.getAccounts().get(currentDonation.getAccounts().size() - 1).getNumber().toString());
                model.addAttribute("updateDonationForm", donation);
                model.addAttribute("idToDelete", donationId);
                model.addAttribute("currentTitle", sessionService.getAttribute("titleHidden") == null ? currentDonation.getTitle() : sessionService.getAttribute("titleHidden").toString());
                model.addAttribute("currentDonationUrl", sessionService.getAttribute("donationUrlHidden") == null ? currentDonation.getDonationUrl() : sessionService.getAttribute("donationUrlHidden").toString());
                model.addAttribute("currentDescription", sessionService.getAttribute("descriptionHidden") == null ? currentDonation.getDescription() : sessionService.getAttribute("descriptionHidden").toString());
                model.addAttribute("currentImageSource", sessionService.getAttribute("imageLocation") == null ? currentDonation.getImageSource() : sessionService.getAttribute("imageLocation").toString().substring(13));
                model.addAttribute("currentStartDate", currentDonation.getStartDate());
                model.addAttribute("currentEndDate", sessionService.getAttribute("endDateHidden") == null ? currentDonation.getEndDate() : sessionService.getAttribute("endDateHidden").toString());
                model.addAttribute("currentPriority", sessionService.getAttribute("priorityHidden") == null ? currentDonation.getPriority() : sessionService.getAttribute("priorityHidden").toString());
                if (currentDonation.getPublished()) {
                    model.addAttribute("currentPublished", "checked");
                } else {
                    model.addAttribute("currentPublished", "");
                }
                model.addAttribute("currentUserId", currentDonation.getUser().getId());
                model.addAttribute("currentCategoryId", sessionService.getAttribute("categoryHidden") == null ? currentDonation.getCategory().getId() : sessionService.getAttribute("categoryHidden").toString());
                model.addAttribute("currentTypeId", sessionService.getAttribute("typeHidden") == null ? currentDonation.getType().getId() : sessionService.getAttribute("categoryHidden").toString());
                model.addAttribute("loggeduserName", loggedUser.getFirstName());

                return "updatedonation";
            } else {

                Donation donation = new Donation();
                List<Category> categories = categoryService.getAllCategories();
                List<Type> types = typeService.getAllTypes();
                model.addAttribute("allCategories", categories);
                model.addAttribute("allTypes", types);
                model.addAttribute("updateDonationForm", donation);
                return "updatedonation";
            }


        } else {
            List<Donation> allDonation = donationService.getAllDonations();
            model.addAttribute("allDonation", allDonation);
            return "home";
        }
    }

    @RequestMapping(value = "/updatedonation", method = RequestMethod.POST)
    public String doUpdateUserForm(HttpServletRequest request, @ModelAttribute(value = "updateDonationForm") Donation donation, BindingResult result, SessionService sessionService, LoginService loginService, DonationService donationService, ModelMap model,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "donationUrl", required = false) String donationUrl,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "imageSource", required = false) String imageSource,
            @RequestParam(value = "endDate", required = false) String endDate,
            @RequestParam(value = "priority", required = false) String priority,
            @RequestParam(value = "user.id", required = false) String userId,
            @RequestParam(value = "category.id", required = false) String categoryId,
            @RequestParam(value = "phones", required = false) String phone,
            @RequestParam(value = "accounts", required = false) String account,
            @RequestParam(value = "type.id", required = false) String typeId,
            @RequestParam(value = "published", required = false, defaultValue = "false") Boolean published) {


        User loggedUser = null;
        sessionService.init(request);
        loginService.setService(sessionService);
        if (loginService.isLoggedIn()) {
            loggedUser = (User) sessionService.getAttribute("user");
            sessionService.setAttribute("user", loggedUser);

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-DD");
            donation.setTitle(title);
            donation.setDescription(description);
            donation.setDonationUrl(donationUrl);
            donation.setStartDate(new Date());

            try {
                donation.setEndDate(format.parse(endDate));
            } catch (ParseException ex) {
                ex.printStackTrace();
            }

            donation.setImageSource(imageSource.toString());
            donation.setPriority(Integer.parseInt(priority));
            donation.setPublished(published);
            User currentUser = donation.getUser();
            currentUser.setId(Integer.parseInt(userId));
            Category currentCategory = donation.getCategory();
            currentCategory.setId(Integer.parseInt(categoryId));
            Type currentType = donation.getType();
            currentType.setId(Integer.parseInt(typeId));

            List<Phone> phones = new ArrayList<Phone>();
            Phone tPhone = new Phone();
            tPhone.setNumber(phone);
            tPhone.setDonation(donation);
            phones.add(tPhone);
            donation.setPhones(phones);

            List<Account> accounts = new ArrayList<Account>();
            Account tAccount = new Account();
            tAccount.setNumber(account);
            tAccount.setDonation(donation);
            accounts.add(tAccount);
            donation.setAccounts(accounts);

            donationService.updateDonation(donation);
            List<Donation> allDonations = donationService.getAllDonations();
            model.addAttribute("from", 8);
            model.addAttribute("Action", "Succesfully Updated");
            model.addAttribute("allDonations", allDonations);
            sessionService.removeDonationAttributes();
            return "donation";



        } else {
            List<Donation> allDonation = donationService.getAllDonations();
            model.addAttribute("allDonation", allDonation);
            return "home";
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/donationdetail")
    public String detaildonation(SessionService sessionService, LoginService loginService, HttpServletRequest request, ModelMap model, DonationService donationService) {



        if (request.getParameterNames().hasMoreElements()) {
            Donation donation = new Donation();
            String donationId = request.getParameter("donationId");
            donation.setId(Integer.parseInt(donationId));


            Donation currentDonation = donationService.findDonationbyId(Integer.parseInt(donationId));

            model.addAttribute("currentTitle", currentDonation.getTitle());
            model.addAttribute("currentDonationUrl", currentDonation.getDonationUrl());
            model.addAttribute("currentDescription", currentDonation.getDescription());
            model.addAttribute("currentImageSource", currentDonation.getImageSource());
            model.addAttribute("currentStartDate", currentDonation.getStartDate());
            model.addAttribute("currentEndDate", currentDonation.getEndDate());
            model.addAttribute("currentPriority", currentDonation.getPriority());
            model.addAttribute("currentUserId", currentDonation.getUser().getId());
            model.addAttribute("currentCategoryId", currentDonation.getCategory().getId());
            model.addAttribute("currentTypeId", currentDonation.getType().getId());
            model.addAttribute("currentDonationPhone", currentDonation.getPhones().isEmpty() ? "/" : currentDonation.getPhones().get(currentDonation.getPhones().size() - 1).getNumber());
            model.addAttribute("currentDonationAccount", currentDonation.getAccounts().isEmpty() ? "/" : currentDonation.getAccounts().get(currentDonation.getAccounts().size() - 1).getNumber().toString());
            return "donationdetail";
        }
        return "donationdetail";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/donationdetail")
    public String detaildonation() {
        return "donationdetail";
    }
}
