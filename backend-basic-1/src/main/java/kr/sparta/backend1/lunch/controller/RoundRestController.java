package kr.sparta.backend1.lunch.controller;


import kr.sparta.backend1.lunch.dto.RoundTodayDto;
import kr.sparta.backend1.lunch.dto.RoundWithOptionsDto;
import kr.sparta.backend1.lunch.service.RoundService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //@Controller + @ResponseBody
@RequestMapping("/rounds")
public class RoundRestController {

    private final RoundService service;

    public RoundRestController(RoundService roundService) { this.service = roundService; }

    @GetMapping("/today")
    public ResponseEntity<List<RoundTodayDto>> today() {
        return ResponseEntity.ok(service.getTodayRounds());
    }

    @GetMapping("/{roundId}") //rounds/1
    public ResponseEntity<RoundWithOptionsDto> round(@PathVariable Long roundId) {
        return ResponseEntity.ok(service.getRound(roundId));
    }
}