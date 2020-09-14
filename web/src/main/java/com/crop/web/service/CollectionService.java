package com.crop.web.service;

import com.crop.mapper.dto.CollectionFolderReq;
import com.crop.mapper.model.CArticleCollectionsFolder;
import com.crop.mapper.model.CUser;

import java.util.List;

public interface CollectionService {
    Long folderCreate(CollectionFolderReq req, CUser user);

    List<CArticleCollectionsFolder> folderList(CUser user);
}
