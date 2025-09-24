package app.exceptions;

public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException(Long id) {

      super(String.format("Билет s ID %d ne naiden", id));
    }
}
