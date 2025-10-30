package kr.sparta.backend1.lunch.mapper;

import kr.sparta.backend1.lunch.dto.MenuDto;
import kr.sparta.backend1.lunch.entity.Menu;

public class MenuMapper {
    public static MenuDto toDto(Menu menu) {
        return new MenuDto(menu.getId(), menu.getName(), menu.getType(),menu.getPrice());
    }
}
