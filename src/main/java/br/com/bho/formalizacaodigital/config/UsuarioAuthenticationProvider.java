package br.com.bho.formalizacaodigital.config;

import br.com.bho.formalizacaodigital.domain.Usuario;
import br.com.bho.formalizacaodigital.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Component
@AllArgsConstructor
public class UsuarioAuthenticationProvider implements AuthenticationProvider {

    private UsuarioRepository repository;

    private PasswordEncoder encoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        Usuario usuario = repository.findByUserNameIgnoreCase(username)
                .orElseThrow(() -> new BadCredentialsException("Sem detalhes de usuário"));

        if (encoder.matches(password, usuario.getPassword())) {
            log.info("Usuário autenticado com sucesso");
            return new UsernamePasswordAuthenticationToken(username, password, buscarRoles(usuario.getRole()));
        } else {
            throw new BadCredentialsException("Senha incorreta");
        }
    }

    private List<GrantedAuthority> buscarRoles(String usuarioRoles) {
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        String[] roles = usuarioRoles.split(",");
        for (String role : roles) {
            log.info("Role: " + role);
            grantedAuthorityList.add(new SimpleGrantedAuthority(role.replaceAll("\\s+", "")));
        }
        return grantedAuthorityList;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
