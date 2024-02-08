package seabattles.controller;

import seabattles.controller.dto.AuthUserDto;
import seabattles.controller.dto.NewUserDto;
import seabattles.controller.dto.UpdateUserPasswordDto;
import seabattles.controller.mapper.MapperControllerToServiceUserDto;
import seabattles.controller.mapper.MapperDtoToView;
import seabattles.controller.view.UserViewModel;
import seabattles.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import seabattles.service.UserService;
import seabattles.service.dto.UserAuthDto;
import seabattles.service.dto.UserDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @Autowired
    private final MapperDtoToView viewMapper;
    @Autowired
    private final MapperControllerToServiceUserDto serviceMapper;

    @AllArgsConstructor
    @Getter
    public static class ControllerResponse {
        private String responseMessage;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserViewModel>> getAllUsers() {
        List<UserDto> usersDto = userService.getAllUsers();
        return ResponseEntity.ok(viewMapper.mapUserDtoListToView(usersDto));
    }

    @PostMapping("/register")
    public ResponseEntity<ControllerResponse> registerUser(@RequestBody NewUserDto newUserDto) {
        User user = serviceMapper.mapNewUserDtoToUser(newUserDto);
        UserDto userDto = userService.registerUser(user);

        UserViewModel viewModel = viewMapper.mapUserDtoToView(userDto);
        return ResponseEntity.ok(new ControllerResponse("User registered."));
    }


    @PostMapping("/login")
    public ResponseEntity<ControllerResponse> loginUser(@RequestBody AuthUserDto authUserDto){
        UserAuthDto userAuthDto = serviceMapper.mapControllerAuthToServiceAuthDto(authUserDto);
        UserDto userDto = userService.loginUser(userAuthDto);
        UserViewModel viewModel = viewMapper.mapUserDtoToView(userDto);
        return ResponseEntity.ok(new ControllerResponse("User logged in."));
    }

    @PostMapping("/update_password")
    public ResponseEntity<ControllerResponse> updateUserPass(@RequestBody UpdateUserPasswordDto updateUserPasswordDto) {
        AuthUserDto authUserDto = new AuthUserDto(updateUserPasswordDto.getLogin(), updateUserPasswordDto.getOldPassword());
        UserAuthDto userAuthDto = serviceMapper.mapControllerAuthToServiceAuthDto(authUserDto);
        UserDto userDto = userService.updateUserPass(userAuthDto, updateUserPasswordDto.getNewPassword());

        UserViewModel viewModel = viewMapper.mapUserDtoToView(userDto);
        return ResponseEntity.ok(new ControllerResponse("User password updated."));
    }
}
