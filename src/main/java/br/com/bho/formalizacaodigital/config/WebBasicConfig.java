package br.com.bho.formalizacaodigital.config;

import br.com.bho.formalizacaodigital.domain.Usuario;
import br.com.bho.formalizacaodigital.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.CollectionUtils;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebBasicConfig extends WebSecurityConfigurerAdapter {

    private final UsuarioRepository usuarioRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
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
