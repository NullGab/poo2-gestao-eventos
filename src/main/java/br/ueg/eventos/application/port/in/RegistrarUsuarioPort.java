package br.ueg.eventos.application.port.in;

import br.ueg.eventos.domain.model.Usuario;

public interface RegistrarUsuarioPort {
    void executar(String id, String nome, String email, String senhaLimpa);
}
