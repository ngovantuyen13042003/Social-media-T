package nvt.st.T.service.post;

import nvt.st.T.dto.post.PostRequest;
import nvt.st.T.dto.post.PostResponse;
import nvt.st.T.service.CrudService;

public interface PostService extends CrudService<Long, PostRequest, PostResponse> {
}
