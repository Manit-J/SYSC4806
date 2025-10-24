package org.example;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class JPATest {

    public static void testJPA(){

        BuddyInfo buddy1 = new BuddyInfo( "John", "123 Street", "89130300435");
        BuddyInfo buddy2 = new BuddyInfo( "Ant", "Somewhere City", "8928492845");

        // Connecting to the database through EntityManagerFactory
        // connection details loaded from persistence.xml
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myTest");
        EntityManager em = emf.createEntityManager();


        // Creating a new transaction
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        // Persisting the product entity objects
        em.persist(buddy1);
        em.persist(buddy2);

        AddressBook book = new AddressBook();
        book.addBuddy(buddy1);
        book.addBuddy(buddy2);

        em.persist(book);

        tx.commit();

        // Querying the contents of the database using JPQL query
        Query q = em.createQuery("SELECT b FROM BuddyInfo b");

        @SuppressWarnings("unchecked")
        List<BuddyInfo> results = q.getResultList();

        System.out.println("List of Buddies\n----------------");

        for (BuddyInfo b: results) {

            System.out.println(b.getName() + " (id=" + b.getId() + ")");
        }

        // Querying the contents of the database using JPQL query
        Query q2 = em.createQuery("SELECT a FROM AddressBook a");

        @SuppressWarnings("unchecked")
        List<AddressBook> results2 = q2.getResultList();

        System.out.println("List of Books\n----------------");

        for (AddressBook a: results2) {

            System.out.println(a.getId());
        }

        // Closing connection
        em.close();

        emf.close();
    }

    public static void main(String[] args) {
        testJPA();
    }
}
