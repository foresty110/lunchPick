package kr.sparta.backend1.lunch.service;

import kr.sparta.backend1.lunch.dto.MemberDto;
import kr.sparta.backend1.lunch.dto.SignUpRequestDto;
import kr.sparta.backend1.lunch.entity.Member;
import kr.sparta.backend1.lunch.exception.NotFoundException;
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
               .orElseThrow(() -> new NotFoundException("회원을 찾을 수 없습니다. ID: " + id));
    }

    public Member getMemberByEmail(String email) {
        return repo.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("회원을 찾을 수 없습니다." + email));
    }


    @Transactional
    public Member createMember(SignUpRequestDto dto) {
        // 이메일 중복 체크
        if (repo.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다: " + dto.getEmail());
        }

        // 비밀번호 필수 체크
        if (dto.getPassword() == null || dto.getPassword().isBlank()) {
            throw new IllegalArgumentException("비밀번호는 필수입니다.");
        }

        String admin = "USER";
        if (dto.getAdmin() == true )
        {
            admin = "ADMIN";
        }

        Member m = Member.builder()
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .name(dto.getName())
                .role(admin)
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