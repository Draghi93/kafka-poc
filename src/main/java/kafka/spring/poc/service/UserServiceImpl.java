package kafka.spring.poc.service;

import kafka.spring.poc.dto.UserRequestDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public final class UserServiceImpl implements UserService {


    @Override
    public void createUser(UserRequestDto userRequestDto) {

    }

    @Override
    public Optional<UserRequestDto> findUserById(Long userId) {
        return Optional.empty();
    }

    @Override
    public void deleteUser(Long userId) {

    }

    @Override
    public UserRequestDto updateUser(UserRequestDto userRequestDto) {
        return null;
    }
}
