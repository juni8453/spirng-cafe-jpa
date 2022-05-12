package jpaproject.cafe.article;

import jpaproject.cafe.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

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

	@CreatedDate
	@Column(nullable = false)
	private LocalDateTime createdDateTime;

	@LastModifiedDate
	@Column(nullable = false)
	private LocalDateTime lastModifiedDateTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEMBER_ID")
	private Member member;

	public Article(String title, String content, LocalDateTime createdDateTime, LocalDateTime lastModifiedDateTime, Member member) {
		this.title = title;
		this.content = content;
		this.createdDateTime = createdDateTime;
		this.lastModifiedDateTime = lastModifiedDateTime;
		this.member = member;
	}
}
