package jpaproject.cafe.article;

import javax.annotation.PostConstruct;
import jpaproject.cafe.article.dto.ArticleCreateDto;
import jpaproject.cafe.article.dto.ArticleReadDto;
import jpaproject.cafe.article.dto.ArticleUpdateDto;
import jpaproject.cafe.member.Member;
import jpaproject.cafe.member.MemberRepository;
import jpaproject.cafe.member.MemberType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/articles")
@RestController
public class ArticleController {

    private final ArticleService articleService;
    private final MemberRepository memberRepository;

    public ArticleController(ArticleService articleService, MemberRepository memberRepository) {
        this.articleService = articleService;
        this.memberRepository = memberRepository;
    }


    @GetMapping
    public ResponseEntity<Page<ArticleReadDto>> read(Pageable pageable) {
        Page<ArticleReadDto> findArticles = articleService.findAll(pageable);

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

//    @PostConstruct
//    public void init() {
//        Member member = new Member("Tany", MemberType.USER);
//        Member saveMember = memberRepository.save(member);
//
//        for (int i = 0; i < 30; i++) {
//            articleService.save(new ArticleCreateDto("title" + i, "content" + i), member);
//        }
//    }
}
