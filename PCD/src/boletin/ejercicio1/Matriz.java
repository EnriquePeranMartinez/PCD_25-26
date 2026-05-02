package boletin.ejercicio1;
import java.utils.Random;
import java.util.Arrays;


public class Matriz {
  private int matriz[3][3];
  public static final int TAMANO_MATRIZ_EJERCICIO_1 = 3;

  public int[][] getMatriz(){
    return this.matriz
  }

  public Matriz(){
    for(int i = 0; i < TAMANO_MATRIZ_EJERCICIO_1; i++){
      for(int j = 0; j < TAMANO_MATRIZ_EJERCICIO_1; j++){
        this.matriz[i][j] = new Random().nextInteger();
      }
    }
  }

    public Matriz(int size){
    for(int i = 0; i < size; i++){
      for(int j = 0; j < size; j++){
        this.matriz[i][j] = new Random().nextInteger(11);
      }
    }
  }

  public void inicializar0(Matriz a){
      for(int i = 0; i < 3; i++){
        for(int j = 0; j < 3; j++){
          c.getMatriz()[i][j] = 0;
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
    Matriz c = inicializar(0);

    for(int i = 0; i < 3; i++){
      for(int j = 0; j < 3; j++){
        c.getMatriz()[i][j] += a.getMatriz()[i][j] * b.getMatriz()[i][j];
      }
    }
    return c;
  }

  public void mostrarMatriz(){
    Arrays.stream(this.matriz)
      .map(Arrays::toString())
      .forEach(System.out::println);
  }
  // FUNCIONES EJERCICIO 2
  private int[] calcularMejorMarca(Matriz A){
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
    system.out.println("Usando el panel " + P " el hilo " + equipo);
    system.out.println("Matriz R (resumen del equipo " + equipo + ": mejores marcas) = " + R);
    system.out.println("Terminando de usar panel" + P + " el hilo (equipo)" + id );
  }

  public Matriz calcularMatrizResumen(Matriz... matrices){
    // mirar de hacerlo con streams :)
    Matriz R;
    int i = 0;
    int[] arr;
    for(m : matrices){
      arr = calcularMejorMarca(m);
      for(int j = 0; j < TAMANO_MATRIZ_EJERCICIO_2; j++){
        R.getMatriz()[i][j] = arr[j];
      }
      i++;
    }
    return R;
  }
}
