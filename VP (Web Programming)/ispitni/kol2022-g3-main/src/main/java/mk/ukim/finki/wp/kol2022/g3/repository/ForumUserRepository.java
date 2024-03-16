package mk.ukim.finki.wp.kol2022.g3.repository;

import mk.ukim.finki.wp.kol2022.g3.model.ForumUser;
import mk.ukim.finki.wp.kol2022.g3.model.ForumUserType;
import mk.ukim.finki.wp.kol2022.g3.model.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ForumUserRepository extends JpaRepository<ForumUser, Long> {
    public List<ForumUser> findAllByInterestsContainingAndBirthdayBefore(Interest interest, LocalDate bornBefore);
    public List<ForumUser> findAllByInterestsContaining(Interest interest);
    public List<ForumUser> findAllByBirthdayBefore(LocalDate bornBefore);
    public ForumUser findByEmail(String email);
}
