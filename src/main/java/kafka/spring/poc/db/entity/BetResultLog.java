package kafka.spring.poc.db.entity;

import jakarta.persistence.*;
import kafka.spring.poc.constants.BetResultLogStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "bet_result_log")
public class BetResultLog {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bet_id", nullable = false)
    private Bet bet;

    @Column(name = "status")
    private BetResultLogStatus status;

    @Column(name = "payout")
    private BigDecimal payout; // Positive if won, negative if lost

    @Column(name = "processed_at")
    private LocalDate processedAt;
}
