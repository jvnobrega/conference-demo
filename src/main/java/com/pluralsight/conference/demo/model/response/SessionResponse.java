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
public class SessionResponse {

    private Long id;

    private String sessionName;

    private String sessionDescription;

    private Integer sessionLength;

    private List<String> speakers;
}
