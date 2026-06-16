# Sistema Academico - Etapa 3

Implementacao em Java puro, Maven, MVC e console para o fluxo de registro de notas descrito nas Etapas 1 e 2.

## Aderencia arquitetural

- **MVC**: `NotasView` recebe dados no terminal, `NotaController` coordena a chamada da View para o Model, e `NotaService` executa a regra de negocio.
- **Strategy**: `Disciplina` e o contexto do padrao e delega o calculo para `CalculoMedia`.
- **Estrategias obrigatorias**: `MediaAritmetica`, `MediaPonderada` e `MediaComExame`.
- **Repositorio em memoria**: `MatriculaRepository` usa `List<Matricula>`, sem banco de dados.
- **Sem Spring Boot**: o projeto usa apenas Java SE e o `exec-maven-plugin` para executar o `Main`.

## Fluxo implementado

1. Professor informa a matricula e as notas no console.
2. `NotaController` recebe a solicitacao da View.
3. `NotaService` localiza a matricula em `MatriculaRepository`.
4. `Disciplina` usa a estrategia configurada para calcular a media.
5. `NotaService` define a situacao academica conforme media e frequencia.
6. `NotasView` exibe media, situacao e detalhes ao usuario.

## Situacoes academicas

- `APROVADO`: frequencia minima atendida e media final maior ou igual a 6.0.
- `EXAME`: frequencia minima atendida e media entre 4.0 e 5.9, quando ainda nao ha nota de exame suficiente.
- `REPROVADO_NOTA`: frequencia minima atendida e media abaixo de 4.0.
- `REPROVADO_FALTA`: frequencia inferior a 75%, independentemente da nota.
- `EM_ANDAMENTO`: estado inicial da matricula antes do lancamento.

## Como executar

```bash
mvn compile
mvn exec:java
```

## Estrutura

```text
sistema-academico-etapa3/
|-- pom.xml
|-- README.md
`-- src/main/java/br/edu/academico/
    |-- Main.java
    |-- controller/NotaController.java
    |-- model/
    |   |-- calculo/
    |   |   |-- CalculoMedia.java
    |   |   |-- MediaAritmetica.java
    |   |   |-- MediaComExame.java
    |   |   `-- MediaPonderada.java
    |   |-- entidades/
    |   |   |-- Aluno.java
    |   |   |-- Disciplina.java
    |   |   |-- Matricula.java
    |   |   |-- Nota.java
    |   |   |-- Professor.java
    |   |   `-- SituacaoEnum.java
    |   |-- repository/MatriculaRepository.java
    |   `-- servicos/NotaService.java
    `-- view/NotasView.java
```
