package br.edu.academico.model.repository;

import br.edu.academico.model.entidades.Matricula;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repository em memoria conforme requisito da Etapa 3: sem banco de dados.
 */
public class MatriculaRepository {

    private final List<Matricula> matriculas = new ArrayList<>();

    public void salvar(Matricula matricula) {
        matriculas.removeIf(item -> item.getId().equals(matricula.getId()));
        matriculas.add(matricula);
    }

    public Optional<Matricula> findMatriculaById(Long id) {
        return matriculas.stream()
                .filter(matricula -> matricula.getId().equals(id))
                .findFirst();
    }

    public void atualizarMatricula(Matricula matricula) {
        salvar(matricula);
    }

    public List<Matricula> listarTodas() {
        return List.copyOf(matriculas);
    }
}
