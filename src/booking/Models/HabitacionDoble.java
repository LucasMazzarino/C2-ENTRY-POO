package booking.Models;

public class HabitacionDoble extends Habitacion {

    public HabitacionDoble(double tarifaPorNoche, String detalles, int diasDelMes, int cantNinos, int cantAdultos) {
        super("Doble", tarifaPorNoche, detalles, diasDelMes, cantNinos, cantAdultos);
    }

    @Override
    public double calcularCosto(int[] dias) {
        double costo = getTarifaPorNoche() * dias.length;
        return aplicarAjustesPorDias(costo, dias);
    }
}