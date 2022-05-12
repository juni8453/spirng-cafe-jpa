package jpaproject.cafe.article;

import java.util.List;
import jpaproject.cafe.article.dto.ArticleCreateDto;
import jpaproject.cafe.article.dto.ArticleReadDto;
import jpaproject.cafe.article.dto.ArticleUpdateDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/articles")
@RestController
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }


    @GetMapping
    public ResponseEntity<List<ArticleReadDto>> read() {
        List<ArticleReadDto> findArticles = articleService.findAll();

        return ResponseEntity.ok(findArticles);
    }

    @PostMapping
    public ResponseEntity<ArticleCreateDto> create(@RequestBody ArticleCreateDto articleCreateDto) {
        articleService.save(articleCreateDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ArticleUpdateDto> update(
        @PathVariable Long id, @RequestBody ArticleUpdateDto articleUpdateDto) {

        articleService.update(id, articleUpdateDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        articleService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
