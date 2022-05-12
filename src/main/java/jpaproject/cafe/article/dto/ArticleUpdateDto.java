package jpaproject.cafe.article.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ArticleUpdateDto {

    private final String title;
    private final String content;
}
