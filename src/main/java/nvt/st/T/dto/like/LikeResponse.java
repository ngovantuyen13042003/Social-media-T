package nvt.st.T.dto.like;

import lombok.Data;
import nvt.st.T.entity.account.User;
import nvt.st.T.entity.chats.Messages;
import nvt.st.T.entity.post.Comment;
import nvt.st.T.entity.post.Pages;
import nvt.st.T.entity.post.Posts;

@Data
public class LikeResponse {
    private Long id;

    private String likeType;

    private Posts posts;

    private Messages messages;

    private Comment comment;

    private Pages pages;

    private User user;
}
