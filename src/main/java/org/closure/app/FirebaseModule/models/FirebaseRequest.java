package org.closure.app.FirebaseModule.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;


@AllArgsConstructor
@NoArgsConstructor
@With
@Getter
@Setter
@Builder
public class FirebaseRequest {
    private String token;
    private Long userID;

    
}
