package seabattles.controller;

import seabattles.service.dto.UserUpdatePasswordDto;
import seabattles.controller.mapper.MapperDtoToView;
import seabattles.controller.view.UserViewModel;
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
import seabattles.service.dto.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @Autowired
    private final MapperDtoToView viewMapper;

    @AllArgsConstructor
    @Getter
    public static class ControllerResponse {
        private String responseMessage;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserViewModel>> getAllUsers() {
        List<UserDto> usersDto = userService.getAllUsers();

        return ResponseEntity.ok(viewMapper.mapDtoListToViews(UserViewModel.class, usersDto));
    }

    @PostMapping("/register")
    public ResponseEntity<ControllerResponse> registerUser(@RequestBody UserRegisterDto userRegisterDto) {
        userService.registerUser(userRegisterDto);

        return ResponseEntity.ok(new ControllerResponse("User registered."));
    }


    @PostMapping("/login")
    public ResponseEntity<ControllerResponse> loginUser(@RequestBody UserAuthDto userAuthDto){
        userService.loginUser(userAuthDto);

        return ResponseEntity.ok(new ControllerResponse("User logged in."));
    }

    @PostMapping("/update_password")
    public ResponseEntity<ControllerResponse> updateUserPass(@RequestBody UserUpdatePasswordDto userUpdatePasswordDto) {
        UserAuthDto userAuthDto = new UserAuthDto(userUpdatePasswordDto.getLogin(), userUpdatePasswordDto.getOldPassword());
        userService.updateUserPass(userAuthDto, userUpdatePasswordDto.getNewPassword());

        return ResponseEntity.ok(new ControllerResponse("User password updated."));
    }
}
