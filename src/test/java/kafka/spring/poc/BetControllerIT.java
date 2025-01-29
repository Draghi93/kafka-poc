package kafka.spring.poc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import kafka.spring.poc.dto.BetDto;
import kafka.spring.poc.kafka.BetConsumer;
import kafka.spring.poc.kafka.BetProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext
@ActiveProfiles("integration")
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
public class BetControllerIT {

    private final ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BetConsumer betConsumer;

    @Autowired
    private BetProducer betProducer;

    /**
     * Dummy implementation of an integration test. The purpose of this is to showcase how can we write integration tests,
     * using test containers to spin up a Kafka instance
     *
     * @throws Exception
     */
    @Test
    void getBetResult_success() throws Exception {

        // GIVEN: A valid BetDto used for getBetResult request
        BetDto betDto = new BetDto();
        betDto.setBetId(1L);
        betDto.setResult("WIN");
        betDto.setAmount(10.0);
        betDto.setUserId(1L);

        String betDtoJson = objectWriter.writeValueAsString(betDto);

        // EXPECT: successful response when performing a bet placement
        mockMvc.perform(post("/api/bets/place")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(betDtoJson))
                .andExpect(status().isOk())
                .andExpect(content().string("Successfully placed a bet"));
    }
}
