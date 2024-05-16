package nvt.st.T.repository.post;

import nvt.st.T.entity.post.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Posts, Long>, JpaSpecificationExecutor<Posts> {
}
