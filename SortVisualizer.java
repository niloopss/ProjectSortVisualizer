import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


public class SortVisualizer extends JFrame {
    private int[] arr;
    private int barWidth = 10;
    private final JPanel barPanel;
    private final JPanel buttonPanel;
    private SortingAlgorithm currentAlgorithm;
    private int a = -1;
    private int b = -1;
    Color neonPink = new Color(255, 105, 180); // RGB para rosa neon
    Color darkPink = new Color(255, 20, 147); // RGB para rosa neon

    public SortVisualizer() {
        setTitle("Sort Visualizer");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        arr = new int[getWidth() / barWidth];
        Random rand = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(getHeight() - 50);
        }
        barPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Insets insets = getInsets(); // Obtém as insets do JFrame
                drawBars(g, insets);
                if (a >= 0 && b >= 0 && a < arr.length && b < arr.length){
                    g.setColor(darkPink);
                    g.fillRect(a * barWidth, getHeight() - arr[a], barWidth - 1, arr[a]);
                    g.fillRect(b * barWidth, getHeight() - arr[b], barWidth - 1, arr[b]);
                }
            }
        };
        add(barPanel, BorderLayout.CENTER);

        buttonPanel = new JPanel();
        JButton bubbleSortButton = new JButton("Bubble Sort");
        JButton insertionSortButton = new JButton("Insertion Sort");
        JButton mergeSortButton = new JButton("Merge Sort");
        JButton quickSortButton = new JButton("Quick Sort");
        JButton shuffleButton = new JButton("Shuffle");

        bubbleSortButton.addActionListener(e -> startSort(new BubbleSort()));
        insertionSortButton.addActionListener(e -> startSort(new InsertionSort()));
        mergeSortButton.addActionListener(e -> startSort(new MergeSort()));
        quickSortButton.addActionListener(e -> startSort(new QuickSort()));
        shuffleButton.addActionListener(e -> shuffleArray());

        buttonPanel.add(bubbleSortButton);
        buttonPanel.add(insertionSortButton);
        buttonPanel.add(mergeSortButton);
        buttonPanel.add(quickSortButton);
        buttonPanel.add(shuffleButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
    private void shuffleArray() {
        Random rand = new Random();
        for (int i = arr.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        updateView(arr); // Atualiza a visualização após o embaralhamento
    }
    private void drawBars(Graphics g, Insets insets) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int panelWidth = barPanel.getWidth() - insets.left - insets.right;
        int numBars = arr.length;

        barWidth = panelWidth / numBars;
        int totalBarWidth = barWidth * numBars;
        if (totalBarWidth > panelWidth) {
            barWidth--;
        }

        for (int i = 0; i < numBars; i++) {
            int x = i * barWidth + insets.left;
            int y = getHeight() - arr[i] - insets.top;
            int width = barWidth - 1;
            int height = arr[i];

            g2d.setColor(i == a || i == b ? darkPink : Color.BLACK);
            g2d.fillRect(x, y, width, height);
        }
    }
    public void updateView(int[] arr) { // Versão para atualização geral
        this.arr = arr;
        barPanel.repaint();
    }

    public void updateView(int[] arr, int a, int b) { // Versão para destacar barras
        this.arr = arr;
        this.a = a; // Índice da primeira barra a destacar
        this.b = b; // Índice da segunda barra a destacar
        barPanel.repaint();
    }
    public void highlightBars(int a, int b) {
        Graphics g = barPanel.getGraphics();
        Insets insets = getInsets(); // Obtem as insets do JFrame
        g.setColor(darkPink);
        g.fillRect((a * barWidth) + insets.left, getHeight() - arr[a] - insets.top, barWidth - 1, arr[a]);
        g.fillRect((b * barWidth) + insets.left, getHeight() - arr[b] - insets.top, barWidth - 1, arr[b]);

        try {
            Thread.sleep(50); // Pausa para visualização (opcional)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Restaura a cor original das barras após um curto intervalo
        g.setColor(darkPink); // Cor original das barras
        g.fillRect(a * barWidth, getHeight() - arr[a], barWidth - 1, arr[a]);
        g.fillRect(b * barWidth, getHeight() - arr[b], barWidth - 1, arr[b]);
    }
    private void startSort(SortingAlgorithm algorithm) {
        Thread sortingThread = new Thread(() -> {
            algorithm.sort(arr, this);
        });
        sortingThread.start(); // Inicia a ordenação em uma thread separada
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()-> new SortVisualizer().setVisible(true));
    }
}
