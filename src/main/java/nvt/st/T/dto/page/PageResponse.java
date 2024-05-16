package nvt.st.T.dto.page;

import lombok.Data;
import nvt.st.T.dto.comment.CommentResponse;
import nvt.st.T.dto.file.FileResponse;
import nvt.st.T.dto.like.LikeResponse;
import nvt.st.T.dto.post.PostResponse;

import java.util.List;

@Data
public class PageResponse {
    private Long id;
    private String name;
    private String content;
    private String privacy;

    private List<FileResponse> files;

    private List<PostResponse> comment;

    private List<LikeResponse> likes;

    private List<UserPageResponse> userPage;
}
