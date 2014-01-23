/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donations.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author user
 */
public class SessionService {

    private HttpSession session;

    public SessionService() {
    }

    public void init(HttpServletRequest request) {
        session = request.getSession(true);
    }

    public void setAttribute(String name, Object data) {
        if (session != null) {
            session.setAttribute(name, data);

        }
    }

    public Object getAttribute(String name) {
        if (session.getAttribute(name) != null) {
            return session.getAttribute(name);
        } else {
            return null;
        }
    }

    public void closeSession() {
        if (session != null) {
            session.invalidate();
            session = null;
        }
    }

    public void removeAttribute(String name) {
        if (session != null) {
            session.removeAttribute(name);
        }
    }
    

    public void removeDonationAttributes() 
    {
        this.removeAttribute("imageLocation");
        this.removeAttribute("titleHidden");
        this.removeAttribute("descriptionHidden");
        this.removeAttribute("priorityHidden");
        this.removeAttribute("donationUrlHidden");
        this.removeAttribute("descriptionHidden");
        this.removeAttribute("endDateHidden");
        this.removeAttribute("categoryHidden");
        this.removeAttribute("typeHidden");
    }

}