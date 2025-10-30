package kr.sparta.backend1.lunch.repository;

import kr.sparta.backend1.lunch.entity.Menu;
import kr.sparta.backend1.lunch.entity.Round;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findAllByRound_Id(Long roundId);
}
