package academy.devdojo.springboot2.util;

import academy.devdojo.springboot2.domain.Anime;

public class AnimeCreator {

    public static Anime createAnimeToBeSaved() {
        return  Anime.builder()
                .name("Cavaleioros dos zodiaco")
                .build();
    }
    public static Anime createValidAnime() {
        return  Anime.builder()
                .name("aaaa")
                .id(12l)
                .build();
    }
    public static Anime createValidUpdatedAnime() {
        return  Anime.builder()
                .name("Naruto")
                .id(1l)
                .build();
    }

}
