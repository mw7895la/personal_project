package personal.project.domain.board;

import java.util.List;

public interface BoardRepository {

    Board register(Board board);

    void delete(long id);

    Board findById(long id);

    List<Board> findAll();

    void update(long id, Board board);
}
