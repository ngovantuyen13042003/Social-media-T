package nvt.st.T.mapper.comment;

import nvt.st.T.dto.comment.CommentRequest;
import nvt.st.T.dto.comment.CommentResponse;
import nvt.st.T.entity.post.Comment;
import nvt.st.T.mapper.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper extends GenericMapper<Comment, CommentRequest, CommentResponse> {
}
