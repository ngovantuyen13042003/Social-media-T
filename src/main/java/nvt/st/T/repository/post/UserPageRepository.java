package nvt.st.T.repository.post;

import nvt.st.T.entity.post.User_Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPageRepository extends JpaRepository<User_Page, Long>, JpaSpecificationExecutor<User_Page> {
}
