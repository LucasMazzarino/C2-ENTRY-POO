package booking.Models;

public class Habitacion {
    private String tipo;
    private double tarifaPorNoche;
    private String detalles;
    private boolean[] disponibilidad;
    private int cantNinos;
    private int cantAdultos;


    public Habitacion(String tipo, double tarifaPorNoche, String detalles, int diasDelMes, int cantNinos, int cantAdultos) {
        this.tipo = tipo;
        this.tarifaPorNoche = tarifaPorNoche;
        this.detalles = detalles;
        this.disponibilidad = new boolean[diasDelMes];
        this.cantNinos = cantNinos;
        this.cantAdultos = cantAdultos;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getTarifaPorNoche() {
        return tarifaPorNoche;
    }

    public void setTarifaPorNoche(double tarifaPorNoche) {
        this.tarifaPorNoche = tarifaPorNoche;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public boolean isDisponible(int dia) {
        return !disponibilidad[dia];
    }

    public void reservar(int dia) {
        disponibilidad[dia] = true;
    }

    public void liberar(int dia) {
        disponibilidad[dia] = false;
    }

    public int getCantNinos() {
        return cantNinos;
    }

    public void setCantNinos(int cantNinos) {
        this.cantNinos = cantNinos;
    }

    public int getCantAdultos() {
        return cantAdultos;
    }

    public void setCantAdultos(int cantAdultos) {
        this.cantAdultos = cantAdultos;
    }

    @Override
    public String toString() {
        return "Tipo: " + tipo + ", Tarifa por noche: " + tarifaPorNoche + ", Detalles: " + detalles + ", Capacidad: " + cantAdultos + " adultos y " + cantNinos + " niños";
    }
}
