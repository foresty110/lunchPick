package kr.sparta.backend1.lunch.repository;

import kr.sparta.backend1.lunch.entity.Round;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface RoundRepository extends JpaRepository<Round, Long> {

    Round findByMember_IdAndDate(Long id, LocalDate date);
}
