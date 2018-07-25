package com.danfeng.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class View {
	@RequestMapping(value = "/is_business", method = RequestMethod.GET)
    public String isBusiness() {
        return "is_business";
    }
	
}
