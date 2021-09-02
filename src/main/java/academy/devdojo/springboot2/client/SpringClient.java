package academy.devdojo.springboot2.client;

import academy.devdojo.springboot2.domain.Anime;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Log4j2
public class SpringClient {
    public static void main(String[] args) {

        ResponseEntity<Anime> entity = new RestTemplate().getForEntity
                ("http://localhost:8080/animes/{id}",Anime.class,2);
        log.info(entity);

      Anime object = new RestTemplate().getForObject("http://localhost:8080/animes/2",Anime.class);
      log.info(object);

      Anime[] animes = new RestTemplate().getForObject("http://localhost:8080/animes/all",Anime[].class);
      log.info(Arrays.toString(animes));

        ResponseEntity<List<Anime>> exchange = new RestTemplate().exchange("http://localhost:8080/animes/all",
                HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                });
        log.info(exchange.getBody());

//        Anime dbz = Anime.builder().name("DragonBallZ").build();
//        Anime dbzSaved = new RestTemplate().postForObject("http://localhost:8080/animes/", dbz, Anime.class);
//        log.info("Saved anime {}",dbzSaved);

        Anime scoobyDoo = Anime.builder().name("ScoobyDo").build();
        ResponseEntity<Anime> scoobyDooSaved = new RestTemplate().exchange("http://localhost:8080/animes/",
                HttpMethod.POST, new HttpEntity<>(scoobyDoo,createJsonHeader()),
                Anime.class);
        log.info("Saved anime {}",scoobyDooSaved) ;

        Anime animeToBeUpdated = scoobyDooSaved.getBody();
        animeToBeUpdated.setName("Homen Aranha");

        ResponseEntity<Void> animeUpdated = new RestTemplate().exchange("http://localhost:8080/animes",
                HttpMethod.PUT, new HttpEntity<>(animeToBeUpdated,createJsonHeader()),
                Void.class);
        log.info(animeToBeUpdated) ;

        ResponseEntity<Void> animeDeleted = new RestTemplate().exchange("http://localhost:8080/animes/{id}",
                HttpMethod.DELETE,
                null,
                Void.class,
                animeToBeUpdated.getId());

        log.info(animeDeleted) ;

    }
    private static HttpHeaders createJsonHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}
