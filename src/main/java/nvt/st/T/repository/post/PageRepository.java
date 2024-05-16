package nvt.st.T.repository.post;

import nvt.st.T.entity.post.Pages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PageRepository extends JpaRepository<Pages, Long> , JpaSpecificationExecutor<Pages> {
}
