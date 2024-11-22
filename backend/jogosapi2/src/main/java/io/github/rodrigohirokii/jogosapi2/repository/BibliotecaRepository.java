package io.github.rodrigohirokii.jogosapi2.repository;

import io.github.rodrigohirokii.jogosapi2.model.BibliotecaSteam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BibliotecaRepository extends JpaRepository<BibliotecaSteam, UUID> {

    List<BibliotecaSteam> findByNome(String nome);
}
