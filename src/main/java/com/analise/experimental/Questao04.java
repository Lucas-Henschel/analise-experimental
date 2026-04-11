package com.analise.experimental;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Questao04 {

	private static final int ELEMENTOS_POR_TESTE = 10000;
	private static final int TESTES_COMPARATIVO = 20;
	private static final double NANO_PARA_MS = 1_000_000.0;

	public static void main(String[] args) {
		new Questao04();
	}

	public Questao04() {
		init();
	}

	private void init() {
		double mediaArrayList = testarArrayListRemocaoAleatoria();
		double mediaLinkedList = testarLinkedListRemocaoAleatoria();
		exibirResultadoComparativo(mediaArrayList, mediaLinkedList);
	}

	private double testarArrayListRemocaoAleatoria() {
		Random random = new Random();
		double somaMs = 0;

		for (int t = 0; t < TESTES_COMPARATIVO; t++) {
			List<Integer> lista = new ArrayList<>();
			preencherLista(lista);
			long inicio = System.nanoTime();
			esvaziarListaAleatoriamente(lista, random);
				somaMs += (System.nanoTime() - inicio) / NANO_PARA_MS;
		}

		return somaMs / TESTES_COMPARATIVO;
	}

	private double testarLinkedListRemocaoAleatoria() {
		Random random = new Random();
		double somaMs = 0;

		for (int t = 0; t < TESTES_COMPARATIVO; t++) {
			List<Integer> lista = new LinkedList<>();
			preencherLista(lista);
			long inicio = System.nanoTime();
			esvaziarListaAleatoriamente(lista, random);
			somaMs += (System.nanoTime() - inicio) / NANO_PARA_MS;
		}

		return somaMs / TESTES_COMPARATIVO;
	}

	private void preencherLista(List<Integer> lista) {
		for (int i = 0; i < ELEMENTOS_POR_TESTE; i++) {
			lista.add(i);
		}
	}

	private void esvaziarListaAleatoriamente(List<Integer> lista, Random random) {
		while (!lista.isEmpty()) {
			int index = random.nextInt(lista.size());
			lista.remove(index);
		}
	}

	private void exibirResultadoComparativo(double mediaArray, double mediaLinked) {
		boolean arrayMaisRapido = mediaArray < mediaLinked;
		String maisRapida = arrayMaisRapido ? "ArrayList" : "LinkedList";
		double fator = arrayMaisRapido ? mediaLinked / mediaArray : mediaArray / mediaLinked;

		System.out.println("===== QUESTAO 4 - REMOCAO DE INDICES ALEATORIOS =====");
		System.out.printf("Elementos por teste: %,d%n", ELEMENTOS_POR_TESTE);
		System.out.printf("Quantidade de testes: %d%n%n", TESTES_COMPARATIVO);

		System.out.printf("Media ArrayList : %.3f ms%n", mediaArray);
		System.out.printf("Media LinkedList: %.3f ms%n%n", mediaLinked);

		System.out.printf("Mais rapida: %s%n", maisRapida);
		System.out.printf("Fator de velocidade: %.2fx%n%n", fator);
        System.out.printf("Motivo: na LinkedList, é necessário percorrer a lista passo a passo até encontrar a posição do elemento. " +
            "Já no ArrayList, o acesso é direto pelo índice, o que torna a remoção mais rápida.%n");	
    }
}