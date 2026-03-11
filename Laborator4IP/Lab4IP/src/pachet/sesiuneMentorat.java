package pachet;
import java.util.ArrayList;
import java.util.List;

class SesiuneMentorat
{
    private String subiect;
    private String dataSiOra;
    private Profesor mentor;
    private List<Student> participanti;

    public SesiuneMentorat(String subiect, String dataSiOra)
    {
        this.subiect = subiect;
        this.dataSiOra = dataSiOra;
        this.participanti = new ArrayList<>();
    }

    public void setMentor(Profesor mentor)
    {
        this.mentor = mentor;
    }

    public void adaugaParticipant(Student student)
    {
        if (!participanti.contains(student)) participanti.add(student);
    }

    public String getSubiect()
    {
        return subiect;
    }
    public String getDataSiOra()
    {
        return dataSiOra;
    }
}