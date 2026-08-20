package br.ueg.eventos.domain.exception;

public class DomainRuleException extends RuntimeException {

    public DomainRuleException(String mensagemErro) {
        super(mensagemErro);
    }
}
