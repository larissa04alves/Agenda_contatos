package Persistencia;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
public class NovoContato {
    public static void gravarContato(String contato) { // Método para gravar um novo contato no arquivo
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("agenda.txt", true)); // Abre o arquivo para escrita
            writer.write(contato); // Escreve o contato no arquivo
            writer.newLine(); // Escreve uma nova linha
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
