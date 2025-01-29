package com.event.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.QRUtility;

// import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.Date;

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
        // Save ticket first to generate the ID
        Ticket savedTicket = ticketRepository.save(ticket);
        
        // Now generate the QR code with the actual ID
        String qrData = "Ticket ID: " + savedTicket.getId() + 
                        ", Event ID: " + savedTicket.getEvent().getId() + 
                        ", Type: " + savedTicket.getTicketType();
    
        savedTicket.setQrCode(QRUtility.generateQRCode(qrData));
    
        // Save again with the QR code
        return ticketRepository.save(savedTicket);
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

    public Ticket buyTicket(Long ticketId) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
        
        if (optionalTicket.isEmpty()) {
            throw new RuntimeException("Ticket with ID " + ticketId + " not found.");
        }
    
        Ticket ticket = optionalTicket.get();
        Date now = new Date(); // ✅ Corrected line
    
        // Check if ticket sales are within allowed dates
        if (ticket.getSalesStartDate() != null && now.before(ticket.getSalesStartDate())) {
            throw new RuntimeException("Ticket sales have not started yet.");
        }
        if (ticket.getSalesEndDate() != null && now.after(ticket.getSalesEndDate())) {
            throw new RuntimeException("Ticket sales have ended.");
        }
    
        // Check if ticket is available
        if (!ticket.isAvailable()) {
            throw new RuntimeException("Ticket is sold out.");
        }
    
        // Sell the ticket
        ticket.sellTicket();
        return ticketRepository.save(ticket);
    }
    
}
