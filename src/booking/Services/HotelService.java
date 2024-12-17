package booking.Services;

import booking.Models.Hotel;
import booking.Models.TipoAlojamiento;
import booking.Models.Habitacion;
import booking.Interface.IHotelService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class HotelService implements IHotelService {
    private final List<Hotel> hoteles;

    public HotelService(List<Hotel> hoteles) {
        this.hoteles = hoteles;
    }

    @Override
    public List<Hotel> buscarHotel(String ciudad, TipoAlojamiento tipoAlojamiento, LocalDate inicioHospedaje, LocalDate finHospedaje, int cantAdultos, int cantNinos, int cantHabitaciones) {
        List<Hotel> hotelesDisponibles = new ArrayList<>();

        for (Hotel hotel : hoteles) {
            if (hotel.getCiudad().equalsIgnoreCase(ciudad) && hotel.getTipoAlojamiento() == tipoAlojamiento) {
                boolean disponible = true;
                int habitacionesDisponibles = 0;

                for (Habitacion habitacion : hotel.getHabitaciones()) {
                    if (habitacion.getCantAdultos() >= cantAdultos && habitacion.getCantNinos() >= cantNinos) {
                        for (int dia = inicioHospedaje.getDayOfMonth(); dia <= finHospedaje.getDayOfMonth(); dia++) {
                            if (!habitacion.isDisponible(dia)) {
                                disponible = false;
                                break;
                            }
                        }
                        if (disponible) {
                            habitacionesDisponibles++;
                            if (habitacionesDisponibles >= cantHabitaciones) {
                                hotelesDisponibles.add(hotel);
                                break;
                            }
                        }
                    }
                }
            }
        }

        return hotelesDisponibles;
    }

    public List<Habitacion> confirmarHabitaciones(String nombreHotel, LocalDate inicioHospedaje, LocalDate finHospedaje, int cantAdultos, int cantNinos, int cantHabitaciones) {
        List<Habitacion> habitacionesDisponibles = new ArrayList<>();

        for (Hotel hotel : hoteles) {
            if (hotel.getNombre().equalsIgnoreCase(nombreHotel)) {
                for (Habitacion habitacion : hotel.getHabitaciones()) {
                    if (habitacion.getCantAdultos() >= cantAdultos && habitacion.getCantNinos() >= cantNinos) {
                        boolean disponible = true;
                        for (int dia = inicioHospedaje.getDayOfMonth(); dia <= finHospedaje.getDayOfMonth(); dia++) {
                            if (!habitacion.isDisponible(dia)) {
                                disponible = false;
                                break;
                            }
                        }
                        if (disponible) {
                            habitacionesDisponibles.add(habitacion);
                        }
                    }
                }
            }
        }

        return habitacionesDisponibles;
    }

    public double calcularPrecioTotal(Hotel hotel, LocalDate inicioHospedaje, LocalDate finHospedaje, int cantHabitaciones) {
        double precioBase = hotel.getHabitaciones().stream()
                .mapToDouble(Habitacion::getTarifaPorNoche)
                .min()
                .orElse(0.0);

        long diasEstadia = ChronoUnit.DAYS.between(inicioHospedaje, finHospedaje) + 1;
        double precioTotal = precioBase * diasEstadia * cantHabitaciones;

        double aumento = 0.0;
        double descuento = 0.0;

        if (inicioHospedaje.getDayOfMonth() >= 25 || finHospedaje.getDayOfMonth() >= 25) {
            aumento = precioTotal * 0.15;
        } else if (inicioHospedaje.getDayOfMonth() >= 10 && finHospedaje.getDayOfMonth() <= 15) {
            aumento = precioTotal * 0.10;
        } else if (inicioHospedaje.getDayOfMonth() >= 5 && finHospedaje.getDayOfMonth() <= 10) {
            descuento = precioTotal * 0.08;
        }

        precioTotal += aumento - descuento;

        return precioTotal;
    }

    public String mostrarInformacion(Hotel hotel, LocalDate inicioHospedaje, LocalDate finHospedaje, int cantHabitaciones) {
        double precioTotal = calcularPrecioTotal(hotel, inicioHospedaje, finHospedaje, cantHabitaciones);
        double precioBase = hotel.getHabitaciones().stream()
                .mapToDouble(Habitacion::getTarifaPorNoche)
                .min()
                .orElse(0.0);
        long diasEstadia = ChronoUnit.DAYS.between(inicioHospedaje, finHospedaje) + 1;

        StringBuilder sb = new StringBuilder();
        sb.append("Nombre de Hotel: ").append(hotel.getNombre()).append("\n");
        sb.append("Calificación: ").append(hotel.getEstrellas()).append(" estrellas\n");
        sb.append("Precio por noche: ").append(precioBase).append("\n");
        sb.append("Precio calculado según los días de estadía: ").append(precioTotal).append("\n");

        double aumento = 0.0;
        double descuento = 0.0;

        if (inicioHospedaje.getDayOfMonth() >= 25 || finHospedaje.getDayOfMonth() >= 25) {
            aumento = precioTotal * 0.15;
            sb.append("Aumento por estadía en los últimos 5 días del mes: ").append(aumento).append("\n");
        } else if (inicioHospedaje.getDayOfMonth() >= 10 && finHospedaje.getDayOfMonth() <= 15) {
            aumento = precioTotal * 0.10;
            sb.append("Aumento por estadía del 10 al 15 del mes: ").append(aumento).append("\n");
        } else if (inicioHospedaje.getDayOfMonth() >= 5 && finHospedaje.getDayOfMonth() <= 10) {
            descuento = precioTotal * 0.08;
            sb.append("Descuento por estadía del 5 al 10 del mes: ").append(descuento).append("\n");
        }

        if (hotel.getTipoAlojamiento() == TipoAlojamiento.DIA_DE_SOL) {
            sb.append("Actividades: ").append(String.join(", ", hotel.getActividades())).append("\n");
        }

        return sb.toString();
    }

    public void mostrarDiaDeSol(String ciudad) {
        for (Hotel hotel : hoteles) {
            if (hotel.getCiudad().equalsIgnoreCase(ciudad) && hotel.getTipoAlojamiento() == TipoAlojamiento.DIA_DE_SOL) {
                System.out.println("Nombre de Hotel: " + hotel.getNombre());
                System.out.println("Ubicación: " + hotel.getCiudad());
                System.out.println("Precio por persona: " + hotel.getHabitaciones().get(0).getTarifaPorNoche());
                System.out.println("Actividades: " + String.join(", ", hotel.getActividades()));
                System.out.println();
            }
        }
    }
}