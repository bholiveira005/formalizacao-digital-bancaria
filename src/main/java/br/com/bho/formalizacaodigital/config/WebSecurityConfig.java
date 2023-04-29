package br.com.bho.formalizacaodigital.config;

import br.com.bho.formalizacaodigital.domain.Usuario;
import br.com.bho.formalizacaodigital.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.util.CollectionUtils;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig {

    private final UsuarioRepository usuarioRepository;

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                .antMatchers("/")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic().and().build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**");
    }

    @Bean
    InitializingBean sendDatabase(PasswordEncoder passwordEncoder) {
        return () -> {
            if (CollectionUtils.isEmpty(usuarioRepository.findAll())) {
                Usuario usuario = new Usuario();
                usuario.setUserName("user");
                usuario.setPassword(passwordEncoder.encode("user"));
                usuario.setRole("USER");
                usuarioRepository.save(usuario);

                Usuario admin = new Usuario();
                admin.setUserName("admin");
                admin.setPassword(passwordEncoder().encode("admin"));
                admin.setRole("ADMIN");
                usuarioRepository.save(admin);
            }
        };
    }
}
