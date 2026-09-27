package br.ueg.eventos.application.port.in;

public interface RegistrarFrequenciaManualPort {
    void executar(String participante, String atividade, String responsavel);
}
