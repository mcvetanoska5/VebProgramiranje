package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.EventBooking;
import mk.ukim.finki.wp.lab.repository.InMemory.InMemoryBookingRepository;
import mk.ukim.finki.wp.lab.service.EventBookingService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EventBookingServiceImpl implements EventBookingService {
    private  InMemoryBookingRepository eventBookings;
    public EventBookingServiceImpl(InMemoryBookingRepository eventBookings) {
        this.eventBookings = eventBookings;
    }
    @Override
    public EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets) {
        EventBooking tmp = new EventBooking(eventName,attendeeName,attendeeAddress,(long)numberOfTickets);
        eventBookings.addBooking(attendeeName,eventName,numberOfTickets,attendeeAddress);
        return tmp;
    }
    @Override
    public List<EventBooking> search(String text) {
        return eventBookings.search(text);
    }
}
