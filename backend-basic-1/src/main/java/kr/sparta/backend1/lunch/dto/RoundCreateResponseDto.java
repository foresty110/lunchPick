package kr.sparta.backend1.lunch.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class RoundCreateResponseDto {
    private Long id;
    private Long userId;
    private String date;
    private List<MenuDto> menus;

    public RoundCreateResponseDto(Long id, Long userId, String date, List<MenuDto> menus) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.menus = menus;
    }
}
