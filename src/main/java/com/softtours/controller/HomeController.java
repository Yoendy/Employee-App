package com.softtours.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softtours.service.DownloadService;

@Controller
public class HomeController {

	 private DownloadService downloadService;
	 
	 @Autowired
	 public HomeController(DownloadService downloadService)
	 {
		this.downloadService =downloadService;
	 }
	@RequestMapping("/home")
	public String home(){
		return "home";
	}
	
    @RequestMapping(value = "/downloadExcel", method = RequestMethod.GET)
    public void downloadExcel(HttpServletResponse response, Model model) 
    {
    	try {
			downloadService.downloadXLS(response);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
