package com.event.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    // Get all tickets
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    // Get tickets by eventId
    public List<Ticket> getTicketsByEventId(Long eventId) {
        return ticketRepository.findByEventId(eventId);
    }

    // Get a ticket by ID
    public Optional<Ticket> getTicketById(Long id) {
        return ticketRepository.findById(id);
    }

    // Create a new ticket
    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    // Update a ticket
    public Ticket updateTicket(Long id, Ticket ticketDetails) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new RuntimeException("Ticket not found"));
        ticket.setTicketType(ticketDetails.getTicketType());
        ticket.setPrice(ticketDetails.getPrice());
        ticket.setQuantityAvailable(ticketDetails.getQuantityAvailable());
        ticket.setQuantitySold(ticketDetails.getQuantitySold());
        ticket.setSalesStartDate(ticketDetails.getSalesStartDate());
        ticket.setSalesEndDate(ticketDetails.getSalesEndDate());
        return ticketRepository.save(ticket);
    }

    // Delete a ticket
    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }
}
