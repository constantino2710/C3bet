import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;

public class C3bet extends JFrame {
    private JLabel imagemLabel;

    public C3bet() {
        super();

        this.getContentPane().setLayout(null);
        this.setSize(1600, 800);
        this.setTitle("br.C3bet.com");

        JLabel login = new JLabel();
        login.setText("Login");
        login.setBounds(1062, 287, 100, 80);
        login.setFont(new Font("Arial", Font.PLAIN, 40));
        login.setForeground(new Color(255, 107, 0));
        this.getContentPane().add(login);

        JLabel sigIn = new JLabel();
        sigIn.setText("Sign in to continue");
        sigIn.setBounds(1052, 351, 180, 20);
        sigIn.setFont(new Font("Arial", Font.PLAIN, 15));
        sigIn.setForeground(new Color(255, 107, 0));
        this.getContentPane().add(sigIn);

        JLabel usuario = new JLabel();
        usuario.setText("CPF ou E-mail:");
        usuario.setBounds(1000, 400, 135, 20);
        usuario.setFont(new Font("Arial", Font.PLAIN, 15));
        usuario.setForeground(new Color(255, 107, 0));
        this.getContentPane().add(usuario);

        JTextField campoUsuario = new JTextField();
        campoUsuario.setBounds(1000, 420, 225, 27);
        campoUsuario.setFont(new Font("Helvetica", Font.PLAIN, 16));
        this.getContentPane().add(campoUsuario);

        JLabel senha = new JLabel();
        senha.setText("Senha:");
        senha.setBounds(1000, 462, 135, 20);
        senha.setFont(new Font("Arial", Font.PLAIN, 15));
        senha.setForeground(new Color(255, 107, 0));
        this.getContentPane().add(senha);

        JPasswordField campoSenha = new JPasswordField();
        campoSenha.setBounds(1000, 482, 225, 27);
        campoSenha.setFont(new Font("Helvetica", Font.PLAIN, 16));
        this.getContentPane().add(campoSenha);

        JButton entrar = new JButton();
        entrar.setText("Entrar");
        entrar.setBounds(1025, 533, 170, 30);
        entrar.setBackground(new Color(255, 107, 0));
        entrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = campoUsuario.getText();
                String senha = new String(campoSenha.getPassword());
                if (!usuario.isEmpty() && !senha.isEmpty()) {
                    if (validarCredenciais(usuario, senha)) {
                        new UserPage(usuario, senha);
                        dispose(); 
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
                }
            }
        });
        this.getContentPane().add(entrar);

        JButton criarConta = new JButton();
        criarConta.setText("Criar Conta");
        criarConta.setBounds(1025, 573, 170, 30);
        criarConta.setBackground(new Color(255, 107, 0));
        criarConta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CriarContaFrame();
            }
        });
        this.getContentPane().add(criarConta);

        JButton esqueciSenha = new JButton();
        esqueciSenha.setText("Esqueci a Senha");
        esqueciSenha.setBounds(1025, 613, 170, 30);
        esqueciSenha.setBackground(new Color(255, 107, 0));
        esqueciSenha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EsqueciSenhaFrame();
            }
        });
        this.getContentPane().add(esqueciSenha);

        String imagePath = "C:/Users/joaoc/Desktop/Códigos/faculdade/C3bet (2)/C3bet/C3bet/assets/LoginBet (6).png";
        File file = new File(imagePath);
        if (file.exists()) {
            ImageIcon icon = new ImageIcon(imagePath);
            int width = 1600;
            int height = 800;
            Image resizedImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
            ImageIcon resizedIcon = new ImageIcon(resizedImage);
            imagemLabel = new JLabel();
            imagemLabel.setIcon(resizedIcon);
            imagemLabel.setBounds(0, 0, width, height);
            this.getContentPane().add(imagemLabel);
            this.getContentPane().setComponentZOrder(imagemLabel, this.getContentPane().getComponentCount() - 1);
        } else {
            System.err.println("O arquivo de imagem não foi encontrado em: " + imagePath);
        }

        this.setVisible(true);
    }

    private boolean validarCredenciais(String usuario, String senha) {
        try (BufferedReader reader = new BufferedReader(new FileReader("dados.txt"))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.contains("Usuário: " + usuario + ", Senha: " + senha)) {
                    return true;
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao validar as credenciais: " + ex.getMessage());
            ex.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        C3bet frame = new C3bet();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

class CriarContaFrame extends JFrame {
    public CriarContaFrame() {
        super();

        this.getContentPane().setLayout(null);
        this.setSize(400, 300);
        this.setTitle("Criar Conta");

        JLabel usuario = new JLabel();
        usuario.setText("CPF ou E-mail:");
        usuario.setBounds(50, 50, 135, 20);
        usuario.setFont(new Font("Arial", Font.PLAIN, 15));
        this.getContentPane().add(usuario);

        JTextField campoUsuario = new JTextField();
        campoUsuario.setBounds(50, 70, 250, 27);
        campoUsuario.setFont(new Font("Helvetica", Font.PLAIN, 16));
        this.getContentPane().add(campoUsuario);

        JLabel senha = new JLabel();
        senha.setText("Senha:");
        senha.setBounds(50, 110, 135, 20);
        senha.setFont(new Font("Arial", Font.PLAIN, 15));
        this.getContentPane().add(senha);

        JPasswordField campoSenha = new JPasswordField();
        campoSenha.setBounds(50, 130, 250, 27);
        campoSenha.setFont(new Font("Helvetica", Font.PLAIN, 16));
        this.getContentPane().add(campoSenha);

        JButton criar = new JButton();
        criar.setText("Criar Conta");
        criar.setBounds(100, 180, 170, 30);
        criar.setBackground(new Color(255, 107, 0));
        criar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = campoUsuario.getText();
                String senha = new String(campoSenha.getPassword());
                if (!usuario.isEmpty() && !senha.isEmpty()) {
                    salvarDados(usuario, senha);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
                }
            }
        });
        this.getContentPane().add(criar);

        this.setVisible(true);
    }

    private void salvarDados(String usuario, String senha) {
        try {
            FileWriter writer = new FileWriter("dados.txt", true);
            writer.write("Usuário: " + usuario + ", Senha: " + senha + "\n");
            writer.close();
            JOptionPane.showMessageDialog(null, "Conta criada com sucesso!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar os dados: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}

class EsqueciSenhaFrame extends JFrame {
    public EsqueciSenhaFrame() {
        super();

        this.getContentPane().setLayout(null);
        this.setSize(400, 300);
        this.setTitle("Esqueci a Senha");

        JLabel usuario = new JLabel();
        usuario.setText("CPF ou E-mail:");
        usuario.setBounds(50, 50, 135, 20);
        usuario.setFont(new Font("Arial", Font.PLAIN, 15));
        this.getContentPane().add(usuario);

        JTextField campoUsuario = new JTextField();
        campoUsuario.setBounds(50, 70, 250, 27);
        campoUsuario.setFont(new Font("Helvetica", Font.PLAIN, 16));
        this.getContentPane().add(campoUsuario);

        JLabel novaSenha = new JLabel();
        novaSenha.setText("Nova Senha:");
        novaSenha.setBounds(50, 110, 135, 20);
        novaSenha.setFont(new Font("Arial", Font.PLAIN, 15));
        this.getContentPane().add(novaSenha);

        JPasswordField campoNovaSenha = new JPasswordField();
        campoNovaSenha.setBounds(50, 130, 250, 27);
        campoNovaSenha.setFont(new Font("Helvetica", Font.PLAIN, 16));
        this.getContentPane().add(campoNovaSenha);

        JButton atualizar = new JButton();
        atualizar.setText("Atualizar");
        atualizar.setBounds(100, 180, 170, 30);
        atualizar.setBackground(new Color(255, 107, 0));
        atualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = campoUsuario.getText();
                String novaSenha = new String(campoNovaSenha.getPassword());
                atualizarSenha(usuario, novaSenha);
            }
        });
        this.getContentPane().add(atualizar);

        this.setVisible(true);
    }

    private void atualizarSenha(String usuario, String novaSenha) {
        File inputFile = new File("dados.txt");
        File tempFile = new File("dados_temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;
            boolean userFound = false;

            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.contains("Usuário: " + usuario + ",")) {
                    writer.write("Usuário: " + usuario + ", Senha: " + novaSenha + "\n");
                    userFound = true;
                } else {
                    writer.write(currentLine + "\n");
                }
            }

            writer.close();
            reader.close();

            if (inputFile.delete()) {
                tempFile.renameTo(inputFile);
                if (userFound) {
                    JOptionPane.showMessageDialog(null, "Senha atualizada com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário não encontrado!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao atualizar a senha.");
            }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar a senha: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}

class UserPage extends JFrame {
    public UserPage(String usuario, String senha) {
        super();

        this.getContentPane().setLayout(null);
        this.setSize(1600, 800);
        this.setTitle("User Page");

        JLabel bemVindo = new JLabel();
        bemVindo.setText("Bem-vindo, " + usuario);
        bemVindo.setBounds(600, 50, 400, 50);
        bemVindo.setFont(new Font("Arial", Font.PLAIN, 30));
        this.getContentPane().add(bemVindo);

        JButton deletarConta = new JButton();
        deletarConta.setText("Deletar Conta");
        deletarConta.setBounds(725, 400, 150, 30);
        deletarConta.setBackground(new Color(255, 107, 0));
        deletarConta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeletarContaFrame(usuario);
                dispose();
            }
        });
        this.getContentPane().add(deletarConta);

        String imagePath = "C:/Users/joaoc/Desktop/Códigos/faculdade/C3bet (2)/C3bet/C3bet/assets/22392977-escada-ceu-paraiso-espiritual-luz-solar-gerar-ai-foto.jpg";
        File file = new File(imagePath);
        if (file.exists()) {
            ImageIcon icon = new ImageIcon(imagePath);
            int width = 1600;
            int height = 800;
            Image resizedImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
            ImageIcon resizedIcon = new ImageIcon(resizedImage);
            JLabel imagemLabel = new JLabel();
            imagemLabel.setIcon(resizedIcon);
            imagemLabel.setBounds(0, 0, width, height);
            this.getContentPane().add(imagemLabel);
            this.getContentPane().setComponentZOrder(imagemLabel, this.getContentPane().getComponentCount() - 1);
        } else {
            System.err.println("O arquivo de imagem não foi encontrado em: " + imagePath);
        }

        this.setVisible(true);
    }
}

