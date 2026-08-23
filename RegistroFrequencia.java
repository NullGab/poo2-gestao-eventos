package br.ueg.eventos.domain.model;

import br.ueg.eventos.domain.exception.DomainRuleException;
import br.ueg.eventos.domain.util.validator.RegraTextoObrigatorio;
import br.ueg.eventos.domain.util.validator.RegraObjetoNaoNulo;
import br.ueg.eventos.domain.util.validator.Validar;
import java.time.ZonedDateTime;

public class RegistroFrequencia { 

    private String participante;
    private String atividade;
    private ZonedDateTime dataHoraExata; 
    private MetodoFrequencia metodoFrequencia;
    private StatusFrequencia statusFrequencia; 
    private String responsavel; 

    public RegistroFrequencia(String participante, String atividade, MetodoFrequencia metodoFrequencia, String responsavel) {
        Validar.avaliar(
            new RegraTextoObrigatorio(participante, "O participante é obrigatório."),
            new RegraTextoObrigatorio(atividade, "A atividade é obrigatória."),
            new RegraObjetoNaoNulo(metodoFrequencia, "O método de frequência é obrigatório.")
        );

        this.participante = participante;
        this.atividade = atividade;
        this.metodoFrequencia = metodoFrequencia;
        this.responsavel = responsavel;
        this.dataHoraExata = ZonedDateTime.now(); 
        
        this.statusFrequencia = StatusFrequencia.PENDENTE; 
    }
    
    //==========Validar Registro Frequencia===================
    public void validaRegistroManual() {
        if (this.statusFrequencia != StatusFrequencia.PENDENTE) {
            throw new DomainRuleException("Não é possível validar: o registro não está pendente.");
        }

        if (this.metodoFrequencia != MetodoFrequencia.MANUAL) {
            throw new DomainRuleException("Este registro não é manual.");
        }

        Validar.avaliar(
            new RegraTextoObrigatorio(this.responsavel, "A identificação do responsável é obrigatória no registro manual.")
        );
        
        this.statusFrequencia = StatusFrequencia.PRESENTE;
    }

    public void validaRegistroQRCode() {
        if (this.statusFrequencia != StatusFrequencia.PENDENTE) {
            throw new DomainRuleException("Não é possível validar: o registro não está pendente.");
        }

        if (this.metodoFrequencia != MetodoFrequencia.QRCODE) {
            throw new DomainRuleException("Este registro não é via QR Code.");
        }
        
        this.statusFrequencia = StatusFrequencia.PRESENTE;
    }
    //==========================================================

    public String getParticipante() {
        return this.participante;
    }

    public String getAtividade() {
        return this.atividade;
    }

    public MetodoFrequencia getMetodoFrequencia() {
        return this.metodoFrequencia;
    }

    public StatusFrequencia getStatusFrequencia() {
        return this.statusFrequencia;
    }

    public String getResponsavel() {
        return this.responsavel;
    }

    public ZonedDateTime getDataHoraExata() {
        return this.dataHoraExata;
    }
}
