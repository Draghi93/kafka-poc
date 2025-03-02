package kafka.spring.poc.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class UserRequestDto {

    private String username;
    private BigDecimal balance;

    public UserRequestDto() {
    }

    public UserRequestDto(String username, BigDecimal balance) {
        this.username = username;
        this.balance = balance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRequestDto that = (UserRequestDto) o;
        return username.equals(that.username) && balance.equals(that.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, balance);
    }
}
