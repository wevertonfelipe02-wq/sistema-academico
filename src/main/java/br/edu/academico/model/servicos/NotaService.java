package br.edu.academico.model.servicos;

import br.edu.academico.model.entidades.Matricula;
import br.edu.academico.model.entidades.Nota;
import br.edu.academico.model.entidades.SituacaoEnum;
import br.edu.academico.model.repository.MatriculaRepository;

import java.util.List;

/**
 * Service do MVC: concentra RF003, RF005, RN06, RN07 e RN08.
 */
public class NotaService {

    private static final double FREQUENCIA_MINIMA = 75.0;
    private final MatriculaRepository matriculaRepository;

    public NotaService(MatriculaRepository matriculaRepository) {
        this.matriculaRepository = matriculaRepository;
    }

    public Matricula salvarNotas(Long matriculaId, List<Nota> notas) {
        if (notas == null || notas.isEmpty()) {
            throw new IllegalArgumentException("Informe pelo menos uma nota.");
        }

        Matricula matricula = matriculaRepository.findMatriculaById(matriculaId)
                .orElseThrow(() -> new IllegalArgumentException("Matricula nao encontrada."));

        matricula.substituirNotas(notas);
        double mediaFinal = matricula.getDisciplina().calcularMedia(matricula.getNotas());
        matricula.setMediaFinal(mediaFinal);
        matricula.setSituacao(definirSituacao(matricula, mediaFinal));

        matriculaRepository.atualizarMatricula(matricula);
        return matricula;
    }

    private SituacaoEnum definirSituacao(Matricula matricula, double mediaFinal) {
        if (matricula.getFrequenciaPercentual() < FREQUENCIA_MINIMA) {
            return SituacaoEnum.REPROVADO_FALTA;
        }

        if (mediaFinal >= 6.0) {
            return SituacaoEnum.APROVADO;
        }

        if (mediaFinal >= 4.0) {
            boolean exameLancado = matricula.getNotas().stream().anyMatch(Nota::isExame);
            if (exameLancado) {
                return SituacaoEnum.REPROVADO_NOTA;
            }
            return SituacaoEnum.EXAME;
        }

        return SituacaoEnum.REPROVADO_NOTA;
    }
}
