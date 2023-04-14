package br.com.bho.formalizacaodigital.service.implemention;

import br.com.bho.formalizacaodigital.domain.Cliente;
import br.com.bho.formalizacaodigital.dto.ClienteDTO;
import br.com.bho.formalizacaodigital.exception.ErroGeral;
import br.com.bho.formalizacaodigital.repository.ClienteRepository;
import br.com.bho.formalizacaodigital.service.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    ClienteRepository clienteRepository;

    @Override
    public List<ClienteDTO> listar() {
        return clienteRepository.findAll().stream()
                .map(cliente -> new ClienteDTO(
                        cliente.getId(),
                        cliente.getNome(),
                        cliente.getCpfCnpj(),
                        cliente.getSexo(),
                        cliente.getIdade(),
                        cliente.getProfissao(),
                        cliente.getSalario()))
                .collect(Collectors.toList());
    }

    @Override
    public ClienteDTO buscar(Long idCliente) {
        return clienteRepository.findById(idCliente)
                .map(cliente -> new ClienteDTO(
                        cliente.getId(),
                        cliente.getNome(),
                        cliente.getCpfCnpj(),
                        cliente.getSexo(),
                        cliente.getIdade(),
                        cliente.getProfissao(),
                        cliente.getSalario())).orElseThrow(() -> new ErroGeral("Cliente não encontrado. Favor Verificar"));
    }

    @Override
    public void salvar(ClienteDTO clienteDTO) {
        Cliente cliente = clienteRepository.findByCpfCnpj(clienteDTO.getCpfCnpj());
        if (ObjectUtils.isEmpty(cliente)) {
            Cliente clienteCadastro = new Cliente();
            clienteCadastro.setNome(clienteDTO.getNome());
            clienteCadastro.setCpfCnpj(clienteDTO.getCpfCnpj());
            clienteCadastro.setSexo(clienteDTO.getSexo());
            clienteCadastro.setIdade(clienteDTO.getIdade());
            clienteCadastro.setProfissao(clienteDTO.getProfissao());
            clienteCadastro.setSalario(clienteDTO.getSalario());
            clienteRepository.save(clienteCadastro);
        } else {
            throw new ErroGeral("Cliente: " + clienteDTO.getNome() + " já cadastrado com o número de CPF/CNPJ: " + clienteDTO.getCpfCnpj());
        }
    }

    @Override
    public void editar(ClienteDTO clienteDTO) {
        Cliente clienteEdicao = clienteRepository.findById(clienteDTO.getId())
                .orElseThrow(() -> new ErroGeral("Cliente não encontrado. Favor Verificar"));
        clienteEdicao.setNome(clienteDTO.getNome());
        clienteEdicao.setCpfCnpj(clienteDTO.getCpfCnpj());
        clienteEdicao.setSexo(clienteDTO.getSexo());
        clienteEdicao.setIdade(clienteDTO.getIdade());
        clienteEdicao.setProfissao(clienteDTO.getProfissao());
        clienteEdicao.setSalario(clienteDTO.getSalario());
        clienteRepository.save(clienteEdicao);
    }

    @Override
    public void deletar(Long idCliente) {
        boolean existeCliente = clienteRepository.existsById(idCliente);
        if (existeCliente) {
            clienteRepository.deleteById(idCliente);
        } else {
            throw new ErroGeral("Cliente não encontrado. Favor Verificar");
        }
    }
}
