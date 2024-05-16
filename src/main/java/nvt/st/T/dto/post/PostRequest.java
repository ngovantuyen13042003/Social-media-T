package nvt.st.T.dto.post;

import lombok.Data;
import nvt.st.T.dto.comment.CommentResponse;
import nvt.st.T.dto.like.LikeResponse;
import nvt.st.T.entity.EPrivacy;
import nvt.st.T.entity.account.User;
import nvt.st.T.entity.post.Pages;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class PostRequest {
    private User user;
    private String content;
    private EPrivacy privacy;
    private Pages pages;
    private boolean enabled;
    private List<LikeResponse> likes;
    private List<MultipartFile> files;
    private List<CommentResponse> comments;

}
