package br.edu.academico.model.calculo;

import br.edu.academico.model.entidades.Nota;

import java.util.List;

/**
 * Estrategia concreta para disciplinas que configuram pesos por avaliacao.
 */
public class MediaPonderada implements CalculoMedia {

    @Override
    public double calcular(List<Nota> notas) {
        double somaValores = notas.stream()
                .filter(nota -> !nota.isExame())
                .mapToDouble(nota -> nota.getValor() * nota.getPeso())
                .sum();

        double somaPesos = notas.stream()
                .filter(nota -> !nota.isExame())
                .mapToDouble(Nota::getPeso)
                .sum();

        return somaPesos == 0.0 ? 0.0 : somaValores / somaPesos;
    }
}
