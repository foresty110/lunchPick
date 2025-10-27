package kr.sparta.backend1.lunch.service;


import kr.sparta.backend1.lunch.dto.RoundTodayDto;
import kr.sparta.backend1.lunch.dto.RoundWithOptionsDto;
import kr.sparta.backend1.lunch.mapper.RoundDetailMapper;
import kr.sparta.backend1.lunch.mapper.RoundMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoundService {
    private final RoundMapper roundMapper;
    private final RoundDetailMapper roundDetailMapper;

    public RoundService(RoundMapper roundMapper, RoundDetailMapper roundDetailMapper) {
        this.roundMapper = roundMapper;
        this.roundDetailMapper = roundDetailMapper;
}
    public List<RoundTodayDto> getTodayRounds() {
        return roundMapper.findTodayRounds();
    }

    public RoundWithOptionsDto getRound(Long roundId) {
        return roundDetailMapper.findRoundWithOptions(roundId);
    }
}