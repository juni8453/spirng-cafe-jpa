package jpaproject.cafe.article.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class ArticleUpdateDto {

    private final String title;
    private final String content;
}
