package seabattles.service;

import jakarta.persistence.Column;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import seabattles.aspect.exception.WarshipBattleException;
import seabattles.entity.Battle;
import seabattles.entity.BattleMember;
import seabattles.entity.Warship;
import seabattles.repository.BattleJpaRepo;
import seabattles.repository.BattleMemberJpaRepo;
import seabattles.repository.WarshipJpaRepo;
import seabattles.service.dto.*;
import seabattles.service.mapper.MapperServiceToRepositoryForWarshipBattleCountry;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
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
        Warship warship = mapper.mapDtoToMyObject(warshipRegisterDto, Warship.class);
        Warship maybePresentWarship = warshipRepository.findByWarshipName(warship.getWarshipName());

        if (maybePresentWarship != null){
            throw new WarshipBattleException("Warship with this name is already present.");
        } else {
            warshipRepository.save(warship);
        }
    }

    public void createBattle(BattleCreateDto battleCreateDto) {
        Battle battle = mapper.mapDtoToMyObject(battleCreateDto, Battle.class);
        Battle maybePresentBattle = battleRepository.findByBattleName(battle.getBattleName());

        if (maybePresentBattle != null){
            throw new WarshipBattleException("Battle with this name is already present.");
        } else {
            battleRepository.save(battle);
        }
    }

    public void createBattleMember(BattleMemberCreateDto memberCreateDto){
        BattleMember battleMember = mapper.mapDtoToMyObject(memberCreateDto, BattleMember.class);

        String warshipColumnName;
        try {
            Field field = Warship.class.getDeclaredField("warshipName");
            Column declaredAnnotation = field.getDeclaredAnnotation(Column.class);
            warshipColumnName = declaredAnnotation.name();
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

        ExampleMatcher warshipMatcher = ExampleMatcher.matching()
                .withMatcher(warshipColumnName, ExampleMatcher.GenericPropertyMatchers.exact().ignoreCase());
        Example<Warship> warshipExample = Example.of(battleMember.getWarship(), warshipMatcher);
        Optional<Warship> optionalWarship = warshipRepository.findOne(warshipExample);
        if (optionalWarship.isEmpty()) {
            throw new WarshipBattleException("Warship with this name is not present in the database.");
        }

        String battleColumnName;
        try {
            Field field = Battle.class.getDeclaredField("battleName");
            Column declaredAnnotation = field.getDeclaredAnnotation(Column.class);
            battleColumnName = declaredAnnotation.name();
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

        ExampleMatcher battleMatcher = ExampleMatcher.matching()
                .withMatcher(battleColumnName, ExampleMatcher.GenericPropertyMatchers.exact().ignoreCase());
        Example<Battle> battleExample = Example.of(battleMember.getBattle(), battleMatcher);
        Optional<Battle> optionalBattle = battleRepository.findOne(battleExample);
        if (optionalBattle.isEmpty()) {
            throw new WarshipBattleException("Battle with this name is not present in the database.");
        }
        Warship warship = optionalWarship.get();
        Battle battle = optionalBattle.get();

        if (warship.getCommissionDate().after(battle.getBattleDate())
                || warship.getDecommissionDate().before(battle.getBattleDate())){
            throw new WarshipBattleException("Warship operational date is not comparable with battle date.");
        }

        battleMember.setWarship(warship);
        battleMember.setBattle(battle);

        battleMemberRepository.save(battleMember);
    }

    @Transactional
    public void updateBattleMember(BattleMemberUpdateDto memberUpdateDto) {
        String warshipColumnName;
        try {
            Field field = Warship.class.getDeclaredField("warshipName");
            Column declaredAnnotation = field.getDeclaredAnnotation(Column.class);
            warshipColumnName = declaredAnnotation.name();
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

        Warship newWarshipForExample = mapper.mapDtoToMyObject(memberUpdateDto.getNewWarship(), Warship.class);
        Warship oldWarshipForExample = mapper.mapDtoToMyObject(memberUpdateDto.getOldWarship(), Warship.class);

        ExampleMatcher warshipMatcher = ExampleMatcher.matching()
                .withMatcher(warshipColumnName, ExampleMatcher.GenericPropertyMatchers.exact().ignoreCase());

        Example<Warship> newWarshipExample = Example.of(newWarshipForExample, warshipMatcher);
        Example<Warship> oldWarshipExample = Example.of(oldWarshipForExample, warshipMatcher);

        Optional<Warship> optionalNewWarship = warshipRepository.findOne(newWarshipExample);
        if (optionalNewWarship.isEmpty()) {
            throw new WarshipBattleException("New warship with this name is not present in the database.");
        }
        Warship newWarship = optionalNewWarship.get();

        Optional<Warship> optionalOldWarship = warshipRepository.findOne(oldWarshipExample);
        if (optionalOldWarship.isEmpty()) {
            throw new WarshipBattleException("Old warship with this name is not present in the database.");
        }
        Warship oldWarship = optionalOldWarship.get();

        String battleColumnName;
        try {
            Field field = Battle.class.getDeclaredField("battleName");
            Column declaredAnnotation = field.getDeclaredAnnotation(Column.class);
            battleColumnName = declaredAnnotation.name();
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

        Battle newBattleForExample = mapper.mapDtoToMyObject(memberUpdateDto.getNewBattle(), Battle.class);
        Battle oldBattleForExample = mapper.mapDtoToMyObject(memberUpdateDto.getOldBattle(), Battle.class);

        ExampleMatcher battleMatcher = ExampleMatcher.matching()
                .withMatcher(battleColumnName, ExampleMatcher.GenericPropertyMatchers.exact().ignoreCase());

        Example<Battle> newBattleExample = Example.of(newBattleForExample, battleMatcher);
        Example<Battle> oldBattleExample = Example.of(oldBattleForExample, battleMatcher);

        Optional<Battle> optionalNewBattle = battleRepository.findOne(newBattleExample);
        if (optionalNewBattle.isEmpty()) {
            throw new WarshipBattleException("New battle with this name is not present in the database.");
        }
        Battle newBattle = optionalNewBattle.get();

        Optional<Battle> optionalOldBattle = battleRepository.findOne(oldBattleExample);
        if (optionalOldBattle.isEmpty()) {
            throw new WarshipBattleException("Old battle with this name is not present in the database.");
        }
        Battle oldBattle = optionalOldBattle.get();

        BattleMember battleMember = battleMemberRepository.findByWarshipAndBattle(oldWarship, oldBattle);

        battleMember.setWarship(newWarship);
        battleMember.setBattle(newBattle);
        battleMember.setStatus(mapper.mapDtoToMyObject(memberUpdateDto, BattleMember.class).getStatus());
    }

    public List<String> getAllWarshipClasses() {
        return Stream.of(Warship.WARSHIP_CLASS.values()).map(Warship.WARSHIP_CLASS::name).collect(Collectors.toList());
    }
}
