+ ## Kod
+ Modell: Person
    + Namn
    + Ålder
    + Adress
+ Modell: Kund
    + Kundnummer
    + toDoc()
    + fromDoc()
+ Modell: AnställdDB
    + Anställningsnummer
    + toDoc()
    + fromDoc()
+ Facade: MongoDBFasadKund
    + CRUD
    + FindByKundnummer
+ Facade: MongoDBFasadAnställd
    + CRUD
    + FindByAnställningsnummer

+ ## Bibliotek och verktyg
+ Följande bibliotek och verktyg används i detta projekt:

  + MongoDB Java Driver
  + org.mongodb:mongodb-driver-sync:4.9.1
  + com.googlecode.sli4j:sli4j-acl:2.0
  + org.mongodb:mongodb-driver:3.12.13

+ ## Förutsättningar
+ För att vår applikation ska fungera korrekt behöver följande vara uppfyllt:

  + Java OpenJDK 19 måste vara installerat
