package kr.sparta.backend1.lunch.mapper;


import kr.sparta.backend1.lunch.dto.RoundWithOptionsDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RoundDetailMapper {
    RoundWithOptionsDto findRoundWithOptions(@Param("roundId") Long roundId);
}