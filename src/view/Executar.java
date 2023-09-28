package view;

import controller.ThreadCarro;

import java.util.concurrent.Semaphore;

public class Executar {
    public static void main(String[] args) {
        int sentidosPorCruzamento = 1;
        Semaphore semaforo = new Semaphore(sentidosPorCruzamento);

        ThreadCarro[] carros = new ThreadCarro[4];
        for (int i = 0; i < carros.length; i++) {
            carros[i] = new ThreadCarro(i, i, semaforo);
            carros[i].start();
        }
    }
}
