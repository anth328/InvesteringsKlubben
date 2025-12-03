import java.time.LocalDate;

public class Bond{

        private String ticker;
        private String name;
        private float price;
        private String currency;
        private float coupon_rate;
        private LocalDate issue_date;
        private LocalDate maturity_date;
        private String rating;
        private String market;
        private LocalDate last_updated;


        Bond(){}

        public Bond(String ticker, String name, float price, String currency,float coupon_rate, LocalDate issue_date, LocalDate maturity_date, String rating, String market,LocalDate last_updated){
            this.ticker = ticker;
            this.name = name;
            this.price = price;
            this.currency = currency;
            this.coupon_rate = coupon_rate;
            this.issue_date = issue_date;
            this.maturity_date = maturity_date;
            this.rating = rating;
            this.market = market;
            this.last_updated = last_updated;

        }


        @Override

        public String toString() {
            return "ticker: " + ticker + " Navn: " + name + " Price: " + price + " currency: " + currency + " coupon_rate: " + coupon_rate + " issue_date: " + issue_date +
                    " maturity_date: " + maturity_date + " rating: " + rating + " market: " + market + "Last Updated: " + last_updated;
        }
    }


