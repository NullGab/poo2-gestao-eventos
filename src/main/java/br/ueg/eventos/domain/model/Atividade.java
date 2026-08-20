package br.ueg.eventos.domain.model;

import br.ueg.eventos.domain.exception.DomainRuleException;
import java.util.Colletions;
import java.util.LocalDaTime;
import java.util.Arraylist;
import java.util.List;

public class Atividade {
    private final String id;
    private String titulo;
    private String descricao;
    private String tipo;
    private LocalDataTime dataInicio;
    private LocalDataTime dataFim;
    private String local;
    private List<VinculoPessoaAtividade> pessoasVinculadas;

    Protected Atividade(String id, String titulo, String descricao, String tipo, LocalDataTime dataInicio, LocalDataTime dataFim, String local) {
        if(textOuVazio(id)) {
            throw new DomainRuleException("Id da entidade não pode ser nulo ou vazio."); 
        }

        if(textOuVazio(titulo)) {
            throw new DomainRuleException("O título da atividade é obrigatória."); 
        }

        if(textOuVazio(descricao)) {
            throw new DomainRuleException("Informe a descrição da atividade."); 
        }

        if(textOuVazio(tipo)) {
            throw new DomainRuleException("Informe o tipo da atividade."); 
        }

        if(textOuVazio(local)) {
            throw new DomainRuleException("O local não pode estar vazio."); 
        }

        if (dataInicio != null && dataFim != null && dataFim.isBefore(dataInicio)) {
            throw new DomainRuleException("A data de término não pode ser anterior à data de início.");
        }

        this.id = id;
        this.titulo = titulo;
        this.tipo = tipo;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.local = local;
        this.pessoasVinculadas = new ArrayList<>();

    }

    public Atividade criarNova(String id, String titulo, String descricao, String tipo, LocalDataTime dataInicio, LocalDataTime dataFim, String local){
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

    public LocalDataTime getDataInicio() {
        return dataInicio;
    }

    public LocalDataTime getDataFim() {
        return dataFim;
    }

    public String getLocal() {
        return local;
    }

    protected Boolean textOuVazio(String valor) {
        return valor == null || valor.isBlank();
    }

}
