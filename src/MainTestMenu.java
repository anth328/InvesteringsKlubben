import java.time.LocalDate;
import java.util.Scanner;

public class MainTestMenu {
    public static void main(String[] args) {

        DataRepository data = new DataRepository();
        data.currency(); // ALTID, ALLTID KØR DENNE HER METODE FØR data.stockMarket() ELLER HELE PROGRAMMET DØR
        data.bruger();
        data.stockMarket();
        data.bonds();
        data.transactions();

        Portfolio portfolio = new Portfolio(data);

        User user = new User();
        User aktiveUser = user.UsernameMatch();

        System.out.println("Brugerens rolle: " + aktiveUser.getRole());

        if (aktiveUser.getRole() == UserRole.leder) {
            lederMenu(aktiveUser, data, portfolio);

        } else {

            boolean menuRun = true;

            System.out.println("Velkommen til Investerings klubben: " + aktiveUser.getFullname());
            System.out.println("Indtast et tal for at bruge programmets funktioner");
            System.out.println("Skriv 1 for hjælp");

            Scanner sc = new Scanner(System.in);

            while (menuRun) {
                portfolio.addUsersTransactionsToList(aktiveUser);
                portfolio.transactionToAktie();
                portfolio.calculateBalance(aktiveUser);

                System.out.println("\n=== HOVEDMENU ===");
                System.out.println("1: Hjælp");
                System.out.println("2: Se aktiemarkedet");
                System.out.println("3: Se obligationer");
                System.out.println("4: Køb aktier");
                System.out.println("5: Vis dit portefølje");
                System.out.println("6: Sælg aktier");
                System.out.println("7: Se rank");

                int valg = readInt(sc, "Vælg: ");

                switch (valg) {
                    case 1 -> {
                        System.out.println("1: Hjælp");
                        System.out.println("2: Se aktiemarkedet");
                        System.out.println("3: Se obligationer");
                        System.out.println("4: Køb aktier");
                        System.out.println("5: Vis dit portfolje");
                        System.out.println("6: Se Aktier som du investerer!");
                        System.out.println("7: Sælg aktier");
                        System.out.println("8: Se rangliste");
                        pause(sc);
                    }

                    case 2 -> {
                        data.printAktierBasic();
                        int index = readInt(sc, "Vælg aktie nummer (Enter for tilbage): ");
                        if (index != 0) data.printAktieFull(index);
                        System.out.println("Vil du købe aktier? Tryk 4");
                        pause(sc);
                    }

                    case 3 -> {
                        data.printBondsBasic();
                        int indexBond = readInt(sc, "Vælg obligationsnummer (Enter for tilbage): ");
                        if (indexBond != 0) data.printBondFull(indexBond);
                        pause(sc);
                    }

                    case 4 -> {
                        String ticker = readString(sc, "Indtast ticker (Enter for tilbage): ").toUpperCase();
                        if (ticker.isEmpty()) break;

                        Aktie chosen = null;
                        for (Aktie a : data.getAktier()) {
                            if (a.getTicker().equalsIgnoreCase(ticker)) {
                                chosen = a;
                                break;
                            }
                        }

                        if (chosen == null) {
                            System.out.println("Aktie findes ikke!");
                            pause(sc);
                            break;
                        }

                        int antal = readInt(sc, "Indtast antal (Enter for tilbage): ");
                        if (antal == 0) break;

                        portfolio.buyAktie(aktiveUser, chosen, antal);
                        pause(sc);
                    }

                    case 5 -> {
                        portfolio.printPortfolio(aktiveUser);
                        pause(sc);
                    }

                    case 6 -> {
                        String tickerSell = readString(sc, "Indtast ticker du vil sælge (Enter for tilbage): ").toUpperCase();
                        if (tickerSell.isEmpty()) break;

                        Aktie chosenSell = null;
                        for (Aktie a : data.getAktier()) {
                            if (a.getTicker().equalsIgnoreCase(tickerSell)) {
                                chosenSell = a;
                                break;
                            }
                        }

                        if (chosenSell == null) {
                            System.out.println("Aktie blev ikke fundet!");
                            pause(sc);
                            break;
                        }

                        int antalSell = readInt(sc, "Hvor mange vil du sælge? (Enter for tilbage): ");
                        if (antalSell == 0) break;

                        portfolio.sellAktie(aktiveUser, chosenSell, antalSell);
                        pause(sc);
                    }

                    case 7 -> {
                        portfolio.rankList();
                        pause(sc);
                    }

                    default -> {
                        System.out.println("Try again!");
                        pause(sc);
                    }
                }
            }
        }
    }


