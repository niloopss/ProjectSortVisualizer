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
    private int delay = 50;
    Color neonPink = new Color(255, 105, 180); // RGB para rosa neon
    Color darkPink = new Color(255, 20, 147); // RGB para rosa neon
    private JButton speed1xButton, speed1_5xButton, speed2xButton, speed3xButton, speed5xButton; // Declarados como atributos
    private JButton bubbleSortButton, insertionSortButton, mergeSortButton, quickSortButton, shuffleButton;


    public int getDelay() {
        return delay;
    }

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
        bubbleSortButton = new JButton("Bubble Sort");
        insertionSortButton = new JButton("Insertion Sort");
        mergeSortButton = new JButton("Merge Sort");
        quickSortButton = new JButton("Quick Sort");
        shuffleButton = new JButton("Shuffle");

        JPanel speedButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Alinhamento à esquerda

        // Botões de velocidade (sem fundo, com texto preto)
        speed1xButton = new JButton("Normal");
        speed1_5xButton = new JButton("1.5x");
        speed2xButton = new JButton("2x");
        speed3xButton = new JButton("3x");
        speed5xButton = new JButton("5x");

        // ActionListeners dos botões
        bubbleSortButton.addActionListener(e -> {
            startSort(new BubbleSort());
            resetAlgorithmButtonColors();
            bubbleSortButton.setForeground(darkPink);
        });

        insertionSortButton.addActionListener(e ->{
            startSort(new InsertionSort());
            resetAlgorithmButtonColors();
            insertionSortButton.setForeground(darkPink);
        });

        mergeSortButton.addActionListener(e ->{
            startSort(new MergeSort());
            resetAlgorithmButtonColors();
            mergeSortButton.setForeground(darkPink);
        });

        quickSortButton.addActionListener(e ->{
            startSort(new QuickSort());
            resetAlgorithmButtonColors();
            quickSortButton.setForeground(darkPink);
        });

        shuffleButton.addActionListener(e -> {
            startSort(new Shuffle()); // Usa a nova classe Shuffle
            resetAlgorithmButtonColors();
            shuffleButton.setForeground(darkPink);
        });

        speed1xButton.addActionListener(e -> {
            delay = 50;
            resetSpeedButtonColors();
            speed1xButton.setForeground(darkPink); // Destaca o botão ativo
        });

        speed1_5xButton.addActionListener(e -> {
            delay = 33;
            resetSpeedButtonColors();
            speed1_5xButton.setForeground(darkPink);
        });

        speed2xButton.addActionListener(e -> {
            delay = 25;
            resetSpeedButtonColors();
            speed2xButton.setForeground(darkPink);
        });

        speed3xButton.addActionListener(e -> {
            delay = 16; // Velocidade 3x (aproximadamente 50 / 3)
            resetSpeedButtonColors();
            speed3xButton.setForeground(darkPink);
        });

        speed5xButton.addActionListener(e -> {
            delay = 10; // Velocidade 5x
            resetSpeedButtonColors();
            speed5xButton.setForeground(darkPink);
        });


        speedButtonPanel.add(speed1xButton);
        speedButtonPanel.add(speed1_5xButton);
        speedButtonPanel.add(speed2xButton);
        speedButtonPanel.add(speed3xButton);
        speedButtonPanel.add(speed5xButton);


        bubbleSortButton.addActionListener(e -> startSort(new BubbleSort()));
        insertionSortButton.addActionListener(e -> startSort(new InsertionSort()));
        mergeSortButton.addActionListener(e -> startSort(new MergeSort()));
        quickSortButton.addActionListener(e -> startSort(new QuickSort()));
        shuffleButton.addActionListener(e -> startSort(new Shuffle()));

        buttonPanel.add(bubbleSortButton);
        buttonPanel.add(insertionSortButton);
        buttonPanel.add(mergeSortButton);
        buttonPanel.add(quickSortButton);
        buttonPanel.add(shuffleButton);
        add(speedButtonPanel, BorderLayout.NORTH); // Adiciona o painel ao topo
        add(buttonPanel, BorderLayout.SOUTH);

        // Configuração inicial dos botões (preto)
        resetSpeedButtonColors();
        resetAlgorithmButtonColors();

        setVisible(true);
    }

    // Método para resetar a cor dos botões dos algoritmos
    private void resetAlgorithmButtonColors() {
        bubbleSortButton.setForeground(Color.BLACK);
        insertionSortButton.setForeground(Color.BLACK);
        mergeSortButton.setForeground(Color.BLACK);
        quickSortButton.setForeground(Color.BLACK);
        shuffleButton.setForeground(Color.BLACK);
    }


    // Método para resetar a cor dos botões de velocidade
    private void resetSpeedButtonColors() {
        speed1xButton.setForeground(Color.BLACK);
        speed1_5xButton.setForeground(Color.BLACK);
        speed2xButton.setForeground(Color.BLACK);
        speed3xButton.setForeground(Color.BLACK);
        speed5xButton.setForeground(Color.BLACK);

        // Remova as bordas dos botões, se houver
        speed1xButton.setBorder(null);
        speed1_5xButton.setBorder(null);
        speed2xButton.setBorder(null);
        speed3xButton.setBorder(null);
        speed5xButton.setBorder(null);
        // Remova o fundo dos botões, se houver
        speed1xButton.setContentAreaFilled(false);
        speed1_5xButton.setContentAreaFilled(false);
        speed2xButton.setContentAreaFilled(false);
        speed3xButton.setContentAreaFilled(false);
        speed5xButton.setContentAreaFilled(false);
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
