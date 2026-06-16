package br.edu.academico.controller;

import br.edu.academico.model.entidades.Matricula;
import br.edu.academico.model.entidades.Nota;
import br.edu.academico.model.servicos.NotaService;

import java.util.List;

/**
 * Controller MVC usado no diagrama de sequencia: recebe dados da View e aciona o Service.
 */
public class NotaController {

    private final NotaService notaService;

    public NotaController(NotaService notaService) {
        this.notaService = notaService;
    }

    public Matricula registrarNotas(Long matriculaId, List<Nota> notas) {
        return notaService.salvarNotas(matriculaId, notas);
    }
}
