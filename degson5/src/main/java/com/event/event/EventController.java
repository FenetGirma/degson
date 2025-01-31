package com.event.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
public class EventController {
    @Autowired
    private EventService eventService;

    private static final String UPLOAD_DIR = "C:\\Users\\Lidya\\Desktop\\degson5\\degson\\degson5\\src\\main\\resources\\static\\uploads\\";


    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public Optional<Event> getEventById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }

  
    @PostMapping
    public Event createEvent(@RequestPart("event") String eventJson,
                             @RequestPart("poster") MultipartFile poster) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Event event = objectMapper.readValue(eventJson, Event.class);
    
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        event.setCreatedBy(email);
    
        if (!poster.isEmpty()) {
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
    
            String fileName = poster.getOriginalFilename();
            String filePath = UPLOAD_DIR + fileName;
            File destinationFile = new File(filePath);
    
            poster.transferTo(destinationFile);
            event.setPosterImagePath("/uploads/" + fileName);
        }
    
        return eventService.createEvent(event);
    }
    


    @PutMapping("/{id}")
    public Event updateEvent(@PathVariable Long id, @RequestBody Event event) {
        return eventService.updateEvent(id, event);
    }

    @DeleteMapping("/{id}")
    public String deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return "Event deleted successfully";
    }
}
