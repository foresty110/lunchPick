package kr.sparta.backend1.lunch.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "lunch_round")
public class Round extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memeber_id")
    private Member member;
    private LocalDate date;

    public Round(Member member, LocalDate date) {
        this.member = member;
        this.date = date;
    }
}
