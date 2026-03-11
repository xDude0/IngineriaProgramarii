package pachet;
import java.util.ArrayList;
import java.util.List;

// 1. Clasa Curs
class Curs {
    private String nume;
    private String descriere;
    private double pret;
    private Profesor profesor;
    private List<Student> studentiInscrisi;

    public Curs(String nume, String descriere, double pret, Profesor profesor) {
        this.nume = nume;
        this.descriere = descriere;
        this.pret = pret;
        this.profesor = profesor;
        this.studentiInscrisi = new ArrayList<>();
    }

    public void adaugaStudent(Student student) {
        if (!studentiInscrisi.contains(student)) {
            studentiInscrisi.add(student);
        }
    }

    public String getNume() {
        return nume;
    }
}


// 2. Clasa Student
class Student {
    private String nume;
    private String email;
    private List<Curs> cursuriLaCareEsteInscris;
    private List<SesiuneMentorat> sesiuniProgramate;

    public Student(String nume, String email) {
        this.nume = nume;
        this.email = email;
        this.cursuriLaCareEsteInscris = new ArrayList<>();
        this.sesiuniProgramate = new ArrayList<>();
    }

    public void inscrieLaCurs(Curs curs) {
        if (!cursuriLaCareEsteInscris.contains(curs)) {
            cursuriLaCareEsteInscris.add(curs);
            curs.adaugaStudent(this);
            System.out.println("Studentul " + this.nume + " s-a înscris la cursul: " + curs.getNume());
        }
    }

    // Metoda prin care studentul se înscrie la o sesiune de mentorat
    public void participaLaSesiune(SesiuneMentorat sesiune) {
        if (!sesiuniProgramate.contains(sesiune)) {
            sesiuniProgramate.add(sesiune);
            sesiune.adaugaParticipant(this); // Relație bidirecțională
            System.out.println("Studentul " + this.nume + " a rezervat locul la sesiunea de mentorat: '"
                    + sesiune.getSubiect() + "' programată pe " + sesiune.getDataSiOra());
        }
    }
}

// 3. Clasa Profesor
class Profesor {
    private String nume;
    private String email;
    private List<Curs> cursuriPredate;
    private List<SesiuneMentorat> sesiuniMentorate;

    public Profesor(String nume, String email) {
        this.nume = nume;
        this.email = email;
        this.cursuriPredate = new ArrayList<>();
        this.sesiuniMentorate = new ArrayList<>();
    }

    public Curs creeazaCurs(String numeCurs, String descriere, double pret) {
        Curs cursNou = new Curs(numeCurs, descriere, pret, this);
        this.cursuriPredate.add(cursNou);
        System.out.println("Profesorul " + this.nume + " a creat cursul: " + numeCurs);
        return cursNou;
    }

    // Metoda prin care profesorul se asociază (se înscrie ca mentor) la o sesiune
    public void participaLaSesiune(SesiuneMentorat sesiune) {
        if (!sesiuniMentorate.contains(sesiune)) {
            sesiuniMentorate.add(sesiune);
            sesiune.setMentor(this); // Setează profesorul curent ca mentor al sesiunii
            System.out.println("Profesorul " + this.nume + " a preluat rolul de mentor pentru sesiunea: '"
                    + sesiune.getSubiect() + "' programată pe " + sesiune.getDataSiOra());
        }
    }
}

// 4. Clasa principală de testare
public class Main {
    public static void main(String[] args) {
        // 1. Instanțiem actorii
        Profesor profInformatica = new Profesor("Andrei Mihai", "andrei.mihai@lms.ro");
        Student student1 = new Student("Elena Popa", "elena.popa@student.ro");

        System.out.println("--- Testare Cursuri ---");
        // Creare și înscriere curs
        Curs cursJava = profInformatica.creeazaCurs("Programare Java", "Învață OOP în Java", 200.0);
        student1.inscrieLaCurs(cursJava);

        System.out.println("\n Mentorat");
        SesiuneMentorat sesiuneDebug = new SesiuneMentorat("Rezolvare bug-uri proiect", "15 Martie 2026, ora 18:00");

        profInformatica.participaLaSesiune(sesiuneDebug);
        student1.participaLaSesiune(sesiuneDebug);
    }
}