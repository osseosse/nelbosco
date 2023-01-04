package com.nelbosco.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nelbosco.domain.MailDTO;
import com.nelbosco.service.MailService;
import com.nelbosco.util.UiUtils;


@Controller
public class MainController extends UiUtils {
	
	@Autowired
	MailService mailService;
	
	@GetMapping(value = "/")
	public String moveHome(Model model) {

		return "index";
	}
	
	@GetMapping(value = {"/cafe", "/cafe/"})
	public String moveMain(Model model) {

		return "/main";
	}
	
	@GetMapping(value = {"/restaurant", "/restaurant/"})
	public String moveBmain(Model model) {

		return "/b_main";
	}
	
	@GetMapping(value = {"/rooftop", "/rooftop/"})
	public String moveRmain(Model model) {

		return "/r_main";
	}
	
	@RequestMapping(value = "/fragments/{viewName}")
    public String openFragments(@PathVariable(value = "viewName", required = false) String viewName)throws Exception{
		
		return "/fragments/"+viewName;
    }
	
	@RequestMapping(value = "/cafe/brandstory/{viewName}")
    public String moveBrandStory(@PathVariable(value = "viewName", required = false) String viewName)throws Exception{
		
		return "/brandstory/"+viewName;
    }
	
	@RequestMapping(value = "/cafe/story/{viewName}")
    public String moveStory(@PathVariable(value = "viewName", required = false) String viewName)throws Exception{
		
		return "/story/"+viewName;
    }
	
	@RequestMapping(value = "/restaurant/music/{viewName}")
    public String moveMusik(@PathVariable(value = "viewName", required = false) String viewName)throws Exception{
		
		return "/music/"+viewName;
    }
	
	@RequestMapping(value = "/cafe/community/{viewName}")
    public String moveCommunity(@PathVariable(value = "viewName", required = false) String viewName)throws Exception{
		
		return "/community/"+viewName;
    }
	
	@RequestMapping(value = "/cafe/contact/{viewName}")
    public String moveContact(@PathVariable(value = "viewName", required = false) String viewName)throws Exception{
		
		return "/contact/"+viewName;
    }
	
	@RequestMapping(value = "/cafe/menu/{viewName}")
    public String moveCafeMenu(@PathVariable(value = "viewName", required = false) String viewName)throws Exception{
		
		return "/menu/"+viewName;
    }
	
	@RequestMapping(value = "/restaurant/menu/{viewName}")
    public String moveRestaurantMenu(@PathVariable(value = "viewName", required = false) String viewName)throws Exception{
		
		return "/menu/"+viewName;
    }
	
	@RequestMapping(value = "/restaurant/bcontact/{viewName}")
    public String moveBcontact(@PathVariable(value = "viewName", required = false) String viewName)throws Exception{
		
		return "/bcontact/"+viewName;
    }
	
	@RequestMapping(value = "/restaurant/about/{viewName}")
    public String moveAbout(@PathVariable(value = "viewName", required = false) String viewName)throws Exception{
		
		return "/about/"+viewName;
    }
	
	@RequestMapping(value = "/rooftop/about/{viewName}")
    public String moveRabout(@PathVariable(value = "viewName", required = false) String viewName)throws Exception{
		
		return "/rabout/"+viewName;
    }
	
	@RequestMapping(value = "/rooftop/menu/{viewName}")
    public String moveRmenu(@PathVariable(value = "viewName", required = false) String viewName)throws Exception{
		
		return "/menu/"+viewName;
    }
	
	@RequestMapping(value = "/rooftop/community/{viewName}")
    public String moveRcommunity(@PathVariable(value = "viewName", required = false) String viewName)throws Exception{
		
		return "/rcommunity/"+viewName;
    }
	
	@RequestMapping(value = "/rooftop/contact/{viewName}")
    public String moveRcontact(@PathVariable(value = "viewName", required = false) String viewName)throws Exception{
		
		return "/rcontact/"+viewName;
    }
	
	@GetMapping("/mail")
    public String dispMail() {
        return "/utils/mail";
    }
	
	@PostMapping("/mail")
	public void execMail(MailDTO mailDto) {
        mailService.mailSend(mailDto);
    }
	
}
