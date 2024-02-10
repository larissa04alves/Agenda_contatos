package Persistencia;
import java.io.*;
import java.util.ArrayList;
public class EditarContato {
    public static void editarContato(int indice, String novoContato)  { // Método para editar um contato
        ArrayList<String> contatos = CarregarContatos.carregarContatos();

        if (indice >= 0 && indice < contatos.size()) { // Se o índice for válido
            contatos.set(indice, novoContato); // Substitui o contato antigo pelo novo
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("agenda.txt")); // Abre o arquivo para escrita
                for (String contato : contatos) {
                    writer.write(contato); // Escreve o contato no arquivo
                    writer.newLine(); // Escreve uma nova linha
                }
                writer.close(); // Fecha o arquivo
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Índice inválido.");
        }
    }
}

