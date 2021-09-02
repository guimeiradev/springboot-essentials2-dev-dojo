package academy.devdojo.springboot2.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data

public class AnimePostRequestBody {
    @NotEmpty(message = "The anime name cannot be empty")
    private String name;
}
