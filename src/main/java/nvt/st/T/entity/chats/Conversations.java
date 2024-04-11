package nvt.st.T.entity.chats;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nvt.st.T.entity.BaseEntity;
import nvt.st.T.entity.account.User;

import java.util.ArrayList;
import java.util.Collection;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Conversations extends BaseEntity {
    @Column(name = "conversation_name")
    private String name;
    private Long last_message_id;

    @ManyToMany
    @JoinTable(
            name = "user_conversation",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "conversation_id", referencedColumnName = "id"))
    private Collection<User> users = new ArrayList<>();
}
