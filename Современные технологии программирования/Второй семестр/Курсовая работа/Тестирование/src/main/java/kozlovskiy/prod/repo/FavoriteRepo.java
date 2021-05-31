package kozlovskiy.prod.repo;

import kozlovskiy.prod.entities.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FavoriteRepo extends JpaRepository<Favorite, Long> {

    /**
     * @param id the user id
     * @return List of {@link Favorite} WHERE user_id = id
     */
    @Query(value = "SELECT * FROM favorites WHERE user_id = :userId ORDER BY add_date", nativeQuery = true)
    List<Favorite> findByUserId(@Param("userId") Long id);
}
