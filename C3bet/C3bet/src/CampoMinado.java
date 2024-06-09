import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CampoMinado extends JFrame {
    private final int NUM_MINAS = 5; // Número de minas reduzido para um grid menor
    private final int GRID_SIZE = 5; // Grid 5x5
    private Celula[][] celulas = new Celula[GRID_SIZE][GRID_SIZE];
    private JLabel statusLabel;
    private JFrame telaAnterior; // Referência para a tela anterior

    public CampoMinado(JFrame telaAnterior) {
        this.telaAnterior = telaAnterior;

        setTitle("Campo Minado");
        setSize(300, 300); // Ajustando o tamanho da janela para o grid menor
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Fechar somente a janela do Campo Minado
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
                                telaAnterior.setVisible(true); // Voltar para a tela anterior
                                dispose();
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
            CampoMinado jogo = new CampoMinado(null); // Null quando não há tela anterior
            jogo.setVisible(true);
        });
    }
}
