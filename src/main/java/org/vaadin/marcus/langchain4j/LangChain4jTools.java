package org.vaadin.marcus.langchain4j;

import dev.langchain4j.agent.tool.Tool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.vaadin.marcus.service.BookingDetails;
import org.vaadin.marcus.service.FlightService;

import java.time.LocalDate;

@Component
public class LangChain4jTools {

    private static final Logger log = LoggerFactory.getLogger(LangChain4jTools.class);
    private final FlightService service;

    public LangChain4jTools(FlightService service) {
        this.service = service;
    }

    @Tool("""
            检索有关现有预订的信息，
            例如航班日期、预订状态、出发和到达机场以及预订舱位。
            """)
    public BookingDetails getBookingDetails(String bookingNumber, String firstName, String lastName) {
        return service.getBookingDetails(bookingNumber, firstName, lastName);
    }

    @Tool("""
            修改现有预订。
            这包括更改航班日期以及出发和到达机场。
            """)
    public String changeBooking(String bookingNumber, String firstName, String lastName,
                                LocalDate newFlightDate, String newDepartureAirport, String newArrivalAirport) {
        service.changeBooking(bookingNumber, firstName, lastName, newFlightDate, newDepartureAirport, newArrivalAirport);
        return "修改成功";
    }

    @Tool("""
            预订新航班。
            这包括新的航班日期以及出发和到达机场。
            """)
    public String createBooking(String firstName, String lastName,
                                LocalDate flightDate, String departureAirport, String arrivalAirport) {
        log.info("Creating booking for {} {} on {} from {} to {}", firstName, lastName, flightDate, departureAirport, arrivalAirport);
        throw new RuntimeException("抱歉，暂不支持此功能。");
    }

    @Tool("""
            取消现有预订。
            """)
    public String cancelBooking(String bookingNumber, String firstName, String lastName) {
        service.cancelBooking(bookingNumber, firstName, lastName);
        return "取消成功";
    }

}
