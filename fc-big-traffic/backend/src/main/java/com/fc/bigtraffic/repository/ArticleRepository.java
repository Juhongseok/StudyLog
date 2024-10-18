package com.fc.bigtraffic.repository;

import com.fc.bigtraffic.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long>  {

}
