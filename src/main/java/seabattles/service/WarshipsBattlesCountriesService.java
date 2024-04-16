package seabattles.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seabattles.aspect.exception.WarshipBattleException;
import seabattles.entity.Battle;
import seabattles.entity.BattleMember;
import seabattles.entity.Warship;
import seabattles.repository.BattleJpaRepo;
import seabattles.repository.BattleMemberJpaRepo;
import seabattles.repository.WarshipJpaRepo;
import seabattles.service.dto.BattleMemberCreateDto;
import seabattles.service.dto.WarshipDto;
import seabattles.service.dto.WarshipCreateDto;
import seabattles.service.dto.BattleCreateDto;
import seabattles.service.mapper.MapperServiceToRepositoryForWarshipBattleCountry;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class WarshipsBattlesCountriesService {
    @Autowired
    MapperServiceToRepositoryForWarshipBattleCountry mapper;
    @Autowired
    WarshipJpaRepo warshipRepository;
    @Autowired
    BattleJpaRepo battleRepository;
    @Autowired
    BattleMemberJpaRepo battleMemberRepository;

    public List<WarshipDto> getAllWarships() {
        List<Warship> warships = warshipRepository.findAll();
        return mapper.mapObjectsToDtoList(WarshipDto.class, warships);
    }

    public void createWarship(WarshipCreateDto warshipRegisterDto) {
        Warship warship = mapper.mapCreateDtoToMyObject(warshipRegisterDto, Warship.class);
        Warship maybePresentWarship = warshipRepository.findByWarshipName(warship.getWarshipName());

        if (maybePresentWarship != null){
            throw new WarshipBattleException("Warship with this name is already present.");
        } else {
            warshipRepository.save(warship);
        }
    }

    public void createBattle(BattleCreateDto battleCreateDto) {
        Battle battle = mapper.mapCreateDtoToMyObject(battleCreateDto, Battle.class);
        Battle maybePresentBattle = battleRepository.findByBattleName(battle.getBattleName());

        if (maybePresentBattle != null){
            throw new WarshipBattleException("Battle with this name is already present.");
        } else {
            battleRepository.save(battle);
        }
    }

    public void createBattleMember(BattleMemberCreateDto memberCreateDto){
        BattleMember battleMember = mapper.mapCreateDtoToMyObject(memberCreateDto, BattleMember.class);

        Warship newWarship = battleMember.getWarship();
        Warship presentWarship = warshipRepository.findByWarshipName(newWarship.getWarshipName());
        if (presentWarship == null) {
            throw new WarshipBattleException("Warship with this name is not present in the database.");
        }

        
        battleMemberRepository.save(battleMember);
    }

    public List<String> getAllWarshipClasses() {
        return Stream.of(Warship.WARSHIP_CLASS.values()).map(Warship.WARSHIP_CLASS::name).collect(Collectors.toList());
    }
}
