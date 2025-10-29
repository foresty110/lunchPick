package kr.sparta.backend1.lunch.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "members")
//Member: 사용자 계정 정보를 담은 entity 클래스
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String email;  // 로그인 ID
    @NotNull
    @Column(length = 100)
    private String password; // 비밀번호
    @Column(nullable = false, length = 50, unique = true)
    private String name;  // 회원 이름
    private String role = "User";

}
