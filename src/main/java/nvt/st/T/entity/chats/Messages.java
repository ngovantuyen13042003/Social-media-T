package nvt.st.T.entity.chats;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nvt.st.T.entity.BaseEntity;
import nvt.st.T.entity.account.User;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Messages extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User senderId;
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiverId;

    private String content;
    @Column(name = "is_read")
    private boolean isRead;

    @ManyToOne
    @JoinColumn(name = "message_parent_id", referencedColumnName = "id")
    private Messages messagesParent;

    @ManyToOne
    @JoinColumn(name = "conversation_id", referencedColumnName = "id")
    private Conversations conversations;



}
