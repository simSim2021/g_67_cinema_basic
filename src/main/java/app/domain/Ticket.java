package app.domain;

import java.util.Objects;

public class Ticket {

    private Long id;
    private String titleMovie;
    private String genre;
    private double price;
    private boolean active;


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Double.compare(price, ticket.price) == 0 && active == ticket.active && Objects.equals(id, ticket.id) && Objects.equals(titleMovie, ticket.titleMovie) && Objects.equals(genre, ticket.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titleMovie, genre, price, active);
    }

    @Override
    public String toString() {
        return String.format("Ticket: id - %d, titleMovie - %s, genre - %s, price - %.2f, active - %b",
                id, titleMovie, genre, price, active);
    }
}
