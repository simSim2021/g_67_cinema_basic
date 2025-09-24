package app.domain;

import java.util.Objects;

public class Ticket {

    private Long id;
    private String titleMovie;
    private Genre genre;
    private double price;
    private boolean active;

    public Ticket(String titleMovie, Genre genre, double price) {
        this.titleMovie = titleMovie;
        this.genre = genre;
        this.price = price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public String getTitleMovie() {
        return titleMovie;
    }

    public double getPrice() {
        return price;
    }

    public Genre getGenre() {
        return genre;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Double.compare(price, ticket.price) == 0 && active == ticket.active && Objects.equals(id, ticket.id) && Objects.equals(titleMovie, ticket.titleMovie) && genre == ticket.genre;
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
