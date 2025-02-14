package dev.earlspilner.users.mapper;

import dev.earlspilner.users.dto.UserDto;
import dev.earlspilner.users.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Nikita Zhelezko
 */
@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUserEntity(UserDto userDto);
    UserDto toUserDto(User user);

    @Mapping(target = "password", ignore = true)
    UserDto toRegisterResponse(User user);
}
