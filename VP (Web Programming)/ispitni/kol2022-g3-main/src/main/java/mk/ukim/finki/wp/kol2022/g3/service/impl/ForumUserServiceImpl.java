package mk.ukim.finki.wp.kol2022.g3.service.impl;

import mk.ukim.finki.wp.kol2022.g3.model.ForumUser;
import mk.ukim.finki.wp.kol2022.g3.model.ForumUserType;
import mk.ukim.finki.wp.kol2022.g3.model.Interest;
import mk.ukim.finki.wp.kol2022.g3.model.exceptions.InvalidForumUserIdException;
import mk.ukim.finki.wp.kol2022.g3.model.exceptions.InvalidInterestIdException;
import mk.ukim.finki.wp.kol2022.g3.repository.ForumUserRepository;
import mk.ukim.finki.wp.kol2022.g3.repository.InterestRepository;
import mk.ukim.finki.wp.kol2022.g3.service.ForumUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class ForumUserServiceImpl implements ForumUserService, UserDetailsService {

    private final ForumUserRepository forumUserRepository;
    private final InterestRepository interestRepository;
    private final PasswordEncoder passwordEncoder;

    public ForumUserServiceImpl(ForumUserRepository forumUserRepository, InterestRepository interestRepository, PasswordEncoder passwordEncoder) {
        this.forumUserRepository = forumUserRepository;
        this.interestRepository = interestRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<ForumUser> listAll() {
        return forumUserRepository.findAll();
    }

    @Override
    public ForumUser findById(Long id) {
        return forumUserRepository.findById(id).orElseThrow(InvalidForumUserIdException::new);
    }

    @Override
    public ForumUser create(String name, String email, String password, ForumUserType type, List<Long> interestId, LocalDate birthday) {
        List<Interest> interests = interestRepository.findAllById(interestId);
        password = passwordEncoder.encode(password);
        ForumUser forumUser = new ForumUser(name, email, password, type, interests, birthday);
        return forumUserRepository.save(forumUser);
    }

    @Override
    public ForumUser update(Long id, String name, String email, String password, ForumUserType type, List<Long> interestId, LocalDate birthday) {
        ForumUser forumUser = findById(id);
        forumUser.setName(name);
        forumUser.setEmail(email);
        if(!password.equals(forumUser.getPassword())){
            password = passwordEncoder.encode(password);
        }
        forumUser.setPassword(password);
        forumUser.setType(type);
        List<Interest> interests = interestRepository.findAllById(interestId);
        forumUser.setInterests(interests);
        forumUser.setBirthday(birthday);
        return forumUserRepository.save(forumUser);
    }

    @Override
    public ForumUser delete(Long id) {
        ForumUser forumUser = findById(id);
        forumUserRepository.delete(forumUser);
        return forumUser;
    }

    @Override
    public List<ForumUser> filter(Long interestId, Integer age) {
        if(interestId==null && age==null){
            return listAll();
        }else if (interestId==null){
            LocalDate bornBefore = LocalDate.of(
                    LocalDate.now().getYear() - age,
                    LocalDate.now().getMonthValue(),
                    LocalDate.now().getDayOfMonth()
            );
            return forumUserRepository.findAllByBirthdayBefore(bornBefore);
        }else if (age == null){
            Interest interest = interestRepository.findById(interestId).orElseThrow(InvalidInterestIdException::new);
            return forumUserRepository.findAllByInterestsContaining(interest);
        }else{
            Interest interest = interestRepository.findById(interestId).orElseThrow(InvalidInterestIdException::new);
            LocalDate bornBefore = LocalDate.of(
                    LocalDate.now().getYear() - age,
                    LocalDate.now().getMonthValue(),
                    LocalDate.now().getDayOfMonth()
            );
            return forumUserRepository.findAllByInterestsContainingAndBirthdayBefore(interest, bornBefore);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ForumUser user = forumUserRepository.findByEmail(username);
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.singletonList(user.getType())
        );
    }
}
