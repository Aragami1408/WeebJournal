package com.weebindustry.weebjournal.dtos.users;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Data
public class UserPreferenceDTO {

    @Id
    @NotNull
    private Long id;

    private String biography;

    private String displayname;

    private String dateOfBirth;
}