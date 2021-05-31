package kozlovskiy.prod.service;

import kozlovskiy.prod.entities.Session;
import kozlovskiy.prod.repo.SessionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionService {

    @Autowired
    private SessionRepo sessionRepo;

    public List<Session> getSessionsByMovieId(Long movieId) {
        return sessionRepo.findByMovieId(movieId);
    }

    public Session getSession(Long sessionId) {
        return sessionRepo.getSession(sessionId);
    }
}
