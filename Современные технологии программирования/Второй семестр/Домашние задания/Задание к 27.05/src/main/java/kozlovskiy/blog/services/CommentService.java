package kozlovskiy.blog.services;

import kozlovskiy.blog.models.Comment;
import kozlovskiy.blog.repo.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private CommentRepo commentRepo;

    @Autowired
    public CommentService(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

    public void createComment(Comment comment){
        commentRepo.save(comment);
    }

    public Comment getCommentById(Long id){
        return commentRepo.findById(id).orElse(null);
    }

    public List<Comment> getComments(){
        return commentRepo.findAll();
    }

    public boolean updateComment(Comment comment, Long id) {
        if (getCommentById(id) != null) {
            comment.setId(id);
            commentRepo.save(comment);
            return true;
        }

        return false;
    }

    public boolean deleteComment(Long id) {
        if (getCommentById(id) != null) {
            commentRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
