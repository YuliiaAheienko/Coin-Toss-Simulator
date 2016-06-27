import java.util.HashMap;
import java.util.Random;

/**
 * Created by Julia on 27.06.2016.
 */
public class Coin {
    private String sideUp = ""; // "heads" or "tails"

    private Random random = new Random();

    public Coin() {
        if (random.nextBoolean())
            sideUp = "heads";
        else
            sideUp = "tails";
    }

    public void toss() {
        if (random.nextBoolean())
            sideUp = "heads";
        else
            sideUp = "tails";
    }

    public void BiasedToss() {
        if (random.nextDouble() < 0.75)
            sideUp = "heads";
        else
            sideUp = "tails";
    }

    public String getSideUp() {
        return sideUp;
    }

    public static void main(String[] args) {
        Coin coin = new Coin();
        int headsNumber = 0;
        int tailsNumber = 0;

        System.out.println("Using the toss method:");
        for (int i = 0; i < 1000; i++) {
            coin.toss();
            if ("heads".equals(coin.getSideUp()))
                headsNumber++;
            else
                tailsNumber++;
        }
        System.out.printf("heads: %d\n", headsNumber);
        System.out.printf("tails: %d\n", tailsNumber);

        headsNumber = tailsNumber = 0;
        System.out.println("\nUsing the BiasedToss method:");
        for (int i = 0; i < 1000; i++) {
            coin.BiasedToss();
            if ("heads".equals(coin.getSideUp()))
                headsNumber++;
            else
                tailsNumber++;
        }
        System.out.printf("heads: %d\n", headsNumber);
        System.out.printf("tails: %d\n", tailsNumber);

        Coin quarter = new Coin();
        Coin dime = new Coin();
        Coin nickel = new Coin();
        double balance;

        // 1st realization
        balance = 0.0;
        while (balance < 1.0) {
            quarter.toss();
            dime.toss();
            nickel.toss();
            balance += "heads".equals(quarter.getSideUp()) ? 0.25 : 0;
            balance += "heads".equals(dime.getSideUp()) ? 0.1 : 0;
            balance += "heads".equals(nickel.getSideUp()) ? 0.01 : 0;
        }
        System.out.printf("\n%s: balance = %.2f", balance == 1.0 ? "User won" : "User lost", balance);

        //2nd realization
        balance = 0.0;
        HashMap<Coin, Double> coins = new HashMap<>();
        coins.put(quarter, 0.25);
        coins.put(dime, 0.10);
        coins.put(nickel, 0.01);
        while (balance < 1.0) {
            for (Coin c : coins.keySet()) {
                c.toss();
                if ("heads".equals(c.getSideUp()))
                    balance += coins.get(c);
            }
        }
        System.out.printf("\n%s: balance = %.2f", balance == 1.0 ? "User won" : "User lost", balance);
    }
}