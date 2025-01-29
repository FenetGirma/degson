package com.event.attendee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendees")
public class AttendeeController {

    @Autowired
    private AttendeeService attendeeService;

    @GetMapping("/event/{eventId}")
    public List<Attendee> getAttendeesForEvent(@PathVariable Long eventId) {
        return attendeeService.getAllAttendeesForEvent(eventId);
    }

    @GetMapping
    public List<Attendee> getAllAttendees() {
        return attendeeService.getAllAttendees();
    }

    @PostMapping
    public Attendee addAttendee(@RequestBody Attendee attendee) {
        return attendeeService.saveAttendee(attendee);
    }

    @DeleteMapping("/{attendeeId}")
    public void deleteAttendee(@PathVariable Long attendeeId) {
        attendeeService.deleteAttendee(attendeeId);
    }
}
