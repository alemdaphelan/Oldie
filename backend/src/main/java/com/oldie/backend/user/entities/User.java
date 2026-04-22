package com.oldie.backend.user.entities;

import com.oldie.backend.locations.City;
import com.oldie.backend.locations.Ward;
import com.oldie.backend.locations.District;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id", nullable = false, updatable = false)
    private UUID userId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = true)
    private String password;

    @Column(nullable = false, length = 10)
    private String role;

    @Column(name = "phone_number", nullable = false, length = 15)
    private String phoneNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", nullable = false, updatable = true)
    private City city;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id", nullable = false, updatable = true)
    private District district;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ward_id", nullable = false, updatable = true)
    private Ward ward;

    @Column(name = "verified_level", nullable = false)
    private Integer verifiedLevel;

    @Column(name = "user_status", nullable = false, length = 10)
    private String userStatus;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
