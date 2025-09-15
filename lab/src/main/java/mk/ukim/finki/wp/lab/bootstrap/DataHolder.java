package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Category;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.model.User;
import mk.ukim.finki.wp.lab.model.enumerations.Role;
import mk.ukim.finki.wp.lab.repository.jpa.CategoryRepository;
import mk.ukim.finki.wp.lab.repository.jpa.EventRepository;
import mk.ukim.finki.wp.lab.repository.jpa.LocationRepository;
import mk.ukim.finki.wp.lab.repository.jpa.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

// In-memory data holder
@Component
public class DataHolder {
    public static List<User> users = null;
    public static List<Location> locations=null;
    public static List<Category> categories=null;
    public static List<Event> eventList;
    private final CategoryRepository categoryRepository;
    private final LocationRepository locationRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final PasswordEncoder passwordEncoder;

    public DataHolder(CategoryRepository categoryRepository, UserRepository userRepository,PasswordEncoder passwordEncoder,LocationRepository locationRepository,EventRepository eventRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.locationRepository=locationRepository;
        this.eventRepository = eventRepository;
    }
    @PostConstruct
    public void init() {
        users = new ArrayList<>();
        locations= new ArrayList<>();
        categories=new ArrayList<>();
        eventList=new ArrayList<>();
        if (this.userRepository.count() == 0) {
            users.add(new User("user1", passwordEncoder.encode("us1"), "User1", "User1", Role.ROLE_USER));
            users.add(new User("user2", passwordEncoder.encode("us2"), "User2", "User2", Role.ROLE_USER));
            users.add(new User("user3", passwordEncoder.encode("us3"), "User3", "User3", Role.ROLE_USER));
            users.add(new User("admin", passwordEncoder.encode("admin"), "Admin", "Admin", Role.ROLE_ADMIN));
            this.userRepository.saveAll(users);
        }
        if(locationRepository.count()==0){
            locations.add(new Location("AAAA","200","BBBB","DAOSDASKODK"));
            locations.add(new Location("FSDFDS","120","FASDFDS","DASDSADD"));
            locationRepository.saveAll(locations);
        }
        if(categoryRepository.count()==0){
            categories.add(new Category("Festival"));
            categories.add(new Category("Panagur"));
            categoryRepository.saveAll(categories);
        }
        if(eventRepository.count()==0){
            Event tmp= new Event("event1","event2",30.00);
            tmp.setCategory(categories.get(0));
            tmp.setLocation(locations.get(0));
            eventList.add(tmp);
            eventRepository.saveAll(eventList);
        }
    }
}