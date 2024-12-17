package booking.Models;

import java.time.LocalDate;

public class Reserva {
    private String nombreCliente;
    private String apellidoCliente;
    private String emailCliente;
    private String telefono;
    private LocalDate entrada;
    private LocalDate salida;
    private int cantNinos;
    private int cantAdultos;
    private int cantHabitaciones;
    private String tipoHabitacion;
    private String horaLlegada;
    private String nacionalidad;
    private String nombreHotel;

    public Reserva(
            String nombreCliente,
            String apellidoCliente,
            String emailCliente,
            String telefono,
            LocalDate entrada,
            LocalDate salida,
            int cantNinos,
            int cantAdultos,
            int cantHabitaciones,
            String tipoHabitacion,
            String horaLlegada,
            String nacionalidad,
            String nombreHotel
            ) {
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.emailCliente = emailCliente;
        this.telefono = telefono;
        this.entrada = entrada;
        this.salida = salida;
        this.cantNinos = cantNinos;
        this.cantAdultos = cantAdultos;
        this.cantHabitaciones = cantHabitaciones;
        this.tipoHabitacion = tipoHabitacion;
        this.horaLlegada = horaLlegada;
        this.nacionalidad = nacionalidad;
        this.nombreHotel = nombreHotel;
    }

    public String getNombreHotel() {
        return nombreHotel;
    }

    public void setNombreHotel(String nombreHotel) {
        this.nombreHotel = nombreHotel;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getApellidoCliente() {
        return apellidoCliente;
    }

    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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

    public String getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public String getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(String horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "nombreCliente='" + nombreCliente + '\'' +
                ", apellidoCliente='" + apellidoCliente + '\'' +
                ", emailCliente='" + emailCliente + '\'' +
                ", telefono='" + telefono + '\'' +
                ", entrada=" + entrada +
                ", salida=" + salida +
                ", cantNinos=" + cantNinos +
                ", cantAdultos=" + cantAdultos +
                ", cantHabitaciones=" + cantHabitaciones +
                ", tipoHabitacion='" + tipoHabitacion + '\'' +
                ", horaLlegada='" + horaLlegada + '\'' +
                ", nacionalidad='" + nacionalidad + '\'' +
                ", nombreHotel='" + nombreHotel + '\'' +
                '}';
    }

}
