package nvt.st.T.controller;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import nvt.st.T.constants.ResourceName;
import nvt.st.T.constants.SearchFields;
import nvt.st.T.dto.post.PostRequest;
import nvt.st.T.dto.post.PostResponse;
import nvt.st.T.dto.user.UserRequest;
import nvt.st.T.dto.user.UserResponse;
import nvt.st.T.entity.account.User;
import nvt.st.T.entity.post.Posts;
import nvt.st.T.mapper.authentication.UserMapper;
import nvt.st.T.mapper.post.PostMapper;
import nvt.st.T.repository.auth.UserRepository;
import nvt.st.T.repository.post.PostRepository;
import nvt.st.T.service.CrudService;
import nvt.st.T.service.GenericService;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.pattern.PathPatternParser;

import java.util.List;

@Component
@AllArgsConstructor
public class GenericMapperRegister {
    private ApplicationContext context;
    private RequestMappingHandlerMapping handlerMapping;

    // Controller
    private GenericController<UserRequest, UserResponse> userController;
    private GenericController<PostRequest, PostResponse> postController;

    // Services
    private GenericService<User, UserRequest, UserResponse> userService;
    private GenericService<Posts, PostRequest, PostResponse> postService;

    @PostConstruct
    public void registerControllers() throws NoSuchMethodException  {

        register("users",
                userController,
                userService.init(
                        context.getBean(UserRepository.class),
                        context.getBean(UserMapper.class),
                        SearchFields.USER,
                        ResourceName.USER
                ),
                UserRequest.class);

        register("posts",
                postController,
                postService.init(
                        context.getBean(PostRepository.class),
                        context.getBean(PostMapper.class),
                        SearchFields.POST,
                        ResourceName.POST
                ),
        PostRequest.class);

    }

    private <I, O> void register(
            String resource,
            GenericController<I, O> controller,
            CrudService<Long, I, O> service,
            Class<I> requestType
    )throws NoSuchMethodException {
        RequestMappingInfo.BuilderConfiguration options = new RequestMappingInfo.BuilderConfiguration();
        options.setPatternParser(new PathPatternParser());

        controller.setCrudService(service);
        controller.setRequestType(requestType);
        handlerMapping.registerMapping(
                RequestMappingInfo.paths("/api/" + resource)
                        .methods(RequestMethod.GET)
                        .produces(MediaType.APPLICATION_JSON_VALUE)
                        .options(options)
                        .build(),
                controller,
                controller.getClass().getMethod("getAllResources", int.class, int.class,
                        String.class, String.class, String.class, boolean.class)
        );

        handlerMapping.registerMapping(
                RequestMappingInfo.paths("/api/" + resource + "/{id}")
                        .methods(RequestMethod.GET)
                        .produces(MediaType.APPLICATION_JSON_VALUE)
                        .options(options)
                        .build(),
                controller,
                controller.getClass().getMethod("getResource", Long.class)
        );

        handlerMapping.registerMapping(
                RequestMappingInfo.paths("/api/" + resource)
                        .methods(RequestMethod.POST)
                        .consumes(MediaType.APPLICATION_JSON_VALUE)
                        .produces(MediaType.APPLICATION_JSON_VALUE)
                        .options(options)
                        .build(),
                controller,
                controller.getClass().getMethod("createResource", JsonNode.class)
        );

        handlerMapping.registerMapping(
                RequestMappingInfo.paths("/api/" + resource + "/{id}")
                        .methods(RequestMethod.PUT)
                        .consumes(MediaType.APPLICATION_JSON_VALUE)
                        .produces(MediaType.APPLICATION_JSON_VALUE)
                        .options(options)
                        .build(),
                controller,
                controller.getClass().getMethod("updateResource", Long.class, JsonNode.class)
        );

        handlerMapping.registerMapping(
                RequestMappingInfo.paths("/api/" + resource + "/{id}")
                        .methods(RequestMethod.DELETE)
                        .options(options)
                        .build(),
                controller,
                controller.getClass().getMethod("deleteResource", Long.class)
        );

        handlerMapping.registerMapping(
                RequestMappingInfo.paths("/api/" + resource)
                        .methods(RequestMethod.DELETE)
                        .consumes(MediaType.APPLICATION_JSON_VALUE)
                        .options(options)
                        .build(),
                controller,
                controller.getClass().getMethod("deleteResources", List.class)
        );
    }
}
