package jpaproject.cafe.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpaproject.cafe.article.Article;
import jpaproject.cafe.article.ArticleRepository;
import jpaproject.cafe.member.Member;
import jpaproject.cafe.member.MemberType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
//@Rollback(value = false)
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
        Article article = new Article("title", "content", member);

        // when
        em.persist(member);
        Article save = articleRepository.save(article);
        Article findArticle = articleRepository.findById(save.getId()).get();

        // then
        assertThat(findArticle.getId()).isEqualTo(save.getId());
    }

    @Test
    @DisplayName("전체 게시글을 조회한다")
    public void findAll() {
        // given
        for (int i = 0; i < 10; i++) {
            articleRepository.save(new Article("title"+i, "내용"+i, null ));
        }

        // when
        List<Article> articles = articleRepository.findAll();

        // then
        assertThat(articles.size()).isEqualTo(10);
    }

}
