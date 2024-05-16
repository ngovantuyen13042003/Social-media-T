package nvt.st.T.service.post;

import nvt.st.T.constants.ResourceName;
import nvt.st.T.constants.SearchFields;
import nvt.st.T.dto.ListResponse;
import nvt.st.T.dto.post.PostRequest;
import nvt.st.T.dto.post.PostResponse;
import nvt.st.T.entity.Files;
import nvt.st.T.entity.post.Posts;
import nvt.st.T.exception.ResourceNotFoundException;
import nvt.st.T.mapper.post.PostMapper;
import nvt.st.T.repository.file.FileRepository;
import nvt.st.T.repository.like.LikeRepository;
import nvt.st.T.repository.post.CommentRepository;
import nvt.st.T.repository.post.PageRepository;
import nvt.st.T.repository.post.PostRepository;
import nvt.st.T.service.cloudinary.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService{
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private PageRepository pageRepository;
    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CloudinaryService cloudinaryService;

    @Value("${cloudinary.folderName}")
    private  String rootFolder;

    @Override
    public ListResponse<PostResponse> findAll(int page, int size, String sort, String filter, String search, boolean all) {
        return defaultFindAll(page, size, sort, filter, search, all, SearchFields.POST, postRepository, postMapper);
    }

    @Override
    public PostResponse findById(Long aLong) {
        return defaultFindById(aLong, postRepository, postMapper, ResourceName.POST);
    }

    @Override
    public PostResponse save(Long aLong, PostRequest request) {
        return defaultSave(aLong, request, postRepository, postMapper, ResourceName.POST);
    }

    @Override
    public void delete(Long id) {
        Posts posts =  postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(ResourceName.POST, "id", id));
        posts.setEnabled(false);
        postRepository.save(posts);
    }

    @Override
    public void delete(List<Long> longs) {
        postRepository.deleteAllById(longs);
    }

    @Override
    public PostResponse save(PostRequest request) {
        Posts post = postMapper.requesToEntity(request);
        post.setPrivacy(request.getPrivacy());

        Posts postAfterSave = postRepository.save(post);


        if(request.getFiles().size() > 0) {

            List<String> url_files = cloudinaryService.upload(request.getFiles(), rootFolder);
            for(String url : url_files) {
                Files files = new Files();
                files.setPosts(postAfterSave);
                files.setUrlFile(url);
                fileRepository.save(files);
            }
        }
        return postMapper.entityToResponse(postAfterSave);
    }
}
