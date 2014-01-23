package com.donations.controllers;

import com.donations.model.Blog;
import com.donations.model.Donation;
import com.donations.model.User;
import com.donations.services.BlogService;
import com.donations.services.DonationService;
import com.donations.services.LoginService;
import com.donations.services.SessionService;
import java.util.Date;
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

@Controller
public class BlogController {

    @RequestMapping(method = RequestMethod.GET, value = "/blogmanagement")
    public String blogManagement(@RequestParam(value = "from", required = false) Integer from, SessionService sessionService, LoginService loginService, HttpServletRequest request, BlogService blogService, ModelMap model, DonationService donationService) {


        User loggedUser;
        sessionService.init(request);
        loginService.setService(sessionService);
        List<Blog> allBlogs = blogService.getOrderedBlogsByPostDate();
        sessionService.removeDonationAttributes();
        if (from == null) {
            from = 5;
        }

        if (from < 5) {
            from = 5;
        }

        if (loginService.isLoggedIn()) {
            loggedUser = (User) sessionService.getAttribute("user");

            sessionService.setAttribute("user", loggedUser);
            if (request.getParameterNames().hasMoreElements()) {

                String blogId = request.getParameter("blogId");

                try {
                    Blog blog = blogService.findBlogById(Integer.parseInt(blogId));
                    blogService.deleteBlog(blog);
                    model.addAttribute("Action", "Deleted Succesfully");

                } catch (Exception ex) {
                    model.addAttribute("error", "blog was not deleted");
                }
                List<Blog> allBlogs1 = blogService.getOrderedBlogsByPostDate();
                model.addAttribute("from", from);
                model.addAttribute("AllBlogs", allBlogs1);
                model.addAttribute("loggeduserName", loggedUser.getFirstName());
                return "blogmanagement";
            } else {
                model.addAttribute("from", 5);
                model.addAttribute("AllBlogs", allBlogs);
                model.addAttribute("loggeduserName", loggedUser.getFirstName());
                return "blogmanagement";

            }
        } else {
            List<Donation> allDonation = donationService.getAllDonations();
            model.addAttribute("allDonation", allDonation);
            return "home";
        }

    }

    @RequestMapping(value = "/updateblog", method = RequestMethod.GET)
    public String showUpdateBlogForm(HttpServletRequest request, ModelMap model, BlogService blogService, SessionService sessionService, LoginService loginService, DonationService donationService) {


        User loggedUser;
        sessionService.init(request);
        loginService.setService(sessionService);
        if (loginService.isLoggedIn()) {
            sessionService.removeDonationAttributes();
            loggedUser = (User) sessionService.getAttribute("user");

            sessionService.setAttribute("user", loggedUser);

            if (request.getParameterNames().hasMoreElements()) {
                Blog blog = new Blog();
                String theid = request.getParameter("blogId");
                blog.setId(Integer.parseInt(theid));
                Blog currentBlog = blogService.findBlogById(Integer.parseInt(theid));
                model.addAttribute("updateBlogForm", blog);

                model.addAttribute("currentTitle", currentBlog.getTitle());
                model.addAttribute("currentShortDescription", currentBlog.getShortDescription());
                model.addAttribute("currentLongDescription", currentBlog.getLongDescription());
                model.addAttribute("currentPostDate", currentBlog.getPostDate().toString());
                model.addAttribute("loggeduserName", loggedUser.getFirstName());
                return "updateblog";
            } else {
                Blog blog = new Blog();
                model.addAttribute("updateBlogForm", blog);
                return "updateblog";
            }


        } else {
            List<Donation> allDonation = donationService.getAllDonations();
            model.addAttribute("allDonation", allDonation);
            return "home";
        }
    }

