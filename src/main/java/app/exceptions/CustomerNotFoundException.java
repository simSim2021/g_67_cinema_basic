package app.exceptions;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(Long id) {

        super(String.format("Pokupatel s ID %d ne naiden", id));
    }
}
