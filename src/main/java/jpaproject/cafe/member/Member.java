package jpaproject.cafe.member;

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

	public Member(String memberName, MemberType memberType) {
		this.memberName = memberName;
		this.memberType = memberType;
	}
}
