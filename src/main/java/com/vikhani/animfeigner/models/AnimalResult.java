package com.vikhani.animfeigner.models;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "animals_results")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnimalResult implements Serializable {
    @Id
    @Column(columnDefinition = "uuid")
    private UUID UUId;

    @Column(name = "status")
    private int httpStatus;

    @Column(name = "animal_names")
    private String animalNames;
}
