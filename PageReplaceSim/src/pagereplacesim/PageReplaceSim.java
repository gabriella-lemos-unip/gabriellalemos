package pagereplacesim;

import java.util.LinkedList;
import java.util.Scanner;

public class PageReplaceSim {
 public static void main(String[] args) {
  System.out.println("Digite uma sequência de números separados por ponto e vírgula ");
  Scanner scanner = new Scanner(System.in);
  String referencia = scanner.nextLine();
  String[] stringReferencia = referencia.split(";");
  

  AlgoritmoDeSubstituicao fifo = new AlgoritmoFifo(3);
  


  for (int i = 0; i < (stringReferencia.length - 1); i++) {
   fifo.inserir(stringReferencia[i]);

  }
  System.out.println("Page Faults: " + fifo.getPageFaultCount());
  
  AlgoritmoDeSubstituicao sc = new AlgoritmoSegundaChance(3);

  for (int i = 0; i < (stringReferencia.length - 1); i++) {
   sc.inserir(stringReferencia[i]);

  }
  System.out.println("Page Faults: " + sc.getPageFaultCount());

 }
}

abstract class AlgoritmoDeSubstituicao {
 protected int numeroDeFalhas;
 protected int numeroDeQuadros;
 LinkedList quadros;

 public AlgoritmoDeSubstituicao(int numeroDeQuadros) {
  if (numeroDeQuadros < 0)
   throw new IllegalArgumentException();
  this.numeroDeQuadros = numeroDeQuadros;
  numeroDeFalhas = 0;
 }

 public int getPageFaultCount() {
  return numeroDeFalhas;
 }

 public abstract void inserir(String pageNumber);

 public void imprimirQuadros() {
  System.out.print("Quadros:  ");
  for (int i = 0; i < quadros.size(); i++) {
   System.out.print(quadros.get(i) + " ");
  }
  System.out.println();
 }
}

class AlgoritmoSegundaChance extends AlgoritmoDeSubstituicao {
 LinkedList bits;
 private static int PONTEIRO = 0;

 public AlgoritmoSegundaChance(int numeroDeQuadros) {
  super(numeroDeQuadros);
  this.quadros = new LinkedList();
  this.bits = new LinkedList();

 }

 @Override
 public void inserir(String pageNumber) {
  int tmp = quadros.indexOf(pageNumber);

  if (tmp == -1) {
   if (quadros.size() < numeroDeQuadros) {
    quadros.add(pageNumber);
    bits.add(0);
   } else {
    this.bits = new LinkedList(); {
     bits.set(PONTEIRO, 0);
     PONTEIRO++;
     if (PONTEIRO == numeroDeQuadros) {
      PONTEIRO = 0;
     }
    }
    quadros.remove(PONTEIRO);
    bits.remove(PONTEIRO);
    quadros.add(PONTEIRO, pageNumber);
    bits.add(PONTEIRO, 0);

    PONTEIRO++;
    if (PONTEIRO == numeroDeQuadros) {
     PONTEIRO = 0;
    }
   }
   numeroDeFalhas++;
  } else {
   bits.set(tmp, 1);

  }
 }

 @Override
 public void imprimirQuadros() {
  System.out.print("Quadros:  ");
  for (int i = 0; i < quadros.size(); i++) {
   System.out.print(quadros.get(i) + " bit: " + bits.get(i) + " ");
  }
  System.out.println();
 }
}

class AlgoritmoFifo extends AlgoritmoDeSubstituicao {
 private static int INSERCAO = 0;

 public AlgoritmoFifo(int numeroDeQuadros) {

  super(numeroDeQuadros);
  this.quadros = new LinkedList();
 }

 @Override
 public void inserir(String pageNumber) {
  if (!quadros.contains(pageNumber)) {
   if (quadros.size() < numeroDeQuadros) {
    quadros.add(pageNumber);
   } else {
    quadros.remove(INSERCAO);
    quadros.add(INSERCAO, pageNumber);
    INSERCAO++;
    if (INSERCAO == numeroDeQuadros) {
     INSERCAO = 0;
    }
   }
   numeroDeFalhas++;

  }
 }
}