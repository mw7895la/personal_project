package personal.project.domain.board;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class MemoryBoardRepository implements BoardRepository{

    private static Map<Long, Board> boardStore = new HashMap<>();
    private static long sequence =0L;

    @Override
    public void register(Board board) {
        board.setId(++sequence);
        boardStore.put(board.getId(), board);
    }

    @Override
    public void delete(Board board) {

    }

    @Override
    public List<Board> findAll() {
        return new ArrayList<>(boardStore.values());
    }
}
