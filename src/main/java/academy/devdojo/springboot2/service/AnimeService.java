package academy.devdojo.springboot2.service;

import academy.devdojo.springboot2.domain.Anime;
import academy.devdojo.springboot2.dto.AnimePostDto;
import academy.devdojo.springboot2.dto.AnimePutDto;
import academy.devdojo.springboot2.exception.BadRequestException;
import academy.devdojo.springboot2.mapper.AnimeMapper;
import academy.devdojo.springboot2.repository.AnimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

// Onde vai ser feita toda logica de negocio

@Service
@RequiredArgsConstructor
public class AnimeService {

    private final AnimeRepository animeRepository;

    public Page<Anime> listAll(Pageable pageable) {
        return animeRepository.findAll(pageable);
    }
    public List<Anime> listAllNonPageable() {
        return animeRepository.findAll();
    }
    public List<Anime> findByName(String name) {
        return animeRepository.findByName(name);
    }
    // Get
    public Anime findByIdOrThrowBadRequestException(long id) {
        return animeRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Anime not found"));
    }
    // Post
    public Anime save(AnimePostDto animePostDtoDto) {
        AnimeMapper.toEntity(animePostDtoDto);
        Anime anime = Anime.builder().name(animePostDtoDto.getName()).build();
        return animeRepository.save(anime);
    }
    // Delete
    public void delete(long id) {
        animeRepository.delete(findByIdOrThrowBadRequestException(id));
    }
    // Update
    public void replace(AnimePutDto animePutDto) {
        Anime savedAnime = findByIdOrThrowBadRequestException(animePutDto.getId());
        Anime anime = AnimeMapper.toEntity(animePutDto);
        anime.setId(savedAnime.getId());
         animeRepository.save(anime);

    }
}

