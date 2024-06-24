public class QuickSort implements SortingAlgorithm {
    private void sleepForVisualization(SortVisualizer visualizer) {
        try {
            Thread.sleep(visualizer.getDelay()); // Usa o getter para obter o delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sort(int[] arr, SortVisualizer visualizer) {
        quickSort(arr, 0, arr.length - 1, visualizer);
    }

    private void quickSort(int[] arr, int low, int high, SortVisualizer visualizer) {
        if (low < high) {
            int pi = partition(arr, low, high, visualizer);
            quickSort(arr, low, pi - 1, visualizer);
            quickSort(arr, pi + 1, high, visualizer);
        }
    }

    private int partition(int[] arr, int low, int high, SortVisualizer visualizer) {
        int pivot = arr[high]; // Escolhe o último elemento como pivô
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;

                // Troca arr[i] com arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;

                visualizer.updateView(arr, i, j); // Atualiza a visualização e destaca as barras sendo trocadas
                sleepForVisualization(visualizer);
            }
        }

        // Troca arr[i+1] com arr[high] (o pivô)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        visualizer.updateView(arr, i + 1, high);
        sleepForVisualization(visualizer);

        return i + 1; // Retorna o índice do pivô
    }
}
