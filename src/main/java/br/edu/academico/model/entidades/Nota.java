package br.edu.academico.model.entidades;

import java.time.LocalDate;

/**
 * Entidade Nota do diagrama de classes, associada a uma Matricula.
 */
public class Nota {

    private Long id;
    private String descricao;
    private double valor;
    private double peso;
    private LocalDate dataLancamento;

    public Nota(Long id, String descricao, double valor, double peso, LocalDate dataLancamento) {
        validarValor(valor);
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.peso = peso;
        this.dataLancamento = dataLancamento;
    }

    public boolean isExame() {
        return "EXAME".equalsIgnoreCase(descricao);
    }

    private void validarValor(double valor) {
        if (valor < 0.0 || valor > 10.0) {
            throw new IllegalArgumentException("A nota deve estar entre 0 e 10.");
        }
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
    }

    public double getPeso() {
        return peso;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }
}
