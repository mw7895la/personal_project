package personal.project.domain.board;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class MemoryBoardRepository implements BoardRepository{

    private static Map<Long, Board> boardStore = new HashMap<>();
    private static long sequence =0L;

    @Override
    public Board register(Board board) {
        board.setId(++sequence);
        board.setViewCount(0);
        Date date = new Date();
        board.setRegDate(date.toString());
        boardStore.put(board.getId(), board);
        return board;
    }

    @Override
    public void delete(long id) {
        boardStore.remove(id);
    }

    @Override
    public Board findById(long id) {
        Board findBoard = boardStore.get(id);
        return findBoard;
    }

    @Override
    public void update(long id, Board board) {
        Board findBoard = findById(id);
        findBoard.setTitle(board.getTitle());
        findBoard.setContent(board.getContent());
    }

    @Override
    public List<Board> findAll() {
        return new ArrayList<>(boardStore.values());
    }
}
