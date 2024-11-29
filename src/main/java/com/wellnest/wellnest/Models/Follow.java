package com.wellnest.wellnest.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "followers")
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRelation")
    private long idRelation;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUser", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idFollower", nullable = false)
    private User follower;

    @Column(name = "dateStartFollow")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateStartFollow;


    @Override
    public String toString() {
        return "Follow{" +
                "idRelation=" + idRelation +
                ", user=" + user.getName() +
                ", follower=" + follower.getName() +
                ", dateStartFollow=" + dateStartFollow +
                '}';
    }
}
