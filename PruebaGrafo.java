import java.util.Scanner;

/**
 *
 * @author aremy
 */
class Vertice {

    String nombre;
    int numVertice;

    public Vertice(String x) {
        nombre = x;
        numVertice = -1;
    }

    // Metodo que devuelve identificador del vértice
    public String nomVertice() {
        return nombre;
    }

    // Metodo que verifica si uno de los vectores es igual y retorna true
    public boolean equals(Vertice n) {
        return nombre.equals(n.nombre);
    }

    // Metodo que establece el número de vértices
    public void asigVert(int n) {
        numVertice = n;
    }

    // Metodo que retorna las características de un vértice
    public String toString() {
        return nombre + " (" + numVertice + ")";
    }
}

class matrizDeAdyacencia {

    int numVerts;
    static int MaxVerts = 20;
    Vertice[] verts;
    int[][] matAd;

    // Constructorers
    public matrizDeAdyacencia() {
        this(MaxVerts);
    }

    public matrizDeAdyacencia(int mx) {
        matAd = new int[mx][mx];
        verts = new Vertice[mx];
        for (int i = 0; i < mx; i++) {
            for (int j = 0; i < mx; i++) {
                matAd[i][j] = 0;
            }
        }
        numVerts = 0;
    }

    // Metodo para ingresar un nuevo vertice
    public void nuevoVertice(String nom) {
        boolean esta = numVertice(nom) >= 0;
        if (!esta) {
            Vertice v = new Vertice(nom);
            v.asigVert(numVerts);
            verts[numVerts++] = v;
        }
    }

    // Metodo para encontrar un vértice
    public int numVertice(String vs) {
        Vertice v = new Vertice(vs);
        boolean encontrado = false;
        int i = 0;
        for (; (i < numVerts) && !encontrado;) {
            encontrado = verts[i].equals(v);
            if (!encontrado) {
                i++;
            }
        }
        return (i < numVerts) ? i : -1;
    }

    // Metodo para añadir un arco
    public void nuevoArco(String a, String b) throws Exception {
        int va, vb;
        va = numVertice(a);
        vb = numVertice(b);
        if (va < 0 || vb < 0) {
            throw new Exception("Vértice no existe");
        }
        matAd[va][vb] = 1;
    }

    public void nuevoArco(int va, int vb) throws Exception {
        if (va < 0 || vb < 0) {
            throw new Exception("Vértice no existe");
        }
        matAd[va][vb] = 1;
    }

    // Metodo de adyacentes
    public boolean adyacente(String a, String b) throws Exception {
        int va, vb;
        va = numVertice(a);
        vb = numVertice(b);
        if (va < 0 || vb < 0) {
            throw new Exception("Vértice no existe");
        }
        return matAd[va][vb] == 1;
    }

    public boolean adyacente(int va, int vb) throws Exception {
        if (va < 0 || vb < 0) {
            throw new Exception("Vértice no existe");
        }
        return matAd[va][vb] == 1;
    }
}

public class PruebaGrafo {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        String opcion1 = "0";
        int opcion = 0;

        System.out.println("Ingresa el numero maximo de vertices:");
        String x = entrada.nextLine();
        int maxVertices = Integer.parseInt(x);
        matrizDeAdyacencia grafo = new matrizDeAdyacencia(5);

        do {
            System.out.println("Elige un opción:");
            System.out.println("1) Añadir un vértice");
            System.out.println("2) Añadir un arco");
            System.out.println("3) Determinar si dos vértices son adyacentes");
            System.out.println("4) Mostrar cantidad de vértices");
            System.out.println("5) Mostrar posición de un vértice");
            System.out.println("6) Salir");
            
            opcion1 = entrada.nextLine();
            opcion = Integer.parseInt(opcion1);

            switch (opcion) {
                case 1:
                    System.out.println("Ingresa el nombre del vértice:");
                    String nombre = entrada.nextLine();
                    grafo.nuevoVertice(nombre);
                    break;

                case 2:
                    System.out.println("Ingresa el nombre del primer vértice:");
                    String nombre1 = entrada.nextLine();
                    System.out.println("Ingresa el nombre del segundo vértice:");
                    String nombre2 = entrada.nextLine();
                    try {
                        grafo.nuevoArco(nombre1, nombre2);
                        System.out.println("Arco agregado con exito");
                    } catch (Exception e) {
                        System.out.println("¡Error!"
                                + e.getMessage());

                    }

                    break;

                case 3:
                    System.out.println("Ingresa el nombre del primer vértice:");
                    nombre1 = entrada.nextLine();
                    System.out.println("Ingresa el nombre del segundo vértice:");
                    nombre2 = entrada.nextLine();
                    try {

                        System.out.println(grafo.adyacente(nombre1, nombre2) == true ? "Son adyacentes" : "No son adyacentes");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());

                    }
                    break;

                case 4:
                    System.out.println("Número de vértices:" + grafo.numVerts);
                    break;

                case 5:
                    System.out.println("Ingresa nombre del vértice:");
                    nombre = entrada.nextLine();
                    System.out.println("El numero del vertice es:"
                            + grafo.numVertice(nombre));
                    break;

                default:
                    break;
            }
            System.out.println(" ");
        } while (opcion != 6);
    }
}