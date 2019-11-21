package ru.samfort.voting.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Restaurant extends AbstractNamedEntity {

    @NotBlank
    @Size(min = 5, max = 250)
    private String address;
}
