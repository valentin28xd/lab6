package exercitiul;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class mainApp {
    public static void main(String[] args) {

        List<Angajat> angajati = generareAngajati(5);

        angajati.stream()
                .filter(angajat -> angajat.getSalariu() > 2500)
                .forEach(System.out::println);

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        try {
            File file = new File("src/main/resources/angajati.json");

            mapper.writeValue(file, angajati);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Angajat> generareAngajati(int numarAngajati) {
        List<Angajat> angajati = new ArrayList<>();
        String[] posturi = {"Programator", "Manager", "Contabil", "HR"};

        for (int i = 0; i < numarAngajati; i++) {
            String nume = "Angajat " + (i + 1);
            String post = posturi[new Random().nextInt(posturi.length)];
            LocalDate dataAngajarii = LocalDate.of(2022, new Random().nextInt(12) + 1, new Random().nextInt(28) + 1);
            float salariu = (int) (2000 + Math.random() * 3000);

            Angajat angajat = new Angajat(nume, post, dataAngajarii, salariu);
            angajati.add(angajat);
        }
        return angajati;
    }


}
class Angajat {
    private String nume;
    private String post;
    private LocalDate dataAngajarii;
    private float salariu;

    public Angajat() {
    }

    Angajat(String nume, String post, LocalDate dataAngajarii, float salariu) {
        this.nume = nume;
        this.post = post;
        this.dataAngajarii = dataAngajarii;
        this.salariu = salariu;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public LocalDate getDataAngajarii() {
        return dataAngajarii;
    }

    public void setDataAngajarii(LocalDate dataAngajarii) {
        this.dataAngajarii = dataAngajarii;
    }

    public float getSalariu() {
        return salariu;
    }

    public void setSalariu(float salariu) {
        this.salariu = salariu;
    }

    @Override
    public String toString() {
        return "Angajat{" +
                "nume='" + nume + '\'' +
                ", post='" + post + '\'' +
                ", dataAngajarii=" + dataAngajarii +
                ", salariu=" + salariu +
                '}';
    }
}
