package io.github.rodrigohirokii.jogosapi2.application.dto;

import io.github.rodrigohirokii.jogosapi2.model.BibliotecaSteam;

import java.util.UUID;

public record BibliotecaDTO(String nome, UUID id) {

    public BibliotecaSteam mapearParaBiblioteca() {
        BibliotecaSteam bibliotecaSteam = new BibliotecaSteam();
        bibliotecaSteam.setNome(this.nome);

        return bibliotecaSteam; // Retorna a instância preenchida
    }
}
