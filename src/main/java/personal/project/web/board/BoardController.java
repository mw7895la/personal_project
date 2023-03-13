package personal.project.web.board;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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

    //board view
    @GetMapping
    public String boardList(Model model) {
        List<Board> boards = boardRepository.findAll();

        model.addAttribute("boards", boards);
        return "board/boards";
    }

    @GetMapping("/add")
    public String addBoardForm(@ModelAttribute Board board) {

        return "board/writeForm";
    }

    //add board
    @PostMapping("/add")
    public String addBoard(@ModelAttribute Board board, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        HttpSession session = request.getSession(false);
        Member findMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        board.setUserId(findMember.getUserId());
        boardRepository.register(board);
        //model.addAttribute("board", register);

        redirectAttributes.addAttribute("id", board.getId());
        return "redirect:/board/read/{id}";
    }

    @GetMapping("/update/{boardId}")
    public String updateForm(@PathVariable("boardId") long id, Model model) {
        Board findBoard = boardRepository.findById(id);
        model.addAttribute("board", findBoard);
        return "board/editForm";
    }

    @PostMapping("/update/{boardId}")
    public String updateBoard(@PathVariable("boardId") long id, @ModelAttribute Board board) {
        boardRepository.update(id, board);

        //redirectAttributes.addAttribute("id", board.getId());
        return "redirect:/board/read/{boardId}";
    }

    @GetMapping("/read/{id}")
    public String readBoard(@PathVariable("id") long id, Model model) {
        Board findBoard = boardRepository.findById(id);
        findBoard.setViewCount(findBoard.getViewCount() + 1);
        model.addAttribute("board", findBoard);
        return "board/board";

    }
}
