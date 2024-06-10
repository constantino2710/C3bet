import javax.swing.*;
import java.awt.*;

public class Celula extends JButton {
    private int linha;
    private int coluna;
    private boolean mina;
    private boolean revelado;
    private boolean marcado;

    public Celula(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
        this.mina = false;
        this.revelado = false;
        this.marcado = false;
        setBackground(Color.LIGHT_GRAY);
    }

    public boolean temMina() {
        return mina;
    }

    public void setMina(boolean mina) {
        this.mina = mina;
    }

    public boolean isRevelado() {
        return revelado;
    }

    public void revelar() {
        if (!revelado && !marcado) {
            revelado = true;
            setBackground(Color.WHITE);
            if (mina) {
                setText("M");
                setBackground(Color.RED);
            }
        }
    }

    public boolean isMarcado() {
        return marcado;
    }

    public void toggleMarca() {
        if (!revelado) {
            marcado = !marcado;
            setText(marcado ? "F" : "");
            setBackground(marcado ? Color.YELLOW : Color.LIGHT_GRAY);
        }
    }
}
