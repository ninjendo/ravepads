package com.rave.pads.model;

import io.micronaut.core.annotation.NonNull;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(includeFieldNames=true)
public class Contact implements Serializable {
    private Long id;
    private String mobilePhone;
    private String homePhone;
    @NonNull
    private String email;
    private String bestTimeToCall;
}
