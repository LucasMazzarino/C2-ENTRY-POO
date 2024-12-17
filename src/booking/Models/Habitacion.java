package booking.Models;

public abstract class Habitacion {
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

    protected double aplicarAjustesPorDias(double costo, int[] dias) {
        boolean ultimoCincoDias = false;
        boolean diezAlQuince = false;
        boolean cincoAlDiez = false;

        for (int dia : dias) {
            if (dia > 25) {
                ultimoCincoDias = true;
            }
            if (dia >= 10 && dia <= 15) {
                diezAlQuince = true;
            }
            if (dia >= 5 && dia <= 10) {
                cincoAlDiez = true;
            }
        }

        if (ultimoCincoDias) {
            costo *= 1.15;
        } else if (diezAlQuince) {
            costo *= 1.10;
        } else if (cincoAlDiez) {
            costo *= 0.92;
        }

        return costo;
    }

    public abstract double calcularCosto(int[] dias);

    // Sobrecarga
    public double calcularCosto(int dia) {
        return calcularCosto(new int[]{dia});
    }

    @Override
    public String toString() {
        return "Tipo: " + tipo + ", Tarifa por noche: " + tarifaPorNoche + ", Detalles: " + detalles + ", Capacidad: " + cantAdultos + " adultos y " + cantNinos + " niños";
    }
}