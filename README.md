# GoL
Projeto de implementação do conceito de Game of Life

Alunos
- Andrei Felipe Silveira Sousa - 13/0039985
- João Pedro de Salles Braga  - 13/0143049 

GITHUB: https://github.com/sousa22/GoL


O projeto foi feito em Intelij, entao para executa-lo da maneira mais rapida e facil, 
abra o Intelij e use o comando Open e logo em seguida Run (rodar como Java Application).

Usamos uma classe chamada Swing que implementa uma interface Swing (a classe foi nomeada com base na bibloteca)
Todos as operacoes de mecanica de jogo estao na classe Engine e sao apenas chamadas pela Swing.

As estatisticas tambem estao sendo manipuladas na bibloteca Engine.

A Classe Swing basicamente cria um frame e o popula com 3 panels: um direto para estatisticas, um esquerdo
para o game e o de baixo para o menu de opcoes.
O panel do game possui dois sub-panels, um onde fica o menu de estrategias e o outro onde fica as celulas.

As celulas foram feitas usando uma array de JButtons, pois assim fica facil para o usuario setar uma
determinada celula viva ou morta. Seu acesso tambem na parte de backend fica facil pois estao identificadas
por coordenadas
  
Sua manipulacao e basica: um loop varre todos seus elementos enquanto invoca a funcao da Engine que
checka se o elemento presente nas tais coordenadas e vivo ou morto, assim o fundo do JButton e alterado
para indicar o estado da celula e sao invocadas as funcoes de reviver ou matar a respectiva celula.
 
Todas as estrategias incluidas foram achadas na internet e devidamente testadas.

Nao foi possivel incluir a feature de injecao de dependencia por falta de tempo habil dos integrantes da dupla.

Foi mantido o maximo possivel do codigo original pois partimos da premissa que um cliente pediria tal tarefa
(implementar funcionalidades em um sistema existente) e nao gostaria que alterasse seu codigo anterior.