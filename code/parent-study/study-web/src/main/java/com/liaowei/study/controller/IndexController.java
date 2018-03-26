package com.liaowei.study.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class IndexController {

	@RequestMapping(value = {"/index"})
	public String index(HttpServletRequest request, Model model) {
		return "index";
	}
}