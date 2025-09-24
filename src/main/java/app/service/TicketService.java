package app.service;

import app.domain.Genre;
import app.domain.Ticket;
import app.exceptions.TicketNotFoundException;
import app.exceptions.TicketSaveException;
import app.exceptions.TicketUpdateException;
import app.repository.TicketRepository;

import java.util.List;

public class TicketService {

    private static TicketService instance;

    private final TicketRepository repository = new TicketRepository();

    private TicketService() {

    }

    public static TicketService getInstance() {
        if (instance == null) {
            instance = new TicketService();
        }
        return instance;
    }

    //Сохранить билет в базе данных (при сохранении продукт автоматически считается активным).
    public Ticket save(Ticket ticket) {
        if (ticket == null) {
            throw new TicketSaveException("Bilet ne mozet bit null");
        }

        String title = ticket.getTitleMovie();
        if (title == null || title.trim().isEmpty()) {
            throw new TicketSaveException("Naimenovanie filma ne dolzno bit pustim");

        }

        //проверка на пустой жанр как на название

        if (ticket.getGenre() == null ) {
            throw new TicketSaveException("Жанр билета не может быть null");
        }

        if (ticket.getPrice() < 0) {
            throw new TicketSaveException("Cena bileta ne dolzna bit otricatelnoi");
        }

        ticket.setActive(true);
        return repository.save(ticket);
    }

    //Вернуть все билеты из базы данных (активные).
    public List<Ticket> getAllActiveTickets() {

        return repository.findAll()
                .stream()
                .filter(Ticket::isActive)
                .toList();

    }

    //Вернуть один билет из базы данных по его идентификатору (если он активен).
    public Ticket getActiveTicketById(Long id) {
        Ticket ticket = repository.findById(id);

        if (ticket == null || !ticket.isActive()) {
            throw new TicketNotFoundException(id);
        }

        return ticket;
    }

    //Изменить один билет в базе данных по его идентификатору.
    public void update(Long id, double newPrice) {
        if (newPrice < 0) {
            throw new TicketUpdateException("Cena bileta ne dolzna bit otricatelnoi");
        }

        repository.update(id, newPrice);
    }

    //Удалить билет из базы данных по его идентификатору.
    public void deleteById(Long id) {
        Ticket ticket = getActiveTicketById(id);
        ticket.setActive(false);
    }

    //Удалить билет из базы данных по наименованию фильма.
    public void deleteByTitle(String title) {

        getAllActiveTickets()
                .stream()
                .filter(x -> x.getTitleMovie().equals(title))
                .forEach(x -> x.setActive(false));
    }

    //Восстановить удалённый билет в базе данных по его идентификатору.
    public void restoreById(Long id) {
        Ticket ticket = repository.findById(id);

        if (ticket == null) {
            throw new TicketNotFoundException(id);
        }

        ticket.setActive(true);
    }

    //Вернуть общее количество билетов в базе данных (активных).
    public int getActiveTicketsCount() {
        return getAllActiveTickets().size();
    }

    //Вернуть суммарную стоимость всех билетов в базе данных (активных).
    public double getActiveTicketsTotalCost() {

        return getAllActiveTickets()
                .stream()
                .mapToDouble(Ticket::getPrice)
                .sum();
    }

    //Вернуть среднюю стоимость билета в базе данных (из активных).
    public double getActiveTicketsAveragePrice() {

        return getAllActiveTickets()
                .stream()
                .mapToDouble(Ticket::getPrice)
                .average()
                .orElse(0.0);
    }

    public List<Ticket> getActiveTicketsByGenre(Genre genre) {
        return getAllActiveTickets()
                .stream()
                .filter(x -> x.getGenre().equals(genre))
                .toList();
    }

}
