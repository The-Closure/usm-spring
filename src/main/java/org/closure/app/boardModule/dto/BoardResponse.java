package org.closure.app.boardModule.dto;

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
public class BoardResponse {
    private Long id;
    private String name;
    private String image;
    private String description;

}
