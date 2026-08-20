package br.ueg.eventos.domain.model;

import br.ueg.eventos.domain.exception.DomainRuleException;
import java.util.Collections;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class Atividade {
    private final String id;
    private String titulo;
    private String descricao;
    private String tipo;
    private ZonedDateTime dataInicio;
    private ZonedDateTime dataFim;
    private String local;
    private List<VinculoPessoaAtividade> pessoasVinculadas;

    protected Atividade(String id, String titulo, String descricao, String tipo, ZonedDateTime dataInicio, ZonedDateTime dataFim, String local) {
      Validador.avaliar(
          new RegraTextoObrigatorio(id, "Id da entidade não pode ser nulo ou vazio."),
          new RegraTextoObrigatorio(titulo, "O título da atividade é obrigatória."),
          new RegraTextoObrigatorio(descricao, "Informe a descrição da atividade."),
          new RegraObjetoNaoNulo(tipo, "Informe o tipo da atividade."),
          new RegraTextoObrigatorio(local, "O local não pode estar vazio."),
          new RegraObjetoNaoNulo(dataInicio, "A data de inicio nao pode estar vazia."),
          new RegraObjetoNaoNulo(dataFim, "A data final nao pode estar vazia."),
          new RegraInicioFim(dataInicio, dataFim)
          );   
        this.id = id;
        this.titulo = titulo;
        this.tipo = tipo;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.local = local;
       this.pessoasVinculadas = new ArrayList<>();

    }

    public Atividade criarNova(String id, String titulo, String descricao, String tipo, ZonedDateTime dataInicio, ZonedDateTime dataFim, String local){
        return Atividade(id, titulo, descricao, tipo, dataInicio, dataFim, local, pessoasVinculadas);
    }

    public void vincularPessoa(VinculoPessoaAtividade vinculo) {
        if(vinculo == null) {
            throw new DomainRuleException("O vínculo de pessoa não pode ser nulo.");
        }
        this.pessoasVinculadas.add(vinculo);
    } 

    public boolean conflitaCom(Atividade outra) {
        if (outra == null || !this.local.equals(outra.local)) {
            return false;
        }
        return this.dataInicio.isBefore(outra.dataFim) && outra.dataInicio.isBefore(this.dataFim);
    }

    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public ZonedDateTime getDataInicio() {
        return dataInicio;
    }

    public ZonedDateTime getDataFim() {
        return dataFim;
    }

    public String getLocal() {
        return local;
    }

    protected Boolean textOuVazio(String valor) {
        return valor == null || valor.isBlank();
    }

}
