import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class CampoMinado extends JFrame {
    private final int NUM_MINAS = 5; // Número de minas reduzido para um grid menor
    private final int GRID_SIZE = 5; // Grid 5x5
    private Celula[][] celulas = new Celula[GRID_SIZE][GRID_SIZE];
    private JLabel statusLabel;
    private JFrame telaAnterior; // Referência para a tela anterior
    private int quadradosRevelados = 0;
    private JButton resgatarButton;

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

        // Botão para resgatar
        resgatarButton = new JButton("Resgatar");
        resgatarButton.setEnabled(false); // Desabilitado inicialmente
        resgatarButton.addActionListener(e -> {
            mostrarResultado();
            telaAnterior.setVisible(true); // Voltar para a tela anterior
            dispose();
        });
        add(resgatarButton, BorderLayout.NORTH);

        // Inicializar as células e adicionar ao painel
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                celulas[i][j] = new Celula(i, j);
                celulas[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        Celula cell = (Celula) e.getSource();
                        if (e.getButton() == MouseEvent.BUTTON1) {
                            if (!cell.isRevelado()) {
                                cell.revelar();
                                quadradosRevelados++;
                                verificarResgatar(); // Verifica se o botão Resgatar pode ser habilitado
                                if (cell.temMina()) {
                                    cell.revelar(); // Atualiza a célula para mostrar mina
                                    JOptionPane.showMessageDialog(null, "Game Over!");
                                    telaAnterior.setVisible(true); // Voltar para a tela anterior
                                    dispose();
                                }
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

    private void verificarResgatar() {
        if (quadradosRevelados >= 3) {
            resgatarButton.setEnabled(true);
        }
    }

    private void mostrarResultado() {
        int totalQuadrados = GRID_SIZE * GRID_SIZE;
        int quadradosSemMina = totalQuadrados - NUM_MINAS;
        double porcentagemRevelada = (quadradosRevelados / (double) quadradosSemMina) * 100;
        JOptionPane.showMessageDialog(null, String.format("Você ganhou %.2f%% a mais", porcentagemRevelada));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CampoMinado jogo = new CampoMinado(null); // Null quando não há tela anterior
            jogo.setVisible(true);
        });
    }

    // Classe Celula (não fornecida, mas presumida necessária para o código completo)
    private class Celula extends JButton {
        private final int linha;
        private final int coluna;
        private boolean temMina;
        private boolean revelado;
        private boolean marcado;

        public Celula(int linha, int coluna) {
            this.linha = linha;
            this.coluna = coluna;
            this.temMina = false;
            this.revelado = false;
            this.marcado = false;
        }

        public boolean temMina() {
            return temMina;
        }

        public void setMina(boolean temMina) {
            this.temMina = temMina;
        }

        public boolean isRevelado() {
            return revelado;
        }

        public void revelar() {
            this.revelado = true;
            setEnabled(false);
            if (temMina) {
                setBackground(Color.RED);
                setText("M");
            } else {
                setText("");
            }
        }

        public boolean isMarcado() {
            return marcado;
        }

        public void toggleMarca() {
            this.marcado = !this.marcado;
            setText(marcado ? "X" : ""); // Exemplo de ação de marcação
        }
    }
}