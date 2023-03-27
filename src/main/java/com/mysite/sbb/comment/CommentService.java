package com.mysite.sbb.comment;

import com.mysite.sbb.DataNotFoundException;
import com.mysite.sbb.question.Question;
import com.mysite.sbb.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public Comment create(Question question, SiteUser author, String content) {
        Comment c = new Comment();
        c.setContent(content);
        c.setCreateDate(LocalDateTime.now());
        c.setQuestion(question);
        c.setAuthor(author);
        c = commentRepository.save(c);
        return c;
    }

    public Comment getComment(Integer id) {
        Optional<Comment> oc = commentRepository.findById(id);
        if(oc.isPresent()) {
            return oc.get();
        } else {
            throw new DataNotFoundException("comment not found");
        }
    }

    public Comment modify(Comment c, String content) {
        c.setContent(content);
        c.setModifyDate(LocalDateTime.now());
        c = commentRepository.save(c);
        return c;
    }

    public void delete(Comment c) {
        commentRepository.delete(c);
    }
}
