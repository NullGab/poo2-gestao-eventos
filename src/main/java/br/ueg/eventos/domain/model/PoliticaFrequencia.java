package br.ueg.eventos.domain.model;

import java.util.List;


public interface PoliticaFrequencia {
  boolean validarFrequencia(List<RegistroFrequencia> registrosDoParticipante);
}

class PoliticaCheckInUnico implements PoliticaFrequencia {

  @Override
  public boolean validarFrequencia(List<RegistroFrequencia> registrosDoParticipante) {
    if (registrosDoParticipante == null || registrosDoParticipante.isEmpty()) {
      return false;
    }
    return true; 
  }
}

class PoliticaEntradaSaida implements PoliticaFrequencia {

  @Override
  public boolean validarFrequencia(List<RegistroFrequencia> registrosDoParticipante) {
    if (registrosDoParticipante == null || registrosDoParticipante.isEmpty()) {
      return false;
    }

    if (registrosDoParticipante.size() < 2) {
      return false;
    }

    return true; 
  }
}

class PoliticaManual implements PoliticaFrequencia {

  @Override
  public boolean validarFrequencia(List<RegistroFrequencia> registrosDoParticipante) {
    if (registrosDoParticipante == null || registrosDoParticipante.isEmpty()) {
      return false;
    }

    for (RegistroFrequencia registro : registrosDoParticipante) {
      if (registro.getMetodoFrequencia() == MetodoFrequencia.MANUAL) {
        if (registro.getStatusFrequencia() == StatusFrequencia.PRESENTE) {
          return true;
        }
      }
    }

    return false;
  }
}
