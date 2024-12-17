package booking.Models;

public class HabitacionPremium extends Habitacion {

    public HabitacionPremium(double tarifaPorNoche, String detalles, int diasDelMes, int cantNinos, int cantAdultos) {
        super("Premium", tarifaPorNoche, detalles, diasDelMes, cantNinos, cantAdultos);
    }

    @Override
    public double calcularCosto(int[] dias) {
        double costo = getTarifaPorNoche() * dias.length;
        return aplicarAjustesPorDias(costo, dias);
    }
}