package org.closure.app.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

@Entity
@Table(name = "posts")
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id"
)
@With
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "u_id" , nullable = true)
    private UserEntity uEntity;
    private String title;
    private String value;
    private String attach;
    @Nullable
    @OneToMany(mappedBy = "pentity" , cascade = CascadeType.ALL)
    private List<LikeEntity> likes = new ArrayList<>();
    @OneToMany(mappedBy = "pentity" , cascade = CascadeType.ALL)
    private List<CommentEntity> comments = new ArrayList<>();
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date created_at;
    @ManyToOne
    @JoinColumn(name = "com_id")
    private CommunityEntity pcommuninty;


  


}