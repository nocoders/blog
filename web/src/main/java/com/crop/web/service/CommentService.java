package com.crop.web.service;

import com.crop.mapper.dto.CommentReq;
import com.crop.mapper.dto.CommentReplyParam;
import com.crop.mapper.model.CUser;

public interface CommentService {
    Long comment(CommentReq param, CUser user);

    Long reply(CommentReplyParam param, CUser user);
}
