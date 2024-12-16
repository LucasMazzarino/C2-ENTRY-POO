package main.java.com.booking;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static Scanner entradaUsuario = new Scanner(System.in);

    static String[][][] hoteles = {
            {
                    {"Hotel Montevideo", "Montevideo", "Hotel", "4"},
                    {"Económica", "150", "Habitación con vista a la ciudad, aire acondicionado, baño privado y acceso Wi-Fi gratuito", "2", "1", "12"},
                    {"Superior", "200", "2 camas dobles, baño en suite, vista al mar, desayuno incluido", "4", "2", "8"},
                    {"Presidencial", "350", "Habitación de lujo con jacuzzi, terraza privada, vista panorámica al mar y acceso a lounge VIP", "2", "0", "3"},
            },
            {
                    {"Hotel Atlántico", "Maldonado", "Hotel", "4.5"},
                    {"Económica", "90", "Habitación con cama doble, vista parcial al mar, aire acondicionado y televisión por cable", "2", "0", "15"},
                    {"Doble", "150", "2 camas individuales, baño privado, minibar, terraza con vista a la piscina", "2", "1", "10"},
                    {"Familiar", "210", "2 camas dobles, baño privado, vista al mar, servicio de limpieza diaria, acceso a la piscina", "4", "2", "6"},
            },
            {
                    {"Posada Las Aguas", "Piriápolis", "Posada", "5"},
                    {"Estándar", "120", "Habitación con cama doble, balcón con vista a las montañas, aire acondicionado y acceso a la piscina", "2", "0", "10"},
                    {"Suite", "220", "Suite con cama king size, jacuzzi, vista al mar, desayuno incluido y acceso a un spa", "2", "1", "4"},
                    {"Familiar", "300", "2 camas dobles, baño privado con jacuzzi, vista al mar y acceso exclusivo a la playa", "4", "3", "3"},
            },
            {
                    {"Hotel Punta Verde", "Punta del Este", "Hotel", "4"},
                    {"Económica", "180", "Habitación con cama doble, vista a la ciudad, baño privado, televisión y aire acondicionado", "2", "1", "20"},
                    {"Doble", "250", "Habitación con cama matrimonial, vista al mar, terraza privada, acceso a gimnasio y spa", "2", "1", "10"},
                    {"Suite", "450", "Suite de lujo con 2 camas dobles, salón de estar, jacuzzi privado y acceso directo a la playa", "4", "3", "5"},
            },
            {
                    {"Hotel Río Blanco", "Colonia del Sacramento", "Hotel", "4.5"},
                    {"Económica", "85", "Habitación con cama doble, baño privado, terraza con vista al río, aire acondicionado", "2", "0", "18"},
                    {"Superior", "160", "2 camas individuales, terraza con vista al río, acceso a piscina y desayuno incluido", "2", "1", "12"},
                    {"Familiar", "220", "2 camas dobles, sala de estar, terraza privada, piscina al aire libre, vista al río", "4", "2", "6"},
            },
            {
                    {"Villa Del Sol", "La Paloma", "Hotel", "5"},
                    {"Económica", "70", "Cabaña pequeña con 1 cama doble, cocina equipada, acceso a jardín y playa cercana", "2", "0", "8"},
                    {"Confort", "110", "Cabaña con 2 camas dobles, terraza privada, acceso a la piscina y área de parrillada", "4", "1", "5"},
                    {"Familiar", "180", "Cabaña con 3 habitaciones, 2 baños, jardín privado y vista al mar", "6", "4", "3"},
            },

            {
                    {"Hotel Playero", "La Paloma", "Día de Sol", "4"},
                    {"Día de Sol","40", "Viaje a la playa Rancho Luna, Almuerzo y Cena incluidos, Refrigerio", "4", "3", "6"},
            },
            {
                    {"Hotel Mona", "Maldonado", "Día de Sol", "4.5"},
                    {"Día de Sol","50", "Viaje a la playa Playa Larga, Almuerzo y Cena incluidos, Refrigerio", "4", "3", "6"},
            },
            {
                    {"Hotel Serena", "Montevideo", "Día de Sol", "4.5"},
                    {"Día de Sol","50", "Viaje a la playa Playa Larga, Almuerzo y Cena incluidos, Refrigerio", "4", "3", "6"},
            },
            {
                    {"Hotel Maloja", "Montevideo", "Finca", "4.5"},
                    {"Finca","50", "Viaje a la playa Playa Larga, Almuerzo y Cena incluidos, Refrigerio", "4", "3", "6"},
            },
            {
                    {"Hotel Suit", "Montevideo", "Finca", "4.5"},
                    {"Finca","70", "Viaje a la playa Playa Larga, Almuerzo y Cena incluidos, Refrigerio", "4", "3", "6"},
            },
    };



    public static void main(String[] args) {


        String[][][][] reservas = new String[hoteles.length][][][];
        boolean[][][][] disponibilidad = new boolean[hoteles.length][][][];

        for(int i = 0; i < hoteles.length; i++) {
            int cantidadTipos = hoteles[i].length - 1;

            disponibilidad[i] = new boolean[cantidadTipos][][];
            reservas[i] = new String[cantidadTipos][][];

            for (int j = 0; j < cantidadTipos; j++) {
                int cantidadHabitaciones = Integer.parseInt(hoteles[i][j + 1][5]);

                disponibilidad[i][j] = new boolean[cantidadHabitaciones][31];
                reservas[i][j] = new String[cantidadHabitaciones][31];
            }
        }

        while (true) {
            System.out.println("\n--- Menú de Reservas ---");
            System.out.println("1. Buscar alojamientos");
            System.out.println("2. Comprobar disponibilidad");
            System.out.println("3. Hacer una reserva");
            System.out.println("4. Actualizar reserva");
            System.out.print("Elige una opción: ");
            int opcion = entradaUsuario.nextInt();
            entradaUsuario.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("\n--- Buscar Alojamiento ---");

                    System.out.println("Elige la ciudad:");
                    boolean[] ciudadesMostradas = new boolean[hoteles.length];

                    for (int i = 0; i < hoteles.length; i++) {
                        String ciudad = hoteles[i][0][1];  // Ciudad actual
                        boolean yaMostrada = false;

                        for (int j = 0; j < i; j++) {
                            if (hoteles[j][0][1].equalsIgnoreCase(ciudad)) {
                                yaMostrada = true;
                                break;
                            }
                        }

                        if (!yaMostrada) {
                            System.out.println((i + 1) + ". " + ciudad);
                            ciudadesMostradas[i] = true;
                        }
                    }

                    int ciudadSeleccionada = -1;
                    while (ciudadSeleccionada < 1 || ciudadSeleccionada > hoteles.length) {
                        System.out.print("Introduce el número de la ciudad: ");
                        ciudadSeleccionada = entradaUsuario.nextInt();
                    }

                    String ciudad = hoteles[ciudadSeleccionada - 1][0][1];

                    System.out.println("\nElige el tipo de alojamiento:");
                    String[] tipos = {"Hotel", "Apartamento", "Finca", "Día de Sol"};
                    for (int i = 0; i < tipos.length; i++) {
                        System.out.println((i + 1) + ". " + tipos[i]);
                    }

                    int tipoSeleccionado = -1;
                    while (tipoSeleccionado < 1 || tipoSeleccionado > tipos.length) {
                        System.out.print("Introduce el tipo de alojamiento: ");
                        tipoSeleccionado = entradaUsuario.nextInt();
                    }

                    String alojamiento = tipos[tipoSeleccionado - 1];
                    entradaUsuario.nextLine();

                    if (!alojamiento.equalsIgnoreCase("Día de Sol")) {
                        int entrada = 0;
                        while (entrada > 31 || entrada < 1) {
                            System.out.print("Día de entrada (1-31): ");
                            entrada = entradaUsuario.nextInt();
                        }

                        int salida = 0;
                        while (salida > 31 || salida < 1 || salida <= entrada) {
                            System.out.print("Día de salida (1-31): ");
                            salida = entradaUsuario.nextInt();
                        }

                        int adultos = 0;
                        while (adultos < 1 || adultos > 100) {
                            System.out.print("Número de adultos: ");
                            adultos = entradaUsuario.nextInt();
                        }

                        int ninios = -1;
                        while (ninios < 0 || ninios > 100) {
                            System.out.print("Número de niños: ");
                            ninios = entradaUsuario.nextInt();
                        }

                        int habitaciones = 0;
                        while (habitaciones < 1 || habitaciones > 5) {
                            System.out.print("Cantidad de habitaciones (1-5): ");
                            habitaciones = entradaUsuario.nextInt();
                        }

                        buscarAlojamientos(ciudad, disponibilidad, alojamiento, entrada, salida, adultos, ninios, habitaciones);
                    }
                    else {
                        System.out.println("\n--- Hoteles 'Día de Sol' disponibles ---");
                        for (int i = 0; i < hoteles.length; i++) {
                            if (hoteles[i][0][2].equalsIgnoreCase("Día de Sol") && hoteles[i][0][1].equalsIgnoreCase(ciudad)) {
                                System.out.println("Nombre: " + hoteles[i][0][0]);
                                System.out.println("Ubicación: " + hoteles[i][0][1]);
                                System.out.println("Precio por persona: $" + hoteles[i][1][1]);
                                System.out.println("Actividades: " + hoteles[i][1][2] + "\n");
                            }
                        }
                    }
                    break;
                case 2:
                    System.out.println("\n--- ver Disponibilidad ---");

                    System.out.println("Elige el hotel:");
                    for (int i = 0; i < hoteles.length; i++) {
                        System.out.println((i + 1) + ". " + hoteles[i][0][0]);
                    }

                    int hotelSeleccionado = -1;
                    while (hotelSeleccionado < 1 || hotelSeleccionado > hoteles.length) {
                        System.out.print("Introduce el número del hotel: ");
                        hotelSeleccionado = entradaUsuario.nextInt();
                    }

                    // Obtener el nombre del hotel seleccionado
                    String hotel = hoteles[hotelSeleccionado - 1][0][0];

                    // Solicitar el día de inicio del hospedaje
                    int entrada = 0;
                    while (entrada < 1 || entrada > 31) {
                        System.out.print("Día de inicio del hospedaje (1-31): ");
                        entrada = entradaUsuario.nextInt();
                    }

                    // Solicitar el día de finalización del hospedaje
                    int salida = 0;
                    while (salida < 1 || salida > 31 || salida <= entrada) {
                        System.out.print("Día de finalización del hospedaje (1-31): ");
                        salida = entradaUsuario.nextInt();
                    }

                    // Solicitar la cantidad de adultos
                    int adultos = 0;
                    while (adultos < 1 || adultos > 100) {
                        System.out.print("Número de adultos: ");
                        adultos = entradaUsuario.nextInt();
                    }

                    // Solicitar la cantidad de niños
                    int ninios = -1;
                    while (ninios < 0 || ninios > 100) {
                        System.out.print("Número de niños: ");
                        ninios = entradaUsuario.nextInt();
                    }

                    // Solicitar la cantidad de habitaciones
                    int habitaciones = 0;
                    while (habitaciones < 1 || habitaciones > 5) {
                        System.out.print("Cantidad de habitaciones (1-5): ");
                        habitaciones = entradaUsuario.nextInt();
                    }

                    comprobarDisponibilidad(hotel, entrada, salida, adultos, ninios, habitaciones);
                    break;

                case 3:
                    System.out.println("\n--- Realizar Reserva ---");

                    System.out.println("Elige el hotel:");
                    for (int i = 0; i < hoteles.length; i++) {
                        System.out.println((i + 1) + ". " + hoteles[i][0][0]);
                    }

                    int hotelSeleccionadoReserva = -1;
                    while (hotelSeleccionadoReserva < 1 || hotelSeleccionadoReserva > hoteles.length) {
                        System.out.print("Introduce el número del hotel: ");
                        hotelSeleccionadoReserva = entradaUsuario.nextInt();
                    }

                    String hotelReserva = hoteles[hotelSeleccionadoReserva - 1][0][0];

                    System.out.println("\n--- Tipos de habitaciones disponibles ---");
                    for (int j = 1; j < hoteles[hotelSeleccionadoReserva - 1].length; j++) {
                        System.out.println(j + ". " + hoteles[hotelSeleccionadoReserva - 1][j][0]);
                    }

                    int tipoHabitacionSeleccionado = 0;
                    while (tipoHabitacionSeleccionado < 1 || tipoHabitacionSeleccionado >= hoteles[hotelSeleccionadoReserva - 1].length) {
                        System.out.print("Introduce el número del tipo de habitación: ");
                        tipoHabitacionSeleccionado = entradaUsuario.nextInt();
                    }

                    tipoHabitacionSeleccionado -= 1;

                    int habitacionesDisponibles = Integer.parseInt(hoteles[hotelSeleccionadoReserva - 1][tipoHabitacionSeleccionado + 1][5]);
                    if (habitacionesDisponibles <= 0) {
                        System.out.println("Lo sentimos, no hay habitaciones disponibles para este tipo.");
                        break;
                    }
                    entradaUsuario.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = entradaUsuario.nextLine();
                    System.out.print("Apellido: ");
                    String apellido = entradaUsuario.nextLine();
                    System.out.print("Email: ");
                    String email = entradaUsuario.nextLine();
                    System.out.print("Nacionalidad: ");
                    String nacionalidad = entradaUsuario.nextLine();
                    System.out.print("Número de teléfono: ");
                    String telefono = entradaUsuario.nextLine();
                    System.out.print("Hora que llegada (HH:mm): ");
                    String horaLlegada = entradaUsuario.nextLine();
                    realizarReserva(
                            hotelReserva,
                            tipoHabitacionSeleccionado,
                            nombre,
                            apellido,
                            email,
                            nacionalidad,
                            telefono,
                            horaLlegada,
                            disponibilidad,
                            reservas
                    );
                    break;
                case 4:
                    System.out.println("\n--- Actualizar Reserva ---");

                    // Solicitar autenticación
                    System.out.print("Ingrese su correo: ");
                    String correo = entradaUsuario.next();
                    System.out.print("Ingrese su fecha de nacimiento (DD/MM/AAAA): ");
                    String fechaNacimiento = entradaUsuario.next();

                    actualizarReserva(correo, fechaNacimiento, disponibilidad, reservas, hoteles);
                    break;
                default:
                    System.out.println("Opción incorrecta.");
            }
        }
    }

    public static void buscarAlojamientos(String ciudad, boolean[][][][] disponibilidad, String alojamiento, int entrada, int salida, int adultos, int ninios, int habitaciones) {
        if (alojamiento.equalsIgnoreCase("Día de Sol")) {
            System.out.println("\nAlojamientos 'Día de Sol' disponibles:");
            boolean encontrado = false;

            for (int i = 0; i < hoteles.length; i++) {
                if (hoteles[i][0][1].equalsIgnoreCase(ciudad) && hoteles[i][0][2].equalsIgnoreCase("Día de Sol")) {
                    encontrado = true;
                    // Mostrar detalles del hotel "Día de Sol"
                    System.out.println("- " + hoteles[i][0][0] + " - Ubicación: " + hoteles[i][0][1]);
                    System.out.println("Descripción de la actividad: " + hoteles[i][1][2]);
                    System.out.println("Precio por persona: $" + hoteles[i][1][1]);
                    System.out.println("Actividades: " + hoteles[i][1][2]);
                    System.out.println("Número de personas permitidas: " + hoteles[i][1][3]);
                    System.out.println("Total por persona: $" + hoteles[i][1][1]);
                    System.out.println();
                }
            }

            if (!encontrado) {
                System.out.println("No se encontró alojamiento disponible para 'Día de Sol' en la ciudad seleccionada.");
            }
        } else {
            System.out.println("\nAlojamientos disponibles:");
            boolean encontrado = false;

            for (int i = 0; i < hoteles.length; i++) {
                // Verificar si el hotel corresponde a la ciudad y el tipo de alojamiento
                if (hoteles[i][0][1].equalsIgnoreCase(ciudad) && hoteles[i][0][2].equalsIgnoreCase(alojamiento)) {
                    for (int j = 0; j < hoteles[i].length - 1; j++) {
                        int capacidadAdultos = Integer.parseInt(hoteles[i][j + 1][3]);
                        int capacidadNinios = Integer.parseInt(hoteles[i][j + 1][4]);

                        // Verificar si la habitación tiene suficiente capacidad para adultos y niños
                        if (capacidadAdultos >= adultos && capacidadNinios >= ninios) {
                            int habitacionesDisponibles = 0;

                            // Verificar la disponibilidad de las habitaciones para las fechas seleccionadas
                            for (int k = 0; k < disponibilidad[i][j].length; k++) {
                                boolean disponible = true;

                                for (int d = entrada - 1; d < salida; d++) {
                                    if (disponibilidad[i][j][k][d]) {
                                        disponible = false;
                                        break;
                                    }
                                }

                                if (disponible) {
                                    habitacionesDisponibles++;
                                    if (habitacionesDisponibles >= habitaciones) {
                                        break;
                                    }
                                }
                            }

                            if (habitacionesDisponibles >= habitaciones) {
                                encontrado = true;
                                double precioPorNoche = Double.parseDouble(hoteles[i][j + 1][1]);
                                double precioTotal = precioPorNoche * habitaciones * (salida - entrada);
                                if (salida >= 26 && salida <= 31) {
                                    precioTotal *= 1.15;
                                }
                                if ((entrada >= 10 && entrada <= 15) || (salida >= 10 && salida <= 15)) {
                                    precioTotal *= 1.10;
                                }
                                if ((entrada >= 5 && entrada <= 10) || (salida >= 5 && salida <= 10)) {
                                    precioTotal *= 0.92;
                                }

                                // Mostrar los detalles del alojamiento
                                System.out.println("- " + hoteles[i][0][0] + " - Ubicación: " + hoteles[i][0][1] + ", " + hoteles[i][0][3] + " estrellas");
                                System.out.println("Tipo de habitación: " + hoteles[i][j + 1][0] + ", Precio por noche: $" + precioPorNoche);
                                System.out.println("Descripción: " + hoteles[i][j + 1][2]);

                                // Mostrar más detalles si no es "Día de Sol"
                                if (!alojamiento.equalsIgnoreCase("Día de Sol")) {
                                    System.out.println("Habitaciones disponibles: " + habitacionesDisponibles);
                                    System.out.println("Total noches: " + (salida - entrada));
                                }

                                System.out.println("Precio total: $" + precioTotal);
                                break;
                            }
                        }
                    }
                }
            }

            if (!encontrado) {
                System.out.println("No se encontró alojamiento disponible.");
            }
        }
    }


    //Funcion 2
    public static void comprobarDisponibilidad(String hotel, int entrada, int salida, int adultos, int ninios, int habitaciones) {

        System.out.println("\nBuscar Habitaciones:");
        boolean encontrado = false;

        for (int i = 0; i < hoteles.length; i++) {
            if (hoteles[i][0][0].equalsIgnoreCase(hotel)) {
                encontrado = true;

                for (int j = 1; j < hoteles[i].length; j++) {
                    String tipo = hoteles[i][j][0];
                    int precio = Integer.parseInt(hoteles[i][j][1]);
                    int capacidadA = Integer.parseInt(hoteles[i][j][3]);
                    int capacidadM = Integer.parseInt(hoteles[i][j][4]);
                    int habitacionesDisponibles = Integer.parseInt(hoteles[i][j][5]);

                    // Verificar capacidad de adultos y niños, y si hay suficientes habitaciones disponibles
                    if (adultos <= capacidadA && ninios <= capacidadM && habitacionesDisponibles >= habitaciones) {
                        System.out.println("Hotel: " + hotel);
                        System.out.println("Tipo de habitación: " + tipo);
                        System.out.println("Precio por habitación: " + precio);
                        System.out.println("Descripción: " + hoteles[i][j][2]);
                        System.out.println("Capacidad adultos: " + capacidadA);
                        System.out.println("Capacidad niños: " + capacidadM);
                        System.out.println("Habitaciones disponibles: " + habitacionesDisponibles);
                        System.out.println("Fechas: Entrada: " + entrada + ", Salida: " + salida);
                        System.out.println("-------------------------------");
                    }
                }
            }
        }
    }

    //Funcion 3
    public static int buscarHotel(String nombreHotel) {
        for (int i = 0; i < hoteles.length; i++) {
            if (hoteles[i][0][0].equalsIgnoreCase(nombreHotel)) {
                return i;
            }
        }
        return -1;  // En caso de que no se encuentre
    }

    public static void realizarReserva(String hotel, int tipoHabitacion, String nombre, String apellido, String email, String nacionalidad, String telefono, String horaLlegada, boolean[][][][] disponibilidad, String[][][][] reservas) {
        // Buscar el índice del hotel
        int hotelIndex = buscarHotel(hotel);
        if (hotelIndex == -1) {
            System.out.println("Error: Hotel no encontrado.");
            return;
        }

        // Verificar disponibilidad
        int habitacionesDisponibles = Integer.parseInt(hoteles[hotelIndex][tipoHabitacion][5]);
        if (habitacionesDisponibles <= 0) {
            System.out.println("Lo sentimos, no hay habitaciones disponibles.");
            return;
        }

        // Reducir la disponibilidad de habitaciones
        habitacionesDisponibles--;
        hoteles[hotelIndex][tipoHabitacion][5] = String.valueOf(habitacionesDisponibles);

        // Inicializar la matriz de reservas si está vacía
        if (reservas[hotelIndex] == null) {
            reservas[hotelIndex] = new String[hoteles[hotelIndex].length][][]; // Asegura que cada hotel tenga reservas para todas las habitaciones
        }

        if (reservas[hotelIndex][tipoHabitacion] == null) {
            reservas[hotelIndex][tipoHabitacion] = new String[0][]; // Inicializa la matriz de reservas para el tipo de habitación
        }

        String[] nuevaReserva = {nombre, apellido, email, nacionalidad, telefono, horaLlegada};

        int reservasActuales = reservas[hotelIndex][tipoHabitacion].length;
        String[][] nuevasReservas = new String[reservasActuales + 1][];

        System.arraycopy(reservas[hotelIndex][tipoHabitacion], 0, nuevasReservas, 0, reservasActuales);

        nuevasReservas[reservasActuales] = nuevaReserva;

        reservas[hotelIndex][tipoHabitacion] = nuevasReservas;

        System.out.println("\n¡Reserva realizada con éxito!");
        System.out.println("Detalles de la reserva:");
        System.out.println("Hotel: " + hotel);
        System.out.println("Tipo de habitación: " + hoteles[hotelIndex][tipoHabitacion][0]);
        System.out.println("Nombre: " + nombre + " " + apellido);
        System.out.println("Email: " + email);
        System.out.println("Nacionalidad: " + nacionalidad);
        System.out.println("Teléfono: " + telefono);
        System.out.println("Hora de llegada: " + horaLlegada);
        System.out.println("-------------------------------");

        System.out.println("Reserva guardada: " + Arrays.toString(reservas[hotelIndex][tipoHabitacion][reservasActuales]));
    }

    //metodo 4

    public static void actualizarReserva(String correo, String fechaNacimiento, boolean[][][][] disponibilidad, String[][][][] reservas, String[][][] hoteles) {
        int hotelIndex = -1, tipoHabitacionIndex = -1, reservaIndex = -1, diaIndex = -1;
        boolean reservaEncontrada = false;

        for (int i = 0; i < reservas.length; i++) {
            for (int j = 0; j < reservas[i].length; j++) {
                for (int k = 0; k < reservas[i][j].length; k++) {
                    for (int d = 0; d < reservas[i][j][k].length; d++) {
                        if (reservas[i][j][k][d] != null && reservas[i][j][k][d].contains(correo.toLowerCase())) {
                            reservaEncontrada = true;
                            System.out.println("Reserva encontrada: " + reservas[i][j][k][d]);
                            hotelIndex = i;
                            tipoHabitacionIndex = j;
                            reservaIndex = k;
                            diaIndex = d;

                            // Mostrar detalles de la reserva
                            String[] reservaActual = reservas[hotelIndex][tipoHabitacionIndex][reservaIndex];
                            System.out.printf(
                                    "\nReserva:\nNombre: %s %s\nCorreo: %s\nAlojamiento: %s\nTipo de habitación: %s\nHora de llegada: %s\n",
                                    reservaActual[0], reservaActual[1], reservaActual[2],
                                    hoteles[hotelIndex][0][0], hoteles[hotelIndex][tipoHabitacionIndex][0],
                                    reservaActual[5]
                            );
                        }
                    }
                }
            }
        }
        // Validar si se encontró alguna reserva
        if (!reservaEncontrada) {
            System.out.println("No se encontraron reservas con el correo proporcionado.");
            return;
        }

        // Preguntar al usuario qué desea actualizar
        System.out.println("\n¿Qué desea actualizar?");
        System.out.println("1. Cambiar de habitación");
        System.out.println("2. Cambiar de alojamiento");
        System.out.print("Seleccione una opción: ");
        int opcion = entradaUsuario.nextInt();

        switch (opcion) {
            case 1:
                modificarHabitacion(hotelIndex, tipoHabitacionIndex, reservaIndex, reservas, disponibilidad);
                break;
            case 2:
                cambiarAlojamiento(hotelIndex, tipoHabitacionIndex, diaIndex, reservaIndex, reservas, disponibilidad, hoteles);
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    public static void modificarHabitacion(int idHotel, int tipoAlojamientoActual, int numHabitacion, String[][][][] reservasExistentes, boolean[][][][] estadoDisponibilidad) {
        System.out.println("\n=== Modificación de Habitacion ===");
        System.out.print("Por favor, indique el día deseado para verificar disponibilidad (1-31): ");
        int diaSeleccionado = entradaUsuario.nextInt() - 1;

        if (diaSeleccionado < 0 || diaSeleccionado > 30) {
            System.out.println("Día no válido. Debe estar entre 1 y 31.");
            return;
        }
        System.out.println("Habitaciones libres para el día especificado:");

        for (int indiceTipo = 0; indiceTipo < estadoDisponibilidad[idHotel].length; indiceTipo++) {
            int cantidadDisponibles = 0;
            for (int indiceHabitacion = 0; indiceHabitacion < estadoDisponibilidad[idHotel][indiceTipo].length; indiceHabitacion++) {
                if (!estadoDisponibilidad[idHotel][indiceTipo][indiceHabitacion][diaSeleccionado]) {
                    cantidadDisponibles++;
                }
            }

            // Si hay habitaciones disponibles de este tipo, mostrar detalles
            if (cantidadDisponibles > 0) {
                String nombreTipoHabitacion = hoteles[idHotel][indiceTipo + 1][0];
                System.out.println(nombreTipoHabitacion + ":");
                System.out.println("  Detalles: " + hoteles[idHotel][indiceTipo + 1][2]);
                System.out.println("  Tarifa por noche: $" + hoteles[idHotel][indiceTipo + 1][1]);
                System.out.println("  Total disponibles: " + cantidadDisponibles + "\n");
            }
        }

        System.out.print("Seleccione el número del tipo de habitación que desea: ");
        int tipoElegido = entradaUsuario.nextInt() - 1;

        if (tipoElegido >= 0 && tipoElegido < estadoDisponibilidad[idHotel].length) {
            System.out.print("Elija una habitación específica (1 a " + estadoDisponibilidad[idHotel][tipoElegido].length + "): ");
            int habitacionElegida = entradaUsuario.nextInt() - 1;

            if (habitacionElegida >= 0 && habitacionElegida < estadoDisponibilidad[idHotel][tipoElegido].length && !estadoDisponibilidad[idHotel][tipoElegido][habitacionElegida][diaSeleccionado]) {
                estadoDisponibilidad[idHotel][tipoAlojamientoActual][habitacionElegida][diaSeleccionado] = true;
                reservasExistentes[idHotel][tipoElegido][habitacionElegida][diaSeleccionado] = "Modificada";
                System.out.println("La habitación fue modificada correctamente.");
            } else {
                System.out.println("La habitación seleccionada no está disponible.");
            }
        } else {
            System.out.println("La selección realizada no es válida. Por favor, inténtelo de nuevo.");
        }
    }


    public static void cambiarAlojamiento(
            int hotelActualIndex, int tipoHabitacionIndex, int habitacionIndex, int diaIndex,
            String[][][][] reservasArray, boolean[][][][] disponibilidadArray, String[][][] hotelesArray) {
        System.out.println("\n--- Cambio de Alojamiento ---");

        reservasArray[hotelActualIndex][tipoHabitacionIndex][habitacionIndex][diaIndex] = null;
        System.out.println("Habitación actual liberada. Proceda a seleccionar un nuevo alojamiento.");


        int nuevoHotelIndex = obtenerSeleccionDeHotel(hotelActualIndex, hotelesArray);

        String tipoAlojamiento = obtenerTipoDeAlojamiento();
        System.out.printf("\nSeleccione una habitación en %s (%s):\n",
                hotelesArray[nuevoHotelIndex][0][0], tipoAlojamiento);

        int nuevaHabitacionIndex = obtenerNumeroHabitacion();
        String datosCliente = obtenerDatosDelCliente(hotelesArray[nuevoHotelIndex][0][0], tipoAlojamiento);
        reservasArray[nuevoHotelIndex][tipoHabitacionIndex][nuevaHabitacionIndex][diaIndex] = datosCliente;
        System.out.println("Reserva realizada con éxito en el nuevo alojamiento.");
    }

    private static int obtenerSeleccionDeHotel(int hotelActualIndex, String[][][] hotelesArray) {
        int hotelSeleccionado = -1;
        Scanner scanner = new Scanner(System.in);

        while (hotelSeleccionado < 0 || hotelSeleccionado >= hotelesArray.length || hotelSeleccionado == hotelActualIndex) {
            System.out.println("Hoteles disponibles:");
            for (int i = 0; i < hotelesArray.length; i++) {
                if (i != hotelActualIndex) {
                    System.out.printf("%d. %s \n", i + 1, hotelesArray[i][0][0]);
                }
            }
            System.out.print("Seleccione el número del hotel: ");
            hotelSeleccionado = scanner.nextInt() - 1;

            if (hotelSeleccionado < 0 || hotelSeleccionado >= hotelesArray.length) {
                System.out.println("Por favor, elija un número válido.");
            } else if (hotelSeleccionado == hotelActualIndex) {
                System.out.println("No puede seleccionar el hotel actual. Elija otro.");
            }
        }

        return hotelSeleccionado;
    }

    private static String obtenerTipoDeAlojamiento() {
        Scanner scanner = new Scanner(System.in);
        int opcionSeleccionada;

        System.out.println("\nTipos de alojamiento disponibles:");
        System.out.println("1. Hotel");
        System.out.println("2. Finca");
        System.out.println("3. Día de Sol");

        do {
            System.out.print("Seleccione el tipo de alojamiento: ");
            opcionSeleccionada = scanner.nextInt();
        } while (opcionSeleccionada < 1 || opcionSeleccionada > 3);

        return (opcionSeleccionada == 1) ? "Hotel"
                : (opcionSeleccionada == 2) ? "Finca"
                : "Día de Sol";
    }

    private static int obtenerNumeroHabitacion() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el número de la habitación: ");
        return scanner.nextInt() - 1;
    }

    private static String obtenerDatosDelCliente(String hotel, String tipoAlojamiento) {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine(); // Limpiar buffer

        System.out.print("Ingrese su Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese su Apellido: ");
        String apellido = scanner.nextLine();

        System.out.print("Ingrese su Email: ");
        String email = scanner.nextLine();

        System.out.print("Ingrese su Nacionalidad: ");
        String nacionalidad = scanner.nextLine();

        System.out.print("Ingrese su Número de Teléfono: ");
        String telefono = scanner.nextLine();

        System.out.print("Dia que ingresa: ");
        String entrada = scanner.nextLine();

        System.out.print("Dia que sale: ");
        String salida = scanner.nextLine();

        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s",
                nombre, apellido, email, hotel, tipoAlojamiento, nacionalidad, telefono, entrada, salida);
    }

}
