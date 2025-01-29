package kafka.spring.poc.dto;

/**
 * This DTO class has a dummy implementation currently. It will be most likely replaced in the future.
 * TODO: To be deleted after the actual implementation
 */

public class BetDto {

    private Long betId;
    private Long userId;
    private Double amount;
    private String result;

    public BetDto() {
    }

    public BetDto(Long betId, Long userId, Double amount, String result) {
        this.betId = betId;
        this.userId = userId;
        this.amount = amount;
        this.result = result;
    }

    public Long getBetId() {
        return betId;
    }

    public void setBetId(Long betId) {
        this.betId = betId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
