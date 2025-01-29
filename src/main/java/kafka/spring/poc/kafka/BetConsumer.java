package kafka.spring.poc.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import kafka.spring.poc.dto.BetDto;
import kafka.spring.poc.dto.BetResultDto;
import kafka.spring.poc.service.BetService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class BetConsumer {
    private final BetService betService;
    private final ObjectMapper objectMapper;

    public BetConsumer(BetService betService, ObjectMapper objectMapper) {
        this.betService = betService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "bet_placement_topic", groupId = "bet_group")
    public void processBetPlacement(String message) {
        try {
            BetDto betDto = objectMapper.readValue(message, BetDto.class);
            System.out.println("Processing new bet placement: " + betDto.toString());
            // Process bet placement logic here
        } catch (Exception e) {
            throw new RuntimeException("Failed to deserialize bet placement message", e);
        }
    }

    @KafkaListener(topics = "bet_result_topic", groupId = "bet_group")
    public void processBetResults(String message) {
        try {
            BetResultDto betResultDto = objectMapper.readValue(message, BetResultDto.class);
            System.out.println("Processing bet result: " + betResultDto);
            // Process bet result logic here
        } catch (Exception e) {
            throw new RuntimeException("Failed to deserialize bet result message", e);
        }
    }
}