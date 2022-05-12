package jpaproject.cafe.article;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import jpaproject.cafe.article.dto.ArticleCreateDto;
import jpaproject.cafe.article.dto.ArticleUpdateDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class ArticleServiceTest {

	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private ArticleService articleService;

	@Test
	@DisplayName("클라이언트에서 CreateDto 로 넘긴 데이터를 Article 엔티티로 변환되어야 한다.")
	void dtoToEntity() {
		// given
		ArticleCreateDto dto = new ArticleCreateDto("title", "content");

		// when
		Long id = articleService.save(dto);

		Article findArticle = articleRepository.findById(id).get();
		// then
		assertThat(findArticle.getTitle()).isEqualTo("title");
		assertThat(findArticle.getContent()).isEqualTo("content");
	}

	@Test
	@DisplayName("update 시 존재하지 않는 id 를 클라이언트에서 받았을 경우, 예외가 터져야 한다.")
	void updateExceptionTest() {
		// given
		ArticleCreateDto dto = new ArticleCreateDto("title", "content");
		Long id = articleService.save(dto);
		ArticleUpdateDto updateDto = new ArticleUpdateDto("수정 title", "수정 content");

		// when, then
		assertThrows(IllegalArgumentException.class,
			() -> articleService.update(id + 1, updateDto));
	}

	@Test
	@DisplayName("update 시 Entity의 정보가 변경돼야한다.")
	void updateTest() {
		// given
		ArticleCreateDto createDto = new ArticleCreateDto("title", "content");
		Long id = articleService.save(createDto);
		ArticleUpdateDto updateDto = new ArticleUpdateDto("수정 title", "수정 content");
		articleService.update(id, updateDto);

		Article findArticle = articleRepository.findById(id).get();

		// when, then
		assertThat(findArticle.getTitle()).isEqualTo(updateDto.getTitle());
		assertThat(findArticle.getContent()).isEqualTo(updateDto.getContent());
	}

	@Test
	@DisplayName("delete 시 DB에서 엔티티가 삭제된다.")
	public void deleteTest() {
		// given
		ArticleCreateDto createDto = new ArticleCreateDto("title", "content");
		Long id = articleService.save(createDto);
        // when
        articleService.delete(id);

        // then
        assertThrows(NoSuchElementException.class, () -> articleRepository.findById(id).get());
	}
}
