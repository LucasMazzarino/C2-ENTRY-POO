package booking.Services;

import booking.Models.Hotel;
import booking.Models.Reserva;
import booking.Models.TipoAlojamiento;
import booking.Models.Habitacion;
import booking.Interface.IHotelService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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

    public void realizarReserva(String nombreHotel, String nombreCliente, String apellidoCliente, String emailCliente, String nacionalidad, String telefono, LocalDate entrada, LocalDate salida, int cantNinos, int cantAdultos, int cantHabitaciones, String tipoHabitacion, String horaLlegada) {
        for (Hotel hotel : hoteles) {
            if (hotel.getNombre().equalsIgnoreCase(nombreHotel)) {
                Reserva reserva = new Reserva(nombreCliente, apellidoCliente, emailCliente, telefono, entrada, salida, cantNinos, cantAdultos, cantHabitaciones, tipoHabitacion, horaLlegada, nacionalidad, nombreHotel);
                hotel.getReservas().add(reserva);

                for (Habitacion habitacion : hotel.getHabitaciones()) {
                    if (habitacion.getTipo().equalsIgnoreCase(tipoHabitacion)) {
                        for (int dia = entrada.getDayOfMonth(); dia <= salida.getDayOfMonth(); dia++) {
                            habitacion.reservar(dia);
                        }
                        break;
                    }
                }
                System.out.println("Se ha realizado la reserva con éxito");
                System.out.println(mostrarInformacionReserva(reserva));
                return;
            }
        }
    }

    public void actualizarReserva(String email, LocalDate fechaNacimiento) {
        Scanner scanner = new Scanner(System.in);
        for (Hotel hotel : hoteles) {
            for (Reserva reserva : hotel.getReservas()) {
                if (reserva.getEmailCliente().equalsIgnoreCase(email)) {
                    System.out.println("Reserva encontrada:");
                    System.out.println(mostrarInformacionReserva(reserva));

                    System.out.println("¿Qué desea cambiar?");
                    System.out.println("1. Cambiar habitación");
                    System.out.println("2. Cambiar alojamiento");
                    int opcion = scanner.nextInt();
                    scanner.nextLine();

                    if (opcion == 1) {
                        System.out.println("Habitaciones disponibles:");
                        List<Habitacion> habitacionesDisponibles = confirmarHabitaciones(hotel.getNombre(), reserva.getEntrada(), reserva.getSalida(), reserva.getCantAdultos(), reserva.getCantNinos(), 1)
                                .stream()
                                .filter(h -> !h.getTipo().equalsIgnoreCase(reserva.getTipoHabitacion()))
                                .collect(Collectors.toList());

                        for (int i = 0; i < habitacionesDisponibles.size(); i++) {
                            System.out.println((i + 1) + ". " + habitacionesDisponibles.get(i).getTipo());
                        }
                        System.out.println("Seleccione la nueva habitación:");
                        int nuevaHabitacionIndex = scanner.nextInt() - 1;
                        scanner.nextLine();

                        reserva.setTipoHabitacion(habitacionesDisponibles.get(nuevaHabitacionIndex).getTipo());
                        System.out.println("La habitación ha sido cambiada con éxito.");
                    } else if (opcion == 2) {
                        System.out.println("Seleccione un nuevo hotel:");
                        for (int i = 0; i < hoteles.size(); i++) {
                            System.out.println((i + 1) + ". " + hoteles.get(i).getNombre());
                        }
                        int nuevoHotelIndex = scanner.nextInt() - 1;
                        scanner.nextLine();
                        String nuevoHotel = hoteles.get(nuevoHotelIndex).getNombre();

                        System.out.println("Tipos de habitación disponibles:");
                        List<Habitacion> habitaciones = hoteles.get(nuevoHotelIndex).getHabitaciones();
                        for (int i = 0; i < habitaciones.size(); i++) {
                            System.out.println((i + 1) + ". " + habitaciones.get(i).getTipo());
                        }
                        System.out.println("Ingrese el número del tipo de habitación:");
                        int tipoHabitacionIndex = scanner.nextInt() - 1;
                        scanner.nextLine();
                        String tipoHabitacion = habitaciones.get(tipoHabitacionIndex).getTipo();

                        realizarReserva(nuevoHotel, reserva.getNombreCliente(), reserva.getApellidoCliente(), reserva.getEmailCliente(), reserva.getNacionalidad(), reserva.getTelefono(), reserva.getEntrada(), reserva.getSalida(), reserva.getCantNinos(), reserva.getCantAdultos(), reserva.getCantHabitaciones(), tipoHabitacion, reserva.getHoraLlegada());
                        hotel.getReservas().remove(reserva);
                        System.out.println("La reserva ha sido cambiada de alojamiento con éxito.");
                    } else {
                        System.out.println("Opción no válida.");
                    }
                    return;
                }
            }
        }
        System.out.println("No se encontró ninguna reserva con los datos proporcionados.");
    }


    public String mostrarInformacionReserva(Reserva reserva) {
        StringBuilder sb = new StringBuilder();
        sb.append("Detalles de la Reserva:\n");
        sb.append("Nombre del Hotel: ").append(reserva.getNombreHotel()).append("\n");
        sb.append("Nombre del Cliente: ").append(reserva.getNombreCliente()).append(" ").append(reserva.getApellidoCliente()).append("\n");
        sb.append("Email: ").append(reserva.getEmailCliente()).append("\n");
        sb.append("Teléfono: ").append(reserva.getTelefono()).append("\n");
        sb.append("Fecha de Entrada: ").append(reserva.getEntrada()).append("\n");
        sb.append("Fecha de Salida: ").append(reserva.getSalida()).append("\n");
        sb.append("Cantidad de Adultos: ").append(reserva.getCantAdultos()).append("\n");
        sb.append("Cantidad de Niños: ").append(reserva.getCantNinos()).append("\n");
        sb.append("Cantidad de Habitaciones: ").append(reserva.getCantHabitaciones()).append("\n");
        sb.append("Tipo de Habitación: ").append(reserva.getTipoHabitacion()).append("\n");
        sb.append("Hora de Llegada: ").append(reserva.getHoraLlegada()).append("\n");
        sb.append("Nacionalidad: ").append(reserva.getNacionalidad()).append("\n");
        return sb.toString();
    }

}