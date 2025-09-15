package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Category;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.repository.jpa.EventRepository;
import mk.ukim.finki.wp.lab.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
    private EventRepository eventRepository;
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
    @Override
    public List<Event> listAll() {
        return eventRepository.findAll();
    }
    @Override
    public List<Event> searchEvents(String text) {
        return eventRepository.findAllByNameLike(text);
    }
    @Override
    public List<Event> specialSearch(String text, double rating) {
        return eventRepository.findAllByPopularityScoreAndDescription(rating,text);
    }
    public Optional<Event> save(Event e){
        return Optional.of(this.eventRepository.save(e));
    }
    @Override
    public Optional<Event> update(Long id, String name, String desc, double popularity_score, Location e, Category c){
        List<Event> eventList = eventRepository.findAllById(id);
        Event tmp=eventList.get(0);
        tmp.setPopularityScore(popularity_score);
        tmp.setName(name);
        tmp.setDescription(desc);
        tmp.setLocation(e);
        tmp.setCategory(c);
        return Optional.of(this.eventRepository.save(tmp));
    }
    @Override
    public List<Event> find_by_ID(long id) {
        return eventRepository.findAllById(id);
    }
    public void deleteById(long id) {
        eventRepository.deleteById(id);
    }
}
