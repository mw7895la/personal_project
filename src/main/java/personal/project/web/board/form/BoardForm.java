package personal.project.web.board.form;

import lombok.Data;

@Data
public class BoardForm {

    private String title;
    private String content;

    public BoardForm(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
