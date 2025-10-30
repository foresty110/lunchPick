package kr.sparta.backend1.lunch.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class RoundCreateRequestDto {

    private String date;
    private List<MenuDto> menus;
}
