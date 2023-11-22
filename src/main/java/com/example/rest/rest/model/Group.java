package com.example.rest.rest.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity(name = "news_groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "group_name", nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
    @ToString.Exclude
    private List<News> news;
}
