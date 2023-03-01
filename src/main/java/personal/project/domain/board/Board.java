package personal.project.domain.board;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class Board {
    private String title;
    private String content;
    private List<MultipartFile> imageFiles;     //이미지 다중 업로드 위함.



}