    private static int readInt(Scanner sc, String message) {
        while (true) {
            System.out.print(message);
            String input = sc.nextLine().trim();
            if (input.isEmpty()) return 0;
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Try again!");
            }
        }
    }

    private static String readString(Scanner sc, String message) {
        System.out.print(message);
        return sc.nextLine().trim();
    }


    private static void pause(Scanner sc) {
        System.out.println("\nTryk Enter for at vende tilbage til menuen...");
        sc.nextLine();
    }


    public static void lederMenu(User aktiveUser, DataRepository data, Portfolio portfolio) {

        Scanner sc = new Scanner(System.in);
        boolean run = true;

        System.out.println("===== LEDER MENU ======");
        System.out.println("Velkommen til " + aktiveUser.getFullname());

        while (run) {
            System.out.println("\nIndtast dit valg:");
            System.out.println("1: Se alle brugere");
            System.out.println("2: Se alle transaktioner");
            System.out.println("3: Se portefølje for specifik bruger");
            System.out.println("4: Se rangliste");
            System.out.println("5: Se aktiemarkedet");
            System.out.println("6: Se transaktionshistorik for specifik bruger");
            System.out.println("0: Log ud");

            int valg = readInt(sc, "Vælg: ");

            switch (valg) {

                case 1 -> {
                    System.out.println("\n==== ALLE BRUGERE ====");
                    data.printUsers();
                    pause(sc);
                }

                case 2 -> {
                    System.out.println("\n==== ALLE TRANSAKTIONER ====");
                    data.printTransactions();
                    pause(sc);
                }

                case 3 -> {
                    System.out.println("\nIndtast bruger-ID for den portefølje du vil se:");
                    int id = readInt(sc, "> ");

                    User valgt = null;
                    for (User u : data.getUsers()) {
                        if (u.getUser_id() == id) {
                            valgt = u;
                            break;
                        }
                    }

                    if (valgt == null) {
                        System.out.println("Bruger ikke fundet.");
                    } else {
                        portfolio.printPortfolio(valgt);
                    }

                    pause(sc);
                }

                case 4 -> {
                    System.out.println("\n==== RANGLISTE (PROFIT) ====");
                    portfolio.rankList();
                    pause(sc);
                }

                case 5 -> {
                    System.out.println("\n==== AKTIEMARKEDET ====");
                    data.printAktierBasic();
                    int index = readInt(sc, "Vælg aktie nummer (Enter for tilbage): ");
                    if (index != 0) {
                        data.printAktieFull(index);
                    }
                    pause(sc);
                }

                case 6 -> {
                    System.out.println("\nIndtast bruger-ID for at se transaktionshistorik:");
                    int id = readInt(sc, "> ");

                    boolean found = false;
                    System.out.println("\n==== Transaktioner for " + id + " ====");
                    for (Transactions t : data.getTransactions()) {
                        if (t.getUserid() == id) {
                            System.out.println(t);
                            found = true;
                        }
                    }

                    if (!found) {
                        System.out.println("Ingen transaktioner fundet for denne bruger.");
                    }

                    pause(sc);
                }

                case 0 -> {
                    System.out.println("Logger ud af ledermenu...");
                    run = false;
                }

                default -> {
                    System.out.println("Ugyldigt valg!");
                    pause(sc);
                }
            }
        }
    }




}


