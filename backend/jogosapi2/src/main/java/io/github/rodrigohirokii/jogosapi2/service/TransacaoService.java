//package io.github.rodrigohirokii.jogosapi2.service;
//
//import io.github.rodrigohirokii.jogosapi2.model.BibliotecaSteam;
//import io.github.rodrigohirokii.jogosapi2.model.JogosSteam;
//import io.github.rodrigohirokii.jogosapi2.repository.BibliotecaRepository;
//import io.github.rodrigohirokii.jogosapi2.repository.JogosRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDate;
//import java.util.UUID;
//
//@Service
//public class TransacaoService {
//
//    @Autowired
//    private BibliotecaRepository bibliotecaRepository;
//
//    @Autowired
//    private JogosRepository jogosRepository;
//
//    @Transactional
//    public void atualizacaoSemAtualizar() {
//        var jogo = jogosRepository.
//                findById(UUID.fromString("ed4ebf7e-d14a-4476-993a-cc1b13e00449")).orElse(null);
//
//        jogo.setDataCompraJogo(LocalDate.of(2024, 11, 20));
//
//        jogosRepository.save(jogo);
//    }
//
//    @Transactional
//    public void executar() {
//        // Criando a biblioteca
//        BibliotecaSteam bibliotecaSteam = new BibliotecaSteam();
//        bibliotecaSteam.setNomeBiblioteca("Ricardo");
//
//        bibliotecaRepository.save(bibliotecaSteam);
//
//        if (bibliotecaSteam.getNomeBiblioteca().equals("Bergs")) {
//            throw new RuntimeException("Rollback");
//
//        }
//
//        // Criar um novo jogo
//        JogosSteam jogoAtual = new JogosSteam();
//        jogoAtual.setNome("The Witcher 3");
//        jogoAtual.setDataCompraJogo(LocalDate.now()); // Usando LocalDate para a data atual
//        jogoAtual.setGenero("FPS");
//
//
//        jogoAtual.setBibliotecaSteam(bibliotecaSteam);
//
//        jogosRepository.save(jogoAtual);
//
//    }
//}
