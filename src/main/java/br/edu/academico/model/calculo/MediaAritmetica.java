package br.edu.academico.model.calculo;

import br.edu.academico.model.entidades.Nota;

import java.util.List;

/**
 * Estrategia concreta para disciplinas cujo criterio e a media simples.
 */
public class MediaAritmetica implements CalculoMedia {

    @Override
    public double calcular(List<Nota> notas) {
        return notas.stream()
                .filter(nota -> !nota.isExame())
                .mapToDouble(Nota::getValor)
                .average()
                .orElse(0.0);
    }
}
