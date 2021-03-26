package org.closure.app.boardModule.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@AllArgsConstructor
@NoArgsConstructor
@With
public class BoardResponse {
    private Long id;
    private String name;
    private String image;
    private String description;
}
