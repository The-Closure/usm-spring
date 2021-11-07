package org.closure.app.boardModule.models;

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
public class BoardModel {
    private Long id;
    private String name;
    private String image;
    private String description;

}
