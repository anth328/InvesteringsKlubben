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
            System.out.println("Nu kan du indtaste et tal for at bruge programmets funktioner");
            System.out.println("Skriv 1 for hjælp");
            while (menuRun) {
                portfolio.addUsersTransactionsToList(aktiveUser);
                portfolio.transactionToAktie();
                portfolio.calculateBalance(aktiveUser);
                Scanner sc = new Scanner(System.in);
                int valg = sc.nextInt();
                switch (valg) {
                    case 1: {
                        System.out.println("1: for hjælp");
                        System.out.println("2: Se aktiemarkedet");
                        System.out.println("3: Se obligationer");
                        System.out.println("4: Køb aktier");
                        System.out.println("5: Vis alle transaktioner");
                        System.out.println("6: Se Aktier som du investere!");
                        System.out.println("7: Sælg aktier");
                        System.out.println("8: Se rank");
                        break;
                    }

                    case 2: {
                        data.printAktierBasic(); // viser alle aktier

                        System.out.println("Vælg aktie nummer for at se alle detaljer:");
                        Scanner sc2 = new Scanner(System.in);
                        int index = sc2.nextInt();   // vælge aktie

                        data.printAktieFull(index);

                        System.out.println("Vil du købe aktier? Tryk 4");
                        break;
                    }

                    case 3: {

                        data.printBondsBasic();

                        System.out.println("Vælg obligationsnummer for at se alle detaljer:");
                        Scanner scBond = new Scanner(System.in);
                        int indexBond = scBond.nextInt();

                        data.printBondFull(indexBond);

                        break;

                    }
                    case 4: {
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

                        portfolio.buyAktie(aktiveUser, chosen, antal);
                        break;
                    }


                    case 5: {
                        data.printTransactions();
                        break;
                    }

                    case 6: {
                        portfolio.printEgneAktier();
                        break;
                    }
                    case 7: {
                        System.out.println("Indtast ticker du vil sælge:");
                        Scanner scSell = new Scanner(System.in);
                        String tickerSell = scSell.nextLine().toUpperCase();

                        Aktie chosenSell = null;
                        for (Aktie a : data.getAktier()) {
                            if (a.getTicker().equalsIgnoreCase(tickerSell)) {
                                chosenSell = a;
                                break;
                            }
                        }

                        if (chosenSell == null) {
                            System.out.println("Aktie blev ikke fundet!");
                            break;
                        }

                        System.out.println("Hvor mange vil du sælge?");
                        int antalSell = scSell.nextInt();

                        portfolio.sellAktie(aktiveUser, chosenSell, antalSell);
                        break;
                    }

                    case 8: {
                        portfolio.rankList();
                        break;
                    }

                }
            }
        }

    }


    public static void lederMenu (User user, DataRepository data, Portfolio portfolio){

        portfolio.calculateBalance(user);
        Scanner sc = new Scanner(System.in);
        boolean run = true;
        System.out.println("LEDER MENU");

        while (run){

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
            System.out.println("");


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
                    portfolio.printEgneAktier(user);
                    break;
                }
                case 4: {
                    portfolio.printAllPortfolios();
                    break;
                }
                case 5: {
                    portfolio.rankList();
                    break;
                }
                case 6: {
                    data.updateCSVFiles();
                    break;
                }
                case 7: {
                    data.printAktierBasic();
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


