public class InsertionSort implements SortingAlgorithm {
    private void sleepForVisualization(SortVisualizer visualizer) {
        try {
            Thread.sleep(visualizer.getDelay()); // Usa o getter para obter o delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sort(int[] arr, SortVisualizer visualizer) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;

                visualizer.updateView(arr, j + 1, i);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            arr[j + 1] = key;
            visualizer.updateView(arr);
            sleepForVisualization(visualizer); // Chama o método sleepForVisualization
        }
    }
}
