package contacts;

import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Director {
    public static void AddContact(List<Contacts> listOfRecords, String phoneNumberRegex, Scanner scanner) {
        System.out.println("Enter the type (person, organization):");
        String personOrOrg = scanner.nextLine();
        Contacts contacts;
        String name;
        String phoneNumber;
        LocalDateTime createdDate = LocalDateTime.now();
        LocalDateTime lastEdit = LocalDateTime.now();
        if (personOrOrg.equals("person")) {
            System.out.print("Enter the name:");
            name = scanner.nextLine();
            System.out.print("Enter the surname:");
            String surname = scanner.nextLine();
            System.out.println("Enter the birth date:");
            String birthDay = scanner.nextLine();
            if (birthDay.isBlank()) {
                birthDay = "[no data]";
                System.out.println("Bad birth date!");
            }
            System.out.println("Enter the gender (M, F):");
            String gender = scanner.nextLine();
            if (!gender.equals("M") && !gender.equals("F")) {
                gender = "[no data]";
                System.out.println("Bad gender!");
            }
            System.out.print("Enter the number:");
            phoneNumber = scanner.nextLine();
            if (!phoneNumber.matches(phoneNumberRegex)) {
                phoneNumber = "[no number]";
            }
            contacts = new Person(name, phoneNumber, createdDate, lastEdit, surname, birthDay, gender);
        } else {
            System.out.println("Enter the organization name:");
            name = scanner.nextLine();
            System.out.println("Enter the address:");
            String address = scanner.nextLine();
            System.out.print("Enter the number:");
            phoneNumber = scanner.nextLine();
            if (!phoneNumber.matches(phoneNumberRegex)) {
                phoneNumber = "[no number]";
            }
            contacts = new Organization(name, phoneNumber, createdDate, lastEdit, address);
        }
        listOfRecords.add(contacts);
        System.out.println("The record added.");
    }
    public static void PrintContactNames(List<Contacts> listOfRecords) {
        int i = 1;
        for (Contacts contact : listOfRecords
        ) {
            if (contact instanceof Person) {
                System.out.printf("%d. %s %s%n", i++, contact.getName(), ((Person) contact).getSurname());
            } else {
                System.out.printf("%d. %s%n", i++, contact.getName());
            }
        }
    }
    public static String ContactNames(List<Contacts> listOfRecords) {
        int i = 1;
        String text = "";
        for (Contacts contact : listOfRecords
        ) {
            if (contact instanceof Person) {
                text += String.format("%d. %s %s%n", i++, contact.getName(), ((Person) contact).getSurname());
            } else {
                text += String.format("%d. %s%n", i++, contact.getName());
            }
        }
        return text;
    }

    public static void PrintContact(Scanner scanner, List<Contacts> listOfRecords, String regexToMatch) {
        PrintContactNames(listOfRecords);
        //while (scanner.hasNextLine()) {
        System.out.println();
        System.out.println("[list] Enter action ([number], back):");
        String whichAction = scanner.nextLine();
        if (whichAction.equals("back")) {
            //break;
        } else {
            int indexToShow = Integer.parseInt(whichAction);
            Contacts contacts = listOfRecords.get(indexToShow - 1);
            Shower(contacts, scanner, regexToMatch, listOfRecords);
        }
        //}

    }
    public static String Shower(Contacts contacts, Scanner scanner, String regexTomatch, List<Contacts> contactsList) {
        if (contacts != null) {
            if (contacts instanceof Organization) {
                System.out.println("Organization name: " + contacts.getName());
                System.out.println("Address: " + ((Organization) contacts).getAddress());
                System.out.println("Number: " + contacts.getPhoneNumber());
                System.out.println("Time created: " + contacts.getCreatedDate());
                System.out.println("Time last edit: " + contacts.getLastEdit());
            } else {
                System.out.println("Name: " + contacts.getName());
                System.out.println("Surname: " + ((Person) contacts).getSurname());
                System.out.println("Birth date: " + ((Person) contacts).getBirthDate() );
                System.out.println("Gender: " + ((Person) contacts).getGender());
                System.out.println("Number: " + contacts.getPhoneNumber());
                System.out.println("Time created: " + contacts.getCreatedDate());
                System.out.println("Time last edit: " + contacts.getLastEdit());
            }
        }

        while (true) {
            System.out.println();
            System.out.println("[record] Enter action (edit, delete, menu):");
            String action = scanner.nextLine();
            switch (action) {
                case "edit" -> EditorNew(scanner, contacts, regexTomatch);
                case "delete" -> RemoverNew(contacts, contactsList);
                default -> {
                    return "back";
                }
            }
        }
    }
    public static void EditorNew(Scanner scanner, Contacts contact, String phoneNumberRegex) {
        if (contact instanceof Person) {
            System.out.println("Select a field (name, surname, birth, gender, number):");
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
                }
                case "name" -> {
                    System.out.println("Enter name:");
                    contact.setName(scanner.nextLine());
                }
                case "gender" -> {
                    System.out.println("Enter gender");
                    String gender = scanner.nextLine();
                    if (gender.isBlank()) {
                        gender = "[no data]";
                        System.out.println("Bad gender");
                    }
                    ((Person) contact).setGender(gender);
                }
                case "birth" -> {
                    System.out.println("Enter birthday:");
                    String birthday = scanner.nextLine();
                    if (birthday.isBlank()) {
                        birthday = "[no data]";
                        System.out.println("Bad birth date!");
                    }
                    ((Person) contact).setBirthDate(birthday);
                }
                default -> ((Person) contact).setSurname(scanner.nextLine());
            }
        } else {
            System.out.println("Select a field (address, number):");
            String field = scanner.nextLine();
            if (field.equals("number")) {
                System.out.println("Enter number:");
                String number = scanner.nextLine();
                if (number.matches(phoneNumberRegex)) {
                    contact.setPhoneNumber(number);
                } else {
                    System.out.println("Wrong number format!");
                    contact.setPhoneNumber("[no number]");
                }
            } else {
                ((Organization) contact).setAddress(scanner.nextLine());
            }
        }
        contact.setLastEdit(LocalDateTime.now());
        System.out.println("Saved");
    }

    public static void RemoverNew(Contacts contact, List<Contacts> listOfRecords) {
        if (listOfRecords.size() == 0) {
            System.out.println("No records to remove!");
        } else {
            listOfRecords.remove(contact);
            System.out.println("The record removed!");
        }
    }
    public static String ContactNamesForSearch(List<Contacts> listOfRecords) {
        int i = 1;
        String text = "";
        for (Contacts contact : listOfRecords
        ) {
            if (contact instanceof Person) {
                text += String.format("%s %s%n%s%n", contact.getName(), ((Person) contact).getSurname(), contact.getPhoneNumber());
            } else {
                text += String.format("%s%n%s%n", contact.getName(), contact.getPhoneNumber());
            }
        }
        return text;
    }
    public static void Searcher(Scanner scanner, List<Contacts> contactsList, String regexTomatch) {
        System.out.println("Enter search query:");
        String regex = scanner.nextLine();
        Pattern pattern = Pattern.compile(".*" + regex + ".*", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(ContactNamesForSearch(contactsList));
        String element = "";
        int i = 1;
        Map<Integer, String> mapOfNames = new HashMap<>();
        while (matcher.find()) {
            String name = matcher.group();
            element += i + "." + name + "\n";
            mapOfNames.put(i, name);
            i++;
        }
        System.out.println(element);
        while (true) {
            System.out.println("[search] Enter action ([number], back, again):");
            String whatToDo = scanner.nextLine();
            Contacts contacts = null;
            if (whatToDo.equals("again")) {
                Searcher(scanner, contactsList, regexTomatch);
            } else if (whatToDo.equals("back")) {
                break;
            } else {
                for (Contacts contact : contactsList
                ) {
                    if (mapOfNames.containsKey(Integer.parseInt(whatToDo)) && contact != null) {
                        if (mapOfNames.get(Integer.parseInt(whatToDo)).contains(contact.getName())) {
                            contacts = contact;
                        }
                    }
                }
                whatToDo = Shower(contacts, scanner, regexTomatch, contactsList);
                if (whatToDo.equals("back")) {
                    break;
                }
            }
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Contacts> listOfRecords = new ArrayList<>();
        String phoneNumberRegex = "^\\+*[0-9a-zA-Z()]+((\\s+|-+)[0-9a-zA-Z]{2,})*$|^\\+*[0-9a-zA)]+((\\s+|-+)\\(*[0-9a-zA-Z]{2,}\\)*)*$";
        String enterAnAction;
        String filePath = "C:\\Users\\alahmm\\Downloads\\txt.txt";
        while (true) {
            System.out.print("[menu] Enter action (add, list, search, count, exit):");
            enterAnAction = scanner.nextLine();
            switch (enterAnAction) {
                case "count" -> System.out.printf("The Phone Book has %d records.%n", listOfRecords.size());
                case "add" -> AddContact(listOfRecords, phoneNumberRegex, scanner);
                case "list" -> PrintContact(scanner, listOfRecords, phoneNumberRegex);
                case "search" -> Searcher(scanner, listOfRecords, phoneNumberRegex);
                default -> {
                    return;
                }
            }
            System.out.println();
        }

    }
}
class Contacts {
    private String name;
    private String phoneNumber;

    private LocalDateTime createdDate;

    private LocalDateTime lastEdit;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getLastEdit() {
        return lastEdit;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public void setLastEdit(LocalDateTime lastEdit) {
        this.lastEdit = lastEdit;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Contacts(String name, String phoneNumber, LocalDateTime createdDate, LocalDateTime lastEdit) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.createdDate = createdDate;
        this.lastEdit = lastEdit;
    }
}
class Organization extends Contacts{
    private String address;


    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        this.address = address;
    }

    public Organization(String name, String phoneNumber, LocalDateTime createdDate, LocalDateTime lastEdit, String address) {
        super(name, phoneNumber, createdDate, lastEdit);
        this.address = address;
    }
}
class Person extends Contacts {
    private String surname;
    private String birthDate;
    private String gender;


    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSurname() {
        return surname;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getGender() {
        return gender;
    }

    public Person(String name, String phoneNumber, LocalDateTime createdDate, LocalDateTime lastEdit, String surname, String birthDate, String gender) {
        super(name, phoneNumber, createdDate, lastEdit);
        this.surname = surname;
        this.birthDate = birthDate;
        this.gender = gender;
    }
}