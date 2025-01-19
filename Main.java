import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final byte MONTHS_IN_YEAR = 12;
        final byte PERCENT = 100;

        Scanner scanner = new Scanner(System.in);

        int principal;
        while (true) {
            System.out.print("Principal ($1K - $1M): ");
            principal = scanner.nextInt();
            if (principal >= 1_000 && principal <= 1_000_000)
                break;
            System.out.println("Enter a value between 1,000 and 1,000,000.");
        }

        String annualInterestString;
        float annualInterest;
        while (true) {
            System.out.print("Annual Interest Rate: ");
            annualInterestString = scanner.next();
            annualInterest = Float.parseFloat(annualInterestString);
            if (annualInterest >= 1 && annualInterest <= 30)
                break;
            System.out.println("Enter a value greater than 0 and less than or equal to 30.");
        }
        float monthlyInterest = annualInterest / PERCENT / MONTHS_IN_YEAR;

        byte years;
        while (true) {
            System.out.print("Period (Years): ");
            years = scanner.nextByte();
            if (years >= 1 && years <= 30)
                break;
            System.out.println("Enter a value between 1 and 30.");
        }
        int numberOfPayments = years * MONTHS_IN_YEAR;

        double part1 = monthlyInterest * Math.pow(1 + monthlyInterest, numberOfPayments);
        double part2 = Math.pow(1 + monthlyInterest, numberOfPayments) - 1;
        double mortgage = principal * part1 / part2;

        System.out.println("Mortgage: " + NumberFormat.getCurrencyInstance().format(mortgage));
    }
}