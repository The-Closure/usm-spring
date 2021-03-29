package org.closure.app.likeModule.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@AllArgsConstructor
@NoArgsConstructor
@With
public class LikeModel {
    Long userID;
    Long postID;
}
