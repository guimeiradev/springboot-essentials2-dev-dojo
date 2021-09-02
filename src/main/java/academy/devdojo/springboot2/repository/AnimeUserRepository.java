package academy.devdojo.springboot2.repository;

import academy.devdojo.springboot2.domain.Anime;
import academy.devdojo.springboot2.domain.AnimeUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimeUserRepository extends JpaRepository<AnimeUser,Long> {

    AnimeUser findByUsername(String username);
}
