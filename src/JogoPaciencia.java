import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class JogoPaciencia {

    private static final int NUMERO_PILHAS = 7;
    private static final int NUMERO_PILHAS_FINAL = 4;
    private static final int NUMERO_CARTAS_NA_MAO = 7;

    private List<Stack<String>> pilhas;
    private List<Stack<String>> pilhasFinais;
    private Stack<String> baralho;

    public JogoPaciencia() {
        inicializarJogo();
    }

    private void inicializarJogo() {
        pilhas = new ArrayList<>();
        pilhasFinais = new ArrayList<>();
        baralho = new Stack<>();

        // Criar cartas
        String[] naipes = {"♥", "♦", "♠", "♣"};
        String[] valores = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        for (String naipe : naipes) {
            for (String valor : valores) {
                baralho.push(valor + naipe);
            }
        }

        // Embaralhar o baralho
        Collections.shuffle(baralho);

        // Distribuir cartas para as pilhas
        for (int i = 0; i < NUMERO_PILHAS; i++) {
            pilhas.add(new Stack<>());
            for (int j = 0; j < i + 1; j++) {
                pilhas.get(i).push(baralho.pop());
            }
        }

        // Inicializar pilhas finais
        for (int i = 0; i < NUMERO_PILHAS_FINAL; i++) {
            pilhasFinais.add(new Stack<>());
        }
    }

    private void exibirTabuleiro() {

        if (!baralho.isEmpty()) {
            String cartaNoTopo = baralho.peek();
            System.out.println("A carta no topo do baralho é: " + cartaNoTopo);
        } else {
            System.out.println("O baralho está vazio.");
        }

        System.out.println("Pilhas:");

        for (int i = 0; i < NUMERO_PILHAS; i++) {
            System.out.print("Pilha " + (i + 1) + ": ");
            if (!pilhas.get(i).isEmpty()) {
                System.out.print(pilhas.get(i).peek());
            }
            System.out.println();
        }

        System.out.println("\nPilhas Finais:");

        for (int i = 0; i < NUMERO_PILHAS_FINAL; i++) {
            System.out.print("Pilha Final " + (i + 1) + ": ");
            if (!pilhasFinais.get(i).isEmpty()) {
                System.out.print(pilhasFinais.get(i).peek());
            }
            System.out.println();
        }


    }

    private void jogar() {
        Scanner scanner = new Scanner(System.in);

        while (!jogoGanho()) {
            exibirTabuleiro();

            System.out.println("\nEscolha uma pilha de origem (1-" + NUMERO_PILHAS + "): ");
            int pilhaOrigem = scanner.nextInt();
            System.out.println("Escolha uma pilha de destino (1-" + NUMERO_PILHAS_FINAL + "): ");
            int pilhaDestino = scanner.nextInt();

            if (validarMovimento(pilhaOrigem, pilhaDestino)) {
                realizarMovimento(pilhaOrigem, pilhaDestino);
            } else {
                System.out.println("Movimento inválido. Tente novamente.");
            }
        }

        exibirTabuleiro();
        System.out.println("Parabéns! Você ganhou!");
    }

    private boolean jogoGanho() {
        for (int i = 0; i < NUMERO_PILHAS_FINAL; i++) {
            if (pilhasFinais.get(i).size() != 13) {
                return false;
            }
        }
        return true;
    }

    private boolean validarMovimento(int pilhaOrigem, int pilhaDestino) {
        // Obter as cartas no topo das pilhas de origem e destino
        String cartaOrigem = pilhas.get(pilhaOrigem - 1).peek();
        String cartaDestino = pilhas.get(pilhaDestino - 1).peek();

        // Obter os naipes das cartas
        char naipeOrigem = cartaOrigem.charAt(cartaOrigem.length() - 1);
        char naipeDestino = cartaDestino.charAt(cartaDestino.length() - 1);

        // Comparar os naipes
        if ((naipeOrigem == '♥' && naipeDestino == '♠') || (naipeOrigem == '♦' && naipeDestino == '♠')) {
            return true;
        } else if ((naipeOrigem == '♥' && naipeDestino == '♣') || (naipeOrigem == '♦' && naipeDestino == '♣')) {
            return true;
        } else {
            return false;
        }
    }

    private void realizarMovimento(int pilhaOrigem, int pilhaDestino) {
        // Lógica para realizar o movimento (adapte conforme necessário)
    }

    public static void main(String[] args) {
        JogoPaciencia jogo = new JogoPaciencia();
        jogo.jogar();
    }
}
