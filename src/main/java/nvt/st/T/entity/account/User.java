package nvt.st.T.entity.account;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nvt.st.T.entity.BaseEntity;
import nvt.st.T.entity.chats.Conversations;
import nvt.st.T.entity.post.User_Page;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User extends BaseEntity {
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    private String password;
    private String fullName;
    private String gender;
    private Date birthdate;
    private String avatar;
    private String major;
    private String nickName;
    @Column(unique = true)
    private String phone_number;
    private String company;
    private String address;
    private String school;
    @Column(nullable = false)
    private Boolean enabled ;   
    @ManyToOne
    @JoinColumn(name = "friend_id", referencedColumnName = "id", nullable = true)
    private User user;

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles = new ArrayList<>();

    @ManyToMany(mappedBy = "users")
    private Collection<Conversations> conversations;

    @OneToOne(mappedBy = "user")
    private Verification verification;

    @Column(name = "reset_password_token")
    private String resetPasswordToken;

    @OneToMany(mappedBy = "user")
    private List<User_Page> userPages;


}
