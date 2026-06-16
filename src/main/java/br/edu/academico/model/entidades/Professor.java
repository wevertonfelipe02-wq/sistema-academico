package br.edu.academico.model.entidades;

/**
 * Entidade do ator Professor, responsavel por registrar notas no fluxo principal.
 */
public class Professor {

    private Long id;
    private String nome;
    private String siape;

    public Professor(Long id, String nome, String siape) {
        this.id = id;
        this.nome = nome;
        this.siape = siape;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSiape() {
        return siape;
    }
}
