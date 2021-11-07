package org.closure.app.CommunityModule.view;

import org.closure.app.CommunityModule.models.CommunityModel;
import org.closure.app.CommunityModule.services.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.servlet.http.HttpServletRequest;
@Controller
@RequestMapping(path = "/community")
public class CommunityView {
    
    @Autowired
    CommunityService communityService;

    @RequestMapping(value={"/",""}, method={RequestMethod.GET,RequestMethod.POST})
    public String requestMethodName(Model model,HttpServletRequest request,@ModelAttribute("community") CommunityModel communityModel) {
        if(request.getMethod().equals("POST")){
           communityService.addCommunity(communityModel);
        }
        model.addAttribute("communities", communityService.getAll());
        return "community/index";
         }

}
