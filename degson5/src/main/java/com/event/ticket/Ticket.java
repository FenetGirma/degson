package com.event.ticket;


import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

import com.event.event.Event;


@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event; // Foreign key relationship with Event

    private String ticketType; // e.g., General Admission, VIP
    private BigDecimal price;
    private Integer quantityAvailable;
    private Integer quantitySold;

    private Date salesStartDate;
    private Date salesEndDate;

    @Column(name = "qr_code", columnDefinition = "TEXT")
    private String qrCode;

    public String getQrCode() { return qrCode; }
    public void setQrCode(String qrCode) { this.qrCode = qrCode; }

    public boolean isAvailable() {
        return quantityAvailable > quantitySold;
    }

    public void sellTicket() {
        if (isAvailable()) {
            this.quantitySold += 1;
        } else {
            throw new RuntimeException("Ticket is sold out");
        }
    }



    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Event getEvent() { return event; }
    public void setEvent(Event event) { this.event = event; }
    public String getTicketType() { return ticketType; }
    public void setTicketType(String ticketType) { this.ticketType = ticketType; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public Integer getQuantityAvailable() { return quantityAvailable; }
    public void setQuantityAvailable(Integer quantityAvailable) { this.quantityAvailable = quantityAvailable; }
    public Integer getQuantitySold() { return quantitySold; }
    public void setQuantitySold(Integer quantitySold) { this.quantitySold = quantitySold; }
    public Date getSalesStartDate() { return salesStartDate; }
    public void setSalesStartDate(Date salesStartDate) { this.salesStartDate = salesStartDate; }
    public Date getSalesEndDate() { return salesEndDate; }
    public void setSalesEndDate(Date salesEndDate) { this.salesEndDate = salesEndDate; }
}

