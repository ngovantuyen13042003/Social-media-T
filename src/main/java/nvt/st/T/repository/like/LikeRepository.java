package nvt.st.T.repository.like;

import nvt.st.T.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Likes, Long>, JpaSpecificationExecutor<Likes> {
}
