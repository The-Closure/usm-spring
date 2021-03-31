package org.closure.app.CommunityModule.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.With;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.With;

@With
@AllArgsConstructor
@Getter @Setter 
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class CommunityResponse {
    private String name;
    private String description;
    private String img;
    
}
