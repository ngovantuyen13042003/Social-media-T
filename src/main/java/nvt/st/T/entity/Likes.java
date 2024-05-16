package nvt.st.T.entity;

import jakarta.persistence.*;
import lombok.Data;
import nvt.st.T.entity.account.User;
import nvt.st.T.entity.chats.Messages;
import nvt.st.T.entity.post.Comment;
import nvt.st.T.entity.post.Pages;
import nvt.st.T.entity.post.Posts;

@Entity
@Data
@Table(name = "likes")
public class Likes extends BaseEntity{

    @Enumerated(EnumType.STRING)
    @Column(name = "likeType")
    private ELikeType likeType;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Posts posts;

    @ManyToOne
    @JoinColumn(name = "message_id")
    private Messages messages;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @ManyToOne
    @JoinColumn(name = "page_id")
    private Pages pages;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
