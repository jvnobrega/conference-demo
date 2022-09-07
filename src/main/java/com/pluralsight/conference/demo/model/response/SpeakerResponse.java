package com.pluralsight.conference.demo.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SpeakerResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String title;

    private String company;

    private String speakerBio;

    private List<String> sessions;

}
