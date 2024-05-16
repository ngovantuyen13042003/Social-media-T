package nvt.st.T.mapper.authentication;

import nvt.st.T.dto.role.RoleRequest;
import nvt.st.T.dto.role.RoleResponse;
import nvt.st.T.entity.account.Role;
import nvt.st.T.mapper.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper extends GenericMapper<Role, RoleRequest, RoleResponse> {
}
