package org.closure.app.CommunityModule.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.With;

@Data
@AllArgsConstructor
@NoArgsConstructor
@With
@ToString
public class CommunityModel {
    private Long id;
    private String name;
    private String description;
    private String img;
    
}
