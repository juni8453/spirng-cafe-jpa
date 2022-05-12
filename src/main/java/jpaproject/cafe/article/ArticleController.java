package jpaproject.cafe.article;

import jpaproject.cafe.article.dto.ArticleReadDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/articles")
@RestController
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    // TODO CRUD 구현
    //  1. READ /articles
    @GetMapping
    public ResponseEntity<List<ArticleReadDto>> read() {
        List<ArticleReadDto> findArticles= articleService.findAll();
        
        return ResponseEntity.ok(findArticles);
    }
}
