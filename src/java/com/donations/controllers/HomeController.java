package com.donations.controllers;

import com.donations.model.About;
import com.donations.model.Category;
import com.donations.model.Donation;
import com.donations.model.Type;
import com.donations.model.User;
import com.donations.services.AboutService;
import com.donations.services.DonaciiMkService;
import com.donations.services.DonationService;
import com.donations.services.LoginService;
import com.donations.services.SessionService;
import com.donations.services.UserService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
 * @author nbuser
 */
@Controller
public class HomeController {

    @RequestMapping(method = RequestMethod.POST, value = "/scrapeDonaciiMk")
    protected String addAccount(HttpServletRequest request, DonationService donationService) throws ParseException {


        List<String> entryIntroList = new ArrayList<String>();
        List<String> images = new ArrayList<String>();
        List<String> contents = new ArrayList<String>();
        List<Donation> scrapeDonations = new ArrayList<Donation>();

        Integer counter0 = 0;
        for (String href : DonaciiMkService.getPageHrefsForDate(2013, 3)) {
            entryIntroList.add(DonaciiMkService.getEntryIntro(DonaciiMkService.sendGetRequest(href).toString()));
            images.add(DonaciiMkService.getEntryImage(DonaciiMkService.sendGetRequest(href).toString()));
            contents.add(DonaciiMkService.getEntryContent(DonaciiMkService.sendGetRequest(href).toString()));
            scrapeDonations.add(new Donation());
            scrapeDonations.get(counter0).setDescription(contents.get(counter0));
            scrapeDonations.get(counter0).setUser(new User());
            scrapeDonations.get(counter0).setCategory(new Category());
            scrapeDonations.get(counter0).setType(new Type());
            scrapeDonations.get(counter0).setPublished(Boolean.FALSE);
        }
        Integer counter1 = 0;
        List<String> titles = new ArrayList<String>();
        for (String entryIntro : entryIntroList) {
            counter1++;
            titles.add(DonaciiMkService.getTitleForEntryIntro(entryIntro));
            scrapeDonations.get(counter1).setTitle(entryIntro);
        }
        Integer counter2 = 0;
        List<String> dates = new ArrayList<String>();
        for (String entryIntro : entryIntroList) {
            dates.add(DonaciiMkService.getStartDateForEntryIntro(entryIntro));
            SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd");
            scrapeDonations.get(counter2).setStartDate(spf.parse(entryIntro));
        }
        Integer counter3 = 0;
        List<String> imageUrls = new ArrayList<String>();
        for (String image : images) {
            imageUrls.add(DonaciiMkService.getEntryImageRefForImage(image));
            scrapeDonations.get(counter2).setImageSource(image);
        }
        for (Donation donation : scrapeDonations) {
            donationService.addDonation(donation);
        }
        return "home";
    }

    @RequestMapping(value = "/home")
    public String listAllDonation(@RequestParam(value = "from", required = false) Integer from,
            HttpServletRequest request, HttpServletResponse response, ModelMap model,
            DonationService donationService) {
        List<Donation> allDonations = donationService.getNewDonations();

        if (request.getParameterNames().hasMoreElements()) {

            if (from < 4 || from > allDonations.size()+5) {
                model.addAttribute("from", 4);
                model.addAttribute("allDonation", allDonations);
                return "home";
            } else {
                model.addAttribute("from", from);
                model.addAttribute("allDonation", allDonations);
                return "home";
            }
        } else {
           /* List<String> entryIntroList = new ArrayList<String>();
            for (String href : DonaciiMkService.getPageHrefsForDate(2013, 3)) {
                entryIntroList.add(DonaciiMkService.getEntryIntro(DonaciiMkService.sendGetRequest(href).toString()));
            }
            List<String> titles = new ArrayList<String>();
            for (String entryIntro : entryIntroList) {
                titles.add(DonaciiMkService.getTitleForEntryIntro(entryIntro));
            }
            List<String> images = new ArrayList<String>();
            for (String href : DonaciiMkService.getPageHrefsForDate(2013, 3)) {
                images.add(DonaciiMkService.getEntryImage(DonaciiMkService.sendGetRequest(href).toString()));
            }
            List<String> imageUrls = new ArrayList<String>();
            for (String image : images) {
                imageUrls.add(DonaciiMkService.getEntryImageRefForImage(image));
            }
            List<String> contents = new ArrayList<String>();
            for (String href : DonaciiMkService.getPageHrefsForDate(2013, 3)) {
                contents.add(DonaciiMkService.getEntryContent(DonaciiMkService.sendGetRequest(href).toString()));
            }
            model.addAttribute("results", contents);*/
            model.addAttribute("from", 4);
            model.addAttribute("allDonation", allDonations);
            return "home";
        }
    }

    @RequestMapping(value = "/donationdetail")
    public String DonationDetail(HttpServletRequest request, HttpServletResponse response, ModelMap model, DonationService donationService) {
        List<Donation> allDonation = donationService.getAllDonations();
        model.addAttribute("allDonation", allDonation);
        return "donationdetail";
    }

