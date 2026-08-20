package br.ueg.eventos.domain.model;

import br.ueg.eventos.domain.exception.DomainRuleException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InscricaoTest {

    @Test
    void deveLancarExcecaoQuandoIdForNulo() {
        Usuario participanteMock = new Usuario("123", "Felipe", "felipe@teste.com");
        Evento eventoMock = new Evento("456", "Semana Acadêmica");

        DomainRuleException erro = assertThrows(DomainRuleException.class, () -> {
            new Inscricao(null, participanteMock, eventoMock);
        });

        assertEquals("O ID da inscricao nao pode ser vazio.", erro.getMessage());
    }

    @Test
    void deveCriarInscricaoComSucessoQuandoDadosForemValidos() {
        Usuario participanteMock = new Usuario("123", "Sofia", "sofia@teste.com");
        Evento eventoMock = new Evento("456", "Simpósio");

        Inscricao inscricao = new Inscricao("789", participanteMock, eventoMock);

        assertNotNull(inscricao);
        assertEquals(StatusInscricao.PENDENTE, inscricao.getSituacao());
    }
}
