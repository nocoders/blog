package com.crop.web.service;

import com.crop.web.dto.CommentReq;
import com.crop.web.dto.CommentReplyParam;
import com.crop.mbg.model.CUser;

public interface CommentService {
    Long comment(CommentReq param, CUser user);

    Long reply(CommentReplyParam param, CUser user);

    void commentDelete(Long id);

    Integer commentReplyDelete(Long id);
}
