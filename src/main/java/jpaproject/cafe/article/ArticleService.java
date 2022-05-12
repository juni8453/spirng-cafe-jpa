package jpaproject.cafe.article;

import jpaproject.cafe.article.dto.ArticleReadDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
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
}
