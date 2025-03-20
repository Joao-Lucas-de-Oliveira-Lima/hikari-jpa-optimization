package dev.jl.jpaperformanceconfigurations.utils.mapper;

import java.util.List;

public interface Mapper {
    <S, D> D map(S source, Class<D> destination);
    <S, D> void map(S source, D destination);
    <S, D> List<D> map(List<S> source, Class<D> destination);
}
