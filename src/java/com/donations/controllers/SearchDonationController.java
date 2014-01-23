/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donations.controllers;

import com.donations.model.Donation;
import com.donations.services.DonationService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author user
 */
@Controller
public class SearchDonationController {

    @RequestMapping(value = "/searchdonation")
    public String searchDonations(@RequestParam(value = "searchText", required = true) String searchText, DonationService donationService, ModelMap modelMap) {

        List<Donation> allDonation = donationService.getDonationsByTitleOrText(searchText);

        modelMap.addAttribute("allDonations", allDonation);

        return "searchdonation";
    }
}