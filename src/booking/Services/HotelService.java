package booking.Services;
import booking.Models.Hotel;
import booking.Models.TipoAlojamiento;
import booking.Models.Habitacion;
import booking.Interface.IHotelService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HotelService implements IHotelService {
    private List<Hotel> hoteles;

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
}