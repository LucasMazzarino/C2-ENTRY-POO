package booking.Interface;

import booking.Models.Hotel;
import booking.Models.TipoAlojamiento;

import java.time.LocalDate;
import java.util.List;

public interface IHotelService {
    List<Hotel> buscarHotel(String ciudad, TipoAlojamiento tipoAlojamiento, LocalDate inicioHospedaje, LocalDate finHospedaje, int cantAdultos, int cantNinos, int cantHabitaciones);
}
