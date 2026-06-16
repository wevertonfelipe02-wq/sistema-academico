package br.edu.academico.model.calculo;

import br.edu.academico.model.entidades.Nota;

import java.util.List;
import java.util.Optional;

/**
 * Estrategia concreta alinhada a RN08: aluno com media entre 4.0 e 5.9
 * tem direito ao exame; aprovacao no exame requer nota de exame >= 6.0.
 */
public class MediaComExame implements CalculoMedia {

    private static final double NOTA_MINIMA_EXAME = 6.0;
    private final CalculoMedia mediaBase;

    public MediaComExame() {
        this(new MediaAritmetica());
    }

    public MediaComExame(CalculoMedia mediaBase) {
        this.mediaBase = mediaBase;
    }

    @Override
    public double calcular(List<Nota> notas) {
        double media = mediaBase.calcular(notas);
        Optional<Nota> notaExame = notas.stream()
                .filter(Nota::isExame)
                .findFirst();

        if (media >= 4.0 && media < 6.0 && notaExame.isPresent()) {
            return notaExame.get().getValor() >= NOTA_MINIMA_EXAME ? NOTA_MINIMA_EXAME : notaExame.get().getValor();
        }

        return media;
    }
}
