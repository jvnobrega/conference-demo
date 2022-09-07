package com.pluralsight.conference.demo.model.converter;

import com.pluralsight.conference.demo.model.Session;
import com.pluralsight.conference.demo.model.Speaker;
import com.pluralsight.conference.demo.model.response.SpeakerResponse;

import java.util.stream.Collectors;

public class SpeakerConverter {

    private SpeakerConverter() {
    }

    public static SpeakerResponse convertFromModel(Speaker speaker) {
        return SpeakerResponse
                .builder()
                .id(speaker.getId())
                .firstName(speaker.getFirstName())
                .lastName(speaker.getLastName())
                .title(speaker.getTitle())
                .company(speaker.getCompany())
                .speakerBio(speaker.getSpeakerBio())
                .sessions(speaker.getSessions()
                        .stream()
                        .map(Session::getSessionName)
                        .collect(Collectors.toList()))
                .build();
    }
}
