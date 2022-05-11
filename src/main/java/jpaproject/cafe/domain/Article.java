package jpaproject.cafe.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {

	@Id @GeneratedValue
	@Column(name = "ARTICLE_ID")
	private Long id;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String content;

	@Column(nullable = false)
	private LocalDateTime createdDateTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEMBER_ID")
	private Member member;

	public Article(String title, String content, LocalDateTime createdDateTime, Member member) {
		this.title = title;
		this.content = content;
		this.createdDateTime = createdDateTime;
		this.member = member;
	}
}
