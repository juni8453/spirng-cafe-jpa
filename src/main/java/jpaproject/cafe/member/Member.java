package jpaproject.cafe.member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import jpaproject.cafe.member.dto.MemberInfoDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

	@Id @GeneratedValue
	@Column(name = "MEMBER_ID")
	private Long id;
	@Column(nullable = false)
	private String memberName;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private MemberType memberType;
	@Column(columnDefinition = "boolean default true")
	private boolean login;

	public Member(MemberInfoDto memberInfoDto, boolean normalUser) {
		this.memberName = memberInfoDto.getName();
		this.memberType = normalUser ? MemberType.USER : MemberType.ADMIN;
	}

	// default true로 설정해주어도 JPA는 그대로 null로 넣어버려서 이렇게 설정해주거나 그냥 초기화를 시켜줘야함
	// @DynamicInsert 적용해봤으나 안됨
	@PrePersist
	public void prePersist() {
		this.login = true;
	}

	public Member(String memberName, MemberType memberType) {
		this.memberName = memberName;
		this.memberType = memberType;
	}

	public void setLogin(boolean login) {
		this.login = login;
	}
}
