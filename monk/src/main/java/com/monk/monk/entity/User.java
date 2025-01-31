package com.monk.monk.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name ="User")
public class User {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private long id;
 private String firstName;
 private String lastName;
 private String otherName;
 private String address;
 private String gender;
 private String stateOfOrigin;
 private String accountNumber;
 private BigDecimal accountBalance;
 private String email;
 private String phoneNumber;
 private String password;
 private String alternativePhoneNumber;
 private String status;

 @CreationTimestamp
 private LocalDateTime createdAt;

 @UpdateTimestamp
 private LocalDateTime modifiedAt;

 // No need for this method as Lombok will generate getters and setters
 // If you want to have a custom method, you can implement it here
}
