package kr.sparta.backend1.lunch.service;


import kr.sparta.backend1.lunch.dto.*;
import kr.sparta.backend1.lunch.entity.Member;
import kr.sparta.backend1.lunch.entity.Menu;
import kr.sparta.backend1.lunch.entity.Round;
import kr.sparta.backend1.lunch.exception.NotFoundException;
import kr.sparta.backend1.lunch.mapper.MenuMapper;
import kr.sparta.backend1.lunch.mapper.RoundDetailMapper;
import kr.sparta.backend1.lunch.mapper.RoundMapper;
import kr.sparta.backend1.lunch.repository.MemberRepository;
import kr.sparta.backend1.lunch.repository.MenuRepository;
import kr.sparta.backend1.lunch.repository.RoundRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoundService {

    private final RoundRepository roundRepository;
    private final RoundMapper roundMapper;
    private final RoundDetailMapper roundDetailMapper;
    private final MemberRepository memberRepository;
    private final MenuRepository menuRepository;

    public RoundService(RoundRepository roundRepository, RoundMapper roundMapper, RoundDetailMapper roundDetailMapper, MemberRepository memberRepository, MenuRepository menuRepository) {
        this.roundRepository = roundRepository;
        this.roundMapper = roundMapper;
        this.roundDetailMapper = roundDetailMapper;
        this.memberRepository = memberRepository;
        this.menuRepository = menuRepository;
    }
    public List<RoundTodayDto> getTodayRounds() {
        return roundMapper.findTodayRounds();
    }

    public RoundWithOptionsDto getRound(Long roundId) {
        return roundDetailMapper.findRoundWithOptions(roundId);
    }

    public RoundCreateResponseDto save(RoundCreateRequestDto request, Authentication auth) {

        Member member = memberRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new NotFoundException("회원을 찾을 수 없습니다. ID: " + auth.getName())
        );

        Round round = new Round(
                member,
                request.getDate()
        );
        roundRepository.save(round);

        //메뉴 데이터 저장
        for(MenuDto reqMenu: request.getMenus()) {
            Menu newMenu = new Menu(
                    reqMenu.getName(),
                    round,
                    reqMenu.getType(),
                    reqMenu.getPrice()
            );
            menuRepository.save(newMenu);
        }
        List<Menu> menuList = menuRepository.findAllByRound_Id(round.getId());

        List<MenuDto> menuDtoList = menuList.stream()
                .map(MenuMapper::toDto)
                .toList();

        return new RoundCreateResponseDto(
                round.getId(),
                member.getId(),
                round.getDate(),
                menuDtoList
        );
    }
}