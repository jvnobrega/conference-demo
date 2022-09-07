package com.pluralsight.conference.demo.controller;

import com.pluralsight.conference.demo.model.Speaker;
import com.pluralsight.conference.demo.model.response.SpeakerResponse;
import com.pluralsight.conference.demo.service.SpeakerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/speakers")
public class SpeakerController {

    private final SpeakerService speakerService;

    @GetMapping
    public List<SpeakerResponse> list() {
        return speakerService.getAllSpeakers();
    }

    @GetMapping("{id}")
    public SpeakerResponse get(@PathVariable Long id) {
        return speakerService.getSpeakerById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public SpeakerResponse create(@RequestBody Speaker speaker) {
        return speakerService.save(speaker);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        speakerService.delete(id);
    }

    @PutMapping("{id}")
    public SpeakerResponse update(@PathVariable Long id, @RequestBody final Speaker speaker) {
        return speakerService.update(id, speaker);
    }


}
