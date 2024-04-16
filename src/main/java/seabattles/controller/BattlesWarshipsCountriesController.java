package seabattles.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import seabattles.controller.mapper.MapperDtoToView;
import seabattles.controller.view.WarshipViewModel;
import seabattles.service.WarshipsBattlesCountriesService;
import seabattles.service.dto.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BattlesWarshipsCountriesController {
    @Autowired
    private final WarshipsBattlesCountriesService battlesAndWarshipsService;

    @Autowired
    private final MapperDtoToView viewMapper;

    @AllArgsConstructor
    @Getter
    public static class ControllerResponse {
        private String responseMessage;
    }

    @GetMapping("/warships")
    public ResponseEntity<List<WarshipViewModel>> getAllWarships(){
        List<WarshipDto> warshipDtoList = battlesAndWarshipsService.getAllWarships();

        return ResponseEntity.ok(viewMapper.mapDtoListToViews(WarshipViewModel.class, warshipDtoList));
    }

    @GetMapping("/warship_classes")
    public ResponseEntity<List<String>> getWarshipClasses(){
        List<String> warshipClasses = battlesAndWarshipsService.getAllWarshipClasses();

        return ResponseEntity.ok(warshipClasses);
    }

    @PostMapping("/create_warship")
    public ResponseEntity<ControllerResponse> createWarship(@RequestBody WarshipCreateDto warshipCreateDto) {
        battlesAndWarshipsService.createWarship(warshipCreateDto);

        return ResponseEntity.ok(new ControllerResponse("Warship created."));
    }

    @PostMapping("/create_battle")
    public ResponseEntity<ControllerResponse> createBattle(@RequestBody BattleCreateDto battleCreateDto) {
        battlesAndWarshipsService.createBattle(battleCreateDto);

        return ResponseEntity.ok(new ControllerResponse("Battle created."));
    }

    @PostMapping("/create_battle_member")
    public ResponseEntity<ControllerResponse> createBattleMember(@RequestBody BattleMemberCreateDto memberCreateDto) {
        battlesAndWarshipsService.createBattleMember(memberCreateDto);

        return ResponseEntity.ok(new ControllerResponse("Battle member created."));
    }
}
