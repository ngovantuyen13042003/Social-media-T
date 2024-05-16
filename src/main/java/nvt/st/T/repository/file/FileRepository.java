package nvt.st.T.repository.file;

import nvt.st.T.entity.Files;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<Files, Long>, JpaSpecificationExecutor<Files> {
}
