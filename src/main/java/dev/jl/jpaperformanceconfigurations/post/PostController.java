package dev.jl.jpaperformanceconfigurations.post;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> findById(
            @PathVariable(name = "id") Long id,
            @RequestParam(name = "withDelay", defaultValue = "false") Boolean withDelay) {
        return ResponseEntity.ok(postService.findById(id, withDelay));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PostResponseDto> patchPostTitleById(
            @RequestBody PostPatchDto patch,
            @PathVariable(name = "id") Long id
    ) {
        PostResponseDto response = postService.patchPostTitleById(patch, id);
        return ResponseEntity.ok(response);

    }
}
