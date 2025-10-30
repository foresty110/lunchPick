package kr.sparta.backend1.lunch.dto;

import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class RoundCreateResponseDto {
    private Long id;
    private Long userId;
    private LocalDate date;
    private List<MenuDto> menus;

    public RoundCreateResponseDto(Long id, Long userId, LocalDate date, List<MenuDto> menus) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.menus = menus;
    }
}
