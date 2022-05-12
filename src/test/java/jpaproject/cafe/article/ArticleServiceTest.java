package jpaproject.cafe.article;

import jpaproject.cafe.article.dto.ArticleCreateDto;
import jpaproject.cafe.article.dto.ArticleUpdateDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

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
        articleService.save(dto);
        Article findArticle = articleRepository.findById(1L).get();

        // then
        assertThat(findArticle.getTitle()).isEqualTo("title");
        assertThat(findArticle.getContent()).isEqualTo("content");
    }

    @Test
    @DisplayName("update 시 존재하지 않는 id 를 클라이언트에서 받았을 경우, 예외가 터져야 한다.")
    void updateExceptionTest() {
        // given
        ArticleCreateDto dto = new ArticleCreateDto("title", "content");
        articleService.save(dto);
        ArticleUpdateDto updateDto = new ArticleUpdateDto("수정 title", "수정 content");

        // when, then
        assertThrows(IllegalArgumentException.class, ()-> articleService.update(2L, updateDto));
    }
}