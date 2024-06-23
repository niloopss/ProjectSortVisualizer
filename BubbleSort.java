public class BubbleSort implements SortingAlgorithm {
    @Override
    public void sort(int[] arr, SortVisualizer visualizer) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                visualizer.updateView(arr, j, j + 1); // Destaca j e j+1 ANTES da comparação
                try {
                    Thread.sleep(50); // Pausa para visualização
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    visualizer.updateView(arr, j, j + 1); // Atualiza a visualização APÓS a troca
                }
            }
        }
        visualizer.updateView(arr); // Garante que o array final seja exibido corretamente
    }
}
