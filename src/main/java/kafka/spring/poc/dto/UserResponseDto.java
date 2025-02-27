package kafka.spring.poc.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class UserResponseDto {

    private Long id;
    private String username;
    private BigDecimal balance;
    private LocalDate createdAt;

    public UserResponseDto() {

    }

    public UserResponseDto(Long id, String username, BigDecimal balance, LocalDate createdAt) {
        this.id = id;
        this.username = username;
        this.balance = balance;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserResponseDto that = (UserResponseDto) o;
        return id.equals(that.id) && username.equals(that.username) && balance.equals(that.balance) && createdAt.equals(that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, balance, createdAt);
    }
}
