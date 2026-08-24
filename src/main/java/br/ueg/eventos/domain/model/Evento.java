package br.ueg.eventos.domain.model;

import br.ueg.eventos.domain.exception.DomainRuleException;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.time.ZonedDateTime;

public class Evento {
  private final String id;
  private String titulo;
  private String descricao;
  private TipoEvento tipo;
  private ModalidadeEvento modalidade;
  private String local;
  private ZonedDateTime dataInicio;
  private ZonedDateTime dataFim;
  private StatusEvento situacao;
  private List<Atividade> atividades;


  protected Evento(String id, String titulo, String descricao, TipoEvento tipo, ModalidadeEvento modalidade, String local, ZonedDateTime dataInicio, ZonedDateTime dataFim) {
    Validador.validar(
        new RegraTextoObrigatorio(id, "Id da entidade não pode ser nulo ou vazio."),
        new RegraTextoObrigatorio(titulo, "O título do evento é obrigatório."),
        new RegraTextoObrigatorio(descricao, "Informe a descrição do evento."),
        new RegraObjetoNaoNulo(tipo, "Informe o tipo do evento."),
        new RegraTextoObrigatorio(local, "O local não pode estar vazio."),
        new RegraObjetoNaoNulo(categoria, "A categoria do Evento precisa ser definida!."),
        new RegraObjetoNaoNulo(modalidade, "A modalidade do Evento precisa ser selecionada!."),

        ); 
    if (dataFim == null || dataInicio == null) {
      throw new DomainRuleException("As datas de iníco e término são obrigatórias!");
    }

    if (dataFim.isBefore(dataInicio)) {
      throw new DomainRuleException("A data de término não pode ser anterior à data de início.");
    }

    this.id = id;
    this.titulo = titulo;
    this.descricao = descricao;
    this.tipo = tipo;
    this.modalidade = modalidade;
    this.local = local;
    this.dataInicio = dataInicio;
    this.dataFim = dataFim;
    this.situacao = StatusEvento.RASCUNHO;
    this.atividades = new ArrayList<>();
  }

  public static Evento criarNovo(String id, String titulo, String descricao, TipoEvento tipo, ModalidadeEvento modalidade, String local, ZonedDateTime inicio, ZonedDateTime fim) {
    return new Evento(id, titulo, descricao, tipo, modalidade, local, inicio, fim);
  }


  //===================================Validações do Status do Evento=====================
  public void publicar() {
    if (this.situacao == StatusEvento.ENCERRADO || this.situacao == StatusEvento.CANCELADO) {
      throw new DomainRuleException("O evento foi cancelado ou já se encerrou, não é possível publicar.");
    }
    this.situacao = StatusEvento.PUBLICADO;
  }

  public void encerrar() {
    if (this.situacao == StatusEvento.CANCELADO) {
      throw new DomainRuleException("O evento já foi cancelado, não é possível encerrar.");
    }
    this.situacao = StatusEvento.ENCERRADO;
  }

  public void cancelar() {
    this.situacao = StatusEvento.CANCELADO;
  }
  //=======================================================================================

  public void adicionarAtividade(Atividade novaAtividade) {
    if (novaAtividade == null) {
      throw new DomainRuleException("A atividade não pode ser nula.");
    }
    if (this.situacao == StatusEvento.ENCERRADO) {
      throw new DomainRuleException("Não é possível adicionar atividades em um evento encerrado.");
    }
    if (novaAtividade.getDataInicio().isBefore(this.dataInicio.toLocalDateTime()) || novaAtividade.getDataFim().isAfter(this.dataFim.toLocalDateTime())) {
      throw new DomainRuleException("O horário da atividade precisa estar dentro do período do evento.");
    }
    for (Atividade atual : atividades){
      if(atual.conflitaCom(novaAtividade)){
        throw new DomainRuleException("Outra atividade já está agendada nesse local e horário");
      }
    }
    this.atividades.add(novaAtividade);
  }

  public List<Atividade> getAtividades() {
    return Collections.unmodifiableList(this.atividades);
  }

  public  String getId() {
    return id;
  }

  public String getTitulo() {
    return titulo;
  }

  public String getDescricao() {
    return descricao;
  }

  public TipoEvento getTipo() {
    return tipo;
  }

  public ModalidadeEvento getModalidade() {
    return modalidade;
  }

  public String getLocal() {
    return local;
  }

  public ZonedDateTime getDataInicio() {
    return dataInicio;
  }

  public ZonedDateTime getDataFim() {
    return dataFim;
  }

  public StatusEvento getSituacao() {
    return situacao;
  }

  public Boolean textOuVazio(String text) {
    return text == null || text.isBlank();
  }
}


