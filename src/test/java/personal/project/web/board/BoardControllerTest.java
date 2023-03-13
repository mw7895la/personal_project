package personal.project.web.board;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import personal.project.domain.board.Board;
import personal.project.domain.board.BoardRepository;
import personal.project.domain.board.MemoryBoardRepository;
import personal.project.domain.member.Member;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class BoardControllerTest {

    private MemoryBoardRepository repository = new MemoryBoardRepository();

    @BeforeEach
    void before(){
        Board board = new Board("hello","hello","qwe",new Date().toString());
        repository.register(board);
    }

    @Test
    void updateBoard() {

        Board findBoard = repository.findById(1L);

        findBoard.setTitle("hi");
        findBoard.setContent("hi");

        Board findBoard_2 = repository.findById(1L);

        Assertions.assertThat(findBoard.getTitle()).isEqualTo(findBoard_2.getTitle());
    }
}