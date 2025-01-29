package kafka.spring.poc.db.entity;

import jakarta.persistence.*;
import kafka.spring.poc.constants.BetStatus;
import kafka.spring.poc.constants.BetType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "bet")
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(name = "bet_amount")
    private BigDecimal betAmount;

    @Column(name = "bet_type")
    private BetType betType;

    @Column(name = "status")
    private BetStatus status;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;
}
