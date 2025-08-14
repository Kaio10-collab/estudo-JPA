package estudo.JPA.controller;

import estudo.JPA.domain.entity.Pessoa;
import estudo.JPA.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoas")
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaService pessoaService;

    // Record para receber os dados de atualização
    record PessoaAtualizarDto(String novoTelefone, String novoEmail) {}

    // Endpoint para salvar uma nova pessoa (CREATE)
    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Pessoa pessoa) {
        try {
            Pessoa novaPessoa = pessoaService.salvar(pessoa);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaPessoa);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Endpoint para listar todas as pessoas (READ)
    @GetMapping
    public ResponseEntity<List<Pessoa>> listarTodos() {
        List<Pessoa> pessoas = pessoaService.listarTodos();
        return ResponseEntity.ok(pessoas);
    }

    // Endpoint para buscar uma pessoa por ID (READ)
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        return pessoaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint para atualizar telefone e e-mail de uma pessoa (UPDATE)
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody PessoaAtualizarDto dto) {
        try {
            Pessoa pessoaAtualizada = pessoaService.atualizar(id, dto.novoTelefone(), dto.novoEmail());
            return ResponseEntity.ok(pessoaAtualizada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Endpoint para deletar uma pessoa por ID (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarPorId(@PathVariable Long id) {
        try {
            pessoaService.deletarPorId(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Endpoint para deletar todos os registros (DELETE ALL)
    @DeleteMapping
    public ResponseEntity<Void> deletarTodos() {
        pessoaService.deletarTodos();
        return ResponseEntity.noContent().build();
    }
}
