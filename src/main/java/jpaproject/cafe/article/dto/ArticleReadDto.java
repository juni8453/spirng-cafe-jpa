package jpaproject.cafe.article.dto;

import jpaproject.cafe.article.Article;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ArticleReadDto {

    private final Long id;
    private final String title;
    private final String content;
    private final LocalDateTime createdDateTime;
    private final LocalDateTime lastModifiedDateTime;
    private final String memberName;

    public ArticleReadDto(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.createdDateTime = article.getCreatedDateTime();
        this.lastModifiedDateTime = article.getLastModifiedDateTime();
        this.memberName = article.getMember().getMemberName();
    }
}
