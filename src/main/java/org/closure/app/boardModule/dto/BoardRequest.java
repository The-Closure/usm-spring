package org.closure.app.boardModule.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.With;

@Getter @Setter 
@RequiredArgsConstructor
@ToString 
@AllArgsConstructor
@With
@EqualsAndHashCode
public class BoardRequest {
    private Long id;
    private String name;
}
