package jpaproject.cafe.article.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class ArticleDeleteDto {

    private final boolean deleted;
}
