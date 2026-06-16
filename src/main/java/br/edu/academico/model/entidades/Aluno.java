package br.edu.academico.model.entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Entidade de dominio conforme diagrama de classes: um Aluno possui matriculas.
 */
public class Aluno {

    private Long id;
    private String nome;
    private String matricula;
    private String email;
    private final List<Matricula> historico = new ArrayList<>();

    public Aluno(Long id, String nome, String matricula, String email) {
        this.id = id;
        this.nome = nome;
        this.matricula = matricula;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getEmail() {
        return email;
    }

    public List<Matricula> getHistorico() {
        return Collections.unmodifiableList(historico);
    }

    public void adicionarMatricula(Matricula matricula) {
        historico.add(matricula);
    }
}
