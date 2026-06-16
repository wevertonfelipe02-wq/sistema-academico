package br.edu.academico.model.calculo;

import br.edu.academico.model.entidades.Nota;

import java.util.List;

/**
 * Interface Strategy descrita no diagrama de classes da Etapa 2.
 */
public interface CalculoMedia {
    double calcular(List<Nota> notas);
}
