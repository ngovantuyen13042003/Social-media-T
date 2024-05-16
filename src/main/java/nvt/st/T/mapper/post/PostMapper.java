package nvt.st.T.mapper.post;

import nvt.st.T.dto.post.PostRequest;
import nvt.st.T.dto.post.PostResponse;
import nvt.st.T.entity.post.Posts;
import nvt.st.T.mapper.GenericMapper;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface PostMapper extends GenericMapper<Posts, PostRequest, PostResponse> {
}
