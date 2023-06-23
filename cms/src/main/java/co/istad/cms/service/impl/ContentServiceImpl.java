package co.istad.cms.service.impl;

import co.istad.cms.model.Content;
import co.istad.cms.repository.ContentRepository;
import co.istad.cms.service.ContentService;
import co.istad.cms.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService {

    private final ContentRepository contentRepository;
    private final FileService fileService;

    @Override
    public List<Content> findContents() {
        return contentRepository.select();
    }

    @Override
    public boolean saveContent(Content content, MultipartFile fileThumbnail) {

        content.setUuid(UUID.randomUUID().toString());
        content.setEditor("<h1>Default Editor</h1>");
        content.setCreatedAt(LocalDateTime.now());
        content.setIsDeleted(false);

        Map<String, Object> result = fileService.uploadSingle(fileThumbnail);
        content.setThumbnail((String) result.get("fileName"));

        boolean isSaved = contentRepository.insert(content);

        return isSaved;

        // return false;
    }

    @Override
    public void deleteContentById(Integer id) {
        boolean isDeleted =contentRepository.updateIsDeletedById(id,true);
        if(!isDeleted){
            throw new RuntimeException("Content is failed to delete");
        }
    }

    @Override
    public Content findContentById(Integer id) {
        Content content= contentRepository.selectById(id).orElseThrow(
                () -> new RuntimeException(" This Error")
        );


      return content;
    }
}
