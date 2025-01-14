package kafka.spring.poc.service;

import kafka.spring.poc.dto.BetDto;
import kafka.spring.poc.dto.BetResultDto;
import kafka.spring.poc.kafka.BetProducer;
import org.springframework.stereotype.Service;

/**
 * Dummy implementation. The purpose of this is to test the Kafka Producer/Consumer flow.
 */
@Service
public class BetService {

    private final BetProducer betProducer;

    public BetService(BetProducer betProducer) {
        this.betProducer = betProducer;
    }

    public String placeBet(BetDto betDto) {
        betProducer.publishToBetPlacementTopic(betDto);
        return "Place bet action has been proccessed";
    }

    public String getBetResults(Long betId) {
        BetResultDto betResultDto = new BetResultDto();
        betResultDto.setBetId(betId);
        betResultDto.setUserId(1L);
        betResultDto.setAmount(10);
        betResultDto.setResult("W");

        betProducer.publishToBetResultTopic(betResultDto);
        return "Place bet action has been proccessed";
    }
}
