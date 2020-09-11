package com.crop.web.service;

import com.crop.mapper.dto.CommentParam;
import com.crop.mapper.dto.CommentReplyParam;
import com.crop.mapper.model.CUser;

public interface CommentService {
    Long comment(CommentParam param, CUser user);

    Long reply(CommentReplyParam param, CUser user);
}
