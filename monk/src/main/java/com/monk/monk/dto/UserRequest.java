package com.monk.monk.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private String firstName;
    private String lastName;
    private String otherName; // Additional name field
    private String address;
    private String gender;
    private String stateOfOrigin; // Updated to camelCase for consistency
    private String email; // Moved email to be more prominent
    private String phoneNumber; // Updated to camelCase for consistency
    private String password; // Consider hashing this before saving
    private String alternativePhoneNumber; // Updated to camelCase for consistency
    private String status; // Status can be set to "Active" or similar

    public String getAccountNumber() {
        return firstName + lastName;

    }
}
