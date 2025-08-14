package estudo.JPA.service;

import estudo.JPA.domain.entity.Pessoa;
import estudo.JPA.domain.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    // Métodos para validação
    private void validarEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("E-mail inválido. Deve conter o símbolo '@'.");
        }
    }

    private void validarTelefone(String telefone) {
        if (telefone == null || telefone.length() > 14) {
            throw new IllegalArgumentException("Telefone inválido. O telefone não deve ter mais de 14 caracteres.");
        }
        // Uma regex simples para verificar se o telefone contém apenas números e caracteres permitidos
        Pattern padrao = Pattern.compile("^[\\d\\s\\-()\\+]*$");
        if (!padrao.matcher(telefone).matches()) {
            throw new IllegalArgumentException("Telefone inválido. O telefone não deve conter letras ou caracteres especiais não permitidos.");
        }
    }

    // Método para salvar um novo cadastro
    public Pessoa salvar(Pessoa pessoa) {
        // Aplica as validações antes de salvar
        validarEmail(pessoa.getEmail());
        validarTelefone(pessoa.getTelefone());
        return pessoaRepository.save(pessoa);
    }

    // Método para listar todas as pessoas
    public List<Pessoa> listarTodos() {
        return pessoaRepository.findAll();
    }

    // Método para buscar uma pessoa por ID
    public Optional<Pessoa> buscarPorId(Long id) {
        return pessoaRepository.findById(id);
    }

    // Método para atualizar telefone e e-mail
    public Pessoa atualizar(Long id, String novoTelefone, String novoEmail) {
        // Busca a pessoa pelo ID. Se não encontrar, lança uma exceção.
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pessoa com o ID " + id + " não encontrada."));

        // Aplica as validações nos novos dados
        validarEmail(novoEmail);
        validarTelefone(novoTelefone);

        // Atualiza os dados
        pessoa.setTelefone(novoTelefone);
        pessoa.setEmail(novoEmail);

        // Salva a pessoa com os dados atualizados
        return pessoaRepository.save(pessoa);
    }

    // Método para deletar uma pessoa por ID
    public void deletarPorId(Long id) {
        // Verifica se a pessoa existe antes de tentar deletar
        if (!pessoaRepository.existsById(id)) {
            throw new IllegalArgumentException("Pessoa com o ID " + id + " não encontrada.");
        }
        pessoaRepository.deleteById(id);
    }

    // Método para deletar todos os registros
    public void deletarTodos() {
        pessoaRepository.deleteAll();
    }
}