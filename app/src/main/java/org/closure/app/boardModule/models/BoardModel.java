package org.closure.app.boardModule.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@AllArgsConstructor
@NoArgsConstructor
@With
public class BoardModel {
    private Long id;
    private String name;
    private String image;
    private String description;
}
