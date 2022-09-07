package com.pluralsight.conference.demo.model.converter;

import com.pluralsight.conference.demo.model.response.SessionResponse;
import com.pluralsight.conference.demo.model.Session;
import com.pluralsight.conference.demo.model.Speaker;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Collections.EMPTY_LIST;
import static java.util.Objects.isNull;

public class SessionConverter {

    private SessionConverter() {
    }

    public static SessionResponse convertFromModel(Session session) {
        return SessionResponse
                .builder()
                .sessionName(session.getSessionName())
                .sessionLength(session.getSessionLength())
                .speakers(getSpeakers(session))
                .sessionDescription(session.getSessionDescription())
                .id(session.getId())
                .build();
    }

    private static List<String> getSpeakers(Session session) {
        return session.getSpeakers()
                .stream().map(Speaker::getFirstName)
                .collect(Collectors.toList());
    }
}
