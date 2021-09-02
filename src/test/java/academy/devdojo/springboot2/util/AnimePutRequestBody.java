package academy.devdojo.springboot2.util;

import academy.devdojo.springboot2.dto.AnimePostDto;
import academy.devdojo.springboot2.dto.AnimePutDto;

public class AnimePutRequestBody {

        public static AnimePutDto createAnimePutRequestBody() {
            return  AnimePutDto.builder()
                    .id(AnimeCreator.createValidUpdatedAnime().getId())
                    .name(AnimeCreator.createValidUpdatedAnime().getName())
                    .build();
       }

}
