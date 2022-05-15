package jpaproject.cafe.member;

import jpaproject.cafe.member.dto.MemberInfoDto;
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
	private boolean login;

	public Member(MemberInfoDto memberInfoDto, boolean normalUser) {
		this.memberName = memberInfoDto.getName();
		this.memberType = normalUser ? MemberType.USER : MemberType.ADMIN;
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
