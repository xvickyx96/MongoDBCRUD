import java.util.Scanner;
import java.util.ArrayList;


        public class Menu {
            private MongoDBFasadAnställd dbA;
            private MongoDBFasadKund dbK;

            public Menu() {
                dbA = new MongoDBFasadAnställd();
                dbK = new MongoDBFasadKund();
            }

            public void menyStart() {
                System.out.println("Välkommen till er databas för anställda och kunder!");

                while (true) {
                    System.out.println("\nVälj alternativ: ");
                    System.out.println("1. Sök efter anställd med anställningsnummer");
                    System.out.println("2. Lägg till anställd");
                    System.out.println("3. Ta bort anställd");
                    System.out.println("4. Ändra information om anställd");
                    System.out.println("5. Visa alla anställda");
                    System.out.println("6. Sök efter kund med kundnummer");
                    System.out.println("7. Lägg till kund");
                    System.out.println("8. Ta bort kund");
                    System.out.println("9. Ändra information om kund");
                    System.out.println("10. Visa alla kunder");
                    System.out.println("0. Avsluta");

                    Scanner scanner = new Scanner(System.in);
                    int choice = scanner.nextInt();

                    switch (choice) {
                        case 0:
                            System.out.println("Programmet avslutas.");
                            return;

                        case 1:
                            System.out.println("Skriv in anställningsnummer:");
                            Scanner scanner1 = new Scanner(System.in);
                            String anställningsnummer = scanner1.nextLine();
                            Anställd anställd = dbA.FindByAnställningsnummer(anställningsnummer);
                            if (anställd != null && !anställd.getAnställningsnummer().isEmpty()) {
                                System.out.println("Anställd hittad: " + anställd);
                            } else {
                                System.out.println("Anställd med anställningsnummer " + anställningsnummer + " hittades inte.");
                            }
                            break;

                        case 2:
                            System.out.println("Lägg till anställd:");
                            System.out.println("Ange namn:");
                            Scanner scanner2 = new Scanner(System.in);
                            String namn = scanner2.nextLine();
                            System.out.println("Ange ålder:");
                            int ålder = scanner2.nextInt();
                            scanner2.nextLine();
                            System.out.println("Ange adress:");
                            String adress = scanner2.nextLine();
                            System.out.println("Ange anställningsnummer:");
                            String anstNr = scanner2.nextLine();

                            Anställd nyAnställd = new Anställd(namn, ålder, adress, anstNr);
                            dbA.insertOne(nyAnställd);
                            System.out.println("Anställd tillagd.");
                            break;

                        case 3:
                            System.out.println("Ta bort anställd:");
                            System.out.println("Ange anställningsnummer:");
                            Scanner scanner3 = new Scanner(System.in);
                            String anstNrToRemove = scanner3.nextLine();
                            dbA.Delete(anstNrToRemove);
                            System.out.println("Anställd borttagen.");
                            break;

                        case 4:
                            System.out.println("Uppdatera information om anställd:");
                            System.out.println("Ange anställningsnummer:");
                            Scanner scanner8 = new Scanner(System.in);
                            String anstNrToChange = scanner8.nextLine();
                            System.out.println("Ange namn:");
                            Scanner scanner9 = new Scanner(System.in);
                            String nyNamn = scanner9.nextLine();
                            System.out.println("Ange ålder:");
                            int nyÅlder = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("Ange adress:");
                            String nyAdress = scanner.nextLine();
                            System.out.println("Ange anställningsnummer:");
                            String nyAnstNr = scanner.nextLine();
                            Anställd nyAnställd1 = new Anställd(nyNamn, nyÅlder, nyAdress, nyAnstNr);
                            dbA.updateOne(anstNrToChange, nyAnställd1);
                            System.out.println("Information om anställd ändrad.");
                        case 5:
                            ArrayList<Anställd> anställdaList = dbA.findAll();
                            if (anställdaList.isEmpty()) {
                                System.out.println("Inga anställda hittades.");
                            } else {
                                System.out.println("Alla anställda:");
                                for (Anställd anställdNu : anställdaList) {
                                    System.out.println(anställdNu);
                                }
                            }
                            break;

                        case 6:
                            System.out.println("Skriv in kundnummer:");
                            Scanner scanner4 = new Scanner(System.in);
                            String kundnummer = scanner4.nextLine();
                            Kund kund = dbK.FindByKundnummer(kundnummer);
                            if (kund != null && !kund.getKundnummer().isEmpty()) {
                                System.out.println("Kund hittad: " + kund);
                            } else {
                                System.out.println("Kund med kundnummer " + kundnummer + " hittades inte.");
                            }
                            break;


                        case 7:
                            System.out.println("Lägg till kund:");
                            System.out.println("Ange namn:");
                            Scanner scanner5 = new Scanner(System.in);
                            String namn2 = scanner5.nextLine();
                            System.out.println("Ange ålder:");
                            int ålder2 = scanner5.nextInt();
                            scanner5.nextLine();
                            System.out.println("Ange adress:");
                            String adress2 = scanner5.nextLine();
                            System.out.println("Ange kundnummer:");
                            String kundNr2 = scanner5.nextLine();

                            Kund nyKund = new Kund(namn2, ålder2, adress2, kundNr2);
                            dbK.insertOne(nyKund);
                            System.out.println("Kund tillagd.");
                            break;

                        case 8:
                            System.out.println("Ta bort kund");
                            System.out.println("Ange kundnummer:");
                            Scanner scanner6 = new Scanner(System.in);
                            String kundNrToRemove = scanner6.nextLine();
                            dbK.Delete(kundNrToRemove);
                            System.out.println("Kund borttagen.");
                            break;

                        case 9:
                            System.out.println("Uppdatera information om kund:");
                            System.out.println("Ange kundnummer:");
                            Scanner scanner11 = new Scanner(System.in);
                            String kundNrToChange = scanner11.nextLine();
                            System.out.println("Ange namn:");
                            Scanner scanner12 = new Scanner(System.in);
                            String nyKundNamn = scanner12.nextLine();
                            System.out.println("Ange ålder:");
                            int nyKundÅlder = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("Ange adress:");
                            String nyKundAdress = scanner.nextLine();
                            System.out.println("Ange kundnummer:");
                            String nyKundNr = scanner.nextLine();
                            Kund nyKund1 = new Kund(nyKundNamn, nyKundÅlder, nyKundAdress, nyKundNr);
                            dbK.updateOne(kundNrToChange, nyKund1);
                            System.out.println("Information om kund ändrad.");

                        case 10:
                            ArrayList<Kund> kundList = dbK.findAll();
                            if (kundList.isEmpty()) {
                                System.out.println("Inga kunder hittades.");
                            } else {
                                System.out.println("Alla kunder:");
                                for (Kund kundNu : kundList) {
                                    System.out.println(kundNu);
                                }
                            }
                            break;



                        default:
                            System.out.println("Ogiltigt val!");
                            break;
                    }
                }
            }
        }
