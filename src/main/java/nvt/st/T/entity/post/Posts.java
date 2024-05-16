package nvt.st.T.entity.post;

import jakarta.persistence.*;
import lombok.*;
import nvt.st.T.entity.BaseEntity;
import nvt.st.T.entity.EPrivacy;
import nvt.st.T.entity.Files;
import nvt.st.T.entity.Likes;
import nvt.st.T.entity.account.User;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class Posts extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    private String content;
    @Enumerated(value = EnumType.STRING)
    private EPrivacy privacy;
    @Column(nullable = false)
    private boolean enabled = true;

    @ManyToOne
    @JoinColumn(name = "page_id")
    private Pages pages;

    @OneToMany(mappedBy = "posts")
    private List<Files> files;

    @OneToMany(mappedBy = "posts")
    private List<Comment> comment;

    @OneToMany(mappedBy = "posts")
    private List<Likes> likes;

}
