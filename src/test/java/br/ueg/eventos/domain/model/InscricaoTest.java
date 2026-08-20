package br.ueg.eventos.domain.model;
import br.ueg.eventos.domain.model.ModalidadeEvento;
import br.ueg.eventos.domain.model.TipoEvento;
import br.ueg.eventos.domain.exception.DomainRuleException;
import org.junit.jupiter.api.Test;
import java.time.ZonedDateTime;
import static org.junit.jupiter.api.Assertions.*;

class InscricaoTest {

    @Test
    void deveLancarExcecaoQuandoIdDaInscricaoForVazio() {
        // Arrange: ID nulo. Como a validação dele é a primeira, 
        // podemos passar null nos objetos dependentes.
        String idInvalido = null;
        Usuario participante = null; 
        Evento evento = null;

        // Act & Assert
        DomainRuleException erro = assertThrows(DomainRuleException.class, () -> {
            new Inscricao(idInvalido, participante, evento);
        });

        assertEquals("O ID da inscricao nao pode ser vazio.", erro.getMessage());
    }

    @Test
    void deveCriarInscricaoComSucessoQuandoDadosForemValidos() {
        // Arrange: Criamos objetos válidos atendendo à quantidade de parâmetros exigidos
        Usuario participante =açõesMockUsuario();
        Evento evento = mockEventoValido();

        // Act
        Inscricao inscricao = new Inscricao("789", participante, evento);

        // Assert
        assertNotNull(inscricao);
        assertEquals(StatusInscricao.PENDENTE, inscricao.getSituacao());
    }

    // Métodos auxiliares para montar objetos de teste limpos
    private Usuario açõesMockUsuario() {
        return new Usuario("123", "Rebeca", "rebeca@teste.com", "senha123");
    }

    private Evento mockEventoValido() {
        return new Evento(
            "456", 
            "Simpósio", 
            "Descrição", 
            TipoEvento.ACADEMICO, 
            ModalidadeEvento.PRESENCIAL, 
            "Local", 
            ZonedDateTime.now(), 
            ZonedDateTime.now().plusDays(1)
        );
    }
}
