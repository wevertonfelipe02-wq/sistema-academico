package br.edu.academico.view;

import br.edu.academico.controller.NotaController;
import br.edu.academico.model.entidades.Matricula;
import br.edu.academico.model.entidades.Nota;
import br.edu.academico.model.entidades.SituacaoEnum;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * View de console: representa a interacao do Professor com o modulo de notas.
 */
public class NotasView {

    private final NotaController notaController;
    private final Scanner scanner;

    public NotasView(NotaController notaController, Scanner scanner) {
        this.notaController = notaController;
        this.scanner = scanner;
    }

    public void iniciar() {
        System.out.println("Sistema Academico - Registro de Notas");
        System.out.println("Professor: informe os dados da avaliacao para calcular a situacao.");

        try {
            Long matriculaId = lerLong("ID da matricula: ");
            int quantidade = lerInteiro("Quantidade de notas: ");
            List<Nota> notas = lerNotas(quantidade);

            Matricula matricula = notaController.registrarNotas(matriculaId, notas);
            exibirResultado(matricula);
        } catch (IllegalArgumentException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }

    private List<Nota> lerNotas(int quantidade) {
        List<Nota> notas = new ArrayList<>();

        for (int i = 1; i <= quantidade; i++) {
            System.out.println();
            System.out.println("Nota " + i);
            String descricao = lerTexto("Descricao (N1, N2 ou EXAME): ");
            double valor = lerDouble("Valor (0 a 10): ");
            double peso = lerDouble("Peso: ");
            notas.add(new Nota((long) i, descricao, valor, peso, LocalDate.now()));
        }

        return notas;
    }

    private void exibirResultado(Matricula matricula) {
        System.out.println();
        System.out.println("Resultado do lancamento");
        System.out.println("Aluno: " + matricula.getAluno().getNome());
        System.out.println("Disciplina: " + matricula.getDisciplina().getNome());
        System.out.printf("Media final: %.2f%n", matricula.getMediaFinal());
        System.out.printf("Frequencia: %.2f%%%n", matricula.getFrequenciaPercentual());
        System.out.println("Situacao: " + matricula.getSituacaoFinal());

        if (matricula.getSituacao() == SituacaoEnum.EXAME) {
            System.out.println("Observacao: aluno dentro da faixa de exame final.");
        }
    }

    private String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine().trim();
    }

    private Long lerLong(String mensagem) {
        return Long.parseLong(lerTexto(mensagem));
    }

    private int lerInteiro(String mensagem) {
        return Integer.parseInt(lerTexto(mensagem));
    }

    private double lerDouble(String mensagem) {
        return Double.parseDouble(lerTexto(mensagem).replace(",", "."));
    }
}
