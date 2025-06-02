import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import br.com.rafael.model.Moeda;
import br.com.rafael.services.CurrencyApi;
import br.com.rafael.view.Separador;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        ArrayList<Moeda> moedas = new ArrayList<>();
        CurrencyApi api = new CurrencyApi();

        Moeda dolar = new Moeda("Dolar", "$", "USD", api);
        Moeda euro = new Moeda("Euro", "€", "EUR", api);
        Moeda libra = new Moeda("Libra", "£", "GBP", api);
        Moeda real = new Moeda("Real", "R$", "BRL", api);
        Moeda peso = new Moeda("Peso Argentino", "$", "ARS", api);
        Moeda dolarCanadense = new Moeda("Dolar Canadense", "$", "CAD", api);
        Moeda francoSuico = new Moeda("Franco Suico", "Fr", "CHF", api);

        moedas.add(real);
        moedas.add(dolar);
        moedas.add(euro);
        moedas.add(libra);
        moedas.add(peso);
        moedas.add(dolarCanadense);
        moedas.add(francoSuico);

        JFrame frame = new JFrame("Conversor de moedas");
        frame.setSize(540, 460);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBorder(new EmptyBorder(32, 32, 32, 32));

        Map<Moeda, JTextField> campos = new HashMap<>();

        for (Moeda moeda : moedas) {
            JPanel linha = new JPanel();
            linha.setLayout(new BoxLayout(linha, BoxLayout.X_AXIS));
            linha.setAlignmentX(JPanel.LEFT_ALIGNMENT);

            JButton botao = new JButton(moeda.getNome());
            Dimension tamanhoBotao = new Dimension(120, 40);
            botao.setPreferredSize(tamanhoBotao);
            botao.setMaximumSize(tamanhoBotao);

            JLabel label = new JLabel(moeda.getSimbolo());
            label.setAlignmentY(Component.CENTER_ALIGNMENT);
            label.setBorder(new EmptyBorder(0, 8, 0, 4));

            JTextField campoTexto = new JTextField(10);
            Dimension campoTamanho = new Dimension(campoTexto.getPreferredSize().width, 40);
            campoTexto.setPreferredSize(campoTamanho);
            campoTexto.setMaximumSize(campoTamanho);
            campoTexto.setText(String.valueOf(moeda.getValor()));
            campoTexto.setAlignmentY(Component.CENTER_ALIGNMENT);

            Separador separador = new Separador();
            separador.setAlignmentY(Component.CENTER_ALIGNMENT);
            separador.setOpaque(false);
            separador.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

            campos.put(moeda, campoTexto);

            botao.addActionListener(_ -> {
                try {
                    String texto = campoTexto.getText().trim().replace(",", ".");

                    if (texto.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Campo vazio. Por favor, Digite um valor válido.");
                        return;
                    }

                    double valor = Double.parseDouble(texto);
                    moeda.setValor(valor);
                    api.converter(moeda);

                    for (Moeda m : campos.keySet()) {
                        JTextField campo = campos.get(m);
                        campo.setText(String.format("%.2f", (m.getValor())));
                    }

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Valor inválido: " + campoTexto.getText());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro ao consultar a API: " + e.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro ao conectar a API: " + e.getMessage());
                }
            });

            linha.add(botao);
            linha.add(Box.createHorizontalStrut(8));
            linha.add(separador);
            linha.add(Box.createHorizontalStrut(8));
            linha.add(label);
            linha.add(campoTexto);

            painel.add(linha);
            painel.add(Box.createVerticalStrut(8));
        }

        JLabel instrucoes = new JLabel(
                "<html><div style='width: 416px; text-align: center; font-size: 10px;'>Digite o valor e clique na moeda correspondente para converter. Ao clicar na moeda que deseja converter, automaticamente, o valor que você digitou será convertido para todas as outras moedas.</div></html>",
                SwingConstants.CENTER);
        instrucoes.setFont(new Font("SansSerif", Font.ITALIC, 12));
        instrucoes.setForeground(Color.DARK_GRAY);
        instrucoes.setBorder(new EmptyBorder(10, 0, 10, 0));

        frame.add(painel, BorderLayout.CENTER);
        frame.add(instrucoes, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}