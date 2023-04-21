package br.com.bho.formalizacaodigital.dto;

import br.com.bho.formalizacaodigital.domain.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private Integer id;

    @NotBlank
    private String userName;

    @NotBlank
    private String password;

    @NotBlank
    private String role;

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.userName = usuario.getUserName();
        this.password = usuario.getPassword();
        this.role = usuario.getRole();
    }
}

