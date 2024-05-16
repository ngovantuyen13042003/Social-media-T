package nvt.st.T.mapper.page;

import nvt.st.T.dto.user.UserRequest;
import nvt.st.T.dto.user.UserResponse;
import nvt.st.T.entity.post.User_Page;
import nvt.st.T.mapper.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserPageMapper extends GenericMapper<User_Page, UserRequest, UserResponse> {
}
