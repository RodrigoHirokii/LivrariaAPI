package io.github.rodrigohirokii.jogosapi2.service;

import io.github.rodrigohirokii.jogosapi2.model.BibliotecaSteam;
import io.github.rodrigohirokii.jogosapi2.repository.BibliotecaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BibliotecaService {

    private final BibliotecaRepository bibliotecaRepository;

    public BibliotecaService(BibliotecaRepository bibliotecaRepository) {

        this.bibliotecaRepository = bibliotecaRepository;
    }

    public BibliotecaSteam salvarBiblioteca(BibliotecaSteam bibliotecaSteam) {
        return bibliotecaRepository.save(bibliotecaSteam);
    }

    public Optional<BibliotecaSteam> obterPorId(UUID id) {
        return bibliotecaRepository.findById(id);
    }

    public void deletar(BibliotecaSteam bibliotecaSteam) {
        bibliotecaRepository.delete(bibliotecaSteam);
    }

    public List<BibliotecaSteam> pesquisa(String nome) {
        if (nome != null) {
            return bibliotecaRepository.findByNome(nome);
        }

        return bibliotecaRepository.findAll();
    }
}
