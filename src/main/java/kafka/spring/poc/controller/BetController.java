package kafka.spring.poc.controller;

import kafka.spring.poc.dto.BetDto;
import kafka.spring.poc.service.BetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Dummy implementation. The purpose of this is to test the Kafka Producer/Consumer flow.
 * TODO: Delete/replace after OpenAPI implementation
 */
@RestController
@RequestMapping("/api/bets")
public class BetController {

    private final BetService betService;

    public BetController(BetService betService) {
        this.betService = betService;
    }

    @GetMapping("/results/{betId}")
    public ResponseEntity<String> getBetResult(@PathVariable Long betId) {
        // Logic to retrieve specific bet result
        betService.getBetResults(betId);
        return ResponseEntity.ok(new String());
    }

    @PostMapping("/place")
    public ResponseEntity<String> placeBet(@RequestBody BetDto betDto) {
        // Logic to retrieve specific bet result
        betService.placeBet(betDto);
        return ResponseEntity.ok("Successfully placed a bet");
    }
}