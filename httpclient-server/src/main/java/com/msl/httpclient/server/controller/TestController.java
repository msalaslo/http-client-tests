package com.msl.httpclient.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestController {
	
    @PostMapping("/test")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
    	try {
			Thread.sleep(2000000000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        model.addAttribute("name", name);
        return "greeting";
    }

}
