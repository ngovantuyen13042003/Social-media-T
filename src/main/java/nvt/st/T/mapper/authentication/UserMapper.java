package nvt.st.T.mapper.authentication;


import nvt.st.T.dto.user.UserRequest;
import nvt.st.T.dto.user.UserResponse;
import nvt.st.T.entity.account.User;
import nvt.st.T.mapper.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends GenericMapper<User, UserRequest, UserResponse> {

}
