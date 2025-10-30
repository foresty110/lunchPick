package kr.sparta.backend1.lunch.dto;

import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class RoundCreateRequestDto {

    private LocalDate date;
    private List<MenuDto> menus;
}
