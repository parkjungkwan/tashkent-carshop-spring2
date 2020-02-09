package texas.sbv.car.magazine;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Lazy
public interface ArticleRepository extends CrudRepository<Article, Long> {
}
