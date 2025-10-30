package kr.sparta.backend1.lunch.dto;

import lombok.Getter;

@Getter
public class MenuDto {
    private Long id;
    private String name;
    private String type;
    private int price;

    public MenuDto(Long id, String name, String type, int price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
    }
}
