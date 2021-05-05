import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ValidaCarga {
    public static void main(String[] args) throws IOException {
        List<Integer> dadosEntrada = new ArrayList<>();
        List<String> dadosSaida = new ArrayList<>();
        int custoTotal = 0;
        String temp = null;
        // Configurando para ler arquivo input
        String path = System.getProperty("user.dir") + "/arquivos/input.txt";
        FileReader fileReader = new FileReader(path);
        BufferedReader read = new BufferedReader(fileReader);

        // Configurando para escrever arquivo out
        FileWriter writer = new FileWriter(System.getProperty("user.dir") + "/arquivos/output.txt");
        PrintWriter gravarArq = new PrintWriter(writer);

        //Lendo arquivo input e inserindo numa lista
        while (read.ready()) {
            dadosEntrada.add(Integer.parseInt(read.readLine()));
        }
        read.close();

        Servidor servidor = new Servidor();
        Usuario usuario = new Usuario();

        //Validando se  1 ≤ ttask ≤ 10
        // Primeiro index da lista
        if (servidor.verificarValorTtask(dadosEntrada.get(0))) {
            usuario.setTtask(dadosEntrada.get(0));
        } else {
            System.exit(0);
        }

        //Validando se  1 ≤ umax ≤ 10
        // Segundo index da lista
        if (servidor.verificarValorUmax(dadosEntrada.get(1))) {
            servidor.setUmax(dadosEntrada.get(1));
        } else {
            System.exit(0);
        }

        //Percorrendo a lista - Terceiro index da lista
        for (int i = 2; i < dadosEntrada.size(); i++) {
            //Verificando valor ttask para remover usuario/servidor
            if (servidor.numerosDeUsuarios.size() >= usuario.getTtask()) {
                servidor.removerServidor(servidor.numerosDeUsuarios.get(0));
            }
            //Verificando se cria ou insere usuario no servidor
            servidor.verificarServidores(dadosEntrada.get(i));
            //Resultadpo sendo inserido na lista dadosSaida
            dadosSaida.add(StringBuilderFormat(servidor.getQuantidadeDeUsuarios()));
            //Somando custo total
            for (int a : servidor.getQuantidadeDeUsuarios()) {
                custoTotal++;
            }
        }
        //Após verificação dos dados input.txt liberando os servidores que ainda estão ligados
        for (int x = 0; x < servidor.getQuantidadeDeUsuarios().hashCode(); x++) {
            if (servidor.numerosDeUsuarios.size() >= usuario.getTtask()) {
                servidor.removerServidor(servidor.numerosDeUsuarios.get(0));
            }
            dadosSaida.add(StringBuilderFormat(servidor.getQuantidadeDeUsuarios()));
            servidor.numerosDeUsuarios.add(x);
            for (int a : servidor.getQuantidadeDeUsuarios()) {
                custoTotal++;
            }
        }

        //Escrevendo informações no arquivo output.txt
        for (String saida : dadosSaida) {
            gravarArq.println(saida.isEmpty() ? "0" : saida);
        }
        //Escrevendo valor tatol no arquivo output.txt
        gravarArq.println(custoTotal);
        writer.close();
    }

    public static String StringBuilderFormat(List<Integer> quantidadeDeUsuarios) {
        StringBuilder sb = new StringBuilder();
        sb.append(quantidadeDeUsuarios + "");
        return sb.toString().replaceAll("[\\[\\]]", "");
    }
}
