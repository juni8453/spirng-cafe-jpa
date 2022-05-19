package jpaproject.cafe.article;

import jpaproject.cafe.article.dto.ArticleCreateDto;
import jpaproject.cafe.article.dto.ArticleReadDto;
import jpaproject.cafe.article.dto.ArticleDeleteDto;
import jpaproject.cafe.member.Member;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ArticleService {

	private final ArticleRepository articleRepository;

	public ArticleService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}

	public Slice<ArticleReadDto> findAll(Pageable pageable) {

		Slice<Article> articlePage = articleRepository.findAll(pageable);
		Slice<ArticleReadDto> pages = articlePage.map(article -> new ArticleReadDto(article));

		return pages;
	}


	public Long save(ArticleCreateDto articleCreateDto, Member member) {
    Article article = Article.dtoToEntity(articleCreateDto, member);

		return articleRepository.save(article).getId();
	}

	public Long save(ArticleCreateDto articleCreateDto) {
		Article article = Article.dtoToEntity(articleCreateDto);
		return articleRepository.save(article).getId();
	}

	public void delete(Long id, ArticleDeleteDto articleDeleteDto) {
		Article findArticle = articleRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));

		findArticle.delete(articleDeleteDto);
	}
}
