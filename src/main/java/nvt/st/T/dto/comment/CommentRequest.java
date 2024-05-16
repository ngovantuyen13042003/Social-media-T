package nvt.st.T.dto.comment;


import lombok.Data;
import nvt.st.T.dto.like.LikeResponse;
import nvt.st.T.entity.account.User;
import nvt.st.T.entity.post.Comment;
import nvt.st.T.entity.post.Posts;

import java.util.List;
@Data
public class CommentRequest {

    private Posts posts;

    private User user;

    private String content;

    private Comment parentComment;

    private List<LikeResponse> likes;
}
