package personal.project.domain.board;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import personal.project.domain.member.Member;

import java.util.List;

@Data
public class Board {

    private long id;
    private String title;
    private String content;
    private Member member;
    private List<MultipartFile> imageFiles;     //이미지 다중 업로드 위함.



    public Board(String title, String content, Member member) {
        this.title = title;
        this.content = content;
        this.member = member;
    }
}
