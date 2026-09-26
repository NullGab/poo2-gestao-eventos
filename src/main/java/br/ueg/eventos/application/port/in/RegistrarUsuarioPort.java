package br.ueg.eventos.application.port.in;


public interface RegistrarUsuarioPort {
    void executar(String id, String nome, String email, String senhaLimpa);
}
