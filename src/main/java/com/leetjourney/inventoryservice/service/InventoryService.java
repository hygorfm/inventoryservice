package com.leetjourney.inventoryservice.service;

import com.leetjourney.inventoryservice.entity.Event;
import com.leetjourney.inventoryservice.entity.Venue;
import com.leetjourney.inventoryservice.repository.EventRepository;
import com.leetjourney.inventoryservice.repository.VenueRepository;
import com.leetjourney.inventoryservice.response.EventInventoryResponse;
import com.leetjourney.inventoryservice.response.VenueInventoryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class InventoryService {

    private final EventRepository eventRepository;
    private final VenueRepository venueRepository;

    @Autowired
    public InventoryService(final EventRepository eventRepository, final VenueRepository venueRepository) {
        this.eventRepository = eventRepository;
        this.venueRepository = venueRepository;
    }


    public List<EventInventoryResponse> getAllEvents() {
        final List<Event> events = eventRepository.findAll();

        return events.stream().map(event -> EventInventoryResponse.builder()
                .eventId(event.getId())
                .event(event.getName())
                .capacity(event.getLeftCapacity())
                .venue(event.getVenue())
                .ticketPrice(event.getTicketPrice())
                .build()).collect(Collectors.toList());
    }


    public EventInventoryResponse getEventInventory(final Long eventId) {
        final Event event = eventRepository.findById(eventId).orElse(null);
        if(event == null) {
            return null;
        }

        return EventInventoryResponse.builder()
                .eventId(event.getId())
                .event(event.getName())
                .capacity(event.getLeftCapacity())
                .venue(event.getVenue())
                .ticketPrice(event.getTicketPrice())
                .build();
    }


    public VenueInventoryResponse getVenueInformation(final Long venueId) {
        final Venue venue = venueRepository.findById(venueId).orElse(null);
        if (venue == null) {
            return null;
        }
        return VenueInventoryResponse.builder()
                .venueId(venue.getId())
                .venueName(venue.getName())
                .totalCapacity(venue.getTotalCapacity())
                .build();
    }


    public void updateEventCapacity(final Long eventId, final Long ticketsBooked) {
        final Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));
        if (event.getLeftCapacity() < ticketsBooked) {
            throw new RuntimeException("Not enough capacity");
        }
        event.setLeftCapacity(event.getLeftCapacity() - ticketsBooked);
        eventRepository.saveAndFlush(event);
        log.info("Updated event capacity for eventId: {}, leftCapacity: {}, ticketsBooked: {}", eventId, event.getLeftCapacity(), ticketsBooked);
    }

}
