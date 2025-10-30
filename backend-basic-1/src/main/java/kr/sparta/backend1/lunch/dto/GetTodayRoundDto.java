package kr.sparta.backend1.lunch.dto;

import kr.sparta.backend1.lunch.entity.Menu;
import kr.sparta.backend1.lunch.entity.Round;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class GetTodayRoundDto {

    private Long id;
    private String name;
    private LocalDate date;
    private List<MenuDto> menus;
}
