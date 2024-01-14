import org.bson.*;

public class Kund extends Person {
    private String kundnummer;

    public Kund(String namn, int ålder, String adress, String kundnummer) {
        super(namn, ålder, adress);
        this.kundnummer = kundnummer;
    }

    public String getKundnummer() {
        return kundnummer;
    }

    @Override
    public String toString() { return getNamn()+" är "+getÅlder()+" är och bor på "+getAdress()+" med kundnummer "+kundnummer;}

    public static Kund fromDoc(Document doc)
    {
        if (doc==null) {
            return new Kund("",0,"", "");
        }

        return new Kund(
                doc.getString("namn"),
                doc.getInteger("ålder"),
                doc.getString("adress"),
                doc.getString("kundnummer")
        );
    }


    public Document toDoc(){
        return new Document("namn", getNamn())
                .append("ålder", getÅlder())
                .append("adress", getAdress())
                .append("kundnummer", kundnummer);
    }
}

