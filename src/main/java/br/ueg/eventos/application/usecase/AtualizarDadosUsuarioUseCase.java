package br.ueg.eventos.application.usecase;

import br.ueg.eventos.application.port.in.AtualizarDadosUsuarioPort;
import br.ueg.eventos.application.port.out.UsuarioRepositoryPort;
import br.ueg.eventos.domain.exception.DomainRuleException;
import br.ueg.eventos.domain.model.Usuario;

import java.util.Optional;

public class AtualizarDadosUsuarioUseCase implements AtualizarDadosUsuarioPort {

    private final UsuarioRepositoryPort usuarioRepository;

    public AtualizarDadosUsuarioUseCase(UsuarioRepositoryPort usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario executar(String idUsuario, String novoNome, String novoEmail) {
        Usuario usuario = usuarioRepository.buscarPorId(idUsuario)
                .orElseThrow(() -> new DomainRuleException("Usuário não encontrado."));

        if (!usuario.getEmail().equalsIgnoreCase(novoEmail)) {
            Optional<Usuario> usuarioComMesmoEmail = usuarioRepository.buscarPorEmail(novoEmail);
            if (usuarioComMesmoEmail.isPresent()) {
                throw new DomainRuleException("O e-mail informado já está em uso por outro usuário.");
            }
        }

        usuario.atualizarDados(novoNome, novoEmail);

        return usuarioRepository.salvar(usuario);
    }
}