public class MergeSort implements SortingAlgorithm {
    @Override
    public void sort(int[] arr, SortVisualizer visualizer) {
        mergeSort(arr, 0, arr.length - 1, visualizer);
    }

    private void mergeSort(int[] arr, int l, int r, SortVisualizer visualizer) {
        if (l < r) {
            int m = l + (r - l) / 2;
            mergeSort(arr, l, m, visualizer);
            mergeSort(arr, m + 1, r, visualizer);
            merge(arr, l, m, r, visualizer);
        }
    }

    private void merge(int[] arr, int l, int m, int r, SortVisualizer visualizer) {
        int n1 = m - l + 1;
        int n2 = r - m;

        // Cria arrays temporários
        int[] L = new int[n1];
        int[] R = new int[n2];

        // Copia dados para os arrays temporários
        for (int i = 0; i < n1; ++i) {
            L[i] = arr[l + i];
        }
        for (int j = 0; j < n2; ++j) {
            R[j] = arr[m + 1 + j];
        }

        // Mescla os arrays temporários de volta para arr[l..r]
        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            visualizer.updateView(arr, k, k); // Atualiza visualização e destaca a posição k
            try {
                Thread.sleep(50); // Pausa para visualização
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            k++;
        }

        // Copia os elementos restantes de L[], se houver
        while (i < n1) {
            arr[k] = L[i];
            visualizer.updateView(arr, k, k);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
            k++;
        }

        // Copia os elementos restantes de R[], se houver
        while (j < n2) {
            arr[k] = R[j];
            visualizer.updateView(arr, k, k);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            j++;
            k++;
        }
    }
}
