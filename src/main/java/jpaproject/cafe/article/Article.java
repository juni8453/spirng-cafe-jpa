package jpaproject.cafe.article;

import java.time.LocalDateTime;
import javax.persistence.*;

import jpaproject.cafe.article.dto.ArticleCreateDto;
import jpaproject.cafe.article.dto.ArticleDeleteDto;
import jpaproject.cafe.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "content", "createdDateTime", "lastModifiedDateTime"})
@Where(clause = "deleted = false")
public class Article {

	@Id
	@GeneratedValue
	@Column(name = "ARTICLE_ID")
	private Long id;

	@Column(nullable = false)
	private String content;

	@Column(columnDefinition = "boolean default false")
	private boolean deleted;

	@CreatedDate
	@Column(nullable = false, updatable = false)
	private LocalDateTime createdDateTime;

	@LastModifiedDate
	@Column(nullable = false)
	private LocalDateTime lastModifiedDateTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEMBER_ID")
	private Member member;

	public Article(String content, Member member) {
		this.content = content;
		this.member = member;
	}

	public Article(String content) {
		this.content = content;
	}

	public static Article dtoToEntity(ArticleCreateDto dto, Member member) {
		return new Article(dto.getContent(), member);
	}

	public static Article dtoToEntity(ArticleCreateDto dto) {
		return new Article(dto.getContent());
	}

	// @Column(columnDefinition = "boolean default false") 만으로는 DB 에 default 값을 지정할 수 없다.
	// @PrePersist 어노테이션 필요
	@PrePersist
	public void prePersist() {
		this.deleted = false;
	}

	// JpaRepository 인터페이스에 update 가 없기 때문에 새로 생성
	public void delete(ArticleDeleteDto articleDeleteDto) {
		this.deleted = articleDeleteDto.isDeleted();
	}

	public void validateMember(Member member) {
		if (member != this.member) {
			throw new IllegalArgumentException("멤버가 일치하지 않습니다.");
		}
	}
}
