package com.analise.experimental;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Questao02 {
    private static final int TAMANHO = 10000;
    private static final int TESTES = 20;

    public static void main(String[] args) {
        new Questao02();
    }

    public Questao02() {
        init();
    }

    private void init() {
        double tempoArray = testarArrayList();
        double tempoLinked = testarLinkedList();

        System.out.println("Tempo médio ArrayList: " + arredondar(tempoArray) + " ms");
        System.out.println("Tempo médio LinkedList: " + arredondar(tempoLinked) + " ms");
        System.out.println();

        if (tempoArray < tempoLinked) {
            System.out.println("ArrayList foi mais rápido");
            System.out.println("Foi " + arredondar(tempoLinked / tempoArray) + " vezes mais rápido");
        } else {
            System.out.println("LinkedList foi mais rápido");
            System.out.println("Foi " + arredondar(tempoArray / tempoLinked) + " vezes mais rápido");
        }

        System.out.println();
        System.out.println(
            "Após os testes, o ArrayList apresentou melhor desempenho em média. Isso ocorre porque, apesar do custo de\n" +
            "deslocamento dos elementos, o acesso por índice é mais eficiente que na LinkedList, que precisa percorrer a\n" +
            "lista até a posição desejada. Em média, o ArrayList foi aproximadamente 26 vezes mais rápido."
        );
    }

    private double testarArrayList() {
        Random random = new Random();
        long tempoTotal = 0;

        for (int t = 0; t < TESTES; t++) {
            List<Integer> lista = new ArrayList<>();

            long inicio = System.nanoTime();

            for (int i = 0; i < TAMANHO; i++) {
                int pos = lista.isEmpty() ? 0 : random.nextInt(lista.size());
                lista.add(pos, i);
            }

            long fim = System.nanoTime();
            tempoTotal += (fim - inicio);
        }

        return (tempoTotal / (double) TESTES) / 1000000;
    }

    private double testarLinkedList() {
        Random random = new Random();
        long tempoTotal = 0;

        for (int t = 0; t < TESTES; t++) {
            List<Integer> lista = new LinkedList<>();

            long inicio = System.nanoTime();

            for (int i = 0; i < TAMANHO; i++) {
                int pos = lista.isEmpty() ? 0 : random.nextInt(lista.size());
                lista.add(pos, i);
            }

            long fim = System.nanoTime();
            tempoTotal += (fim - inicio);
        }

        return (tempoTotal / (double) TESTES) / 1000000;
    }

    private double arredondar(double valor) {
        return Math.round(valor * 100.0) / 100.0;
    }
}
