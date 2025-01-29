package com.event.attendee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendeeService {

    @Autowired
    private AttendeeRepository attendeeRepository;

    public List<Attendee> getAllAttendeesForEvent(Long eventId) {
        return attendeeRepository.findByEventId(eventId);
    }
    public List<Attendee> getAllAttendees() {
        return attendeeRepository.findAll();
    }

    public Attendee saveAttendee(Attendee attendee) {
        return attendeeRepository.save(attendee);
    }

    public void deleteAttendee(Long attendeeId) {
        attendeeRepository.deleteById(attendeeId);
    }
}
