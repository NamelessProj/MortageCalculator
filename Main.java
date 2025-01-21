import java.text.NumberFormat;
import java.util.Currency;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final byte MONTHS_IN_YEAR = 12;
        final byte PERCENT = 100;

        Scanner scanner = new Scanner(System.in);

        int principal = getPrincipal(scanner);

        float monthlyInterest = getMonthlyInterest(scanner, PERCENT, MONTHS_IN_YEAR);

        int numberOfPayments = getNumberOfPayments(scanner, MONTHS_IN_YEAR);

        double mortgage = getMortgage(monthlyInterest, numberOfPayments, principal);

        double totalPayment = mortgage * numberOfPayments;

        System.out.println("Mortgage: " + NumberFormat.getCurrencyInstance().format(mortgage));
        System.out.println("Total Payment: " + NumberFormat.getCurrencyInstance().format(totalPayment));
    }

    public static int getPrincipal(Scanner scanner) {
        int principal;
        Currency currency = NumberFormat.getCurrencyInstance().getCurrency();
        while (true) {
            System.out.print("Principal ("+currency+" 1K - "+currency+" 1M): ");
            principal = scanner.nextInt();
            if (principal >= 1_000 && principal <= 1_000_000)
                break;
            System.out.println("Enter a value between 1,000 and 1,000,000.");
        }
        return principal;
    }

    public static float getMonthlyInterest(Scanner scanner, final byte PERCENT, final byte MONTHS_IN_YEAR) {
        float annualInterest;
        while (true) {
            System.out.print("Annual Interest Rate: ");
            annualInterest = scanner.nextFloat();
            if (annualInterest >= 1 && annualInterest <= 30)
                break;
            System.out.println("Enter a value greater than 0 and less than or equal to 30.");
        }
        return annualInterest / PERCENT / MONTHS_IN_YEAR;
    }

    public static int getNumberOfPayments(Scanner scanner, final byte MONTHS_IN_YEAR) {
        byte years;
        while (true) {
            System.out.print("Period (Years): ");
            years = scanner.nextByte();
            if (years >= 1 && years <= 30)
                break;
            System.out.println("Enter a value between 1 and 30.");
        }
        return years * MONTHS_IN_YEAR;
    }

    public static double getMortgage(float monthlyInterest, int numberOfPayments, int principal) {
        double part1 = monthlyInterest * Math.pow(1 + monthlyInterest, numberOfPayments);
        double part2 = Math.pow(1 + monthlyInterest, numberOfPayments) - 1;
        return principal * (part1 / part2);
    }
}