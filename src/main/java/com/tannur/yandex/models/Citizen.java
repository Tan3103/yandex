package com.tannur.yandex.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Citizen")
public class Citizen {
    @Id
    @Column(name = "citizen_id")
    private Integer citizen_id;

    @Column(name = "town")
    @NotEmpty(message = "Town should not be empty")
    @Size(min = 1, message = "Town should be min 1")
    private String town;

    @Column(name = "street")
    @NotEmpty(message = "Street should not be empty")
    @Size(min = 1, message = "Street should be min 1")
    private String street;

    @Column(name = "building")
    @NotEmpty(message = "Building should not be empty")
    @Size(min = 1, message = "Building should be min 1")
    private String building;

    @Column(name = "apartment")
    @Min(value = 0, message = "Age should be greater than 0")
    private int apartment;

    @Column(name = "name")
    @NotEmpty(message = "Name should not be empty")
    private String name;

    @Column(name = "birth_date")
    @Pattern(regexp = "\\d{2}.\\d{2}.\\d{4}", message = "Date of birth in the format DD.MM.YYYY (UTC)")
    private String birth_date;

    @Column(name = "gender")
    private String gender;

    @Column(name = "relatives")
    @ElementCollection
    private List<Integer> relatives;
}
