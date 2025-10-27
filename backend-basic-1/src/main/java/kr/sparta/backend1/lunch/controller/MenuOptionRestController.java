package kr.sparta.backend1.lunch.controller;

import kr.sparta.backend1.lunch.dto.MenuOptionDto;
import kr.sparta.backend1.lunch.service.MenuOptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu-option")
public class MenuOptionRestController {
    private final MenuOptionService service;

    public MenuOptionRestController(MenuOptionService service) { this.service = service; }

    @GetMapping
    public ResponseEntity<List<MenuOptionDto>> search(
            @RequestParam(required = false) Long roundId,
            @RequestParam(required = false) String foodType,
            @RequestParam(required = false) String keyword) {
        return ResponseEntity.ok(service.search(roundId, foodType, keyword));
    }
}
