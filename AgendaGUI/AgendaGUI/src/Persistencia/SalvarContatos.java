package Persistencia;
import java.io.BufferedWriter;
import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
public class SalvarContatos {
    public static void salvarContatos(DefaultListModel modelo) { // Método para salvar os contatos no arquivo
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("agenda.txt")); // Abre o arquivo para escrita
            for (int i = 0; i < modelo.size(); i++) {
                String contato = (String) modelo.getElementAt(i); // Pega o contato na posição i
                writer.write(contato); // Escreve o contato no arquivo
                writer.newLine(); // Escreve uma nova linha
            }
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
