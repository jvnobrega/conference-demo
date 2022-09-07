package com.pluralsight.conference.demo.service;

import com.pluralsight.conference.demo.model.response.SessionResponse;
import com.pluralsight.conference.demo.model.converter.SessionConverter;
import com.pluralsight.conference.demo.model.Session;
import com.pluralsight.conference.demo.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.pluralsight.conference.demo.model.converter.SessionConverter.convertFromModel;

@Service
@RequiredArgsConstructor
@Log4j2
public class SessionService {
    public static final String SESSION_ID = "id";

    private final SessionRepository sessionRepository;

    public List<SessionResponse> getAllSessions() {
        log.debug("m=getAllSessions");
        return sessionRepository
                .findAll()
                .stream()
                .map(SessionConverter::convertFromModel)
                .collect(Collectors.toList());
    }

    public SessionResponse getSession(Long id) {
        log.debug("m=getSession id: {}", id);
        return sessionRepository.findById(id)
                .map(SessionConverter::convertFromModel)
                .orElseThrow(getSessionNoFoundException());
    }

    public SessionResponse save(Session session) {
        log.debug("m=save session name: {}", session.getSessionName());
        return convertFromModel(sessionRepository
                .saveAndFlush(session));
    }

    public SessionResponse update(Long id, Session session) {
        log.debug("m=update session id: {}", id);
        Session existingSession = sessionRepository.findById(id)
                .orElseThrow(getSessionNoFoundException());
        // TODO: add validation that all attributes are passed in, otherwise return 400 bad payload.
        BeanUtils.copyProperties(session, existingSession, SESSION_ID);
        return save(session);
    }

    public void delete(Long id) {
        log.debug("m=delete session id: {}", id);
        // TODO: Also need to check for children records before deleting.
        sessionRepository.deleteById(id);
    }

    private Supplier<IllegalArgumentException> getSessionNoFoundException() {
        return () -> new IllegalArgumentException("session not found");
    }

}
