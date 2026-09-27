package br.ueg.eventos.application.port.in;

public interface CalcularSituacaoPresencaPort {
    boolean executar(String participante, String atividade);
}
