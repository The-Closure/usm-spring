package org.closure.app.CommunityModule.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@AllArgsConstructor
@NoArgsConstructor
@With
public class CommunityResponse {
    private String name;
    private String description;
    private String img;
    
}
