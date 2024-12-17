package booking;

import booking.Models.*;
import booking.Services.HotelService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        List<Hotel> hoteles = new ArrayList<>();

        hoteles.add(new Hotel("Hotel Montevideo", "Detalles del Hotel Montevideo", "Montevideo", 4.5, TipoAlojamiento.HOTEL));
        hoteles.add(new Hotel("Hotel Maldonado", "Detalles del Hotel Maldonado", "Maldonado", 4.0, TipoAlojamiento.HOTEL));
        hoteles.add(new Hotel("Hotel Rocha", "Detalles del Hotel Rocha", "Rocha", 3.5, TipoAlojamiento.HOTEL));
        hoteles.add(new Hotel("Hotel Paloma", "Detalles del Hotel Paloma", "Paloma", 4.2, TipoAlojamiento.HOTEL));
        hoteles.add(new Hotel("Hotel Punta del Este", "Detalles del Hotel Punta del Este", "Punta del Este", 5.0, TipoAlojamiento.HOTEL));

        Hotel hotelDiaDeSol = new Hotel("Hotel Sol", "Detalles del Hotel Sol", "Sol City", 4.8, TipoAlojamiento.DIA_DE_SOL);
        hotelDiaDeSol.setActividades(List.of("Natación", "Tenis", "Yoga", "Almuerzo incluido"));
        hoteles.add(hotelDiaDeSol);

        hoteles.add(new Hotel("Apartamento Montevideo", "Detalles del Apartamento Montevideo", "Montevideo", 4.0, TipoAlojamiento.APARTAMENTO));
        hoteles.add(new Hotel("Apartamento Maldonado", "Detalles del Apartamento Maldonado", "Maldonado", 4.3, TipoAlojamiento.APARTAMENTO));
        hoteles.add(new Hotel("Apartamento Rocha", "Detalles del Apartamento Rocha", "Rocha", 4.1, TipoAlojamiento.APARTAMENTO));

        hoteles.add(new Hotel("Finca Montevideo", "Detalles de la Finca Montevideo", "Montevideo", 4.5, TipoAlojamiento.FINCA));
        hoteles.add(new Hotel("Finca Maldonado", "Detalles de la Finca Maldonado", "Maldonado", 4.7, TipoAlojamiento.FINCA));
        hoteles.add(new Hotel("Finca Rocha", "Detalles de la Finca Rocha", "Rocha", 4.6, TipoAlojamiento.FINCA));

        for (Hotel hotel : hoteles) {
            List<Habitacion> habitaciones = new ArrayList<>();
            habitaciones.add(new HabitacionComun(50.0, "Habitación común", 30, 2, 2));
            habitaciones.add(new HabitacionDoble(80.0, "Habitación doble", 30, 2, 4));
            habitaciones.add(new HabitacionPremium(120.0, "Habitación premium", 30, 3, 4));
            habitaciones.add(new HabitacionEstrella(150.0, "Habitación estrella", 30, 4, 4));
            habitaciones.add(new HabitacionSuit(200.0, "Habitación suit", 30, 4, 6));
            hotel.setHabitaciones(habitaciones);
        }

        HotelService hotelService = new HotelService(hoteles);
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Buscar Hotel");
            System.out.println("2. Consultar disponibilidad");
            System.out.println("3. Realizar Reserva");
            System.out.println("4. Cambiar reserva");
            System.out.println("5. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    List<String> ciudades = List.of("Montevideo", "Maldonado", "Rocha", "Paloma", "Punta del Este", "Sol City");
                    System.out.println("Ciudades disponibles:");
                    for (int i = 0; i < ciudades.size(); i++) {
                        System.out.println((i + 1) + ". " + ciudades.get(i));
                    }
                    System.out.println("Ingrese el número de la ciudad:");
                    int ciudadIndex = scanner.nextInt() - 1;
                    scanner.nextLine();
                    String ciudad = ciudades.get(ciudadIndex);

                    System.out.println("Tipos de alojamiento disponibles:");
                    TipoAlojamiento[] tipos = TipoAlojamiento.values();
                    for (int i = 0; i < tipos.length; i++) {
                        System.out.println((i + 1) + ". " + tipos[i]);
                    }
                    System.out.println("Ingrese el número del tipo de alojamiento:");
                    int tipoIndex = scanner.nextInt() - 1;
                    scanner.nextLine();
                    TipoAlojamiento tipoAlojamiento = tipos[tipoIndex];

                    if (tipoAlojamiento == TipoAlojamiento.DIA_DE_SOL) {
                        hotelService.mostrarDiaDeSol(ciudad);
                    } else {
                        System.out.println("Ingrese la fecha de inicio de hospedaje (YYYY-MM-DD):");
                        LocalDate inicioHospedaje = LocalDate.parse(scanner.nextLine());
                        System.out.println("Ingrese la fecha de fin de hospedaje (YYYY-MM-DD):");
                        LocalDate finHospedaje = LocalDate.parse(scanner.nextLine());
                        System.out.println("Ingrese la cantidad de adultos:");
                        int cantAdultos = scanner.nextInt();
                        System.out.println("Ingrese la cantidad de niños:");
                        int cantNinos = scanner.nextInt();
                        System.out.println("Ingrese la cantidad de habitaciones:");
                        int cantHabitaciones = scanner.nextInt();

                        List<Hotel> hotelesDisponibles = hotelService.buscarHotel(ciudad, tipoAlojamiento, inicioHospedaje, finHospedaje, cantAdultos, cantNinos, cantHabitaciones);
                        System.out.println("Hoteles disponibles:");
                        for (Hotel hotel : hotelesDisponibles) {
                            System.out.println(hotelService.mostrarInformacion(hotel, inicioHospedaje, finHospedaje, cantHabitaciones));
                        }
                    }
                    break;
                case 2:
                    System.out.println("Seleccione un hotel:");
                    for (int i = 0; i < hoteles.size(); i++) {
                        System.out.println((i + 1) + ". " + hoteles.get(i).getNombre());
                    }
                    int hotelIndex = scanner.nextInt() - 1;
                    scanner.nextLine();
                    String nombreHotel = hoteles.get(hotelIndex).getNombre();

                    System.out.println("Ingrese la fecha de inicio de hospedaje (YYYY-MM-DD):");
                    LocalDate inicioHospedaje = LocalDate.parse(scanner.nextLine());
                    System.out.println("Ingrese la fecha de fin de hospedaje (YYYY-MM-DD):");
                    LocalDate finHospedaje = LocalDate.parse(scanner.nextLine());
                    System.out.println("Ingrese la cantidad de adultos:");
                    int adultos = scanner.nextInt();
                    System.out.println("Ingrese la cantidad de niños:");
                    int ninos = scanner.nextInt();
                    System.out.println("Ingrese la cantidad de habitaciones:");
                    int habitaciones = scanner.nextInt();

                    List<Habitacion> habitacionesDisponibles = hotelService.confirmarHabitaciones(nombreHotel, inicioHospedaje, finHospedaje, adultos, ninos, habitaciones);
                    System.out.println("Habitaciones disponibles:");
                    for (Habitacion habitacion : habitacionesDisponibles) {
                        System.out.println(habitacion);
                    }
                    break;
                case 3:
                    System.out.println("Seleccione un hotel:");
                    for (int i = 0; i < hoteles.size(); i++) {
                        System.out.println((i + 1) + ". " + hoteles.get(i).getNombre());
                    }
                    int hotelI = scanner.nextInt() - 1;
                    scanner.nextLine();
                    String nombreH = hoteles.get(hotelI).getNombre();

                    System.out.println("Ingrese su nombre:");
                    String nombreCliente = scanner.nextLine();
                    System.out.println("Ingrese su apellido:");
                    String apellidoCliente = scanner.nextLine();
                    System.out.println("Ingrese su email:");
                    String emailCliente = scanner.nextLine();
                    System.out.println("Ingrese su nacionalidad:");
                    String nacionalidad = scanner.nextLine();
                    System.out.println("Ingrese su número de teléfono:");
                    String telefono = scanner.nextLine();
                    System.out.println("Ingrese la fecha de inicio de hospedaje (YYYY-MM-DD):");
                    LocalDate entrada = LocalDate.parse(scanner.nextLine());
                    System.out.println("Ingrese la fecha de fin de hospedaje (YYYY-MM-DD):");
                    LocalDate salida = LocalDate.parse(scanner.nextLine());
                    System.out.println("Ingrese la cantidad de adultos:");
                    int cantAdultos = scanner.nextInt();
                    System.out.println("Ingrese la cantidad de niños:");
                    int cantNinos = scanner.nextInt();
                    System.out.println("Ingrese la cantidad de habitaciones:");
                    int cantHabitaciones = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Tipos de habitación disponibles:");
                    List<Habitacion> habitacioness = hoteles.get(hotelI).getHabitaciones();
                    for (int i = 0; i < habitacioness.size(); i++) {
                        System.out.println((i + 1) + ". " + habitacioness.get(i).getTipo());
                    }
                    System.out.println("Ingrese el número del tipo de habitación:");
                    int tipoHabitacionIndex = scanner.nextInt() - 1;
                    scanner.nextLine();
                    String tipoHabitacion = habitacioness.get(tipoHabitacionIndex).getTipo();

                    System.out.println("Ingrese la hora aproximada de llegada:");
                    String horaLlegada = scanner.nextLine();

                    hotelService.realizarReserva(nombreH, nombreCliente, apellidoCliente, emailCliente, nacionalidad, telefono, entrada, salida, cantNinos, cantAdultos, cantHabitaciones, tipoHabitacion, horaLlegada);
                    break;
                case 4:
                    System.out.println("Ingrese su email:");
                    String email = scanner.nextLine();
                    System.out.println("Ingrese su fecha de nacimiento (YYYY-MM-DD):");
                    LocalDate fechaNacimiento = LocalDate.parse(scanner.nextLine());

                    hotelService.actualizarReserva(email, fechaNacimiento);
                    break;
                case 5:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }

        scanner.close();
    }
}