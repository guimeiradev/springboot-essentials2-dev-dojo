package academy.devdojo.springboot2.mapper;

import academy.devdojo.springboot2.domain.Anime;
import academy.devdojo.springboot2.dto.AnimePostDto;
import academy.devdojo.springboot2.dto.AnimePutDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class AnimeMapper {
    public static Anime toEntity(AnimePutDto animePutDto) {
        return Anime.builder().name(animePutDto.getName()).id(animePutDto.getId()).build();
    }
    public static Anime toEntity(AnimePostDto animePostDto) {
        return Anime.builder().name(animePostDto.getName()).build();
    }

}
