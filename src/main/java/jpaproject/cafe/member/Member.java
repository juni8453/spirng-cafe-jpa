package jpaproject.cafe.member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import jpaproject.cafe.member.dto.OauthMemberInfo;
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

	private String sessionId;

	// 테스트용..
	public Member(String memberName, MemberType memberType) {
		this.memberName = memberName;
		this.memberType = memberType;
	}

	public Member(OauthMemberInfo oauthMemberInfo, MemberType memberType, String sessionId) {
		this.memberName = oauthMemberInfo.getLogin();
		this.memberType = memberType;
		this.sessionId = sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public void logout() {
		this.sessionId = null;
	}
}
