import java.util.ArrayList;
import java.util.Scanner;

// Clase base
class Gasto {
    protected String nombre;
    protected double monto;
    protected String fecha;
    protected String metodoPago;
    protected String categoria;

    public Gasto(String nombre, double monto, String fecha, String metodoPago, String categoria) {
        this.nombre = nombre;
        this.monto = monto;
        this.fecha = fecha;
        this.metodoPago = metodoPago;
        this.categoria = categoria;
    }

    public String descripcion() {
        return categoria + ": " + nombre + " - $" + monto + " (" + fecha + ", " + metodoPago + ")";
    }

    public double getMonto() {
        return monto;
    }
}

// Clases hijas
class GastoComida extends Gasto {
    public GastoComida(String nombre, double monto, String fecha, String metodoPago) {
        super(nombre, monto, fecha, metodoPago, "Comida");
    }

    @Override
    public String descripcion() {
        return "🍔 Comida: " + nombre + " - $" + monto;
    }
}

class GastoTransporte extends Gasto {
    public GastoTransporte(String nombre, double monto, String fecha, String metodoPago) {
        super(nombre, monto, fecha, metodoPago, "Transporte");
    }

    @Override
    public String descripcion() {
        return "🚌 Transporte: " + nombre + " - $" + monto;
    }
}

// Clase principal
public class ControlGastos {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Gasto> listaGastos = new ArrayList<>();
        boolean salir = false;

        while (!salir) {
            System.out.println("\n=== SISTEMA DE CONTROL DE GASTOS ===");
            System.out.println("1. Registrar gasto");
            System.out.println("2. Mostrar todos los gastos");
            System.out.println("3. Calcular total de gastos");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");

            String opcionStr = sc.nextLine(); // Lee toda la línea
            int opcion = 0;
            try {
                opcion = Integer.parseInt(opcionStr);
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Ingresa un número válido.");
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.print("Nombre del gasto: ");
                    String nombre = sc.nextLine();
                    System.out.print("Monto: ");
                    double monto;
                    try {
                        monto = Double.parseDouble(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("⚠️ Valor inválido, intenta de nuevo.");
                        continue;
                    }
                    System.out.print("Fecha (dd/mm/aaaa): ");
                    String fecha = sc.nextLine();
                    System.out.print("Método de pago: ");
                    String metodo = sc.nextLine();
                    System.out.print("Categoría (1=Comida, 2=Transporte, otra=General): ");
                    String catStr = sc.nextLine();

                    Gasto gasto;
                    if (catStr.equals("1")) {
                        gasto = new GastoComida(nombre, monto, fecha, metodo);
                    } else if (catStr.equals("2")) {
                        gasto = new GastoTransporte(nombre, monto, fecha, metodo);
                    } else {
                        gasto = new Gasto(nombre, monto, fecha, metodo, "General");
                    }

                    listaGastos.add(gasto);
                    System.out.println("✅ Gasto agregado correctamente.");
                    break;

                case 2:
                    System.out.println("\n--- Lista de gastos ---");
                    if (listaGastos.isEmpty()) {
                        System.out.println("No hay gastos registrados.");
                    } else {
                        for (Gasto g : listaGastos) {
                            System.out.println(g.descripcion());
                        }
                    }
                    break;

                case 3:
                    double total = 0;
                    for (Gasto g : listaGastos) {
                        total += g.getMonto();
                    }
                    System.out.println("💰 Total gastado: $" + total);
                    break;

                case 4:
                    System.out.println("👋 Saliendo del sistema...");
                    salir = true;
                    break;

                default:
                    System.out.println("⚠️ Opción no válida, intenta otra vez.");
            }
        }

        sc.close();
    }
}
