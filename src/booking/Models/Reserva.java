package booking.Models;

import java.time.LocalDate;

public class Reserva {
    private Cliente cliente;
    private Habitacion habitacion;
    private LocalDate entrada;
    private LocalDate salida;
    private String horaLlegada;
    private int cantNinos;
    private int cantAdultos;
    private int cantHabitaciones;
    private Alojamiento alojamiento;

    public Reserva(Cliente cliente, Habitacion habitacion,Alojamiento alojamiento, LocalDate entrada, LocalDate salida, String horaLlegada, int cantNinos, int cantAdultos, int cantHabitaciones) {
        this.cliente = cliente;
        this.habitacion = habitacion;
        this.alojamiento = alojamiento;
        this.entrada = entrada;
        this.salida = salida;
        this.horaLlegada = horaLlegada;
        this.cantNinos = cantNinos;
        this.cantAdultos = cantAdultos;
        this.cantHabitaciones = cantHabitaciones;
    }

    public void setAlojamiento(Alojamiento alojamiento) {
        this.alojamiento = alojamiento;
    }

    public Alojamiento getAlojamiento() {
        return alojamiento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public LocalDate getEntrada() {
        return entrada;
    }

    public void setEntrada(LocalDate entrada) {
        this.entrada = entrada;
    }

    public LocalDate getSalida() {
        return salida;
    }

    public void setSalida(LocalDate salida) {
        this.salida = salida;
    }

    public String getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(String horaLlegada) {
        this.horaLlegada = horaLlegada;
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

    public int getCantHabitaciones() {
        return cantHabitaciones;
    }

    public void setCantHabitaciones(int cantHabitaciones) {
        this.cantHabitaciones = cantHabitaciones;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "cliente=" + cliente +
                ", habitacion=" + habitacion +
                ", entrada=" + entrada +
                ", salida=" + salida +
                ", horaLlegada='" + horaLlegada + '\'' +
                ", cantNinos=" + cantNinos +
                ", cantAdultos=" + cantAdultos +
                ", cantHabitaciones=" + cantHabitaciones +
                '}';
    }
}
