package contacts;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
class Director {
    public static void AddContact(List<Contact> listOfRecords, String phoneNumberRegex, Scanner scanner) {
        System.out.print("Enter the name:");
        String name = scanner.nextLine();
        System.out.print("Enter the surname:");
        String surname = scanner.nextLine();
        System.out.print("Enter the number:");
        String phoneNumber = scanner.nextLine();
        Contact contact;
        if (!phoneNumber.matches(phoneNumberRegex)) {
            contact = new Contact.Builder()
                    .setName(name)
                    .setSurname(surname)
                    .setPhoneNumber("[no number]")
                    .build();
        } else {
            contact = new Contact.Builder()
                    .setName(name)
                    .setSurname(surname)
                    .setPhoneNumber(phoneNumber)
                    .build();
        }

        listOfRecords.add(contact);
        System.out.println("The record added.");
    }
    public static void PrintContact(List<Contact> listOfRecords) {
        int i = 1;
        for (Contact contact : listOfRecords
        ) {
            System.out.printf("%d. %s %s, %s%n", i++, contact.getName(), contact.getSurname(), contact.getPhoneNumber());
        }
    }
    public static void Editor(Scanner scanner, List<Contact> listOfRecords, String phoneNumberRegex) {
        if (listOfRecords.size() == 0) {
            System.out.println("No records to edit!");
        } else {
            PrintContact(listOfRecords);
            System.out.println("Select a record:");
            int whichContact = Integer.parseInt(scanner.nextLine());
            Contact contact = listOfRecords.get(whichContact - 1);
            System.out.println("Select a field (name, surname, number):");
            String field = scanner.nextLine();
            switch (field) {
                case "number" -> {
                    System.out.println("Enter number:");
                    String number = scanner.nextLine();
                    if (number.matches(phoneNumberRegex)) {
                        contact.setPhoneNumber(number);
                    } else {
                        System.out.println("Wrong number format!");
                        contact.setPhoneNumber("[no number]");
                    }
                    System.out.println("The record updated!");
                }
                case "name" -> {
                    contact.setName(scanner.nextLine());
                    System.out.println("The record updated!");
                }
                default -> {
                    contact.setSurname(scanner.nextLine());
                    System.out.println("The record updated!");
                }
            }
        }
    }
    public static void Remover(Scanner scanner, List<Contact> listOfRecords) {
        if (listOfRecords.size() == 0) {
            System.out.println("No records to remove!");
        } else {
            PrintContact(listOfRecords);
            System.out.println("Select a record:");
            int whichContact = Integer.parseInt(scanner.nextLine());
            Contact contact = listOfRecords.get(whichContact - 1);
            listOfRecords.remove(contact);
            System.out.println("The record removed!");
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Contact> listOfRecords = new ArrayList<>();
        String phoneNumberRegex = "^\\+*[0-9a-zA-Z()]+((\\s+|-+)[0-9a-zA-Z]{2,})*$|^\\+*[0-9a-zA)]+((\\s+|-+)\\(*[0-9a-zA-Z]{2,}\\)*)*$";
        String enterAnAction;
        while (true) {
            System.out.print("Enter action (add, remove, edit, count, list, exit):");
            enterAnAction = scanner.nextLine();
            switch (enterAnAction) {
                case "count" -> System.out.printf("The Phone Book has %d records.%n", listOfRecords.size());
                case "edit" -> Editor(scanner, listOfRecords, phoneNumberRegex);
                case "remove" -> Remover(scanner, listOfRecords);
                case "add" -> AddContact(listOfRecords, phoneNumberRegex, scanner);
                case "list" -> PrintContact(listOfRecords);
                default -> {
                    return;
                }
            }
        }

    }
}
class Contact {
    private String name;
    private String surname;
    private String phoneNumber;
    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Contact(String name, String surname, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    static class Builder {
        private String name;
        private String surname;
        private String phoneNumber;
        Builder() {}

        Builder setName(String name) {
            this.name = name;
            return this;
        }

        Builder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }
        Contact build() {
            return new Contact(name, surname, phoneNumber);
        }
    }
}
