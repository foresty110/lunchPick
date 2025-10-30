package kr.sparta.backend1.lunch.entity;

import jakarta.persistence.*;

import kr.sparta.backend1.lunch.dto.MenuDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
    private Member userId;
    private String date;

    public Round(Member userId, String date) {
        this.userId = userId;
        this.date = date;
    }
}
