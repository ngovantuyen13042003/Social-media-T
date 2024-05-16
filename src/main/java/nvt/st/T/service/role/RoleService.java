package nvt.st.T.service.role;

import nvt.st.T.entity.account.ERole;
import nvt.st.T.entity.account.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findByName(ERole name);
}
