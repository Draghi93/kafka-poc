package kafka.spring.poc.db.entity;

import jakarta.persistence.*;
import kafka.spring.poc.constants.EventStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "event_date", nullable = false)
    private LocalDate eventDate;

    @Column(name = "status")
    private EventStatus status;
}
