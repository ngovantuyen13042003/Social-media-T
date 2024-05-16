package nvt.st.T.entity.post;

import jakarta.persistence.*;
import lombok.Data;
import nvt.st.T.entity.BaseEntity;
import nvt.st.T.entity.Likes;
import nvt.st.T.entity.account.User;

import java.util.List;

@Entity
@Data
@Table(name = "comments")
public class Comment extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Posts posts;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String content;

    @ManyToOne
    @JoinColumn(name = "parent_comment", referencedColumnName = "id")
    private Comment parentComment;

    @OneToMany(mappedBy = "comment")
    private List<Likes> likes;


}
