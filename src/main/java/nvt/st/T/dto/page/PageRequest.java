package nvt.st.T.dto.page;


import lombok.Data;
import nvt.st.T.dto.comment.CommentResponse;
import nvt.st.T.dto.file.FileResponse;
import nvt.st.T.dto.like.LikeResponse;
import nvt.st.T.dto.post.PostResponse;
import nvt.st.T.entity.EPrivacy;
import nvt.st.T.entity.Files;
import nvt.st.T.entity.Likes;
import nvt.st.T.entity.post.Comment;

import java.util.List;
@Data
public class PageRequest {

    private String name;
    private String content;
    private String privacy;

    private List<FileResponse> files;

    private List<PostResponse> comment;

    private List<LikeResponse> likes;

    private List<UserPageResponse> userPage;
}
