package kr.sparta.backend1.lunch.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "menus")
public class Menu extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "round_id")
    private Round round;
    private String name;
    private String type;
    private int price;

    public Menu(String name,Round round, String type, int price) {
        this.name = name;
        this.round = round;
        this.type = type;
        this.price = price;
    }
}