    @RequestMapping(value = "/aboutus")
    public String Aboutus(HttpServletRequest request, HttpServletResponse response, ModelMap model, DonationService donationService, AboutService aboutService) {

        List<About> abouts = aboutService.getAbout();
        model.addAttribute("abouts", abouts);
        return "aboutus";
    }

    @RequestMapping(value = "/aboutusadminpanel")
    public String AboutusAdminPanel(HttpServletRequest request, HttpServletResponse response, ModelMap model, DonationService donationService, SessionService sessionService, LoginService loginService, AboutService aboutService) {

        User loggedUser = null;
        sessionService.init(request);
        loginService.setService(sessionService);
        if (loginService.isLoggedIn()) {
            loggedUser = (User) sessionService.getAttribute("user");
            List<About> abouts = aboutService.getAbout();
            model.addAttribute("abouts", abouts);
            model.addAttribute("loggeduserName", loggedUser.getFirstName());
            return "aboutusadminpanel";
        }
        List<Donation> allDonation = donationService.getAllDonations();
        model.addAttribute("allDonation", allDonation);
        return "login";
    }

    @RequestMapping(value = "/updateabout", method = RequestMethod.GET)
    public String showUpdateAbout(HttpServletRequest request, ModelMap model, UserService userService, SessionService sessionService, LoginService loginService, DonationService donationService, AboutService aboutService) {

        User loggedUser = null;
        sessionService.init(request);
        loginService.setService(sessionService);
        if (loginService.isLoggedIn()) {
            loggedUser = (User) sessionService.getAttribute("user");

            sessionService.setAttribute("user", loggedUser);

            About about = new About();
            about.setId(1);
            model.addAttribute("UpdateAboutUs", about);
            model.addAttribute("idToUpdate", 1);
            About currentAbout = aboutService.getAboutById(1);
            model.addAttribute("currentId", currentAbout.getId());
            model.addAttribute("currentContent", currentAbout.getContent());
            model.addAttribute("currentCountry", currentAbout.getCountry());
            model.addAttribute("currentCity", currentAbout.getCity());
            model.addAttribute("currentTelephone", currentAbout.getTelephone());
            model.addAttribute("currentFax", currentAbout.getFax());
            model.addAttribute("currentEmail", currentAbout.getEmail());
            model.addAttribute("loggeduserName", loggedUser.getFirstName());

            return "updateabout";
        } else {

            List<Donation> allDonation = donationService.getAllDonations();
            model.addAttribute("allDonation", allDonation);
            return "home";
        }
    }

    @RequestMapping(value = "/updateabout", method = RequestMethod.POST)
    public String updateuser(@ModelAttribute(value = "UpdateAboutUs") About about, BindingResult bindingResults, SessionService sessionService, LoginService loginService, DonationService donationService, AboutService aboutService,
            @RequestParam(value = "content", required = false) String content,
            @RequestParam(value = "country", required = false) String country,
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(value = "telephone", required = false) String telephone,
            @RequestParam(value = "fax", required = false) String fax,
            @RequestParam(value = "email", required = false) String email,
            ModelMap model, HttpServletRequest request, HttpServletResponse response) {

        User loggedUser = null;
        sessionService.init(request);
        loginService.setService(sessionService);
        if (loginService.isLoggedIn()) {
            loggedUser = (User) sessionService.getAttribute("user");

            sessionService.setAttribute("user", loggedUser);

            about.setContent(content);
            about.setCountry(country);
            about.setCity(city);
            about.setTelephone(telephone);
            about.setFax(fax);
            about.setEmail(email);

            aboutService.updateAbout(about);
            model.addAttribute("loggeduserName", loggedUser.getFirstName());
            List<About> abouts = aboutService.getAbout();
            model.addAttribute("abouts", abouts);
            return "aboutusadminpanel";
        }
        List<Donation> allDonation = donationService.getAllDonations();
        model.addAttribute("allDonation", allDonation);
        return "home";
    }

    @RequestMapping(value = "/archive", method = RequestMethod.GET)
    public String listallolddonations(@RequestParam(value = "from", required = false) Integer from, HttpServletRequest request, HttpServletResponse response, ModelMap model, DonationService donationService) {

        List<Donation> allDonations = donationService.getAllOldDonations();

        if (request.getParameterNames().hasMoreElements()) {

            if (from == null) {
                from = 4;
            }
            if (from < 4 || from > allDonations.size() + 4) {
                from = 4;
            }
            if (from < 4) {
                model.addAttribute("from", 4);
                model.addAttribute("allDonationOld", allDonations);
                return "archive";
            } else {

                model.addAttribute("from", from);
                model.addAttribute("allDonationOld", allDonations);
                return "archive";
            }
        } else {
            model.addAttribute("from", 4);
            model.addAttribute("allDonationOld", allDonations);
            return "archive";
        }
    }
}
