package org.closure.app.boardModule.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.With;
import lombok.NoArgsConstructor;
import lombok.With;

@Getter @Setter 
@RequiredArgsConstructor
@ToString 
@EqualsAndHashCode
public class BoardResponse {
    private Long id;
    private String name;
    private String image;
    private String description;
}
