package co.istad.cms.service;

import co.istad.cms.model.Content;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ContentService {

    List<Content> findContents();

    boolean saveContent(Content content, MultipartFile fileThumbnail);

    void deleteContentById(Integer id);

    Content findContentById(Integer id);

   void editContentById(Content editContent );

}
