package com.tannur.yandex.models;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class CitizenDTO {
    @Id
    private Integer citizen_id;

    @NotEmpty(message = "Town should not be empty")
    @Size(min = 1, message = "Town should be min 1")
    private String town;

    @NotEmpty(message = "Street should not be empty")
    @Size(min = 1, message = "Street should be min 1")
    private String street;

    @NotEmpty(message = "Building should not be empty")
    @Size(min = 1, message = "Building should be min 1")
    private String building;

    @Min(value = 0, message = "Age should be greater than 0")
    private int apartment;

    @NotEmpty(message = "Name should not be empty")
    private String name;

    @Pattern(regexp = "\\d{2}.\\d{2}.\\d{4}", message = "Date of birth in the format DD.MM.YYYY (UTC)")
    private String birth_date;

    private String gender;

    private List<Integer> relatives;
}
