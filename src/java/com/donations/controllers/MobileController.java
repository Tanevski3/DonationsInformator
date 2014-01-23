package com.donations.controllers;

import com.donations.model.About;
import com.donations.model.Blog;
import com.donations.model.Donation;
import com.donations.services.AboutService;
import com.donations.services.BlogService;
import com.donations.services.DonationService;
import com.donations.services.LoginService;
import com.donations.services.SessionService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author user
 */
@Controller
public class MobileController {

    @RequestMapping(value = "/mobilehome", method = RequestMethod.GET)
    public String getAllDonationsMob(ModelMap model, DonationService donationService) {
        List<Donation> allDonationsMob = donationService.getNewDonations();
        model.addAttribute("allDonationsMob", allDonationsMob);
        return "mobilehome";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/mobiledetail")
    public String mobiledetail(SessionService sessionService, LoginService loginService, HttpServletRequest request, ModelMap model, DonationService donationService) {



        if (request.getParameterNames().hasMoreElements()) {
            Donation donation = new Donation();
            String theid = request.getParameter("donationId");
            donation.setId(Integer.parseInt(theid));


            Donation currentDonation = donationService.findDonationbyId(Integer.parseInt(theid));

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
            model.addAttribute("currentDonationPhone", currentDonation.getPhones().isEmpty() ? "No Phone" : currentDonation.getPhones().get(currentDonation.getPhones().size() - 1).getNumber());
            model.addAttribute("currentDonationAccount", currentDonation.getAccounts().isEmpty() ? "No Account" : currentDonation.getAccounts().get(currentDonation.getAccounts().size() - 1).getNumber().toString());
            return "mobiledetail";
        }
        return "mobiledetail";
    }

    @RequestMapping(value = "/mobilearchive", method = RequestMethod.GET)
    public String getAllDonationsMobArchive(ModelMap model, DonationService donationService) {
        List<Donation> allDonationsMob = donationService.getAllOldDonations();
        model.addAttribute("allDonationsMobOld", allDonationsMob);
        return "mobilearchive";
    }

    @RequestMapping(value = "/mobileaboutus")
    public String Aboutus(ModelMap model, AboutService aboutService) {

        List<About> abouts = aboutService.getAbout();
        model.addAttribute("abouts", abouts);
        return "mobileaboutus";
    }
    
   @RequestMapping(method = RequestMethod.GET, value = "/mobileblog")
    public String blog(BlogService blogService, ModelMap model) {

        List<Blog> allBlogs = blogService.getOrderedBlogsByPostDate();
            model.addAttribute("allBlogs", allBlogs);
            return "mobileblog";
        }  
    
    

    @RequestMapping(method = RequestMethod.GET, value = "/mobileblogdetail")
    public String blogDetail(@RequestParam(value = "blogId", required = false) String blogId, SessionService sessionService, LoginService loginService, HttpServletRequest request, BlogService blogService, ModelMap model, DonationService donationService) {

        if (request.getParameterNames().hasMoreElements()) {

            String theid = request.getParameter("blogId");

            Blog detailBlog = blogService.findBlogById(Integer.parseInt(blogId));
            model.addAttribute("blogTitle", detailBlog.getTitle());
            model.addAttribute("blogPostDate", detailBlog.getPostDate());
            model.addAttribute("blogShortDescription", detailBlog.getShortDescription());
            model.addAttribute("blogLongDescription", detailBlog.getLongDescription());
            model.addAttribute("blogUserUsername", detailBlog.getUser().getUsername());
            return "mobileblogdetail";
        } else {
            return "mobileblogdetail";
        }
    }

   
}
