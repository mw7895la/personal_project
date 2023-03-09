package personal.project.web.board;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import personal.project.domain.board.Board;
import personal.project.domain.board.BoardRepository;
import personal.project.domain.member.Member;
import personal.project.web.SessionConst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {
    private final BoardRepository boardRepository;

    @Autowired
    public BoardController(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @GetMapping
    public String boardList(Model model) {
        List<Board> boards = boardRepository.findAll();

        model.addAttribute("boards", boards);
        return "board/boardList";
    }

    @GetMapping("/add")
    public String addBoardForm(@ModelAttribute Board board) {

        return "board/addBoardForm";
    }

    @PostMapping("/add")
    public String addBoard(@ModelAttribute Board board, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Member findMember = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);

        board.setMember(findMember);
        log.info("{}",board.getMember());
        boardRepository.register(board);


        return "";
    }
}
