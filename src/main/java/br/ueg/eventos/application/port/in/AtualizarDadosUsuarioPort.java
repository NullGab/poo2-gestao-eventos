package br.ueg.eventos.application.port.in;
import br.ueg.eventos.domain.model.Usuario;

public interface AtualizarDadosUsuarioPort {
    Usuario executar(String idUsuario, String novoNome, String novoEmail);
}
