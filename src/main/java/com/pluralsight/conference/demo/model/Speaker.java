package com.pluralsight.conference.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "speakers")
public class Speaker {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "speaker_id", nullable = false)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String title;

    private String company;

    @Column(name = "speaker_bio")
    private String speakerBio;

    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    @Column(name = "speaker_photo")
    private byte[] speakerPhotos;

    @JsonIgnoreProperties("speakers")
    @ManyToMany(mappedBy = "speakers")
    private List<Session> sessions = new ArrayList<>();

}
