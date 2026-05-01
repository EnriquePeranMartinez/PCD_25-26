package boletin.ejercicio1;
import java.utils.Random;
import java.util.Arrays;


public class Matriz {
  private int matriz[3][3];

  

  public Matriz(){
    for(int i = 0; i < 3; i++){
      for(int j = 0; j < 3; j++){
        matriz[i][j] = new Random().nextInteger();
      }
    }
  }

  public void inicializar0(Matriz a){
      for(int i = 0; i < 3; i++){
        for(int j = 0; j < 3; j++){
          c[i][j] = 0;
      }
    }
  }

  public Matriz sumar(Matriz a, Matriz b){
    Matriz c = new Matriz();
    for(int i = 0; i < 3; i++){
      for(int j = 0; j < 3; j++){
        c[i][j] = a[i][j] + b[i][j];
      }
    }
    return c;
  }

  public Matriz multiplicar(Matriz a, Matriz b){
    Matriz c = inicializar(0);

    for(int i = 0; i < 3; i++){
      for(int j = 0; j < 3; j++){
        c[i][j] += a[i][j] * b[i][j];
      }
    }
    return c;
  }

  public void mostrarMatriz(){
    Arrays.stream(this.matriz)
      .map(Arrays::toString())
      .forEach(System.out::println);
  }
}
