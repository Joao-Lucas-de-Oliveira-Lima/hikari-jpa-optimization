package dev.jl.jpaperformanceconfigurations.post;

import dev.jl.jpaperformanceconfigurations.exception.ResourceNotFoundException;
import dev.jl.jpaperformanceconfigurations.httpclient.JsonPlaceholderClient;
import dev.jl.jpaperformanceconfigurations.user.UserService;
import dev.jl.jpaperformanceconfigurations.utils.mapper.Mapper;
import jakarta.persistence.OptimisticLockException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetrySynchronizationManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final Mapper mapper;
    private final JsonPlaceholderClient jsonPlaceholderClient;
    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(PostService.class);

    public PostService(PostRepository postRepository, Mapper mapper, JsonPlaceholderClient jsonPlaceholderClient, UserService userService) {
        this.postRepository = postRepository;
        this.mapper = mapper;
        this.jsonPlaceholderClient = jsonPlaceholderClient;
        this.userService = userService;
    }

    @Transactional
    public void importPosts() {
        if (Boolean.FALSE.equals(postRepository.existsPosts())) {
            List<PostModel> posts = mapper.map(jsonPlaceholderClient.fetchPosts(), PostModel.class);
            posts.forEach(post -> post.setUser(userService.getReferenceById(post.getUserId())));
            postRepository.saveAll(posts);
        }
    }

    @Transactional(readOnly = true)
    public PostResponseDto findById(Long id, Boolean withDelay) throws ResourceNotFoundException {
        if (Boolean.TRUE.equals(withDelay)) {
            for (int index = 0; index < 1000000; index++) {
                for (int index2 = 0; index2 < 1000; index2++) ;
            }
        }
        PostModel response = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Post with ID %d was not found!", id)));
        return mapper.map(response, PostResponseDto.class);
    }

    @Retryable(retryFor = OptimisticLockingFailureException.class, maxAttempts = 3, backoff = @Backoff(maxDelay = 1000))
    @Transactional
    public PostResponseDto patchPostTitleById(PostPatchDto patch, Long id) throws ResourceNotFoundException {
        if(RetrySynchronizationManager.getContext().getRetryCount() != 0) {
            logger.info(String.format("Retry Count: %s", RetrySynchronizationManager.getContext().getRetryCount()));
        }
        PostModel post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Post with ID %d was not found!", id)));
        mapper.map(patch, post);
        return mapper.map(post, PostResponseDto.class);
    }

    @Recover
    PostResponseDto recover(OptimisticLockingFailureException e){
        logger.info("The transaction could not be completed due to concurrent modifications. Please try again.");
        return null;
    }
}