class DeletarContaFrame extends JFrame {
    private String usuarioAtual;

    public DeletarContaFrame(String usuarioAtual) {
        super();
        this.usuarioAtual = usuarioAtual;

        this.getContentPane().setLayout(null);
        this.setSize(400, 300);
        this.setTitle("Deletar Conta");

        DefaultListModel<String> listModel = new DefaultListModel<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("dados.txt"))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String[] parts = currentLine.split(", ");
                if (parts.length > 0) {
                    String usuario = parts[0].replace("Usuário: ", "");
                    listModel.addElement(usuario);
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar os dados: " + ex.getMessage());
            ex.printStackTrace();
        }

        JList<String> userList = new JList<>(listModel);
        userList.setBounds(50, 50, 300, 150);
        this.getContentPane().add(userList);

        JButton deletar = new JButton();
        deletar.setText("Deletar");
        deletar.setBounds(150, 220, 100, 30);
        deletar.setBackground(new Color(255, 107, 0));
        deletar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedUser = userList.getSelectedValue();
                if (selectedUser != null) {
                    String senha = JOptionPane.showInputDialog(null, "Digite a senha para deletar a conta de " + selectedUser + ":");
                    if (senha != null) {
                        if (validarSenha(selectedUser, senha)) {
                            deletarConta(selectedUser);
                            JOptionPane.showMessageDialog(null, "Conta deletada com sucesso!");
                            new C3bet();
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Senha incorreta!");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um usuário para deletar.");
                }
            }
        });
        this.getContentPane().add(deletar);

        this.setVisible(true);
    }

    private boolean validarSenha(String usuario, String senha) {
        try (BufferedReader reader = new BufferedReader(new FileReader("dados.txt"))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.contains("Usuário: " + usuario + ", Senha: " + senha)) {
                    return true;
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao validar a senha: " + ex.getMessage());
            ex.printStackTrace();
        }
        return false;
    }

    private void deletarConta(String usuario) {
        File inputFile = new File("dados.txt");
        File tempFile = new File("dados_temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                if (!currentLine.contains("Usuário: " + usuario + ",")) {
                    writer.write(currentLine + "\n");
                }
            }

            writer.close();
            reader.close();

            if (inputFile.delete()) {
                tempFile.renameTo(inputFile);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao deletar a conta.");
            }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar a conta: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
