package jpaproject.cafe.article;

import java.util.List;
import java.util.stream.Collectors;
import jpaproject.cafe.article.dto.ArticleCreateDto;
import jpaproject.cafe.article.dto.ArticleReadDto;
import jpaproject.cafe.article.dto.ArticleUpdateDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ArticleService {

	private final ArticleRepository articleRepository;

	public ArticleService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}

	public List<ArticleReadDto> findAll() {
		List<Article> findArticles = articleRepository.findAll();

		return findArticles.stream()
			.map(ArticleReadDto::new)
			.collect(Collectors.toList());
	}

	public Long save(ArticleCreateDto articleCreateDto) {
		Article article = Article.dtoToEntity(articleCreateDto);
		return articleRepository.save(article).getId();
	}

	public void update(Long id, ArticleUpdateDto articleUpdateDto) {
		// 예외 처리 필요
		Article findArticle = articleRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));

		findArticle.updateArticle(articleUpdateDto);
	}

	public void delete(Long id) {
		try {
			articleRepository.deleteById(id);
		} catch (Exception e) {
			throw new IllegalArgumentException("존재하지 않는 게시글입니다.");
		}
	}
}
