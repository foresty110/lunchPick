package kr.sparta.backend1.lunch.service;

import kr.sparta.backend1.lunch.dto.MenuOptionDto;
import kr.sparta.backend1.lunch.mapper.MenuOptionMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuOptionService {
    private final MenuOptionMapper mapper;
    public MenuOptionService(MenuOptionMapper mapper) { this.mapper = mapper; }

    public List<MenuOptionDto> search(Long roundId, String foodType, String keyword) {
        return mapper.search(roundId, foodType, keyword);
    }

}