package jpaproject.cafe.article;

import javax.annotation.PostConstruct;
import jpaproject.cafe.article.dto.ArticleCreateDto;
import jpaproject.cafe.article.dto.ArticleDeleteDto;
import jpaproject.cafe.article.dto.ArticleReadDto;
import jpaproject.cafe.member.Member;
import jpaproject.cafe.member.MemberRepository;
import jpaproject.cafe.member.MemberType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/articles")
@RestController
@Slf4j
public class ArticleController {

	private final ArticleService articleService;
	private final MemberRepository memberRepository;
	private final ArticleRepository articleRepository;
	private String test;

	public ArticleController(ArticleService articleService,
		MemberRepository memberRepository, ArticleRepository articleRepository,
		@Value("${oauth2.user.github.testing}") String test) {
		this.articleService = articleService;
		this.memberRepository = memberRepository;
		this.articleRepository = articleRepository;
		this. test = test;
	}

	@GetMapping
	public ResponseEntity<Slice<ArticleReadDto>> read(Pageable pageable) {
		Slice<ArticleReadDto> findArticles = articleService.findAll(pageable);
		System.out.printf("=============%s=====================", test);
		return ResponseEntity.ok(findArticles);
	}

	@PostMapping
	public ResponseEntity<ArticleCreateDto> create(@RequestBody ArticleCreateDto articleCreateDto,
		@RequestHeader("Authorization") String authorization) {
		System.out.println("articleCreateDto = " + articleCreateDto.getContent());
		String sessionId = authorization.split("=")[1];

		Member member = memberRepository.findBySessionId(sessionId)
			.orElseThrow(() -> new IllegalArgumentException("멤버가 존재하지 않습니다."));

		articleService.save(articleCreateDto, member);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	// Todo : 이거를 삭제로 바꿀 예정
	@PatchMapping("/{id}")
	public ResponseEntity<ArticleDeleteDto> delete(
		@PathVariable Long id, @RequestBody ArticleDeleteDto articleDeleteDto,
		@RequestHeader("Authorization") String authorization) {

		String sessionId = authorization.split("=")[1];
		Member member = memberRepository.findBySessionId(sessionId)
			.orElseThrow(() -> new IllegalArgumentException("멤버가 존재하지 않습니다."));

		Article article = articleRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));

		article.validateMember(member);
		articleService.delete(id, articleDeleteDto);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostConstruct
	public void createDummyMember() {
		Member member = new Member("Dummy", MemberType.USER);
		memberRepository.save(member);
	}
}
