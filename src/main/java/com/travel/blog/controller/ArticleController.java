package com.travel.blog.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.travel.blog.domain.Article;
import com.travel.blog.domain.Views;
import com.travel.blog.repo.ArticleRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {
	private final ArticleRepo articleRepo;

	public ArticleController(ArticleRepo articleRepo) {
		this.articleRepo = articleRepo;
	}

	@GetMapping
	@JsonView(Views.IdName.class)
	public List<Article> list(){
		return articleRepo.findAll();
	}

	@GetMapping("{id}")
	public Article getOne(@PathVariable("id") Article article){
		return article;
	}

	@PostMapping
	public Article create(@RequestBody Article article){
		article.setCreationTime(LocalDateTime.now());
		return articleRepo.save(article);
	}

	@PutMapping("{id}")
	public Article update(@PathVariable("id") Article articleDb, @RequestBody Article article){
		BeanUtils.copyProperties(article, articleDb,"id");
		return articleRepo.save(articleDb);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Article article){
		articleRepo.delete(article);
	}
}
