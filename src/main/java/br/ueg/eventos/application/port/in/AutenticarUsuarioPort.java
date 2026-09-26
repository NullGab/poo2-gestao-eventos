package br.ueg.eventos.application.port.in;

import br.ueg.eventos.domain.model.Usuario;

public interface AutenticarUsuarioPort {
    Usuario executar(String email, String senhaLimpa);
}
