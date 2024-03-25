package seabattles.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seabattles.aspect.exception.WarshipException;
import seabattles.entity.Warship;
import seabattles.repository.BattleJpaRepo;
import seabattles.repository.BattleMemberJpaRepo;
import seabattles.repository.WarshipJpaRepo;
import seabattles.service.dto.WarshipDto;
import seabattles.service.dto.WarshipRegisterDto;
import seabattles.service.mapper.MapperServiceToRepositoryWarshipDto;

@Service
@RequiredArgsConstructor
public class BattlesAndShipsService {
    @Autowired
    MapperServiceToRepositoryWarshipDto mapper;
    @Autowired
    WarshipJpaRepo warshipRepository;
    @Autowired
    BattleJpaRepo battleRepository;
    @Autowired
    BattleMemberJpaRepo battleMemberRepository;

    public WarshipDto createWarship(WarshipRegisterDto warshipRegisterDto) {
        Warship warship = mapper.mapRegisterToWarship(warshipRegisterDto);
        Warship maybePresentWarship = warshipRepository.findByWarshipName(warship.getWarshipName());

        if (maybePresentWarship != null){
            throw new WarshipException("warship with this name is already present.");
        } else {
            return mapper.mapWarshipToDto(warshipRepository.save(warship));
        }
    }


}
