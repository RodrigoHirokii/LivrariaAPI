package io.github.rodrigohirokii.jogosapi2.application;

import io.github.rodrigohirokii.jogosapi2.application.dto.BibliotecaDTO;
import io.github.rodrigohirokii.jogosapi2.model.BibliotecaSteam;
import io.github.rodrigohirokii.jogosapi2.service.BibliotecaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://127.0.0.1:5500", allowedHeaders = "*") // Permite todas as origens
@RestController
@RequestMapping("/biblioteca")
public class BibliotecaController {

    private final BibliotecaService bibliotecaService;

    public BibliotecaController(BibliotecaService bibliotecaService) {
        this.bibliotecaService = bibliotecaService;
    }

    @PostMapping
    public ResponseEntity<Object> salvarBiblioteca(@RequestBody BibliotecaDTO bibliotecaSteam) {
        var entidade = bibliotecaSteam.mapearParaBiblioteca();
        var entidadeSalva = bibliotecaService.salvarBiblioteca(entidade);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entidadeSalva.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<BibliotecaDTO> obterDetalhes(@PathVariable("id") String id) {
        var idBiblioteca = UUID.fromString(id);

        Optional<BibliotecaSteam> possivelBiblioteca = bibliotecaService.obterPorId(idBiblioteca);

        if (possivelBiblioteca.isPresent()) {
            BibliotecaSteam bibliotecaSteam = possivelBiblioteca.get();

            BibliotecaDTO bibliotecaDTO = new BibliotecaDTO(bibliotecaSteam.getNome(),
                    bibliotecaSteam.getId());

            return ResponseEntity.ok(bibliotecaDTO);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        var idBiblioteca = UUID.fromString(id);

        Optional<BibliotecaSteam> possivelBiblioteca = bibliotecaService.obterPorId(idBiblioteca);

        if (possivelBiblioteca.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        bibliotecaService.deletar(possivelBiblioteca.get());
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<BibliotecaDTO>> pesquisar(@RequestParam(value = "nome", required = false) String nome) {
        List<BibliotecaSteam> resultado = bibliotecaService.pesquisa(nome);
        List<BibliotecaDTO> lista = resultado.stream()
                .map(biblio -> new BibliotecaDTO(biblio.getNome(), biblio.getId()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }
}
