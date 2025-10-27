package kr.sparta.backend1.lunch.mapper;

import kr.sparta.backend1.lunch.dto.RoundTodayDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoundMapper {
    List<RoundTodayDto> findTodayRounds();
}