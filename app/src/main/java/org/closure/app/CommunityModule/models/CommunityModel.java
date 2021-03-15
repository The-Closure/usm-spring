package org.closure.app.CommunityModule.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@AllArgsConstructor
@NoArgsConstructor
@With
public class CommunityModel {
    private String name;
    private String description;
    private String img;
    
}
