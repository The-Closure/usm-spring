package org.closure.app.CommunityModule.models;

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

@Getter @Setter 
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class CommunityModel {
    private Long id;
    private String name;
    private String description;
    private String img;
    
}
