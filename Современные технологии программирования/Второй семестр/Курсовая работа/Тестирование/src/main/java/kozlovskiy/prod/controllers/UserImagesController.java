package kozlovskiy.prod.controllers;

import kozlovskiy.prod.entities.UploadResponse;
import kozlovskiy.prod.service.AuthService;
import kozlovskiy.prod.service.UserImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/api/users/images")
public class UserImagesController {

    @Autowired
    private UserImagesService userImagesService;

    @Autowired
    private AuthService authService;

    /**
     * @param file is MultiPart file.
     * @return {@link UploadResponse} with Uri if successful uploaded.
     */
    @PostMapping("/upload")
    private UploadResponse uploadUserImage(@RequestBody MultipartFile file) {
        String fileName = userImagesService.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/users/images/download/")
                .path(fileName)
                .toUriString();

        return new UploadResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    /**
     * @param fileName is name of uploaded file
     * @param request  is servlet request
     * @return OK with uploaded file as body.
     */
    @GetMapping("/download/{fileName:.+}")
    private ResponseEntity<Resource> downloadUserImage(@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = userImagesService.loadFileAsResource(fileName);

        String contentType = null;

        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ignored) {
            // --
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}