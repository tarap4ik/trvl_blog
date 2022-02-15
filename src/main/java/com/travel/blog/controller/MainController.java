package com.travel.blog.controller;

import com.travel.blog.domain.User;
import com.travel.blog.repo.ArticleRepo;
import com.travel.blog.repo.UserRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController {
	private final ArticleRepo articleRepo;
	private final UserRepo userRepo;

	public MainController(ArticleRepo articleRepo, UserRepo userRepo) {
		this.articleRepo = articleRepo;
		this.userRepo = userRepo;
	}

	@Value("${spring.profiles.active}")
	private String profile;

	@GetMapping
	public String main(Model model, @AuthenticationPrincipal OAuth2User principal) {
		User user = null;
		if (principal != null) {
			user = userRepo.findById(principal.getAttribute("sub")).get();
		}
		HashMap<Object, Object> data = new HashMap<>();
		data.put("profile", user);
		data.put("articles", articleRepo.findAll());

		model.addAttribute("frontendData", data);
		model.addAttribute("isDevMode", "dev".equals(profile));
		return "index";
	}
}
