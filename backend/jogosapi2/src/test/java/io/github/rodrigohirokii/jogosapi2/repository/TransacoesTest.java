package io.github.rodrigohirokii.jogosapi2.repository;

import io.github.rodrigohirokii.jogosapi2.service.TransacaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class TransacoesTest {

    @Autowired
    TransacaoService transacaoService;

    /**
     * Commit -> confirmar alterações
     * Rollback -> desfazer alterações
     */
    @Test
    @Transactional // serve para realizar operações de escrita
    void transacaoSimples() {
        // Resumo: se der erro em qualquer passo vai ter um rollback

        // salvar um jogo
        // salvar a biblioteca
        // alugar o jogo
        // enviar o email pra pessoa que alugou
        // notificar

        transacaoService.executar();
    }

    @Test
    void transacaoEstadoManaged() {
        transacaoService.atualizacaoSemAtualizar();
    }
}
