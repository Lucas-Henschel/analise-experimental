package com.analise.experimental;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.IntFunction;

public class Questao03 {

	private static final int ELEMENTOS_POR_TESTE = 10000;
	private static final int TESTES_COMPARATIVO = 20;
	private static final double NANO_PARA_MS = 1_000_000.0;

	public static void main(String[] args) {
		aquecerJVM(3);

		double mediaArrayListInicio = mediaBenchmarkRemocaoInicio(TESTES_COMPARATIVO, c -> new ArrayList<>(ELEMENTOS_POR_TESTE), ELEMENTOS_POR_TESTE);
		double mediaLinkedListInicio = mediaBenchmarkRemocaoInicio(TESTES_COMPARATIVO, c -> new LinkedList<>(), ELEMENTOS_POR_TESTE);
		exibirResultadoRemocaoInicio(mediaArrayListInicio, mediaLinkedListInicio);

		System.out.println();

		double mediaArrayListFim = mediaBenchmarkRemocaoFim(TESTES_COMPARATIVO, c -> new ArrayList<>(ELEMENTOS_POR_TESTE), ELEMENTOS_POR_TESTE);
		double mediaLinkedListFim = mediaBenchmarkRemocaoFim(TESTES_COMPARATIVO, c -> new LinkedList<>(), ELEMENTOS_POR_TESTE);
		exibirResultadoRemocaoFim(mediaArrayListFim, mediaLinkedListFim);
	}

	private static void aquecerJVM(int repeticoes) {
		for (int i = 0; i < repeticoes; i++) {
			preencherERemoverInicio(c -> new ArrayList<>(ELEMENTOS_POR_TESTE), ELEMENTOS_POR_TESTE);
			preencherERemoverInicio(c -> new LinkedList<>(), ELEMENTOS_POR_TESTE);
			preencherERemoverFim(c -> new ArrayList<>(ELEMENTOS_POR_TESTE), ELEMENTOS_POR_TESTE);
			preencherERemoverFim(c -> new LinkedList<>(), ELEMENTOS_POR_TESTE);
		}
	}

	private static double mediaBenchmarkRemocaoInicio(int testes, IntFunction<List<Integer>> fabrica, int quantidade) {
		double somaMs = 0;
		for (int i = 0; i < testes; i++) {
			somaMs += preencherERemoverInicio(fabrica, quantidade);
		}
		return somaMs / testes;
	}

	private static double mediaBenchmarkRemocaoFim(int testes, IntFunction<List<Integer>> fabrica, int quantidade) {
		double somaMs = 0;
		for (int i = 0; i < testes; i++) {
			somaMs += preencherERemoverFim(fabrica, quantidade);
		}
		return somaMs / testes;
	}

	private static double preencherERemoverInicio(IntFunction<List<Integer>> fabrica, int quantidade) {
		List<Integer> lista = fabrica.apply(quantidade);
		
		for (int i = 0; i < quantidade; i++) {
			lista.add(i);
		}

		long inicio = System.nanoTime();
		while (!lista.isEmpty()) {
			lista.remove(0);
		}
		return (System.nanoTime() - inicio) / NANO_PARA_MS;
	}

	private static double preencherERemoverFim(IntFunction<List<Integer>> fabrica, int quantidade) {
		List<Integer> lista = fabrica.apply(quantidade);
		
		for (int i = 0; i < quantidade; i++) {
			lista.add(i);
		}

		long inicio = System.nanoTime();
		while (!lista.isEmpty()) {
			lista.remove(lista.size() - 1);
		}
		return (System.nanoTime() - inicio) / NANO_PARA_MS;
	}

	private static void exibirResultadoRemocaoInicio(double mediaArray, double mediaLinked) {
		boolean arrayMaisRapido = mediaArray < mediaLinked;
		String maisRapida = arrayMaisRapido ? "ArrayList" : "LinkedList";
		double fator = arrayMaisRapido ? mediaLinked / mediaArray : mediaArray / mediaLinked;

		System.out.println("===== QUESTAO 3.1 - REMOCAO DO PRIMEIRO ELEMENTO =====");
		System.out.printf("Elementos por teste: %,d%n", ELEMENTOS_POR_TESTE);
		System.out.printf("Quantidade de testes: %d%n%n", TESTES_COMPARATIVO);

		System.out.printf("Media ArrayList : %.3f ms%n", mediaArray);
		System.out.printf("Media LinkedList: %.3f ms%n%n", mediaLinked);

		System.out.printf("Mais rapida: %s%n", maisRapida);
		System.out.printf("Fator de velocidade: %.2fx%n", fator);
		System.out.printf("Motivo: ArrayList precisa deslocar todos os elementos apos remover o primeiro, enquanto LinkedList apenas desaponta o primeiro nó.%n");
	}

	private static void exibirResultadoRemocaoFim(double mediaArray, double mediaLinked) {
		boolean arrayMaisRapido = mediaArray < mediaLinked;
		String maisRapida = arrayMaisRapido ? "ArrayList" : "LinkedList";
		double fator = arrayMaisRapido ? mediaLinked / mediaArray : mediaArray / mediaLinked;

		System.out.println("===== QUESTAO 3.2 - REMOCAO DO ULTIMO ELEMENTO =====");
		System.out.printf("Elementos por teste: %,d%n", ELEMENTOS_POR_TESTE);
		System.out.printf("Quantidade de testes: %d%n%n", TESTES_COMPARATIVO);

		System.out.printf("Media ArrayList : %.3f ms%n", mediaArray);
		System.out.printf("Media LinkedList: %.3f ms%n%n", mediaLinked);

		System.out.printf("Mais rapida: %s%n", maisRapida);
		System.out.printf("Fator de velocidade: %.2fx%n", fator);
		System.out.printf("Motivo: ArrayList remove o ultimo elemento sem deslocar outros (O(1)), enquanto LinkedList precisa percorrer ate o penultimo (O(n)).%n");
	}
}
