package co.istad.cms.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface FileService {

    Map<String, Object> uploadSingle(MultipartFile file);

}
