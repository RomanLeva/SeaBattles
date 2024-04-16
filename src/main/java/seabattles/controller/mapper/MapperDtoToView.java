package seabattles.controller.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MapperDtoToView {
    @Autowired
    private final ModelMapper modelMapper;

    public <DtoType, ViewType> List<ViewType> mapDtoListToViews(Class<ViewType> type, List<DtoType> dtoList) {
        return dtoList.stream().map(dto ->
                modelMapper.map(dto, type)).collect(Collectors.toList());
    }

}
