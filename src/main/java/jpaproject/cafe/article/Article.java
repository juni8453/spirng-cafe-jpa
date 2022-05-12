package jpaproject.cafe.article;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import jpaproject.cafe.article.dto.ArticleCreateDto;
import jpaproject.cafe.article.dto.ArticleUpdateDto;
import jpaproject.cafe.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "title", "content", "createdDateTime", "lastModifiedDateTime"})
public class Article {

	@Id @GeneratedValue
	@Column(name = "ARTICLE_ID")
	private Long id;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String content;

	@CreatedDate
	@Column(nullable = false, updatable = false)
	private LocalDateTime createdDateTime;

	@LastModifiedDate
	@Column(nullable = false)
	private LocalDateTime lastModifiedDateTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEMBER_ID")
	private Member member;

	public Article(String title, String content, Member member) {
		this.title = title;
		this.content = content;
		this.member = member;
	}

	public static Article dtoToEntity(ArticleCreateDto dto) {
		return new Article(dto.getTitle(),dto.getContent(), null);
	}

	public void updateArticle(ArticleUpdateDto articleUpdateDto) {
		this.title = articleUpdateDto.getTitle();
		this.content = articleUpdateDto.getContent();
	}
}
