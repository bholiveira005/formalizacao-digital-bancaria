package br.com.bho.formalizacaodigital.service.implemention;

import br.com.bho.formalizacaodigital.domain.Usuario;
import br.com.bho.formalizacaodigital.dto.UsuarioDTO;
import br.com.bho.formalizacaodigital.exception.ErroGeral403;
import br.com.bho.formalizacaodigital.exception.ErroGeral404;
import br.com.bho.formalizacaodigital.repository.UsuarioRepository;
import br.com.bho.formalizacaodigital.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final BCryptPasswordEncoder encoder;

    @Override
    public void cadastrar(UsuarioDTO usuarioDTO) {
        Optional<Usuario> usuario = usuarioRepository.findByUserNameIgnoreCase(usuarioDTO.getUserName());
        if (!usuario.isPresent()) {
            usuarioDTO.setPassword(encoder.encode(usuarioDTO.getPassword()));
            usuarioRepository.save(new Usuario(usuarioDTO));
        } else {
            throw new ErroGeral403("Usuário já cadastrado.");
        }
    }

    @Override
    public void editar(UsuarioDTO usuarioDTO) {
        usuarioRepository.findById(usuarioDTO.getId()).map(usuario -> {
            usuarioDTO.setPassword(encoder.encode(usuarioDTO.getPassword()));
            usuarioRepository.save(new Usuario(usuarioDTO));
            return usuarioDTO;
        }).orElseThrow(() -> new ErroGeral404("Usuário não existe. Favor verificar."));
    }

    @Override
    public UsuarioDTO buscarDetalheUsuario(String userName) {
        return usuarioRepository.findByUserNameIgnoreCase(userName).map(UsuarioDTO::new)
                .orElseThrow(() -> new ErroGeral404("Usuário não encontrado. Favor verificar"));
    }

    @Override
    public String buscarRole(String userName) {
        return usuarioRepository.findByUserNameIgnoreCase(userName).map(Usuario::getRole)
                .orElseThrow(() -> new ErroGeral404("Role de permissão não encontrada. Favor verificar"));
    }
}
