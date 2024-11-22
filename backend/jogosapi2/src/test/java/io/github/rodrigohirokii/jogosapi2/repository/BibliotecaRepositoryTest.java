package io.github.rodrigohirokii.jogosapi2.repository;

import io.github.rodrigohirokii.jogosapi2.model.BibliotecaSteam;
import io.github.rodrigohirokii.jogosapi2.model.JogosSteam;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.*;

@SpringBootTest
public class BibliotecaRepositoryTest {
    @Autowired
    BibliotecaRepository bibliotecaRepository;

    @Autowired
    JogosRepository jogosRepository;

    @Test
    public void salvarBibliotecaTest() {
        BibliotecaSteam bibliotecaSteam = new BibliotecaSteam();
        bibliotecaSteam.setNomeBiblioteca("Débora");

        // Salva a biblioteca no repositório
        bibliotecaRepository.save(bibliotecaSteam);
    }

    @Test
    public void atualizarBibliotecaTest() {
        UUID uuid = UUID.fromString("fe0ec48b-25ca-4c0a-8b99-df7c299dae41");

        // Busca a biblioteca pelo ID
        Optional<BibliotecaSteam> possivelBiblioteca = bibliotecaRepository.findById(uuid);

        if (possivelBiblioteca.isPresent()) {
            BibliotecaSteam biblioteca = possivelBiblioteca.get();

            // Faz a atualização desejada
            biblioteca.setNomeBiblioteca("Biblioteca Atualizada");

            // Salva a biblioteca atualizada no repositório
            bibliotecaRepository.save(biblioteca);
        }
    }

    @Test
    public void listarBibliotecaTest() {
        List<BibliotecaSteam> lista = bibliotecaRepository.findAll();

        lista.forEach(System.out::println);
    }

    @Test
    public void contagemBibliotecaTest() {
        long contagem = bibliotecaRepository.count();

        System.out.println("Possui " + contagem + " bibliotecas ");
    }

    @Test
    public void excluirBiliotecaPorIDTest() {
        UUID uuid = UUID.fromString("4a60a35e-2f6a-4f7f-b844-2a979619dd46");


        Optional<BibliotecaSteam> possivelBiblioteca = bibliotecaRepository.findById(uuid);

        if (possivelBiblioteca.isPresent()) {
            BibliotecaSteam bibliotecaSteam = possivelBiblioteca.get();

            System.out.println("Biblioteca está presente " + bibliotecaSteam);

            bibliotecaRepository.delete(bibliotecaSteam);

            System.out.println("Biblioteca apagada com sucesso!");
        }


    }
    // ------------------------------------------------------------------------------------------------------------

    // OBS: Essa é uma outra abordagem para salvar uma biblioteca e os livros
    @Test
    void salvarBibliotecaComJogosTest() {
        // Criando uma biblioteca
        BibliotecaSteam bibliotecaSteam = new BibliotecaSteam();
        bibliotecaSteam.setNomeBiblioteca("Carol");

        // Criando um jogo
        JogosSteam jogoAtual = new JogosSteam();
        jogoAtual.setNome("Dead by Daylight");
        jogoAtual.setDataCompraJogo(LocalDate.now()); // Usando LocalDate
        jogoAtual.setGenero("FPS");
        jogoAtual.setBibliotecaSteam(bibliotecaSteam);

        JogosSteam jogoAtual2 = new JogosSteam();
        jogoAtual2.setNome("Marvel Rivals");
        jogoAtual2.setDataCompraJogo(LocalDate.now()); // Usando LocalDate
        jogoAtual2.setGenero("FPS");
        jogoAtual2.setBibliotecaSteam(bibliotecaSteam);

        JogosSteam jogoAtual3 = new JogosSteam();
        jogoAtual3.setNome("Valorant");
        jogoAtual3.setDataCompraJogo(LocalDate.now()); // Usando LocalDate
        jogoAtual3.setGenero("FPS");
        jogoAtual3.setBibliotecaSteam(bibliotecaSteam);

        // Inicializando um array list vazio
        // Depois adicionando jogos nesse array
        bibliotecaSteam.setJogosSteamList(new ArrayList<>());
        bibliotecaSteam.getJogosSteamList().add(jogoAtual);
        bibliotecaSteam.getJogosSteamList().add(jogoAtual2);
        bibliotecaSteam.getJogosSteamList().add(jogoAtual3);

        bibliotecaRepository.save(bibliotecaSteam);

        jogosRepository.saveAll(bibliotecaSteam.getJogosSteamList());
    }

    @Test
    void salvarBibliotecaComJogosCascadeTest() {
        // Criando uma biblioteca
        BibliotecaSteam bibliotecaSteam = new BibliotecaSteam();
        bibliotecaSteam.setNomeBiblioteca("Yasmin");

        // Criando um jogo
        JogosSteam jogoAtual = new JogosSteam();
        jogoAtual.setNome("Poppy Playtime");
        jogoAtual.setDataCompraJogo(LocalDate.now()); // Usando LocalDate
        jogoAtual.setGenero("FPS");
        jogoAtual.setBibliotecaSteam(bibliotecaSteam);

        JogosSteam jogoAtual2 = new JogosSteam();
        jogoAtual2.setNome("Fortnite");
        jogoAtual2.setDataCompraJogo(LocalDate.now()); // Usando LocalDate
        jogoAtual2.setGenero("FPS");
        jogoAtual2.setBibliotecaSteam(bibliotecaSteam);

        JogosSteam jogoAtual3 = new JogosSteam();
        jogoAtual3.setNome("Lol");
        jogoAtual3.setDataCompraJogo(LocalDate.now()); // Usando LocalDate
        jogoAtual3.setGenero("FPS");
        jogoAtual3.setBibliotecaSteam(bibliotecaSteam);

        // Inicializando um array list vazio
        // Depois adicionando jogos nesse array
        bibliotecaSteam.setJogosSteamList(new ArrayList<>());
        bibliotecaSteam.getJogosSteamList().add(jogoAtual);
        bibliotecaSteam.getJogosSteamList().add(jogoAtual2);
        bibliotecaSteam.getJogosSteamList().add(jogoAtual3);

        bibliotecaRepository.save(bibliotecaSteam);
    }

    @Test
    void listarJogosBiblioteca() {
        UUID uuid = UUID.fromString("6e8208cc-2bfb-46e1-af9d-0766ae49f4fa");
        BibliotecaSteam bibliotecaSteam = bibliotecaRepository.findById(uuid).orElse(null);

        // Buscar os jogos da biblioteca
        List<JogosSteam> jogosLista = jogosRepository.findByBibliotecaSteam(bibliotecaSteam);
        bibliotecaSteam.setJogosSteamList(jogosLista);

        bibliotecaSteam.getJogosSteamList().forEach(System.out::println);
    }
}
