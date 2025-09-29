package client;

import app.controller.CustomerController;
import app.controller.TicketController;

import java.util.Scanner;

public class Client {

    private static TicketController ticketController;
    private static CustomerController customerController;
    private static Scanner scanner;

    public static void main(String[] args) {

        // Создаём объекты контроллеров для взаимодействия с приложением
        ticketController = new TicketController();
        customerController = new CustomerController();
        scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Выберите желаемую операцию:");
            System.out.println("1 - операции с билетами");
            System.out.println("2 - операции с покупателями");
            System.out.println("0 - выход");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    ticketOperations();
                    break;
                case "2":
                    customerOperations();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Некорректный ввод!");
                    break;
            }
        }
    }

    public static void ticketOperations() {
        while (true) {
            try {
                System.out.println("Выберите желаемую операцию с билетами:");
                System.out.println("1 - сохранить билет");
                System.out.println("2 - получить все билеты");
                System.out.println("3 - получить билет по идентификатору");
                System.out.println("4 - изменить билет");
                System.out.println("5 - удалить билет по идентификатору");
                System.out.println("6 - удалить билет по названию фильма");
                System.out.println("7 - восстановить билет по идентификатору");
                System.out.println("8 - получить количество билетов");
                System.out.println("9 - получить суммарную стоимость всех билетов");
                System.out.println("10 - получить среднюю стоимость билета");
                System.out.println("11 - получить все билеты по жанру фильма");
                System.out.println("0 - выход");

                String input = scanner.nextLine();

                switch (input) {
                    case "1":
                        System.out.println("Введите название фильма");
                        String title = scanner.nextLine();
                        System.out.println("Введите цену билета");
                        String price = scanner.nextLine();
                        System.out.println("Введите жанр фильма");
                        String genre = scanner.nextLine();
                        System.out.println(ticketController.save(title, genre, price));
                        break;
                    case "2":
                        ticketController.getAll().forEach(System.out::println);
                        break;
                    case "3":
                        System.out.println("Введите идентификатор билета");
                        String id = scanner.nextLine();
                        System.out.println(ticketController.getById(id));
                        break;
                    case "4":
                        System.out.println("Введите идентификатор билета");
                        id = scanner.nextLine();
                        System.out.println("Введите новую цену билета");
                        price = scanner.nextLine();
                        ticketController.update(id, price);
                        break;
                    case "5":
                        System.out.println("Введите идентификатор билета");
                        id = scanner.nextLine();
                        ticketController.deleteById(id);
                        break;
                    case "6":
                        System.out.println("Введите название фильма");
                        title = scanner.nextLine();
                        ticketController.deleteByTitle(title);
                        break;
                    case "7":
                        System.out.println("Введите идентификатор билета");
                        id = scanner.nextLine();
                        ticketController.restoreById(id);
                        break;
                    case "8":
                        System.out.println("Количество билетов - " + ticketController.getTicketsCount());
                        break;
                    case "9":
                        System.out.println("Суммарная стоимость билетов - " +
                                ticketController.getTicketsTotalCost());
                        break;
                    case "10":
                        System.out.println("Средняя стоимость билета - " +
                                ticketController.getTicketsAveragePrice());
                        break;
                    case "11":
                        System.out.println("Введите жанр фильма - ");
                        genre = scanner.nextLine();
                        System.out.println("Все билеты по указанному жанру -" +
                                ticketController.getTicketsByGenre(genre));
                        break;
                    case "0":
                        return;
                    default:
                        System.out.println("Некорректный ввод!");
                        break;
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static void customerOperations() {
        while (true) {
            try {
                System.out.println("Выберите желаемую операцию с покупателями:");
                System.out.println("1 - сохранить покупателя");
                System.out.println("2 - получить всех покупателей");
                System.out.println("3 - получить покупателя по идентификатору");
                System.out.println("4 - изменить покупателя");
                System.out.println("5 - удалить покупателя по идентификатору");
                System.out.println("6 - удалить покупателя по имени");
                System.out.println("7 - восстановить покупателя по идентификатору");
                System.out.println("8 - получить количество покупателей");
                System.out.println("9 - получить стоимость корзины покупателя");
                System.out.println("10 - получить среднюю стоимость билетов в корзине покупателя");
                System.out.println("11 - добавить билет в корзину покупателя");
                System.out.println("12 - удалить билет из корзины покупателя");
                System.out.println("13 - очистить корзину покупателя");
                System.out.println("0 - выход");

                String input = scanner.nextLine();

                switch (input) {
                    case "1":
                        System.out.println("Введите имя покупателя");
                        String name = scanner.nextLine();
                        System.out.println(customerController.save(name));
                        break;
                    case "2":
                        customerController.getAll().forEach(System.out::println);
                        break;
                    case "3":
                        System.out.println("Введите идентификатор");
                        String id = scanner.nextLine();
                        System.out.println(customerController.getById(id));
                        break;
                    case "4":
                        System.out.println("Введите идентификатор");
                        id = scanner.nextLine();
                        System.out.println("Введите новое имя покупателя");
                        name = scanner.nextLine();
                        customerController.update(id, name);
                        break;
                    case "5":
                        System.out.println("Введите идентификатор");
                        id = scanner.nextLine();
                        customerController.deleteById(id);
                        break;
                    case "6":
                        System.out.println("Введите имя покупателя");
                        name = scanner.nextLine();
                        customerController.deleteByName(name);
                        break;
                    case "7":
                        System.out.println("Введите идентификатор");
                        id = scanner.nextLine();
                        customerController.restoreById(id);
                        break;
                    case "8":
                        System.out.println("Количество покупателей - "
                                + customerController.getCustomersNumber());
                        break;
                    case "9":
                        System.out.println("Введите идентификатор");
                        id = scanner.nextLine();
                        System.out.println("Стоимость корзины покупателя - " +
                                customerController.getCustomersCartTotalCost(id));
                        break;
                    case "10":
                        System.out.println("Введите идентификатор");
                        id = scanner.nextLine();
                        System.out.println("Средняя цена билета в корзине - " +
                                customerController.getCustomersCartAveragePrice(id));
                        break;
                    case "11":
                        System.out.println("Введите идентификатор покупателя");
                        String customerId = scanner.nextLine();
                        System.out.println("Введите идентификатор билета");
                        String ticketId = scanner.nextLine();
                        customerController.addTicketToCustomersCart(customerId, ticketId);
                        break;
                    case "12":
                        System.out.println("Введите идентификатор покупателя");
                        customerId = scanner.nextLine();
                        System.out.println("Введите идентификатор билета");
                        ticketId = scanner.nextLine();
                        customerController.removeTicketFromCustomersCart(customerId, ticketId);
                        break;
                    case "13":
                        System.out.println("Введите идентификатор покупателя");
                        id = scanner.nextLine();
                        customerController.clearCustomersCart(id);
                        break;
                    case "0":
                        return;
                    default:
                        System.out.println("Некорректный ввод!");
                        break;
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

}
