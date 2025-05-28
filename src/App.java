import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import br.com.rafael.model.Moeda;
import br.com.rafael.services.CurrencyApi;

public class App {
    public static void main(String[] args) {
        ArrayList<Moeda> moedas = new ArrayList<>();
        CurrencyApi api = new CurrencyApi();

        Moeda dolar = new Moeda("Dolar", "$", "USD");
        Moeda euro = new Moeda("Euro", "€", "EUR");
        Moeda libra = new Moeda("Libra", "£", "GBP");
        Moeda real = new Moeda("Real", "R$", "BRL");

        moedas.add(real);
        moedas.add(dolar);
        moedas.add(euro);
        moedas.add(libra);

        JFrame frame = new JFrame("Conversor de moedas");
        frame.setSize(480, 380);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBorder(new EmptyBorder(32, 32, 32, 32));

        for (Moeda moeda : moedas) {
            JPanel linha = new JPanel();
            linha.setLayout(new BoxLayout(linha, BoxLayout.X_AXIS));
            linha.setAlignmentX(JPanel.LEFT_ALIGNMENT);

            JButton botao = new JButton(moeda.getNome());
            Dimension tamanhoBotao = new Dimension(120, 40);
            botao.setPreferredSize(tamanhoBotao);
            botao.setMaximumSize(tamanhoBotao);

            botao.addActionListener(e -> {
                try {
                    System.out.println(api.converter(moeda));
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            });

            JLabel label = new JLabel(moeda.getSimbolo());
            label.setAlignmentY(Component.CENTER_ALIGNMENT);
            label.setBorder(new EmptyBorder(0, 8, 0, 4)); // espaço entre label e campo

            JTextField campoTexto = new JTextField(10);
            Dimension campoTamanho = new Dimension(campoTexto.getPreferredSize().width, 40);
            campoTexto.setPreferredSize(campoTamanho);
            campoTexto.setMaximumSize(campoTamanho);
            campoTexto.setText(moeda.getValor());
            campoTexto.setAlignmentY(Component.CENTER_ALIGNMENT);

            linha.add(botao);
            linha.add(Box.createHorizontalGlue());
            linha.add(label);
            linha.add(campoTexto);

            painel.add(linha);
            painel.add(Box.createVerticalStrut(8)); // Espaço entre linhas
        }

        JLabel instrucoes = new JLabel(
                "Digite o valor e clique na moeda correspondente para converter.",
                SwingConstants.CENTER);
        instrucoes.setFont(new Font("SansSerif", Font.ITALIC, 12));
        instrucoes.setForeground(Color.DARK_GRAY);
        instrucoes.setBorder(new EmptyBorder(10, 0, 10, 0));

        frame.add(painel, BorderLayout.CENTER);
        frame.add(instrucoes, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}