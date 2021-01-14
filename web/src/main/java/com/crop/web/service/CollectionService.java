package com.crop.web.service;

import com.crop.web.dto.CollectionFolderReq;
import com.crop.web.dto.CollectorReq;
import com.crop.mbg.model.CArticleCollectionsFolder;
import com.crop.mbg.model.CUser;

import java.util.List;

public interface CollectionService {
    Long folderCreate(CollectionFolderReq req, CUser user);

    List<CArticleCollectionsFolder> folderList(CUser user);

    Long collect(CollectorReq req,Long userId);

    void collectDelete(Long id);
}
