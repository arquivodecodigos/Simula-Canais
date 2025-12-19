package estudos;

public class AlturaLaminaNewtonRaphson {
  static final float EPSILON = (float) 0.00001; // erro permitido

  // Dados do canal
  static double b = 2.0; // base menor (m)
  static double Z = 1.5; // talude lateral (H:V)
  static double n = 0.015; // coeficiente de Manning
  static double I = 0.001; // declividade
  static double Q = 12.15894; // vazão desejada (m³/s)

  // Parâmetros do método
  static double y0 = 0.01; // Chute inicial (m)
  static int max_iter = 100;
  static double dy = 1e-5; // Incremento para derivada numérica
  
  // Função objetivo f(y)
  static double f(double y) {
    double A = y * (b + Z * y);
    double P = b + 2 * y * Math.sqrt(1.0 + Math.pow(Z, 2));
    double R = A / P;
    return (1.0 / n) * A * Math.pow(R, (2.0 / 3.0)) * 
      Math.sqrt(I) - Q;
  }
  
  public static void main(String[] args) {
    double y = y0;

    for (int i = 0; i < max_iter; i++) {
      // Derivada numérica
      double df = (f(y + dy) - f(y)) / dy;

      // Atualização
      double y_new = y - f(y) / df;

      // Critério de convergência
      if (Math.abs(y_new - y) < EPSILON) {
        break;
      }
          
      y = y_new;
    }
    
    System.out.println("O valor de y é: " + y);
  }
}