package kafka.spring.poc.service;

import kafka.spring.poc.dto.UserRequestDto;

import java.util.Optional;

/**
 * Service interface for user operations.
 * Sealed interface to restrict implementation and enforce API stability.
 * TODO: adapt implementation when using DB stored User objects
 */
public sealed interface UserService permits UserServiceImpl {

    /**
     * Creates a new user.
     *
     * @param userRequestDto the user data transfer object (must not be null)
     * @throws IllegalArgumentException if userRequestDto is null
     */
    void createUser(UserRequestDto userRequestDto);

    /**
     * Finds a user by ID.
     *
     * @param userId the unique user identifier
     * @return an Optional containing the user if found, or empty if not
     */
    Optional<UserRequestDto> findUserById(Long userId);

    /**
     * Deletes a user by ID.
     *
     * @param userId the unique user identifier
     * @throws IllegalArgumentException if userId is null or invalid
     */
    void deleteUser(Long userId);

    /**
     * Updates user information.
     *
     * @param userRequestDto the updated user data
     * @return the updated user details
     * @throws IllegalArgumentException if userRequestDto is null
     */
    UserRequestDto updateUser(UserRequestDto userRequestDto);
}
