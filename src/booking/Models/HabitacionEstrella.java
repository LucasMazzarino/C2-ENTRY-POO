package booking.Models;

public class HabitacionEstrella extends Habitacion {

    public HabitacionEstrella(double tarifaPorNoche, String detalles, int diasDelMes, int cantNinos, int cantAdultos) {
        super("Estrella", tarifaPorNoche, detalles, diasDelMes, cantNinos, cantAdultos);
    }

    @Override
    public double calcularCosto(int[] dias) {
        double costo = getTarifaPorNoche() * dias.length;
        return aplicarAjustesPorDias(costo, dias);
    }
}