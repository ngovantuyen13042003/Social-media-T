package nvt.st.T.mapper.page;

import nvt.st.T.dto.page.PageRequest;
import nvt.st.T.dto.page.PageResponse;
import nvt.st.T.entity.post.Pages;
import nvt.st.T.mapper.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PageMapper extends GenericMapper<Pages, PageRequest, PageResponse> {
}
