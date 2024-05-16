package nvt.st.T.mapper.file;

import nvt.st.T.dto.like.LikeRequest;
import nvt.st.T.dto.like.LikeResponse;
import nvt.st.T.entity.Likes;
import nvt.st.T.mapper.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FileMapper extends GenericMapper<Likes, LikeRequest, LikeResponse> {
}
