package io.github.rodrigohirokii.jogosapi2.repository;

import io.github.rodrigohirokii.jogosapi2.model.BibliotecaSteam;
import io.github.rodrigohirokii.jogosapi2.model.JogosSteam;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class JogoRepositoryTest {

    @Autowired
    private JogosRepository jogosRepository;

    @Autowired
    private BibliotecaRepository bibliotecaRepository;

    @Test
    void salvarJogoTest() {
        // Criar um novo jogo
        JogosSteam jogoAtual = new JogosSteam();
        jogoAtual.setNome("Teste");
        jogoAtual.setDataCompraJogo(LocalDate.now()); // Usando LocalDate para a data atual
        jogoAtual.setGenero("FPS");

        // Buscar a biblioteca associada pelo ID
        BibliotecaSteam bibliotecaSteam = bibliotecaRepository
                .findById(UUID.fromString("f614d95a-eb0c-4544-9b9d-d9ea593a3708"))
                .orElseThrow(() -> new RuntimeException("Biblioteca não encontrada"));

        // Associar o jogo à biblioteca
        jogoAtual.setBibliotecaSteam(bibliotecaSteam);

        // Salvar o jogo no banco de dados
        jogosRepository.save(jogoAtual);

        // Verificação adicional (opcional)
        System.out.println("Jogo salvo com sucesso: " + jogoAtual.getId());
    }

    @Test
    void salvarAutorELivroTest() {
        // Criar um novo jogo
        JogosSteam jogoAtual = new JogosSteam();
        jogoAtual.setNome("The Witcher 3");
        jogoAtual.setDataCompraJogo(LocalDate.now()); // Usando LocalDate para a data atual
        jogoAtual.setGenero("FPS");

        // Criando a biblioteca
        BibliotecaSteam bibliotecaSteam = new BibliotecaSteam();
        bibliotecaSteam.setNomeBiblioteca("Daniel");

        bibliotecaRepository.save(bibliotecaSteam);

        jogoAtual.setBibliotecaSteam(bibliotecaSteam);

        jogosRepository.save(jogoAtual);
    }

    @Test
    void salvarCascadeTest() {
        // Criar um novo jogo
        JogosSteam jogoAtual = new JogosSteam();
        jogoAtual.setNome("RE4 REMAKE");
        jogoAtual.setDataCompraJogo(LocalDate.now()); // Usando LocalDate para a data atual
        jogoAtual.setGenero("AVENTURA");

        // Criando a biblioteca
        BibliotecaSteam bibliotecaSteam = new BibliotecaSteam();
        bibliotecaSteam.setNomeBiblioteca("Bergs");

        jogoAtual.setBibliotecaSteam(bibliotecaSteam);

        jogosRepository.save(jogoAtual);
    }

    @Test
    void atualizarAutorDoLivro() {
        // Pegando o ID do Jogo
        UUID id = UUID.fromString("d42fefc8-f92c-41bc-af77-685c75bc8a1a");
        var jogoParaAtualizar = jogosRepository.findById(id).orElse(null);

        // Pegando o ID de Hiroki para trocar
        UUID idBibliotecaHiroki = UUID.fromString("4a2e4481-1ef3-4048-8f40-33920f2286a0");
        BibliotecaSteam bibliotecaSteam = bibliotecaRepository.findById(idBibliotecaHiroki).orElse(null);

        // Atualizando o jogo de "Daniel" para a biblioteca de Hiroki
        jogoParaAtualizar.setBibliotecaSteam(bibliotecaSteam);

        jogosRepository.save(jogoParaAtualizar);
    }

    @Test
    void deletar() {
        UUID uuid = UUID.fromString("165d5a53-f2ef-4c6b-9e80-c485cb9e0ad0");

        JogosSteam jogoEncontrado = jogosRepository.findById(uuid).orElse(null);

        jogosRepository.delete(jogoEncontrado);
    }

    @Test
    void deletarCascade() {
        UUID uuid = UUID.fromString("ed9e9d1f-d6bd-4980-8ad4-69d58648f0e8");

        JogosSteam jogoEncontrado = jogosRepository.findById(uuid).orElse(null);

        jogosRepository.delete(jogoEncontrado);
    }

    @Test
    void buscarJogoLazyTest() {
        List<JogosSteam> jogos = jogosRepository.findAll();

        for (JogosSteam jogoAtual : jogos) {
            System.out.println("Jogo " + jogoAtual.getNome());
        }

        // Aqui vai dar erro pois não está como eager
        //System.out.println("Biblioteca:");
        // System.out.println(jogo.getBibliotecaSteam().getNomeBiblioteca());
    }

    @Test
    @Transactional
    void buscarJogoLazyTest2() {
        UUID uuid = UUID.fromString("eeb8740c-a55d-45c7-a964-73117561d796");
        JogosSteam jogoEncontrado = jogosRepository.findById(uuid).orElse(null);

        System.out.println("Jogo: ");
        System.out.println(jogoEncontrado.getNome());

        System.out.println("Biblioteca: ");
        System.out.println(jogoEncontrado.getBibliotecaSteam().getNomeBiblioteca());
    }

    @Test
    void pesquisaPorNomeTest() {
        List<JogosSteam> jogos = jogosRepository.findByNome("RE4 REMAKE");

        jogos.forEach(System.out::println);
    }

    @Test
    void listarTodosOsJogosTestJQPL() {
        List<JogosSteam> jogos = jogosRepository.listarTodos();

        jogos.forEach(System.out::println);
    }

    @Test
    void listarAutoresDaBiblioteca() {
        var resultado = jogosRepository.listarAutoresBiblioteca();

        resultado.forEach(System.out::println);
    }

    @Test
    void listarPorGeneroQueryParamTest() {
        var resultado = jogosRepository.findByGenero("FPS");

        resultado.forEach(System.out::println);
    }

    @Test
    void listarPorGeneroQueryParamTest2() {
        var resultado = jogosRepository.findByGeneroPositionalParameters("FPS");

        resultado.forEach(System.out::println);
    }










}
