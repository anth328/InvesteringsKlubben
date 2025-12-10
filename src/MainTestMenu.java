import java.time.LocalDate;
import java.util.Scanner;

public class MainTestMenu {
    public static void main(String[] args) {

        DataRepository data = new DataRepository();
        data.bruger();
        data.stockMarket();
        data.bonds();
        data.transactions();

        Portfolio portfolio = new Portfolio(data);

        User user = new User();
        User aktiveUser = user.UsernameMatch();

        System.out.println("Brugerens rolle: " + aktiveUser.getRole());

        if (aktiveUser.getRole() == UserRole.leder) {
            lederMenu(user, data, portfolio);

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
                System.out.println("1: Se aktiemarkedet");
                System.out.println("2: Se obligationer");
                System.out.println("3: Køb aktier");
                System.out.println("4: Vis alle transaktioner");
                System.out.println("5: Se dine aktier");
                System.out.println("6: Sælg aktier");
                System.out.println("7: Se rank");

                int valg = readInt(sc, "Vælg: ");

                switch (valg) {

                    case 1 -> {
                        data.printAktierBasic();
                        int index = readInt(sc, "Vælg aktie nummer (Enter for tilbage): ");
                        if (index != 0) data.printAktieFull(index);
                        System.out.println("Vil du købe aktier? Tryk 4");
                        pause(sc);
                    }

                    case 2 -> {
                        data.printBondsBasic();
                        int indexBond = readInt(sc, "Vælg obligationsnummer (Enter for tilbage): ");
                        if (indexBond != 0) data.printBondFull(indexBond);
                        pause(sc);
                    }

                    case 3 -> {
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

                    case 4 -> {
                        data.printTransactions();
                        pause(sc);
                    }

                    case 5 -> {
                        portfolio.printEgneAktier();
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


    public static void lederMenu (User user, DataRepository data, Portfolio portfolio){

        portfolio.calculateBalance(user);
        Scanner sc = new Scanner(System.in);
        boolean run = true;
        System.out.println("LEDER MENU");

        while (run){
            System.out.println("Indtast dit valg:");
            System.out.println("1: Se alle brugere");
            System.out.println("2: Se alle transaktioner");
            System.out.println("3: Se personlig portofolio");
            System.out.println("4: Se alle portofolios");
            System.out.println("5: Se rangliste");
            System.out.println("6: Opdater CSV");
            System.out.println("7: Se Aktiemarkedet");
            System.out.println("8: Transaktion historik");
            System.out.println("9: Køb aktier");
            System.out.println("10: Sælg aktier");



            int valg = sc.nextInt();

            switch(valg){

                case 1: {
                    data.printUsers();

                    break;
                }
                case 2: {
                    data.printTransactions();
                    break;
                }
                case 3:{
                    portfolio.printPortfolio(user);
                    break;
                }
                case 4: {
                    portfolio.printAllPortfolios();//
                    break;
                }
                case 5: {
                    portfolio.rankList();
                    break;
                }
                case 6: {
                     //
                    break;
                }
                case 7: {
                    data.printAktierBasic(); // viser alle aktier

                    System.out.println("Vælg aktie nummer for at se alle detaljer:");
                    Scanner sc2 = new Scanner(System.in);
                    int index = sc2.nextInt();   // vælge aktie

                    data.printAktieFull(index);

                    System.out.println("Vil du købe aktier? Tryk 4");
                    break;
                }
                case 8: {
                    data.printTransactions();
                    break;
                }
                case 9: {
                    Scanner scanner = new Scanner(System.in);
                    scanner.nextLine();
                    System.out.println("Indtast ticker (fx AAPL):");
                    String ticker = scanner.nextLine().toUpperCase();

                    Aktie chosen = null;
                    for (Aktie a : data.getAktier()) {
                        if (a.getTicker().equalsIgnoreCase(ticker)) {
                            chosen = a;
                            break;
                        }
                    }

                    if (chosen == null) {
                        System.out.println("Aktie findes ikke!");
                        break;
                    }

                    System.out.println("Indtast antal:");
                    int antal = scanner.nextInt();

                    portfolio.buyAktie(user, chosen, antal);
                    break;
                }

                case 10: {

                    break;
                }

                default:
                    System.out.println("Ugyldigt valg!");

            }



        }
    }
}


