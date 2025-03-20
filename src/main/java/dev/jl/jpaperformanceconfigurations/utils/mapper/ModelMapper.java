package dev.jl.jpaperformanceconfigurations.utils.mapper;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ModelMapper implements Mapper {

    private final org.modelmapper.ModelMapper mapper;

    public ModelMapper() {
        this.mapper = new org.modelmapper.ModelMapper();
        mapper.getConfiguration().setPropertyCondition(source -> source.getSource() != null);
    }

    @Override
    public <S, D> D map(S source, Class<D> destination) {
        return mapper.map(source, destination);
    }

    @Override
    public <S, D> void map(S source, D destination) {
        mapper.map(source, destination);
    }

    @Override
    public <S, D> List<D> map(List<S> source, Class<D> destination) {
        return source.stream().map(s ->
                mapper.map(s, destination)
        ).toList();
    }
}
