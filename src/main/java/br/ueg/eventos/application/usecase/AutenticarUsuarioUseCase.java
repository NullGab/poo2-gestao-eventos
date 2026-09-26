package br.ueg.eventos.application.usecase;

import br.ueg.eventos.application.port.in.AutenticarUsuarioPort;
import br.ueg.eventos.application.port.out.PasswordEncoderPort;
import br.ueg.eventos.application.port.out.UsuarioRepositoryPort;
import br.ueg.eventos.domain.exception.DomainRuleException;
import br.ueg.eventos.domain.model.Usuario;

public class AutenticarUsuarioUseCase implements AutenticarUsuarioPort {
    private final UsuarioRepositoryPort usuarioRepository;
    private final PasswordEncoderPort passwordEncoder;

    public AutenticarUsuarioUseCase(UsuarioRepositoryPort usuarioRepository, PasswordEncoderPort passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Usuario executar(String email, String senhaLimpa) {
        Usuario usuario = usuarioRepository.buscarPorEmail(email)
                .orElseThrow(() -> new DomainRuleException("E-mail ou senha inválidos."));

        boolean senhaValida = passwordEncoder.matches(senhaLimpa, usuario.getSenhaHash());
        if (!senhaValida) {
            throw new DomainRuleException("E-mail ou senha inválidos.");
        }

        return usuario;
    }
}
