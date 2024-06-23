**Visualizador de Algoritmos de Ordenação em Java com Swing**

Este projeto oferece uma visualização interativa de algoritmos de ordenação populares, usando a biblioteca Swing para a interface gráfica. 
O objetivo é auxiliar no aprendizado e compreensão de como esses algoritmos funcionam na prática.





**Algoritmos Implementados**

**_Bubble Sort:_** Um dos algoritmos mais simples, mas menos eficiente para grandes conjuntos de dados.

**_Insertion Sort:_** Eficaz para conjuntos de dados pequenos ou parcialmente ordenados.

**_Merge Sort:_** Um algoritmo eficiente que funciona dividindo o array e mesclando as partes ordenadas.

**_Quick Sort:_** Geralmente rápido e eficiente, mas com pior caso em cenários específicos.





**Como Usar**

1. _**Clone o Repositório:**_ https://github.com/niloopss/ProjectSortVisualizer.git

2. _**Compile o Código:**_ javac SortVisualizer.java BubbleSort.java InsertionSort.java MergeSort.java QuickSort.java

3. _**Execute o Visualizador:**_ java SortVisualizer

4. _**Interaja com a Interface:**_ Clique nos botões para selecionar o algoritmo de ordenação.
 Clique em "Shuffle" para embaralhar o array.





**Estrutura do Código**

_**SortVisualizer.java:**_ Classe principal que cria a janela, desenha as barras e controla a interação.

_**SortingAlgorithm.java:**_ Interface que define o método sort para os algoritmos.

_**BubbleSort.java, InsertionSort.java, MergeSort.java, QuickSort.java:**_ Implementações dos algoritmos, cada um em sua própria classe.





**Personalização e Melhorias**

**_Velocidade de Animação:_** Ajuste o tempo de pausa (Thread.sleep(50)) nos métodos de ordenação para controlar a velocidade da visualização.

_**Cores:**_ Modifique as cores das barras no método drawBars para personalizar a aparência.

**_Algoritmos Adicionais:_** Implemente outros algoritmos de ordenação, como Selection Sort, Heap Sort, etc.

**_Interface:_** Adicione recursos como um controle deslizante para ajustar o tamanho do array ou um painel de informações para exibir estatísticas (comparações, trocas).





**Observações**

_Este projeto é principalmente para fins educacionais e de demonstração._

A performance dos algoritmos pode ser afetada pela interface gráfica. 
Para análise de desempenho rigorosa, testes em um ambiente sem GUI são recomendados.





**Sinta-se à vontade para contribuir, reportar problemas e sugerir melhorias!**
