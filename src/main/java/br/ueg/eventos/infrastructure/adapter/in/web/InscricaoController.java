package br.ueg.eventos.infrastructure.adapter.in.web;

import br.ueg.eventos.application.port.in.ConfirmarInscricaoPort;
import br.ueg.eventos.application.port.in.CancelarInscricaoPort;
import io.javalin.http.Context;

public class InscricaoController {

  private final ConfirmarInscricaoPort confirmarInscricaoPort;
  private final CancelarInscricaoPort cancelarInscricaoPort;

  public InscricaoController(
      ConfirmarInscricaoPort confirmarInscricaoPort,
      CancelarInscricaoPort cancelarInscricaoPort) {
    this.confirmarInscricaoPort = confirmarInscricaoPort;
    this.cancelarInscricaoPort = cancelarInscricaoPort;
      }

  public void confirmar(Context ctx) {
    String idInscricao = ctx.pathParam("id");
    confirmarInscricaoPort.executar(idInscricao);
    ctx.status(200).result("Inscrição confirmada com sucesso!");
  }

  public void cancelar(Context ctx) {
    String idInscricao = ctx.pathParam("id");
    cancelarInscricaoPort.executar(idInscricao);
    ctx.status(200).result("Inscrição cancelada com sucesso!");
  }
}
