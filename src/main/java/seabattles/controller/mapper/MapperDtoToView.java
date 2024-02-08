package seabattles.controller.mapper;

import seabattles.controller.view.UserViewModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import seabattles.service.dto.UserDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MapperDtoToView {
    @Autowired
    private ModelMapper modelMapper;

    public List<UserViewModel> mapUserDtoListToView(List<UserDto> userDtoList){
        return userDtoList.stream().map(userDto ->
                modelMapper.map(userDto, UserViewModel.class)).collect(Collectors.toList());
    }

    public UserViewModel mapUserDtoToView(UserDto userDto){
        UserViewModel userViewModel = new UserViewModel();
        modelMapper.map(userDto, userViewModel);
        return userViewModel;
    }
}