    @RequestMapping(value = "/updateblog", method = RequestMethod.POST)
    public String doUpdateBlog(HttpServletRequest request, @ModelAttribute(value = "updateBlogForm") Blog blog, BindingResult result, BlogService blogService, ModelMap model,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "shortDescription", required = false) String shortDescription,
            @RequestParam(value = "longDescription", required = false) String longDescription, SessionService sessionService, DonationService donationService, LoginService loginService) {


        User loggedUser;
        sessionService.init(request);
        loginService.setService(sessionService);
        if (loginService.isLoggedIn()) {
            loggedUser = (User) sessionService.getAttribute("user");
            sessionService.removeDonationAttributes();

            blog.setUser(loggedUser);
            blog.setTitle(title);
            blog.setShortDescription(shortDescription);
            blog.setLongDescription(longDescription);
            blog.setPostDate(new Date());
            blogService.updateBlog(blog);

            List<Blog> allBlogs = blogService.getOrderedBlogsByPostDate();
            model.addAttribute("Action", "Updated Succesfully");
            model.addAttribute("from", 5);
            model.addAttribute("loggeduserName", loggedUser.getFirstName());
            model.addAttribute("AllBlogs", allBlogs);

            return "blogmanagement";
        } else {
            List<Donation> allDonation = donationService.getAllDonations();
            model.addAttribute("allDonation", allDonation);
            return "home";
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/blog")
    public String blog(@RequestParam(value = "from", required = false) Integer from, SessionService sessionService, LoginService loginService, HttpServletRequest request, BlogService blogService, ModelMap model, DonationService donationService) {

        List<Blog> allBlogs = blogService.getOrderedBlogsByPostDate();

        if (request.getParameterNames().hasMoreElements()) {

            if (from == null) {
                from = 3;
            }
            if (from < 3 || from > allBlogs.size()) {
                from = 3;
            }


            if (from < 3) {
                model.addAttribute("from", 3);
                model.addAttribute("allBlogs", allBlogs);
                return "blog";
            } else {

                model.addAttribute("from", from);
                model.addAttribute("allBlogs", allBlogs);
                return "blog";
            }


        } else {
            model.addAttribute("from", 3);
            model.addAttribute("allBlogs", allBlogs);
            return "blog";
        }

    }

    @RequestMapping(method = RequestMethod.POST, value = "/blog")
    public String processBlog(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        return "searchresultsblog.htm";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/blogdetail")
    public String blogDetail(@RequestParam(value = "blogId", required = false) String blogId, SessionService sessionService, LoginService loginService, HttpServletRequest request, BlogService blogService, ModelMap model, DonationService donationService) {

        if (request.getParameterNames().hasMoreElements()) {

            String theid = request.getParameter("blogId");

            Blog detailBlog = blogService.findBlogById(Integer.parseInt(blogId));
            model.addAttribute("blogTitle", detailBlog.getTitle());
            model.addAttribute("blogPostDate", detailBlog.getPostDate());
            model.addAttribute("blogShortDescription", detailBlog.getShortDescription());
            model.addAttribute("blogLongDescription", detailBlog.getLongDescription());
            model.addAttribute("blogUserUsername", detailBlog.getUser().getUsername());
            return "blogdetail";
        } else {
            return "blogdetail";
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/blogdetail")
    public String processBlogDetail() {
        return "blogdetail";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/createblog")
    public String getCreateBlogForm(SessionService sessionService, LoginService loginService, DonationService donationService, HttpServletRequest request, ModelMap model) {
        User loggedUser;
        sessionService.init(request);
        loginService.setService(sessionService);
        if (loginService.isLoggedIn()) {
            loggedUser = (User) sessionService.getAttribute("user");
            sessionService.removeDonationAttributes();
            sessionService.setAttribute("user", loggedUser);
            Blog blog = new Blog();
            model.addAttribute("createBlog", blog);
            model.addAttribute("loggeduserName", loggedUser.getFirstName());
            return "createblog";
        } else {
            List<Donation> allDonation = donationService.getAllDonations();

            model.addAttribute("from", 4);
            model.addAttribute("allDonation", allDonation);
            return "home";
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/createblog")
    public String doCreateBlogForm(@ModelAttribute("createBlog") Blog blog,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "shortDescription", required = false) String shortDescription,
            @RequestParam(value = "longDescription", required = false) String longDescription,
            HttpServletRequest request,
            HttpServletResponse response,
            DonationService donationService,
            SessionService sessionService,
            LoginService loginService,
            BlogService blogService,
            ModelMap model) {

        User loggedUser = null;
        sessionService.init(request);
        loginService.setService(sessionService);
        if (loginService.isLoggedIn()) {
            loggedUser = (User) sessionService.getAttribute("user");
            sessionService.removeDonationAttributes();


            blog.setTitle(title);
            blog.setShortDescription(shortDescription);
            blog.setLongDescription(longDescription);
            blog.setPostDate(new Date());
            blog.setUser(loggedUser);
            blogService.addBlog(blog);
            model.addAttribute("Action", "Created Succesfully");
            List<Blog> allBlogs = blogService.getOrderedBlogsByPostDate();

            model.addAttribute("from", 5);
            model.addAttribute("AllBlogs", allBlogs);
            model.addAttribute("loggeduserName", loggedUser.getFirstName());
            return "blogmanagement";

        } else {
            List<Donation> allDonation = donationService.getAllDonations();
            model.addAttribute("allDonation", allDonation);
            model.addAttribute("from", 4);
            return "home";
        }
    }

    @RequestMapping(value = "/searchresultsblog")
    public String blogResults(ModelMap model, BlogService blogService, @RequestParam(value = "searchText", required = false) String searchText, HttpServletRequest request) {

        model.addAttribute("searchValue", searchText);
        List<Blog> allBlogs = blogService.getBlogsByTitleOrText(searchText);
        model.addAttribute("allBlogs", allBlogs);
        return "searchresultsblog";

    }
}
