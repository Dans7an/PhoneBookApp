import java.util.Scanner;

class Main {

    static Scanner scanner = new Scanner(System.in);
    static Phonebook phonebook = new Phonebook();

    static PhoneBookDao phoneBookDao = new PhoneBookDao();

    public static void main(String[] args) {
        boolean running = true;

        while(running){
            menu();
            String word = scanner.nextLine();
            switch (word){
                case "1":
                    welcome();
                    break;
                case "2":
                    running = false;
                    break;
                case "3":
                    menu();
                    break;
                case "4":
                    view();
                    break;
                case "5":
                    add();
                    break;
                case "6":
                    delete();
                    break;
                case "7":
                    getContact();
                    break;
            }
        }

        scanner.close();
    }

    private static void delete() {
        System.out.println("What is the name of the contact you want to delete?");
        String name = scanner.nextLine();
//        phonebook.removeContact(name);
        phoneBookDao.connect();
        phoneBookDao.removeStudent(name);
    }

    private static void add() {
        System.out.print("What is the name of the contact? \n");
        String name = scanner.nextLine();
        System.out.print("What is the number? \n");
        String number = scanner.nextLine();

//        Contact contact = new Contact(name,number);
//        phonebook.addContact(contact);

        Contact contact = new Contact();
        contact.setNumber(number);
        contact.setName(name);

        phoneBookDao.connect();
        phoneBookDao.addContact(contact);

        System.out.println("Contact saved \n");
    }

    private static void view() {
        phoneBookDao.connect();
        phoneBookDao.view();
    }

    private static void getContact(){
        System.out.print("What is the name of the contact? \n");
        String name = scanner.nextLine();
        phoneBookDao.connect();
        Contact contact = phoneBookDao.getContact(name);
        System.out.println(contact.getName() + " | " + contact.getNumber());
    }

    private static void menu(){
        System.out.println("1. Welcome Message \n" +
            "2. Quit \n" +
            "3. Menu \n" +
            "4. View Contacts \n" +
            "5. Add Contact \n" +
            "6. Delete Contact \n" +
            "7. Get Contact");
    }

    private static void welcome(){
        System.out.println("Welcome to your Phonebook");
    }
}
