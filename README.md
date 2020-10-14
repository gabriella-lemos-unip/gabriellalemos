# gabriellalemos
Simulador de Algortimo de Substituição de Páginas
1.	INTRODUÇÃO

Para definir a prioridade de um processo, o sistema operacional utiliza algoritmos de alto nível que levam em consideração a prioridade entre eles. Entre os algoritmos mais comuns, está o FIFO (First In First Out), que é o algoritmo mais simples e não preemptivo. Os processos ali ocupam a CPU de acordo com a ordem em que entram na fila de processos. O processo controlará a CPU pelo tempo que julgar necessário, liberando-a para o próximo processo após completar sua execução, pois o processo não será interrompido mesmo que a CPU fique muito tempo ocupada.

Quando não há espaço livre para armazenar uma nova página, o sistema operacional deve selecionar uma das páginas na memória principal para excluir e permitir que a nova página seja trazida para a memória. Se a página a ser excluída for modificada na memória, ela deve ser salva no disco antes que uma nova página possa ser trazida. Se as páginas acessadas com frequência forem excluídas da memória, é provável que sejam trazidas de volta à memória em um curto espaço de tempo, resultando em um período de tempo evitável. Para gerenciar a memória, um algoritmo de troca de página é usado.

Para estudar algoritmos alternativos, é utilizada uma string de referência, que indica a ordem das páginas acessadas pelo processo durante a execução. Exemplos: 0, 2, 1, 3, 5, 4, 6, 3, 7, 4, 7, 3, 3, 5, 5, 3, 1, 1, 1, 7, 1, 3, 4, 1. Cada valor é uma referência à página. Dizemos que, enquanto a página for substituída na memória, ela falhará. A seguir, será mostrado o código de um simulador de algoritmo de substituição de páginas realizado para a disciplina de Sistemas Operacionais Abertos.
