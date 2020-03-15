package com.eteration.site.Services;

import com.eteration.site.Model.Comment;
import com.eteration.site.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public void save(Comment comment){
        commentRepository.save(comment);
    }

    public List<Comment> findByProductId(Long id){
        return commentRepository.findByProductId(id);
    }
}
