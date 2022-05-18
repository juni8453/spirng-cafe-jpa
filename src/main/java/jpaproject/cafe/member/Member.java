package jpaproject.cafe.member;

import jpaproject.cafe.member.dto.OauthMemberInfo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

	private String sessionId;

	// 테스트용..
	public Member(String memberName, MemberType memberType) {
		this.memberName = memberName;
		this.memberType = memberType;
	}

	public Member(OauthMemberInfo oauthMemberInfo, MemberType memberType) {
		this.memberName = oauthMemberInfo.getLogin();
		this.memberType = memberType;
	}

	public Member(OauthMemberInfo oauthMemberInfo, MemberType memberType, String sessionId) {
		this.memberName = oauthMemberInfo.getLogin();
		this.memberType = memberType;
		this.sessionId = sessionId;
	}

	// default true로 설정해주어도 JPA는 그대로 null로 넣어버려서 이렇게 설정해주거나 그냥 초기화를 시켜줘야함
	// @DynamicInsert 적용해봤으나 안됨
	@PrePersist
	public void prePersist() {
		this.login = true;
	}

	public void setLogin(boolean login) {
		this.login = login;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
}
