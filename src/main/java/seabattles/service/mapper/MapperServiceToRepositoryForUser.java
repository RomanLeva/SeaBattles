package seabattles.service.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import seabattles.entity.User;
import org.springframework.stereotype.Component;
import seabattles.service.dto.UserDto;
import seabattles.service.dto.UserRegisterDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MapperServiceToRepositoryForUser {
    @Autowired
    private final ModelMapper modelMapper;

    public List<UserDto> mapUserToDtoList(List<User> users){
        return users.stream().map(user ->
                modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    public UserDto mapUserToDto(User user){
        UserDto userDto = new UserDto();
        modelMapper.map(user, userDto);
        return userDto;
    }

    public User mapUserRegisterDtoToUser(UserRegisterDto userRegisterDto) {
        User user = new User();
        modelMapper.map(userRegisterDto, user);
        return user;
    }
}
