package seabattles.service.mapper;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import seabattles.aspect.exception.MapperException;
import seabattles.entity.Battle;
import seabattles.entity.Warship;
import seabattles.service.dto.BattleCreateDto;
import seabattles.service.dto.WarshipDto;
import seabattles.service.dto.WarshipCreateDto;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class MapperServiceToRepositoryForWarshipBattleCountry {
    @Autowired
    private final ModelMapper modelMapper;

    public <ObjectType, DtoType> ObjectType mapCreateDtoToMyObject(DtoType dtoType, Class<ObjectType> myObject){
        ObjectType object;
        try {
            object = myObject.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new MapperException("Mapping my objects to DTOs exception: " + e.getMessage());
        }
        modelMapper.map(dtoType, object);
        return object;
    }

    public <ObjectType, DtoType> List<DtoType> mapObjectsToDtoList(Class<DtoType> dtoType, List<ObjectType> myObjects) {
        return myObjects.stream().map(object ->
                modelMapper.map(object, dtoType)).collect(Collectors.toList());
    }
}
