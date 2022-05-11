package jpaproject.cafe.repository;

import jpaproject.cafe.domain.Member;
import jpaproject.cafe.domain.MemberType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @Test
    @DisplayName("Member 객체를 영속성 컨텍스트에 추가한 ID 값이 ind 한 객체의 ID 값과 동일해야 한다.")
    void createMember() {

        //given
        Member member = new Member("kim", MemberType.USER);

        // when
        Member save = memberRepository.save(member);
        Member findMember = memberRepository.findById(member.getId()).get();

        // then
        assertThat(findMember.getId()).isEqualTo(save.getId());
    }
}