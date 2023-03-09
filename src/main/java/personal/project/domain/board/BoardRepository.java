package personal.project.domain.board;

import java.util.List;

public interface BoardRepository {

    void register(Board board);

    void delete(Board board);

    List<Board> findAll();
}
