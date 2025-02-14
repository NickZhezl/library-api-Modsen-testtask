package dev.earlspilner.users.service;

import dev.earlspilner.users.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Nikita Zhelezko
 */
public interface UserService {
    UserDto saveUser(UserDto dto);
    UserDto getUser(String username);
    Page<UserDto> getUsers(Pageable pageable);
    UserDto updateUser(String username, UserDto dto);
    void deleteUser(Integer id);
}
