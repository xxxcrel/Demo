package beer.cheese.jpa.web.api;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import beer.cheese.jpa.model.dto.PostDTO;
import beer.cheese.jpa.model.entity.User;
import beer.cheese.jpa.security.CurrentUser;
import beer.cheese.jpa.view.Result;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final Log logger = LogFactory.getLog(getClass());

    @GetMapping("/listByCategory")
    public Result listByCategory(@RequestParam("category") String category) {
        return Result.ok("success");
    }

    @GetMapping("/listByUser")
    public Result listByUser(@RequestParam("user_id") Long userId) {
        return Result.ok("success");
    }

    @PostMapping("/publish")
    public Result publishPost(@CurrentUser User user,
                              @RequestParam("category") String category,
                              @RequestPart("meta-data")PostDTO postDTO,
                              @RequestPart("images[]") List<MultipartFile> images) {
        logger.info("meta-data:" + postDTO);
        logger.info("image.length: " + images.size());
        return Result.ok("publish success");
    }

    @PostMapping("/remove")
    public Result removePost(@RequestParam Long postId) {
        return Result.ok("delete success");
    }

    @GetMapping("/queryById")
    public Result queryById(@RequestParam("post_id") Long postId) {
        return Result.ok("success");
    }
}
