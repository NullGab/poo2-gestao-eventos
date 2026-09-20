package br.ueg.eventos.domain.model;

import br.ueg.eventos.domain.util.validator.Validador;
import br.ueg.eventos.domain.util.validator.RegraTextoObrigatorio;
import br.ueg.eventos.domain.util.validator.RegraObjetoNaoNulo;
import br.ueg.eventos.domain.exception.DomainRuleException;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.time.ZonedDateTime;

public class Evento {
  private final String id;
  private Usuario organizador;
  private String titulo;
  private String descricao;
  private TipoEvento tipo;
  private ModalidadeEvento modalidade;
  private String endereco;
  private ZonedDateTime dataInicio;
  private ZonedDateTime dataFim;
  private StatusEvento situacao;

  private List<Atividade> atividades;
  private List<Local> locaisDisponiveis;


  protected Evento(String id, Usuario organizador, String titulo, String descricao, TipoEvento tipo,
                   ModalidadeEvento modalidade, String endereco, ZonedDateTime dataInicio, ZonedDateTime dataFim) {
    Validador.avaliar(
            new RegraTextoObrigatorio(id, "Id da entidade não pode ser nulo ou vazio."),
            new RegraTextoObrigatorio(titulo, "O título do evento é obrigatório."),
            new RegraTextoObrigatorio(descricao, "Informe a descrição do evento."),
            new RegraObjetoNaoNulo(tipo, "Informe o tipo do evento."),
            new RegraTextoObrigatorio(endereco, "O endereço/local geral não pode estar vazio."),
            new RegraObjetoNaoNulo(modalidade, "A modalidade do Evento precisa ser selecionada!"),
            new RegraObjetoNaoNulo(organizador, "O organizador do evento é obrigatório.")
    );

    if (dataFim == null || dataInicio == null) {
      throw new DomainRuleException("As datas de iníco e término são obrigatórias!");
    }

    if (dataFim.isBefore(dataInicio)) {
      throw new DomainRuleException("A data de término não pode ser anterior à data de início.");
    }

    this.id = id;
    this.organizador = organizador;
    this.titulo = titulo;
    this.descricao = descricao;
    this.tipo = tipo;
    this.modalidade = modalidade;
    this.endereco = endereco;
    this.dataInicio = dataInicio;
    this.dataFim = dataFim;
    this.situacao = StatusEvento.RASCUNHO;

    this.atividades = new ArrayList<>();
    this.locaisDisponiveis = new ArrayList<>();
  }

  public static Evento criarNovo(String id, Usuario organizador, String titulo, String descricao, TipoEvento tipo,
                                 ModalidadeEvento modalidade, String endereco, ZonedDateTime inicio, ZonedDateTime fim) {
    return new Evento(id, organizador, titulo, descricao, tipo, modalidade, endereco, inicio, fim);
  }

  public void cadastrarLocal(Local novoLocal) {
    if(novoLocal == null) {
      throw new DomainRuleException("O local não pode ser nulo!");
    }
    boolean localCadastrado = this.locaisDisponiveis.stream()
            .anyMatch(local -> local.getNome().equalsIgnoreCase(novoLocal.getNome()));
    if(localCadastrado){
      throw new DomainRuleException("Esse local já está cadastrado!");
    }
    this.locaisDisponiveis.add(novoLocal);
  }

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

  public void adicionarAtividade(Atividade novaAtividade) {
    if (novaAtividade == null) {
      throw new DomainRuleException("A atividade não pode ser nula.");
    }

    if (this.situacao == StatusEvento.ENCERRADO) {
      throw new DomainRuleException("Não é possível adicionar atividades em um evento encerrado.");
    }

    if (novaAtividade.getDataInicio().isBefore(this.dataInicio) || novaAtividade.getDataFim().isAfter(this.dataFim)) {
      throw new DomainRuleException("O horário da atividade precisa estar dentro do período do evento.");
    }

    boolean localCadastrado = this.locaisDisponiveis.stream().anyMatch(local -> local.getId().equals(novaAtividade.getLocal().getId()));
    if(!localCadastrado) {
      throw new DomainRuleException("A atividade deve ser realizada em um local já cadastrado!");
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

  public List<Local> getLocaisDiponiveis() {
    return Collections.unmodifiableList(this.locaisDisponiveis); }

  public String getId() { return id; }

  public Usuario getOrganizador() { return organizador; }

  public String getTitulo() { return titulo; }

  public String getDescricao() { return descricao; }

  public TipoEvento getTipo() { return tipo; }

  public ModalidadeEvento getModalidade() { return modalidade; }

  public String getEndereco() { return endereco; }

  public ZonedDateTime getDataInicio() { return dataInicio; }

  public ZonedDateTime getDataFim() { return dataFim; }

  public StatusEvento getSituacao() { return situacao; }

}