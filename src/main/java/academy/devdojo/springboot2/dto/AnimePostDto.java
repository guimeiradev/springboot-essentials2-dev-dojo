package academy.devdojo.springboot2.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AnimePostDto {
    @NotEmpty(message = "Escreva algo por favor")
    private String name;
}
