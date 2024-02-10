package Persistencia;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
public class CarregarContatos {
    public static ArrayList<String> carregarContatos() { // Método para carregar os contatos do arquivo
        ArrayList<String> contatos = new ArrayList<>(); // Cria um ArrayList para armazenar os contatos
        try {
            BufferedReader reader = new BufferedReader(new FileReader("agenda.txt")); // Abre o arquivo para leitura
            String linha;
            while ((linha = reader.readLine()) != null) { // Lê uma linha do arquivo
                    contatos.add(linha); // Adiciona a linha ao ArrayList
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contatos;
    }
}
