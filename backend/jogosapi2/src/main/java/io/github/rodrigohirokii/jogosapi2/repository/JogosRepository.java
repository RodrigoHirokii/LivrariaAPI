package io.github.rodrigohirokii.jogosapi2.repository;

import io.github.rodrigohirokii.jogosapi2.model.JogosSteam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JogosRepository extends JpaRepository<JogosSteam, UUID> {



}
