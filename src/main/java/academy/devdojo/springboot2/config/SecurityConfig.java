package academy.devdojo.springboot2.config;

import academy.devdojo.springboot2.repository.AnimeUserRepository;
import academy.devdojo.springboot2.service.AnimeService;
import academy.devdojo.springboot2.service.AnimeUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * BasicAutheticationFilter = Verfica se voce tem uma autenticacao Base 64
 * UserNamePasswordAuthenticaticonFilter = Verifica se na sua requicao tem seu usuario e password.
 * DefaultLoginPageGeneratingFilter = Reponsavel por gerar o login
 * DefaultLogoutPageGeneratingFilter
 *  FilterSecurityInterceptor = valida seu usuario
 */
@EnableWebSecurity
@Log4j2
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter  {
    private  final AnimeUserService animeUserService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/animes/admin/**").hasRole("ADMIN")
                .antMatchers("/animes/**").hasRole("USER")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        log.info("Password enconded {}",passwordEncoder.encode("gui123"));
//        auth.inMemoryAuthentication()
//                .withUser("gui")
//                .password(passwordEncoder.encode("teste"))
//                .roles("user","admin");
        auth.userDetailsService(animeUserService)
                .passwordEncoder(passwordEncoder);
    }
}
