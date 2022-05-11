package jpaproject.cafe.repository;

import jpaproject.cafe.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
