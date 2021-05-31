package kozlovskiy.prod.service;

import kozlovskiy.prod.entities.User;
import kozlovskiy.prod.repo.AuthRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class AuthService {

    @Autowired
    private AuthRepo authRepo;

    /**
     * @param rd is register data such as password, salt, username
     * @return created user if successful registered, else - null
     */
    public User registerUser(User rd) {
        User existed = authRepo.findByLogin(rd.getNickname(), rd.getEmail());
        if (existed == null) {
            try {
                String encodedPass = getHashedPass(rd.getPassword(), rd.getSalt());
                rd.setPassword(encodedPass);
                return authRepo.save(rd);

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    public User authorizeUser(User ld) {
        User u = authRepo.findByLogin(ld.getNickname(), ld.getEmail());

        if (u != null) {
            try {
                if (u.getPassword().equals(getHashedPass(ld.getPassword(), u.getSalt())))
                    return u;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        return null;
    }

    public User getUserData(Long userId) {
        return authRepo.findById(userId).orElse(null);
    }

    public String getHashedPass(String pass, String salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest((pass + salt).getBytes());

        BigInteger no = new BigInteger(1, messageDigest);

        StringBuilder encoded = new StringBuilder(no.toString(16));

        while (encoded.length() < 32) {
            encoded.insert(0, "0");
        }

        return encoded.toString();
    }
}
