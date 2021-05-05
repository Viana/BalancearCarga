import java.util.*;

public class Servidor {
    private int umax;
    public List<Integer> numerosDeUsuarios = new ArrayList<>();
    public List<Integer> quantidadeDeUsuarios = new ArrayList<>();

    public void verificarServidores(int quantUser) {

        numerosDeUsuarios.add(quantUser);
        for (int i = 0; i < quantUser; i++) {
            if (quantidadeDeUsuarios.size() == 0) {
                addServidor();
            } else if (quantidadeDeUsuarios.get(quantidadeDeUsuarios.size() - 1) == getUmax()) {
                addServidor();
            } else {
                atualizarServidor(quantidadeDeUsuarios.size() - 1, quantidadeDeUsuarios.get(quantidadeDeUsuarios.size() - 1) + 1);
            }
        }
    }

    public void addServidor() {
        try {
            quantidadeDeUsuarios.add(1);
        } catch (IndexOutOfBoundsException e) {
            quantidadeDeUsuarios.add(1);
        }
    }

    public void atualizarServidor(int index, int valor) {
        quantidadeDeUsuarios.set(index, valor);
    }

    public void removerServidor(int index) {
        for (int i = 0; i < index; i++) {
            atualizarServidor(0, quantidadeDeUsuarios.get(0) - 1);
            if (quantidadeDeUsuarios.get(0) == 0) {
                quantidadeDeUsuarios.remove(0);
            }
        }
        numerosDeUsuarios.remove(0);
    }

    public boolean verificarValorTtask(int valorTtask) {
        boolean flagTtask = true;
        if (valorTtask < 1 || valorTtask > 10) {
            System.out.println("Valor de 'ttask' informado: " + valorTtask);
            System.out.println("Atenção !!!\nValor 'ttask' deve estar entre os valores '1' e '10' !\n");
            flagTtask = false;
        }
        return flagTtask;
    }

    public boolean verificarValorUmax(int valorUmax) {
        boolean flagUmax = true;
        if (valorUmax < 1 || valorUmax > 10) {
            System.out.println("Valor de 'umax' informado: " + valorUmax);
            System.out.println("Atenção !!!\nValor 'umax' deve estar entre os valores '1' e '10' !\n");
            flagUmax = false;
        }
        return flagUmax;
    }

    public int getUmax() {
        return umax;
    }

    public void setUmax(int umax) {
        this.umax = umax;
    }

    public List<Integer> getQuantidadeDeUsuarios() {
        return quantidadeDeUsuarios;
    }
}
