package booking.Models;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private String nombre;
    private String detalles;
    private List<Habitacion> habitaciones;
    private List<Reserva> reservas;
    private String ciudad;
    private double estrellas;
    private TipoAlojamiento tipoAlojamiento;
    private List<String> actividades;

    public Hotel(String nombre, String detalles, String ciudad, double estrellas, TipoAlojamiento tipoAlojamiento) {
        this.nombre = nombre;
        this.detalles = detalles;
        this.habitaciones = new ArrayList<>();
        this.reservas = new ArrayList<>();
        this.ciudad = ciudad;
        this.estrellas = estrellas;
        this.tipoAlojamiento = tipoAlojamiento;
        this.actividades = new ArrayList<>();
    }

    public List<String> getActividades() {
        return actividades;
    }

    public void setActividades(List<String> actividades) {
        this.actividades = actividades;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(List<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public double getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(double estrellas) {
        this.estrellas = estrellas;
    }

    public TipoAlojamiento getTipoAlojamiento() {
        return tipoAlojamiento;
    }

    public void setTipoAlojamiento(TipoAlojamiento tipoAlojamiento) {
        this.tipoAlojamiento = tipoAlojamiento;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre de Hotel: ").append(nombre).append("\n");
        sb.append("Ubicación: ").append(ciudad).append("\n");
        sb.append("Estrellas: ").append(estrellas).append(" estrellas\n");
        sb.append("Tipo de alojamiento: ").append(tipoAlojamiento).append("\n");
        sb.append("Habitaciones:\n");
        for (Habitacion habitacion : habitaciones) {
            sb.append("  - ").append(habitacion).append("\n");
        }
        sb.append("Reservas: ").append(reservas.isEmpty() ? "No hay reservas" : reservas).append("\n");
        return sb.toString();
    }
}