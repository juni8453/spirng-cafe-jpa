package jpaproject.cafe.article;

import jpaproject.cafe.article.dto.ArticleCreateDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class ArticleServiceTest {

    @Autowired private  ArticleRepository articleRepository;
    @Autowired private  ArticleService articleService;

    @Test
    @DisplayName("클라이언트에서 CreateDto 로 넘긴 데이터를 Article 엔티티로 변환되어야 한다.")
    void dtoToEntity() {
        // given
        ArticleCreateDto dto = new ArticleCreateDto("title", "content");

        // when
        articleService.save(dto);
        Article findArticle = articleRepository.findById(1L).get();

        // then
        assertThat(findArticle.getTitle()).isEqualTo("title");
        assertThat(findArticle.getContent()).isEqualTo("content");
    }
}