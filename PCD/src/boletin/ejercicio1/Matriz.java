package boletin.ejercicio1;
import java.util.Arrays;
import java.util.Random;

import boletin.Panel;
import boletin.ejercicio2.*;


public class Matriz {
  private int matriz[][];
  public static final int TAMANO_MATRIZ_EJERCICIO_1 = 3;
  public final static int TAMANO_MATRIZ_EJERCICIO_2 = 5;


  public int[][] getMatriz(){
    return this.matriz;
  }

  public Matriz(){
	  matriz = new int[TAMANO_MATRIZ_EJERCICIO_1][TAMANO_MATRIZ_EJERCICIO_1];
    for(int i = 0; i < TAMANO_MATRIZ_EJERCICIO_1; i++){
      for(int j = 0; j < TAMANO_MATRIZ_EJERCICIO_1; j++){
        this.matriz[i][j] = new Random().nextInt();
      }
    }
  }

    public Matriz(int size){
    for(int i = 0; i < size; i++){
      for(int j = 0; j < size; j++){
        this.matriz[i][j] = new Random().nextInt(11);
      }
    }
  }

  public void inicializar0(){
      for(int i = 0; i < 3; i++){
        for(int j = 0; j < 3; j++){
         this.matriz[i][j] = 0;
      }
    }
  }

  public Matriz sumar(Matriz a, Matriz b){
    Matriz c = new Matriz();
    for(int i = 0; i < 3; i++){
      for(int j = 0; j < 3; j++){
        c.getMatriz()[i][j] = a.getMatriz()[i][j] + b.getMatriz()[i][j];
      }
    }
    return c;
  }

  public Matriz multiplicar(Matriz a, Matriz b){
    Matriz c = new Matriz(); c.inicializar0();

    for(int i = 0; i < 3; i++){
      for(int j = 0; j < 3; j++){
        c.getMatriz()[i][j] += a.getMatriz()[i][j] * b.getMatriz()[i][j];
      }
    }
    return c;
  }

  public void mostrarMatriz(){
    Arrays.stream(this.matriz)
      .map(Arrays::toString)
      .forEach(System.out::println);
  }
  // FUNCIONES EJERCICIO 2
  private static int[] calcularMejorMarca(Matriz A){
    int[] marcas = {0, 0, 0, 0, 0};
    int mejorMarca = -1;
    
    for(int i = 0; i < TAMANO_MATRIZ_EJERCICIO_2; i++){
      for(int j = 0; j < TAMANO_MATRIZ_EJERCICIO_2; j++){
        if(A.getMatriz()[i][j] > mejorMarca) mejorMarca = A.getMatriz()[i][j];
      }
      marcas[i] = mejorMarca;
    }
    return marcas;
  }

  public void mostrarMatriz(Matriz R, Panel P, int equipo){
    System.out.println("Usando el panel " + P + " el hilo " + equipo);
    System.out.println("Matriz R (resumen del equipo " + equipo + ": mejores marcas) = " + R);
    System.out.println("Terminando de usar panel" + P + " el hilo (equipo)" + equipo );
  }

  public static Matriz calcularMatrizResumen(Matriz... matrices){
    // mirar de hacerlo con streams :)
    Matriz R = new Matriz();
    int i = 0;
    int[] arr;
    for(Matriz m : matrices){
      arr = calcularMejorMarca(m);
      for(int j = 0; j < TAMANO_MATRIZ_EJERCICIO_2; j++){
        R.getMatriz()[i][j] = arr[j];
      }
      i++;
    }
    return R;
  }
}
