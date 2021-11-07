package org.closure.app.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

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
@Entity
@Table(name = "boards")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String image;
    private String description;
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "users_in_board", joinColumns = { @JoinColumn(name = "board_id") }, inverseJoinColumns = {
            @JoinColumn(name = "user_id") })
    private List<UserEntity> users;
    @OneToMany(mappedBy = "board")
    private List<ScholarEntity> scholarships;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name = "profs_in_board", joinColumns = { @JoinColumn(name = "board_id") }, inverseJoinColumns = {
            @JoinColumn(name = "prof_id") })
    private List<ProfsEntity> profs;

}