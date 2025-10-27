package kr.sparta.backend1.lunch.mapper;

import kr.sparta.backend1.lunch.dto.MenuOptionDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MenuOptionMapper {
    List<MenuOptionDto> search(
            @Param("roundId") Long roundId,
            @Param("foodType") String foodType,
            @Param("keyword") String keyword
    );
}