package com.hust.visum.service;

import com.hust.visum.model.Comment;
import com.hust.visum.request.CommentDTO;
import org.springframework.data.domain.Page;

public interface CommentService {

    Comment createComment(CommentDTO commentDTO);

    Comment deleteComment(Long id) ;

    Comment findByCommentId(Long id);

    Page<CommentDTO> getCommentPagination(Long filmId, int page, int size, String sortBy, String orderBy);

}
