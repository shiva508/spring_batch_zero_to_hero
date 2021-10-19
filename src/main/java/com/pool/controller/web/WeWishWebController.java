package com.pool.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WeWishWebController {
	@GetMapping("/")
	public String wishHomePage(Model model) {
		return "index"; // view
	}
	@GetMapping("/wewish")
	public String createWishPage() {
		return "createwish";
	}
}
