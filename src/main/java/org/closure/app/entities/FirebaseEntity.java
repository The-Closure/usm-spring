package org.closure.app.entities;

import java.time.Instant;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

@Entity
@Table(name = "firebase_tokens")
@With
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class FirebaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String FirebaseToken;

    private Instant created_at;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "token", nullable = false, unique = true)
    private UserEntity owner;


}
