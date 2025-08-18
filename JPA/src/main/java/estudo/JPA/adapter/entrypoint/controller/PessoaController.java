package estudo.JPA.adapter.entrypoint.controller;

import estudo.JPA.adapter.entrypoint.controller.dto.PessoaAtualizarDto;
import estudo.JPA.adapter.entrypoint.mapper.PessoaMapper;
import estudo.JPA.adapter.entrypoint.request.PessoaRequest;
import estudo.JPA.adapter.entrypoint.response.PessoaResponse;
import estudo.JPA.entity.Pessoa;
import estudo.JPA.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pessoas")
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaService pessoaService;
    private final PessoaMapper pessoaMapper;

    // Endpoint para salvar uma nova pessoa (CREATE)
    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody PessoaRequest pessoaRequest) {
        try {
            Pessoa pessoa = pessoaMapper.toDomain(pessoaRequest);
            Pessoa novaPessoa = pessoaService.salvar(pessoa);
            PessoaResponse pessoaResponse = pessoaMapper.toResponse(novaPessoa);
            return ResponseEntity.status(HttpStatus.CREATED).body(pessoaResponse);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Endpoint para listar todas as pessoas (READ)
    @GetMapping
    public ResponseEntity<List<PessoaResponse>> listarTodos() {
        List<Pessoa> pessoas = pessoaService.listarTodos();
        List<PessoaResponse> response = pessoas.stream()
                .map(pessoaMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    // Endpoint para buscar uma pessoa por ID (READ)
    @GetMapping("/{id}")
    public ResponseEntity<PessoaResponse> buscarPorId(@PathVariable Long id) {
        return pessoaService.buscarPorId(id)
                .map(pessoaMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint para atualizar telefone e e-mail de uma pessoa (UPDATE)
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody PessoaAtualizarDto dto) {
        try {
            Pessoa pessoaAtualizada = pessoaService.atualizar(id, dto.novoTelefone(), dto.novoEmail());
            PessoaResponse response = pessoaMapper.toResponse(pessoaAtualizada);
            return ResponseEntity.ok(response);
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
