package com.event.event;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String category;
    private String location;
    private Date date;
    private String tags;
    private String createdBy;
    private String posterImagePath;

    @ElementCollection
    @CollectionTable(name = "tickets", joinColumns = @JoinColumn(name = "event_id"))
    private List<Ticket> tickets;

    


    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
    public String getTags() { return tags; }
    public void setTags(String tags) { this.tags = tags; }
    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }
    public String getPosterImagePath() { return posterImagePath; }
    public void setPosterImagePath(String posterImagePath) { this.posterImagePath = posterImagePath; }
    public List<Ticket> getTickets() { return tickets; }
    public void setTickets(List<Ticket> tickets) { this.tickets = tickets; }
}


@Embeddable
class Ticket {
    private String ticketType;
    private double price;
    private int quantityAvailable;
    private int quantitySold;
    private Date salesStartDate;
    private Date salesEndDate;

    // Getters and Setters
    public String getTicketType() { return ticketType; }
    public void setTicketType(String ticketType) { this.ticketType = ticketType; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getQuantityAvailable() { return quantityAvailable; }
    public void setQuantityAvailable(int quantityAvailable) { this.quantityAvailable = quantityAvailable; }
    public int getQuantitySold() { return quantitySold; }
    public void setQuantitySold(int quantitySold) { this.quantitySold = quantitySold; }
    public Date getSalesStartDate() { return salesStartDate; }
    public void setSalesStartDate(Date salesStartDate) { this.salesStartDate = salesStartDate; }
    public Date getSalesEndDate() { return salesEndDate; }
    public void setSalesEndDate(Date salesEndDate) { this.salesEndDate = salesEndDate; }
}
