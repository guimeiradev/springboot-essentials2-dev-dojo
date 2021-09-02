package academy.devdojo.springboot2.service;

import academy.devdojo.springboot2.repository.AnimeUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnimeUserService  implements UserDetailsService {
    private final AnimeUserRepository animeUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return Optional.ofNullable(animeUserRepository.findByUsername(username))
                .orElseThrow(()-> new UsernameNotFoundException("Anime User does not found"));
    }
}
