package booking.Models;

public class HabitacionComun extends Habitacion {

    public HabitacionComun(double tarifaPorNoche, String detalles, int diasDelMes, int cantNinos, int cantAdultos) {
        super("Comun", tarifaPorNoche, detalles, diasDelMes, cantNinos, cantAdultos);
    }

    @Override
    public double calcularCosto(int[] dias) {
        double costo = getTarifaPorNoche() * dias.length;

        return aplicarAjustesPorDias(costo, dias);
    }
}