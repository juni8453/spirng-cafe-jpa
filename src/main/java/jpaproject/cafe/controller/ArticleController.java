package jpaproject.cafe.controller;

import jpaproject.cafe.service.ArticleService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }
}
