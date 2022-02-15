package com.travel.blog.repo;

import com.travel.blog.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepo extends JpaRepository<Article,Long> {
}
