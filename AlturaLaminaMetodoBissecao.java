package estudos;

public class AlturaLaminaMetodoBissecao {
  static final float EPSILON = (float) 0.00001; // erro permitido

  // Dados do canal
  static double b = 2.0; // base menor (m)
  static double Z = 1.5; // talude lateral (H:V)
  static double n = 0.015; // coeficiente de Manning
  static double I = 0.001; // declividade
  static double Q = 12.15894; // vazão desejada (m³/s)
  
  // Função que calcula a vazão para uma lâmina y
  static double vazao(double y) {
    double A = (b + Z * y) * y;
    //System.out.println("Área Molhada: " + A);
    double P = b + 2.0 * y * Math.sqrt(1.0 + Math.pow(Z, 2.0));
    //System.out.println("Perímetro Molhado: " + P);
    double R = A / P;
    //System.out.println("Raio Hidráulico: " + R);
    double Q = (1.0 / n) * A * (Math.pow(R, 2.0 / 3.0)) * 
      Math.pow(I, 1.0 / 2.0);
    return Q;
  }
  
  // Função objetivo f(y) = Vazão Calculada - Vazão desejada
  static double f(double y) {
    return vazao(y) - Q;
  }
    
  // Método da biseção
  static double bisecao(double a, double b) {
    double fa = f(a);
    double fb = f(b);
    double mid = 0;
    
    if (fa * fb > 0) {
      System.out.println("A função não muda de sinal no intervalo [a, b].");
      return 0;
    }  
    
    while ((fb - fa) >= EPSILON) {
      mid = (a + b) / 2.0;
      double fmid = f(mid);
      
      if (Math.abs(fmid) < EPSILON) {
        return mid;
      }
      
      if ((fa * fmid) < 0) {
        b = mid;
        fb = fmid;
      }
      else {
        a = mid;
        fa = fmid;
      }   
    }
    
    return mid;
  }
  
  public static void main(String[] args) {
    double a = 0.0, b = 10; // valores iniciais
    double r = bisecao(a, b);
    System.out.println("O valor de y é: " + r);
  }
}
