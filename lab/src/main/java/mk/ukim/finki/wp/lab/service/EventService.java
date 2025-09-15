package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Category;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> listAll();
    List<Event> searchEvents(String text);
    List<Event> specialSearch(String text,double rating);
    //Optional<Event> save(Event e);
    List<Event> find_by_ID(long id);
    void deleteById(long id);
    Optional<Event> save(Event e);
    Optional<Event> update(Long id, String name, String desc, double popularity_score, Location e, Category c);
}
