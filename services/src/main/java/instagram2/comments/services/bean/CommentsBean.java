package instagram2.comments.services.bean;

import instagram2.comments.lib.Comment;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@ApplicationScoped
public class CommentsBean {

    private Logger log = Logger.getLogger(CommentsBean.class.getName());

    private List<Comment> comments;

    @PostConstruct
    private void init() {
        comments = new ArrayList<>();
    }

    public List<Comment> getComments() {

        return comments;

    }

    public List<Comment> getCommentsForImage(Integer imageId) {

        return comments.stream().filter(comment -> comment.getImageId().equals(imageId)).collect(Collectors.toList());
    }

    public void createComment(Comment comment) {
        comments.add(comment);
    }

    public boolean deleteComment(String commentId) {
        for (Comment c : comments) {
            if (c.getId().equals(commentId)) {
                comments.remove(c);
                return true;
            }
        }
        return false;
    }

}
