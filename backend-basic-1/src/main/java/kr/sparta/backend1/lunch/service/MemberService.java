package kr.sparta.backend1.lunch.service;

import kr.sparta.backend1.lunch.dto.MemberDto;
import kr.sparta.backend1.lunch.entity.Member;
import kr.sparta.backend1.lunch.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository repo;
    private final PasswordEncoder passwordEncoder;

    public Page<Member> getAllMembers(int page, int size) {
        return repo.findAll(PageRequest.of(page, size));
    }

    public Member getMemberById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalStateException("회원을 찾을 수 없습니다. ID: " + id));

//                .orElseThrow(() -> new NotFoundException("회원을 찾을 수 없습니다. ID: " + id));
    }

    public Member getMemberByUsername(String username) {
        return repo.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException("회원을 찾을 수 없습니다. Username: " + username));
                //.orElseThrow(() -> new NotFoundException("회원을 찾을 수 없습니다. Username: " + username));
    }


    @Transactional
    public Member createMember(MemberDto dto) {
        // 아이디 중복 체크
        if (repo.existsByUsername(dto.getUsername())) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다: " + dto.getUsername());
        }

        // 비밀번호 필수 체크
        if (dto.getPassword() == null || dto.getPassword().isBlank()) {
            throw new IllegalArgumentException("비밀번호는 필수입니다.");
        }

        Member m = Member.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role("USER")
                .build();
        repo.save(m);
        return m;
    }

    @Transactional
    public Member updateMember(Long id, MemberDto dto) {
        Member m = getMemberById(id);
       // m.setName(dto.getName());

        // 비밀번호가 제공된 경우에만 업데이트
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            //m.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        return repo.save(m);
    }

    @Transactional
    public void deleteMember(Long id) {
        Member m = getMemberById(id);
        repo.delete(m);
    }
}