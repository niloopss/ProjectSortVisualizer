import java.util.Random;


public class Shuffle implements SortingAlgorithm {
    @Override
    public void sort(int[] arr, SortVisualizer visualizer) {
        Random rand = new Random();
        for (int i = arr.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;

            visualizer.updateView(arr);
            try {
                Thread.sleep(visualizer.getDelay());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
