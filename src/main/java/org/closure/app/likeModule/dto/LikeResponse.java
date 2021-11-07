package org.closure.app.likeModule.dto;




import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;
import lombok.With;

@With

@NoArgsConstructor
@AllArgsConstructor
public class LikeResponse {
    Long id;
    String likerName;
    Long likerId;
    String likerImg;

}
