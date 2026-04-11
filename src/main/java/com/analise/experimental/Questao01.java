package com.analise.experimental;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Questao01 {

	private static final int ELEMENTOS_POR_TESTE = 500_000;
	private static final int TESTES_COMPARATIVO = 20;
	private static final int TESTES_CAPACIDADE = 10;
	private static final int[] CAPACIDADES = {10, 1_000, 100_000};

	public static void main(String[] args) {
		new Questao01();
	}

	public Questao01() {
		init();
	}

	private void init() {
		double mediaArrayList = testarArrayListInsercaoFinal();
		double mediaLinkedList = testarLinkedListInsercaoFinal();
		exibirResultadoComparativo(mediaArrayList, mediaLinkedList);

		System.out.println();

		exibirResultadoCapacidades();
	}

	private double testarArrayListInsercaoFinal() {
		double somaMs = 0;

		for (int t = 0; t < TESTES_COMPARATIVO; t++) {
			List<Integer> lista = new ArrayList<>(10);
			long inicio = System.nanoTime();
			preencherLista(lista);
			somaMs += (System.nanoTime() - inicio) / 1_000_000.0;
		}

		return somaMs / TESTES_COMPARATIVO;
	}

	private double testarLinkedListInsercaoFinal() {
		double somaMs = 0;

		for (int t = 0; t < TESTES_COMPARATIVO; t++) {
			List<Integer> lista = new LinkedList<>();
			long inicio = System.nanoTime();
			preencherLista(lista);
			somaMs += (System.nanoTime() - inicio) / 1_000_000.0;
		}

		return somaMs / TESTES_COMPARATIVO;
	}

	private void preencherLista(List<Integer> lista) {
		for (int i = 0; i < ELEMENTOS_POR_TESTE; i++) {
			lista.add(i);
		}
	}

	private void exibirResultadoComparativo(double mediaArray, double mediaLinked) {
		boolean arrayMaisRapido = mediaArray < mediaLinked;
		String maisRapida = arrayMaisRapido ? "ArrayList" : "LinkedList";
		double fator = arrayMaisRapido ? mediaLinked / mediaArray : mediaArray / mediaLinked;

		System.out.println("===== QUESTAO 1.1 - INSERCAO NO FINAL =====");
		System.out.printf("Elementos por teste: %,d%n", ELEMENTOS_POR_TESTE);
		System.out.printf("Quantidade de testes: %d%n%n", TESTES_COMPARATIVO);

		System.out.printf("Media ArrayList : %.3f ms%n", mediaArray);
		System.out.printf("Media LinkedList: %.3f ms%n%n", mediaLinked);

		System.out.printf("Mais rapida: %s%n", maisRapida);
		System.out.printf("Fator de velocidade: %.2fx%n%n", fator);
		System.out.printf("Motivo: ambas inserem no final em tempo amortizado constante, mas o ArrayList pode sofrer realocacoes quando cresce, enquanto a LinkedList cria novos nos a cada insercao.%n");
	}

	private void exibirResultadoCapacidades() {
		System.out.println("===== QUESTAO 1.2 - INFLUENCIA DA CAPACIDADE INICIAL (ArrayList) =====");
		System.out.printf("Execucoes por capacidade: %d%n%n", TESTES_CAPACIDADE);
		double melhorTempo = Double.MAX_VALUE;
		int melhorCapacidade = CAPACIDADES[0];

		for (int capacidade : CAPACIDADES) {
			double media = testarArrayListComCapacidade(capacidade);
			if (media < melhorTempo) {
				melhorTempo = media;
				melhorCapacidade = capacidade;
			}
			System.out.printf("Capacidade inicial %,d -> media %.3f ms%n", capacidade, media);
		}

		System.out.printf("%nConfiguracao mais rapida: capacidade inicial %,d%n", melhorCapacidade);
		System.out.println("Motivo: capacidades iniciais maiores reduzem realocacoes e copias internas do vetor durante o crescimento.");
	}

	private double testarArrayListComCapacidade(int capacidadeInicial) {
		double somaMs = 0;

		for (int t = 0; t < TESTES_CAPACIDADE; t++) {
			List<Integer> lista = new ArrayList<>(capacidadeInicial);
			long inicio = System.nanoTime();
			preencherLista(lista);
			somaMs += (System.nanoTime() - inicio) / 1_000_000.0;
		}

		return somaMs / TESTES_CAPACIDADE;
	}
}
