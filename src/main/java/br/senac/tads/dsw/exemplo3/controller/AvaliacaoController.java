package br.senac.tads.dsw.exemplo3.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.senac.tads.dsw.exemplo3.model.Avaliacao;
import br.senac.tads.dsw.exemplo3.repository.AvaliacaoRepository;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/avaliacoes")
public class AvaliacaoController {
     
    private final AvaliacaoRepository repository;

    public AvaliacaoController(AvaliacaoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Avaliacao> criarAvaliacao(@RequestBody @Valid Avaliacao avaliacao) {
        
        Avaliacao avaliacaoSalvo = repository.save(avaliacao);

        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(avaliacaoSalvo.getId())
            .toUri();
        
        return ResponseEntity.created(location).body(avaliacaoSalvo);
    }

    @GetMapping
    public List<Avaliacao> listarTodos() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Avaliacao> buscarPorId(@PathVariable Long id) {
        
        Optional<Avaliacao> avaliacaoBuscado = repository.findById(id);

        if (avaliacaoBuscado.isPresent()) {
            return ResponseEntity.ok(avaliacaoBuscado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Avaliacao> atualizarAvaliacao(@PathVariable Long id,
                                            @RequestBody @Valid Avaliacao avaliacaoAtualizado) {
        
        Optional<Avaliacao> avaliacaoBuscado = repository.findById(id);

        if (avaliacaoBuscado.isPresent()) {
            Avaliacao avaliacaoExistente = avaliacaoBuscado.get();

            avaliacaoExistente.setAutor(avaliacaoAtualizado.getAutor());
            avaliacaoExistente.setComentario(avaliacaoAtualizado.getComentario());
            avaliacaoExistente.setNota(avaliacaoAtualizado.getNota());


            Avaliacao avaliacaoSalvo = repository.save(avaliacaoExistente);
            
            return ResponseEntity.ok(avaliacaoSalvo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagarAvaliacao(@PathVariable Long id) {

        Optional<Avaliacao> avaliacaoBuscado = repository.findById(id);

        if (avaliacaoBuscado.isPresent()) {
            repository.deleteById(id);

            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
