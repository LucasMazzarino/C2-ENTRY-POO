package booking.Models;

public class HabitacionSuit extends Habitacion {

    public HabitacionSuit(double tarifaPorNoche, String detalles, int diasDelMes, int cantNinos, int cantAdultos) {
        super("Suit", tarifaPorNoche, detalles, diasDelMes, cantNinos, cantAdultos);
    }

    @Override
    public double calcularCosto(int[] dias) {
        double costo = getTarifaPorNoche() * dias.length;
        return aplicarAjustesPorDias(costo, dias);
    }
}