package com.fgt.walletsystem.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import javax.validation.constraints.NotEmpty;

@Data
public class CreateWalletDTO {
    @JsonProperty("email")
    @NotEmpty(message = "Email is required")
    private String email;
    @NotEmpty(message = "First name is required")
    @JsonProperty("first_name")
    private String firstName;
    @NotEmpty(message = "Last name is required")
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("phone")
    private String phoneNumber;
}
