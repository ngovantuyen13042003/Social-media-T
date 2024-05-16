package nvt.st.T.controller.post;

import lombok.AllArgsConstructor;
import nvt.st.T.dto.post.PostRequest;
import nvt.st.T.dto.post.PostResponse;
import nvt.st.T.entity.EPrivacy;
import nvt.st.T.entity.account.User;
import nvt.st.T.service.post.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/api/post")
@AllArgsConstructor
@RestController
public class PostController {
    private PostService postService;

    @PostMapping
    public ResponseEntity<PostResponse> savePost(@RequestParam Long user_id, List<MultipartFile> files, String content, String privacy) {
        PostRequest postResponse = new PostRequest();
        User user = new User();
        user.setId(user_id);
        postResponse.setUser(user);
        postResponse.setContent(content);

        postResponse.setFiles(files);

        if(privacy.equalsIgnoreCase("public")) {
            postResponse.setPrivacy(EPrivacy.PUBLIC);
        }else if (privacy.equalsIgnoreCase("private")) {
            postResponse.setPrivacy(EPrivacy.PRIVATE);
        }

        postResponse.setEnabled(true);

        PostResponse p = postService.save(postResponse);
        return ResponseEntity.ok(p);
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePost(@RequestParam Long id) {
        postService.delete(id);
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
