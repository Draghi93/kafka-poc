package kafka.spring.poc.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kafka.spring.poc.dto.BetDto;
import kafka.spring.poc.dto.BetResultDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class BetProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public BetProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void publishToBetPlacementTopic(BetDto betDto) {
        try {
            String message = objectMapper.writeValueAsString(betDto);
            kafkaTemplate.send("bet_placement_topic", message);
        } catch (JsonProcessingException e) {
            //TODO: replace with a proper error handling mechanism
            throw new RuntimeException("Failed to serialize betDto to JSON", e);
        }
    }

    public void publishToBetResultTopic(BetResultDto betResultDto) {
        try {
            String message = objectMapper.writeValueAsString(betResultDto);
            kafkaTemplate.send("bet_result_topic", message);
        } catch (JsonProcessingException e) {
            //TODO: replace with a proper error handling mechanism
            throw new RuntimeException("Failed to serialize betEvent to JSON", e);
        }
    }
}
