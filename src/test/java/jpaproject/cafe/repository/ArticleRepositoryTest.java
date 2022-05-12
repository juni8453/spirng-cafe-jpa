package jpaproject.cafe.repository;

import jpaproject.cafe.article.Article;
import jpaproject.cafe.article.ArticleRepository;
import jpaproject.cafe.member.Member;
import jpaproject.cafe.member.MemberType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(value = false)
class ArticleRepositoryTest {

    @Autowired
    ArticleRepository articleRepository;

    @PersistenceContext
    private EntityManager em;

    @Test
    @DisplayName("Member 객체를 영속성 컨텍스트에 추가한 ID 값이 ind 한 객체의 ID 값과 동일해야 한다.")
    void createArticle() {

        //given
        Member member = new Member("name", MemberType.USER);
        Article article = new Article("title", "content", LocalDateTime.now(), member);

        // when
        em.persist(member);
        Article save = articleRepository.save(article);
        Article findArticle = articleRepository.findById(save.getId()).get();

        // then
        assertThat(findArticle.getId()).isEqualTo(save.getId());
    }

}
