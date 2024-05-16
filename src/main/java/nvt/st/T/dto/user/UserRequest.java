package nvt.st.T.dto.user;

import lombok.Data;
import nvt.st.T.dto.role.RoleRequest;
import nvt.st.T.dto.role.RoleResponse;

import java.util.Date;
import java.util.List;

@Data
public class UserRequest {
    private String username;
    private String email;
    private String password;
    private String fullName;
    private String gender;
    private Date birthdate;
    private String avatar;
    private String major;
    private String nickName;
    private String phone_number;
    private String company;
    private String address;
    private String school;
    private Boolean enabled;
    private List<RoleResponse> roles;
}
