package personal.project.domain.board;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import personal.project.domain.member.Member;

import java.util.Date;
import java.util.List;

@Data
public class Board {

    private long id;
    private String title;
    private String content;
    private String userId;
    private long viewCount;
    private String regDate;

    //private List<MultipartFile> imageFiles;     //이미지 다중 업로드 위함.



    public Board(String title, String content, String userId,String regDate) {
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.regDate = regDate;

    }
}
