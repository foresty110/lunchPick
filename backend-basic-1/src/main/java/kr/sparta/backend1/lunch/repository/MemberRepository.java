package kr.sparta.backend1.lunch.repository;

import kr.sparta.backend1.lunch.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);  // username으로 조회
    boolean existsByEmail(String email);
}