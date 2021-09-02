package academy.devdojo.springboot2.repository;

import academy.devdojo.springboot2.domain.Anime;
import academy.devdojo.springboot2.util.AnimeCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

// Testes unitarios em banco de dados
@DataJpaTest
@ActiveProfiles("TestMysql")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("Tests for Anime repository") // Usamos essa anotação para trocar o nome do teste
class AnimeRepositoryTest {
    @Autowired
    private AnimeRepository animeRepository;

    @Test
    @DisplayName("Save persits anime when successful ")//Podemos utilizar o displayName em cada teste mudando seu nome
    public void save_PesistAnime_WhenSuccessful() {

        Anime createAnimeToBeSaved = AnimeCreator.createAnimeToBeSaved();
        Anime animeSaved = this.animeRepository.save(createAnimeToBeSaved);
        Assertions.assertThat(animeSaved).isNotNull(); // Verificando se ele não é null

        Assertions.assertThat(animeSaved.getId()).isNotNull(); // Verificando se ele tem um id

        Assertions.assertThat(animeSaved.getName())

                .isEqualTo(createAnimeToBeSaved.getName())

                .isEqualTo

                        (createAnimeToBeSaved.getName()); // Verificando se o valor que foi salvo é igual ao que eu pedi para salvar

    }

    @Test
    @DisplayName("Save updates anime when Sucessful")
    public void save_UpdatesAnime_WhenSuccessful() {
        Anime createAnimeToBeSaved = AnimeCreator.createAnimeToBeSaved();

        Anime animeSaved = this.animeRepository.save(createAnimeToBeSaved);

        animeSaved.setName("Testando Update");

        Anime animeUpdated = this.animeRepository.save(animeSaved);

        Assertions.assertThat(animeUpdated).isNotNull();

        Assertions.assertThat(animeUpdated.getId()).isNotNull();

        Assertions.assertThat(animeUpdated.getName()).isEqualTo(animeSaved.getName());

    }

    @Test
    @DisplayName("Delete removes anime when Sucessful")
    public void delete_RemovesAnime_WhenSuccessful() {

        Anime createAnimeToBeSaved = AnimeCreator.createAnimeToBeSaved();

        Anime animeSaved = this.animeRepository.save(createAnimeToBeSaved);

        this.animeRepository.delete(animeSaved);

        Optional<Anime> animeOptional = this.animeRepository
                .findById(animeSaved.getId());// Verificando se foi excluido mesmo

        Assertions.assertThat(animeOptional.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Find By Name returns list of anime when successful ")
    public void findByName_ReturnsListOfAnime_WhenSuccessful() {
        Anime createAnimeToBeSaved = AnimeCreator.createAnimeToBeSaved();
        Anime animeSaved = this.animeRepository.save(createAnimeToBeSaved);

        String name = animeSaved.getName();
        List<Anime> animes = this.animeRepository.findByName(name);

        Assertions.assertThat(animes)
                .isNotEmpty()
                .contains(animeSaved); // Verificando se a lista é vazia
    }


    @Test
    @DisplayName("Find By Name returns empty list  when no anime is found ")
    public void FindByName_ReturnsEmptyList_WhenAnimeIsNotFound() {

        List<Anime> animes = this.animeRepository.findByName("xaxa");

        Assertions.assertThat(animes).isEmpty(); // Verificando se a lista é vazia
    }

    @Test
    @DisplayName("Save throw ConstraintViolationException when name is empty")
    public void save_ThrowsConstraintViolationException_WhenNameIsEmpty() {
        Anime anime = new Anime();

        Assertions.assertThatThrownBy(() -> this.animeRepository.save(anime))
                .isInstanceOf(ConstraintViolationException.class);


    }

}