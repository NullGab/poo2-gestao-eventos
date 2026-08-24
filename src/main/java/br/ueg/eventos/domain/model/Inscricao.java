package br.ueg.eventos.domain.model;
import br.ueg.eventos.domain.exception.DomainRuleException;
import br.ueg.eventos.domain.util.validator.Validador;
import br.ueg.eventos.domain.util.validator.RegraTextoObrigatorio;
import br.ueg.eventos.domain.util.validator.RegraObjetoNaoNulo;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Inscricao {

  private final String id;
  private final LocalDateTime dataHoraRegistro;

  private Usuario participante;
  private Evento evento;
  private StatusInscricao situacao;

  private List<Atividade> atividadesSelecionadas;

  protected Inscricao(String id, Usuario participante, Evento evento) {
    Validador.avaliar(
        new RegraTextoObrigatorio(id, "O ID da inscricao nao pode ser vazio."), 
        new RegraObjetoNaoNulo(participante, "Um usuario precisa estar vinculado a inscricao."), 
        new RegraObjetoNaoNulo(evento, "Um evento precisa estar vinculado a inscricao.") 
        );

    this.id = id;
    this.participante = participante;
    this.evento = evento;
    this.dataHoraRegistro = LocalDateTime.now();
    this.situacao = StatusInscricao.PENDENTE; 
    this.atividadesSelecionadas = new ArrayList<>();
  }
  public StatusInscricao getSituacao() {
    return this.situacao;
  }

  public List<Atividade> getAtividadesSelecionadas() {
    return Collections.unmodifiableList(this.atividadesSelecionadas);
  }

  public void cancelar() {
    if (this.situacao == StatusInscricao.CANCELADA) {
      throw new DomainRuleException("Esta inscrição já se encontra cancelada.");
    }
    this.situacao = StatusInscricao.CANCELADA;
  }

  public void confirmar() {
    this.situacao = StatusInscricao.CONFIRMADA;
  }

  public void adicionarAtividade(Atividade novaAtividade) {
    Validador.avaliar(new RegraObjetoNaoNulo(novaAtividade, "A atividade é obrigatória."));
    
    // TODO: tenho que fazer uma checagem de horarios em conflito aqui, vai ficar pra depois tho

    this.atividadesSelecionadas.add(novaAtividade);
  }
}
