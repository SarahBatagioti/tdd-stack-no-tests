# tdd-stack

Projeto Maven de exemplo para prática de TDD com uma pilha numérica customizada (`CustomStack`) e testes automatizados com JUnit 5, Mockito e JaCoCo.

## O que o projeto faz

- Implementa uma pilha com limite de capacidade.
- Permite empilhar, desempilhar, consultar o topo e verificar tamanho.
- Usa uma estratégia externa (`CalculableStrategy`) para calcular o valor antes de armazená-lo.

## Como testar

Na pasta `tdd-stack`, execute:

```bash
mvn test
```

Esse comando:

- compila o projeto;
- executa os testes da classe `StackTest`;
- gera o relatório de cobertura com JaCoCo.

## Como ver a cobertura

Após rodar os testes, o relatório HTML fica em:

```text
target/site/jacoco/index.html
```

Abra esse arquivo no navegador para consultar os detalhes da cobertura do projeto.
