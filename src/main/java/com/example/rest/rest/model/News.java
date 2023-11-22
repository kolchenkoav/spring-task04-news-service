package com.example.rest.rest.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String message;

    @ManyToOne
    @JoinColumn(name = "group_id")
    @ToString.Exclude
    private Group group;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;

    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL)
    @ToString.Exclude
    @Builder.Default
    private List<Comment> commentList = new ArrayList<>();

    @CreationTimestamp
    private Instant createAt;
    @UpdateTimestamp
    private Instant updateAt;

    public void addComment(Comment comment) {
        commentList.add(comment);
    }

    public void removeComment(Long commentId) {
        commentList = commentList.stream()
                .filter(news -> !news.getId().equals(commentId))
                .collect(Collectors.toList());
    }
}
