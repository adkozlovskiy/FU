package kozlovskiy.prod.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "favorites")
public class Favorite {

    /**
     * Favorites id (primary key).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Date when the user added a favorite.
     */
    @Column(nullable = false)
    private Date addDate;

    /**
     * Foreign key on {@link Movie} which user added in favorites.
     */
    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "id", nullable = false)
    private Movie movie;

    /**
     * Foreign key on {@link User} which add favorite.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    /**
     * Gets the favorite id.
     *
     * @return the received id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the favorite id.
     *
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the favorite add date.
     *
     * @return the received date
     */
    public Date getAddDate() {
        return addDate;
    }

    /**
     * Sets the favorite add date.
     *
     * @param addDate the date to set
     */
    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    /**
     * Gets the favorite movie.
     *
     * @return the received movie
     */
    public Movie getMovie() {
        return movie;
    }

    /**
     * Sets the favorite movie
     *
     * @param movie the movie to set
     */
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    /**
     * Gets the favorite user.
     *
     * @return the received user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the favorite user.
     *
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }
}
