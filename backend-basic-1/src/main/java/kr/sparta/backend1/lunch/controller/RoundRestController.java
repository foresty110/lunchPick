package kr.sparta.backend1.lunch.controller;


import kr.sparta.backend1.lunch.dto.RoundCreateRequestDto;
import kr.sparta.backend1.lunch.dto.RoundCreateResponseDto;
import kr.sparta.backend1.lunch.dto.RoundTodayDto;
import kr.sparta.backend1.lunch.dto.RoundWithOptionsDto;
import kr.sparta.backend1.lunch.service.RoundService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController //@Controller + @ResponseBody
@RequestMapping("/api/rounds")
public class RoundRestController {

    private final RoundService service;

    public RoundRestController(RoundService roundService) { this.service = roundService; }

    @PostMapping
    public ResponseEntity<RoundCreateResponseDto> create(@RequestBody RoundCreateRequestDto request, Authentication auth) {

        RoundCreateResponseDto responseDto = service.save(request,auth);

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/today")
    public ResponseEntity<List<RoundTodayDto>> today() {
        return ResponseEntity.ok(service.getTodayRounds());
    }

    @GetMapping("/{roundId}") //rounds/1
    public ResponseEntity<RoundWithOptionsDto> round(@PathVariable Long roundId) {
        return ResponseEntity.ok(service.getRound(roundId));
    }
}