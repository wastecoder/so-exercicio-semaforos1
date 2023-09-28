package controller;

import java.util.concurrent.Semaphore;

public class ThreadCarro extends Thread {
    private int id;
    private int sentido;
    private Semaphore semaforo;

    public ThreadCarro(int id, int sentido, Semaphore semaforo) {
        this.id = id;
        this.sentido = sentido;
        this.semaforo = semaforo;
    }

    @Override
    public void run() {
        irAteCruzamento();

        try {
            semaforo.acquire();
            atravessarCruzamento();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaforo.release();
        }
    }

    public String exibirSentidoAtual() {
        String sentidoAtual = switch (sentido) {
            case 0 -> "norte a sul";
            case 1 -> "sul a norte";
            case 2 -> "leste a oeste";
            case 3 -> "oeste a leste";
            default -> "ERRO!";
        };

        return sentidoAtual;
    }

    public void irAteCruzamento() {
        System.out.println("#"  + id + " >>> chegou no cruzamento");
    }

    public void atravessarCruzamento() {
        final int TEMPO_PARA_ATRAVESSAR_CRUZAMENTO = 2000;
        System.out.println("#" + id + " >>> esta passando no sentido "
                + exibirSentidoAtual() + ", levara " + TEMPO_PARA_ATRAVESSAR_CRUZAMENTO/1000 + "s");

        try {
            sleep(TEMPO_PARA_ATRAVESSAR_CRUZAMENTO);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
