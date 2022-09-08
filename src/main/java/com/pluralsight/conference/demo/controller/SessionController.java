package com.pluralsight.conference.demo.controller;

import com.pluralsight.conference.demo.model.response.SessionResponse;
import com.pluralsight.conference.demo.model.Session;
import com.pluralsight.conference.demo.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/sessions")
public class SessionController {

    private final SessionService sessionService;

    @GetMapping
    public List<SessionResponse> list() {
        return sessionService.getAllSessions();
    }

    @GetMapping("{id}")
    public SessionResponse get(@PathVariable Long id) {
        return sessionService.getSession(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public SessionResponse create(@RequestBody final Session session) {
        return sessionService.save(session);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        sessionService.delete(id);
    }

    @PutMapping("{id}")
    public SessionResponse update(@PathVariable Long id, @RequestBody final Session session) {
        return sessionService.update(id, session);
    }

}
