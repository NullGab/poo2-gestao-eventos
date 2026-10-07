package br.ueg.eventos.infrastructure.adapter.out.postgres;

import br.ueg.eventos.application.port.out.InscricaoRepositoryPort;
import br.ueg.eventos.domain.model.Inscricao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.List;

public class InscricaoRepositoryPostgres implements InscricaoRepositoryPort  {
  
  private final String url;
  private final String user;
  private final String password;

  public InscricaoRepositoryPostgres(String url, String user, String password) {
    this.url = url;
    this.user = user;
    this.password = password;
  }
}

@Override
public void salvar(Inscricao inscricao) {
  String sql = "INSERT INTO inscricoes (id, usuario_id, evento_id, status) VALUES (?, ?, ?, ?)";
  try (Connection conex = DriverManager.getConnection(url,user,password);
      PreparedStatement stmt = conex.prepareStatement(sql)) {
  stmt.setString(1, inscricao.getid());
  stmt.setString(2, inscricao.getUsuario());
  stmt.setString(3, inscricao.getEvento());
  stmt.setString(4, inscricao.getStatus().name());
  
  stmt.executeUpdate();
    
  } catch (SQLException e) {
      throw new RuntimeException("Erro ao salvar no banco de dados:" + e.getMessage(), e);  
  }
}




















