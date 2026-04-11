package com.analise.experimental;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Questao05 {
    private static final int TAMANHO = 10000;
    private static final int TESTES = 20;
    private static final int ACESSOS = 10000;

    public static void main(String[] args) {
        new Questao05();
    }

    public Questao05() {
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
            "O ArrayList foi significativamente mais rápido nos acessos aleatórios, pois permite acesso direto\n" +
            "por índice (tempo constante O(1)). Já o LinkedList precisa percorrer a lista até a posição\n" +
            "desejada (tempo O(n)), o que torna os acessos muito mais lentos."
        );
    }

    private double testarArrayList() {
        Random random = new Random();
        long tempoTotal = 0;

        for (int t = 0; t < TESTES; t++) {
            List<Integer> lista = new ArrayList<>();

            for (int i = 0; i < TAMANHO; i++) {
                lista.add(i);
            }

            long inicio = System.nanoTime();

            for (int i = 0; i < ACESSOS; i++) {
                int pos = random.nextInt(lista.size());
                lista.get(pos);
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

            for (int i = 0; i < TAMANHO; i++) {
                lista.add(i);
            }

            long inicio = System.nanoTime();

            for (int i = 0; i < ACESSOS; i++) {
                int pos = random.nextInt(lista.size());
                lista.get(pos);
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
