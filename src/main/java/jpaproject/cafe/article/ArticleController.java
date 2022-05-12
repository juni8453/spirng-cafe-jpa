package jpaproject.cafe.article;

import java.util.List;
import jpaproject.cafe.article.dto.ArticleCreateDto;
import jpaproject.cafe.article.dto.ArticleReadDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/articles")
@RestController
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }


    @GetMapping
    public ResponseEntity<List<ArticleReadDto>> read() {
        List<ArticleReadDto> findArticles= articleService.findAll();
        
        return ResponseEntity.ok(findArticles);
    }

    @PostMapping
    public ResponseEntity<ArticleCreateDto> create(@RequestBody ArticleCreateDto articleCreateDto) {
        articleService.save(articleCreateDto);

        return null;
    }



}
