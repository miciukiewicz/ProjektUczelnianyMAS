import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(final String[] args) throws Exception {

        StandardServiceRegistry registry = null;
        SessionFactory sessionFactory = null;

        try {
            registry = new StandardServiceRegistryBuilder()
                    .configure() // configures settings from hibernate.cfg.xml
                    .build();
            sessionFactory = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            System.out.println("Tworzenie obiektow:");

            Osoba szef1 = new Szef("Jacek", "Kowalski", "10203040501", 555444333);
            ObiektSportowy obiektSportowy1 = new ObiektSportowy("FIT Goclaw", "Jugoslowianska 10", 20, 50.2f, (Szef) szef1);
            Osoba manager1 = new Manager("Adam", "Nowak", "50302050101", 333222111, 3000, obiektSportowy1);
            Osoba manager2 = new Manager("Tomasz", "Czajowski", "50102010101", 444111222, 5000, obiektSportowy1);
            Osoba specjalista1 = new Fizjoterapeuta("Oskar", "Lewandowski", "30201010201", 888666444, 5, RodzajFizjoterapeuty.Masaz);
            Osoba klient1 = new Klient("Dawid", "Kaczmarek", "70102030501", 333111222);
            Kontrakt kontrakt1 = new Kontrakt(LocalDate.of(2020, 01, 01), 5000, obiektSportowy1, (Specjalista) specjalista1);
            Karnet karnet1 = new Karnet(LocalDate.now(), LocalDate.of(2019, 10, 10), obiektSportowy1, (Klient) klient1);
            Usluga usluga1 = new Usluga(LocalDate.of(2019, 06, 17), (Klient) klient1, (Specjalista) specjalista1);

            session.save(szef1);
            session.save(obiektSportowy1);
            session.save(manager1);
            session.save(manager2);
            session.save(specjalista1);
            session.save(klient1);
            session.save(kontrakt1);
            session.save(karnet1);
            session.save(usluga1);

            System.out.println("---------------------------");

            List<ObiektSportowy> ObiektSportowyFromDb = session.createQuery("from ObiektSportowy").list();

            System.out.println("---------------------------");

            session.getTransaction().commit();
            session.close();

            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new GUIListaObiektow(ObiektSportowyFromDb).setVisible(true);
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        } finally {
            if (sessionFactory != null) {
                sessionFactory.close();
            }
        }
    }
}