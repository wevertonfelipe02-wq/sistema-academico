package br.edu.academico.model.entidades;

import br.edu.academico.model.calculo.CalculoMedia;

import java.util.List;

/**
 * Contexto do padrao Strategy: a disciplina conhece a estrategia configurada
 * e delega a ela o calculo da media.
 */
public class Disciplina {

    private Long id;
    private String nome;
    private String codigo;
    private int cargaHoraria;
    private Professor professor;
    private CalculoMedia estrategiaCalculo;

    public Disciplina(Long id, String nome, String codigo, int cargaHoraria,
                      Professor professor, CalculoMedia estrategiaCalculo) {
        this.id = id;
        this.nome = nome;
        this.codigo = codigo;
        this.cargaHoraria = cargaHoraria;
        this.professor = professor;
        this.estrategiaCalculo = estrategiaCalculo;
    }

    public double calcularMedia(List<Nota> notas) {
        return estrategiaCalculo.calcular(notas);
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public Professor getProfessor() {
        return professor;
    }

    public CalculoMedia getEstrategiaCalculo() {
        return estrategiaCalculo;
    }
}
