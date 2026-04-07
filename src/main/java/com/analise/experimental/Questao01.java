package com.analise.experimental;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.IntFunction;

public class Questao01 {

	private static final int ELEMENTOS_POR_TESTE = 500_000;
	private static final int TESTES_COMPARATIVO = 20;
	private static final int TESTES_CAPACIDADE = 10;
	private static final int[] CAPACIDADES = {10, 1_000, 100_000};

	public static void main(String[] args) {
		aquecerJVM(3);

		double mediaArrayList = mediaBenchmark(TESTES_COMPARATIVO, c -> new ArrayList<>(10), ELEMENTOS_POR_TESTE);
		double mediaLinkedList = mediaBenchmark(TESTES_COMPARATIVO, c -> new LinkedList<>(), ELEMENTOS_POR_TESTE);
		exibirResultadoComparativo(mediaArrayList, mediaLinkedList);

		exibirResultadoCapacidades();
	}

	private static void aquecerJVM(int repeticoes) {
		for (int i = 0; i < repeticoes; i++) {
			preencher(c -> new ArrayList<>(10), ELEMENTOS_POR_TESTE);
			preencher(c -> new LinkedList<>(), ELEMENTOS_POR_TESTE);
		}
	}

	private static double mediaBenchmark(int testes, IntFunction<List<Integer>> fabrica, int quantidade) {
		double somaMs = 0;
		for (int i = 0; i < testes; i++) {
			somaMs += preencher(fabrica, quantidade);
		}
		return somaMs / testes;
	}

	private static double preencher(IntFunction<List<Integer>> fabrica, int quantidade) {
		List<Integer> lista = fabrica.apply(quantidade);
		long inicio = System.nanoTime();
		for (int i = 0; i < quantidade; i++) {
			lista.add(i);
		}
		return (System.nanoTime() - inicio) / 1_000_000.0;
	}

	private static void exibirResultadoComparativo(double mediaArray, double mediaLinked) {
		boolean arrayMaisRapido = mediaArray < mediaLinked;
		String maisRapida = arrayMaisRapido ? "ArrayList" : "LinkedList";
		double fator = arrayMaisRapido ? mediaLinked / mediaArray : mediaArray / mediaLinked;

		System.out.println("===== QUESTAO 1 - INSERCAO NO FINAL =====");
		System.out.printf("Elementos por teste: %,d%n", ELEMENTOS_POR_TESTE);
		System.out.printf("Quantidade de testes: %d%n%n", TESTES_COMPARATIVO);

		System.out.printf("Media ArrayList : %.3f ms%n", mediaArray);
		System.out.printf("Media LinkedList: %.3f ms%n%n", mediaLinked);

		System.out.printf("Mais rapida: %s%n", maisRapida);
		System.out.printf("Fator de velocidade: %.2fx%n%n", fator);
	}

	private static void exibirResultadoCapacidades() {
		System.out.println("===== INFLUENCIA DA CAPACIDADE INICIAL (ArrayList) =====");
		System.out.printf("Execucoes por capacidade: %d%n%n", TESTES_CAPACIDADE);
		double melhorTempo = Double.MAX_VALUE;
		int melhorCapacidade = CAPACIDADES[0];

		for (int capacidade : CAPACIDADES) {
			double media = mediaBenchmark(TESTES_CAPACIDADE, c -> new ArrayList<>(capacidade), ELEMENTOS_POR_TESTE);
			if (media < melhorTempo) {
				melhorTempo = media;
				melhorCapacidade = capacidade;
			}
			System.out.printf("Capacidade inicial %,d -> media %.3f ms%n", capacidade, media);
		}

		System.out.printf("%nConfiguracao mais rapida: capacidade inicial %,d%n", melhorCapacidade);
		System.out.println("Motivo: capacidades iniciais maiores reduzem realocacoes e copias internas do vetor durante o crescimento.");
	}
}
