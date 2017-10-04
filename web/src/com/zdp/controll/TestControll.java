package com.zdp.controll;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestControll {
	@RequestMapping("test.do")
	public String test(String name, Model model) {
		System.out.println("name: " + name);
		model.addAttribute("name", name);
		model.addAttribute("abc");
		model.addAttribute("def");
		return "test";
	}
}
