package com.pluralsight.conference.demo.service;

import com.pluralsight.conference.demo.model.Speaker;
import com.pluralsight.conference.demo.repository.SpeakerRepository;
import com.pluralsight.conference.demo.model.converter.SpeakerConverter;
import com.pluralsight.conference.demo.model.response.SpeakerResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.pluralsight.conference.demo.model.converter.SpeakerConverter.convertFromModel;

@Service
@RequiredArgsConstructor
@Log4j2
public class SpeakerService {
    public static final String SPEAKER_ID = "id";

    private final SpeakerRepository speakerRepository;

    public List<SpeakerResponse> getAllSpeakers() {
        log.debug("m=getAllSessions");
        return speakerRepository
                .findAll()
                .stream()
                .map(SpeakerConverter::convertFromModel)
                .collect(Collectors.toList());
    }

    public SpeakerResponse getSpeakerById(Long id) {
        log.debug("m=getSpeakerById id: {}", id);
        return speakerRepository.findById(id)
                .map(SpeakerConverter::convertFromModel)
                .orElseThrow(getSpeakerNoFoundException());
    }

    public SpeakerResponse save(Speaker speaker) {
        log.debug("m=save speaker name: {}", speaker.getFirstName());
        return convertFromModel(speakerRepository
                .saveAndFlush(speaker));
    }

    public SpeakerResponse update(Long id, Speaker speaker) {
        log.debug("m=update speaker id: {}", id);
        Speaker existingSpeaker = speakerRepository.findById(id)
                .orElseThrow(getSpeakerNoFoundException());
        // TODO: add validation that all attributes are passed in, otherwise return 400 bad payload.
        BeanUtils.copyProperties(speaker, existingSpeaker, SPEAKER_ID);
        return save(speaker);
    }

    public void delete(Long id) {
        log.debug("m=delete speaker id: {}", id);
        // TODO: Also need to check for children records before deleting.
        speakerRepository.deleteById(id);
    }

    private Supplier<IllegalArgumentException> getSpeakerNoFoundException() {
        return () -> new IllegalArgumentException("speaker not found");
    }

}
