package br.edu.academico.model.entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Entidade que liga Aluno e Disciplina e contem as notas lancadas.
 */
public class Matricula {

    private Long id;
    private Aluno aluno;
    private Disciplina disciplina;
    private LocalDate dataMatricula;
    private SituacaoEnum situacao;
    private double frequenciaPercentual;
    private double mediaFinal;
    private final List<Nota> notas = new ArrayList<>();

    public Matricula(Long id, Aluno aluno, Disciplina disciplina,
                     LocalDate dataMatricula, double frequenciaPercentual) {
        this.id = id;
        this.aluno = aluno;
        this.disciplina = disciplina;
        this.dataMatricula = dataMatricula;
        this.frequenciaPercentual = frequenciaPercentual;
        this.situacao = SituacaoEnum.EM_ANDAMENTO;
        aluno.adicionarMatricula(this);
    }

    public void substituirNotas(List<Nota> novasNotas) {
        notas.clear();
        notas.addAll(novasNotas);
    }

    public String getSituacaoFinal() {
        return situacao.name();
    }

    public Long getId() {
        return id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public LocalDate getDataMatricula() {
        return dataMatricula;
    }

    public SituacaoEnum getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoEnum situacao) {
        this.situacao = situacao;
    }

    public double getFrequenciaPercentual() {
        return frequenciaPercentual;
    }

    public double getMediaFinal() {
        return mediaFinal;
    }

    public void setMediaFinal(double mediaFinal) {
        this.mediaFinal = mediaFinal;
    }

    public List<Nota> getNotas() {
        return Collections.unmodifiableList(notas);
    }
}
