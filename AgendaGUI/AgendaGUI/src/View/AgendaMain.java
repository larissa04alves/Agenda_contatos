package View;
import Persistencia.CarregarContatos;
import Persistencia.NovoContato;
import Persistencia.SalvarContatos;
import Persistencia.EditarContato;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Color;

public class AgendaMain extends JFrame {
    public static AbstractButton lista; // Lista de contatos
    ArrayList <String> Contato = new ArrayList<>();

    public AgendaMain(){
        JPanel p1 = new JPanel(null); // Cria um painel
        JLabel lTitulo = new JLabel("Agenda de contatos"); // Cria um rótulo
        JLabel lNome = new JLabel("Nome:");
        JLabel lEmail = new JLabel("Email:");
        JLabel lTelefone = new JLabel("Fone:");
        JButton bGravar = new JButton("Gravar"); // Cria um botão
        JButton bDeletar = new JButton("Deletar");
        JButton bAlterar = new JButton("Alterar");
        JButton bLimpar = new JButton("Limpar");
        JButton bSair = new JButton("Sair");

        JTextField tNome = new JTextField(20); // Cria uma caixa de texto
        JTextField tEmail = new JTextField(20);
        JTextField tTelefone = new JTextField(20);

        DefaultListModel modelo = new DefaultListModel(); // Cria um modelo de lista
        JList lista = new JList(); // Cria uma lista
        JScrollPane scroll = new JScrollPane(lista); // Cria uma barra de rolagem para a lista
        lista.setModel(new DefaultListModel()); // Define o modelo da lista
        CarregarContatos carregarContatos = new CarregarContatos(); // Cria um objeto para carregar os contatos

        lTitulo.setFont(new Font("Dialog", Font.PLAIN, 20)); // Define a fonte do título
        lTitulo.setForeground(Color.BLUE); // Define a cor do título


        p1.add(lTitulo); // Adiciona o rótulo ao painel
        p1.add(lNome);
        p1.add(tNome); // Adiciona a caixa de texto ao painel
        p1.add(lEmail);
        p1.add(tEmail);
        p1.add(lTelefone);
        p1.add(tTelefone);
        p1.add(bGravar); // Adiciona o botão ao painel
        p1.add(bDeletar);
        p1.add(bAlterar);
        p1.add(bLimpar);
        p1.add(bSair);
        p1.add(scroll); // Adiciona a barra de rolagem ao painel

        add(p1); // Adiciona o painel à janela
        p1.setBackground(new Color(137, 180, 246, 23)); // Define a cor de fundo do painel

        lista.setModel(modelo); // Define o modelo da lista

        ArrayList<String> contatos = CarregarContatos.carregarContatos(); // Carrega os contatos do arquivo
        for (String contato : contatos) {
            modelo.addElement(contato); // Adiciona os contatos ao modelo da lista
        }

        lTitulo.setBounds(140, 20, 200, 80); // Define a posição e o tamanho do rótulo
        lNome.setBounds(40, 130, 100, 25);
        tNome.setBounds(90, 130, 350, 25); // Define a posição e o tamanho da caixa de texto
        lTelefone.setBounds(40, 180, 100, 25);
        tTelefone.setBounds(90, 180, 350, 25);
        lEmail.setBounds(40, 230, 100, 25);
        tEmail.setBounds(90, 230, 350, 25);

        bGravar.setBounds(60, 330, 100, 23); // Define a posição e o tamanho do botão
        bDeletar.setBounds(200, 330, 100, 23);
        bAlterar.setBounds(340, 330, 100, 23);
        bLimpar.setBounds(60, 370, 100, 23);
        bSair.setBounds(200, 370, 100, 23);
        scroll.setBounds(540, 90, 420, 300); // Define a posição e o tamanho da barra de rolagem


        setSize(1010, 500); // Define o tamanho da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define a ação padrão ao fechar a janela
        setVisible(true); // Define a janela como visível

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // Pega o tamanho da tela
        int centerX = (screenSize.width - 1010) / 2; // Calcula a posição horizontal da janela
        int centerY = (screenSize.height - 500) / 2; // Calcula a posição vertical da janela
        setLocation(centerX, centerY); // Define a posição da janela no centro da tela

        //AÇÕES DOS BOTÕES

        bGravar.addActionListener(new ActionListener() { // Gravar um novo contato
            @Override
            public void actionPerformed(ActionEvent e) {
                String novoContato = "[" + tNome.getText() + "]" + " - " + "[" + tTelefone.getText() + "]" + " - " + "[" + tEmail.getText() + "]";  // Cria um novo contato
                NovoContato.gravarContato(novoContato); // Grava o novo contato no arquivo
                modelo.addElement(novoContato); // Adiciona o novo contato ao modelo da lista
            }
        });

        bAlterar.addActionListener(new ActionListener() {  // Alterar um contato
            @Override
            public void actionPerformed(ActionEvent e) {
                int indiceSelecionado = lista.getSelectedIndex(); // Pega o índice do contato selecionado
                if (indiceSelecionado != -1) { // Se um contato foi selecionado
                    String novoContato = "[" + tNome.getText() + "]" + " - " + "[" + tTelefone.getText() + "]" + " - " + "[" + tEmail.getText() + "]"; // Cria um novo contato
                    EditarContato.editarContato(indiceSelecionado, novoContato); // Edita o contato no arquivo

                    DefaultListModel modelo = (DefaultListModel) lista.getModel(); // Pega o modelo da lista
                    modelo.setElementAt(novoContato, indiceSelecionado); // Substitui o contato antigo pelo novo
                }
            }
        });

        bDeletar.addActionListener(new ActionListener() { // Deletar um contato
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = lista.getSelectedIndex(); // Pega o índice do contato selecionado
                if (selectedIndex != -1) { // Se um contato foi selecionado
                    DefaultListModel modelo = (DefaultListModel) lista.getModel(); // Pega o modelo da lista
                    modelo.remove(selectedIndex); // Remove o contato do modelo da lista

                    SalvarContatos.salvarContatos(modelo); // Salva os contatos no arquivo
                }
            }
        });

        bLimpar.addActionListener(new ActionListener() { // Limpar os campos
            @Override
            public void actionPerformed(ActionEvent e){
            tNome.setText("");
            tTelefone.setText("");
            tEmail.setText("");
        }
    });

        bSair.addActionListener(new ActionListener() { // Sair do programa
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Fecha o programa
            }
        });

        this.addWindowListener(new WindowAdapter() { // Salvar os contatos ao fechar o programa
            @Override
            public void windowClosing(WindowEvent e) {
                SalvarContatos.salvarContatos(modelo);  // Salva os contatos no arquivo
            }
        });
    }


    public static void main (String args[]){
        new AgendaMain(); // Cria um objeto da classe AgendaMain
    }
}
