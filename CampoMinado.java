import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CampoMinado extends JFrame {
    private final int NUM_MINAS = 10;
    private final int GRID_SIZE = 10;
    private Celula[][] celulas = new Celula[GRID_SIZE][GRID_SIZE];
    private JLabel statusLabel;

    public CampoMinado() {
        setTitle("Campo Minado");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel gridPanel = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE));
        add(gridPanel, BorderLayout.CENTER);

        statusLabel = new JLabel("Minas restantes: " + NUM_MINAS);
        add(statusLabel, BorderLayout.SOUTH);

        // Inicializar as células e adicionar ao painel
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                celulas[i][j] = new Celula(i, j);
                celulas[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        Celula cell = (Celula) e.getSource();
                        if (e.getButton() == MouseEvent.BUTTON1) {
                            cell.revelar();
                            if (cell.temMina()) {
                                JOptionPane.showMessageDialog(null, "Game Over!");
                                System.exit(0);
                            }
                        } else if (e.getButton() == MouseEvent.BUTTON3) {
                            cell.toggleMarca();
                            atualizarContadorMinas();
                        }
                    }
                });
                gridPanel.add(celulas[i][j]);
            }
        }

        distribuirMinas();
    }

    private void distribuirMinas() {
        int minasColocadas = 0;
        while (minasColocadas < NUM_MINAS) {
            int linha = (int) (Math.random() * GRID_SIZE);
            int coluna = (int) (Math.random() * GRID_SIZE);
            if (!celulas[linha][coluna].temMina()) {
                celulas[linha][coluna].setMina(true);
                minasColocadas++;
            }
        }
    }

    private void atualizarContadorMinas() {
        int count = NUM_MINAS;
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (celulas[i][j].isMarcado()) count--;
            }
        }
        statusLabel.setText("Minas restantes: " + count);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CampoMinado jogo = new CampoMinado();
            jogo.setVisible(true);
        });
    }
}
