package br.edu.academico;

import br.edu.academico.controller.NotaController;
import br.edu.academico.model.calculo.MediaAritmetica;
import br.edu.academico.model.calculo.MediaComExame;
import br.edu.academico.model.calculo.MediaPonderada;
import br.edu.academico.model.entidades.Aluno;
import br.edu.academico.model.entidades.Disciplina;
import br.edu.academico.model.entidades.Matricula;
import br.edu.academico.model.entidades.Professor;
import br.edu.academico.model.repository.MatriculaRepository;
import br.edu.academico.model.servicos.NotaService;
import br.edu.academico.view.NotasView;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * Ponto de entrada console. Monta objetos manualmente para manter Java puro,
 * sem Spring Boot e sem banco de dados.
 */
public class Main {

    public static void main(String[] args) {
        MatriculaRepository repository = criarDadosEmMemoria();
        NotaService service = new NotaService(repository);
        NotaController controller = new NotaController(service);
        NotasView view = new NotasView(controller, new Scanner(System.in));

        listarMatriculasDisponiveis(repository);
        view.iniciar();
    }

    private static MatriculaRepository criarDadosEmMemoria() {
        MatriculaRepository repository = new MatriculaRepository();

        Professor professor = new Professor(1L, "Maria Souza", "SIAPE001");
        Aluno aluno1 = new Aluno(1L, "Ana Lima", "2025001", "ana@academico.edu.br");
        Aluno aluno2 = new Aluno(2L, "Bruno Costa", "2025002", "bruno@academico.edu.br");
        Aluno aluno3 = new Aluno(3L, "Carla Dias", "2025003", "carla@academico.edu.br");

        Disciplina arquitetura = new Disciplina(1L, "Arquitetura de Software", "ADS101", 80,
                professor, new MediaAritmetica());
        Disciplina programacao = new Disciplina(2L, "Programacao Orientada a Objetos", "ADS102", 80,
                professor, new MediaPonderada());
        Disciplina algoritmos = new Disciplina(3L, "Algoritmos", "ADS103", 80,
                professor, new MediaComExame());

        repository.salvar(new Matricula(1L, aluno1, arquitetura, LocalDate.now(), 90.0));
        repository.salvar(new Matricula(2L, aluno2, programacao, LocalDate.now(), 82.5));
        repository.salvar(new Matricula(3L, aluno3, algoritmos, LocalDate.now(), 72.0));

        return repository;
    }

    private static void listarMatriculasDisponiveis(MatriculaRepository repository) {
        System.out.println("Matriculas em memoria para teste:");
        repository.listarTodas().forEach(matricula ->
                System.out.printf("ID %d - %s - %s - frequencia %.2f%%%n",
                        matricula.getId(),
                        matricula.getAluno().getNome(),
                        matricula.getDisciplina().getNome(),
                        matricula.getFrequenciaPercentual()));
        System.out.println();
    }
}
