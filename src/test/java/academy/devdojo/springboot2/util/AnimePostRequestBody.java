package academy.devdojo.springboot2.util;

import academy.devdojo.springboot2.dto.AnimePostDto;

public class AnimePostRequestBody {

        public static AnimePostDto createAnimePostRequestBody() {
            return  AnimePostDto.builder()
                    .name(AnimeCreator.createAnimeToBeSaved().getName())
                    .build();
       }

}
