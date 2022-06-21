import java.util.ArrayList;
import java.util.List;

class Phonebook {
    List<Contact> contacts = new ArrayList();

    public List<Contact> getContacts(){
        return contacts;
    }

    public void addContact(Contact contact){
        contacts.add(contact);
    }

    public void removeContact(String name){
        contacts.remove(findContact(name));
    }

    public void contactList() {
        for (Contact contact: contacts) {
            System.out.print("\nName: " + contact.name + ", Number: " + contact.number);
        }
    }

    public Contact findContact(String name){
        Contact desiredContact = null;
        for (Contact contact: contacts) {
            if(contact.getName().equals(name)){
                desiredContact = contact;
            }
        }
        return desiredContact;
    }
}
