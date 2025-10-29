package kr.sparta.backend1.lunch.service;

import kr.sparta.backend1.lunch.entity.Member;
import kr.sparta.backend1.lunch.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // username으로 회원 조회
        Member member = memberRepository.findByEmail(username)
                .orElseThrow(() -> new IllegalStateException("사용자를 찾을 수 없습니다."));
//                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username));

        return User.builder()
                .username(member.getEmail())  // username 사용
                .password(member.getPassword())
                .roles(member.getRole())
                .build();
    }
}
