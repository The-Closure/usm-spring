package org.closure.app.CommunityModule.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
public class CommunityModel {
    private Long id;
    private String name;
    private String description;
    private String img;

    
}
