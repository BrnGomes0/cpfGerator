import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the first nine digits of the CPF: ");
        String cpfInput = sc.next().replaceAll("\\D", "");  // Remove all non-digit characters

        if (cpfInput.length() != 9) {
            System.out.println("CPF must contain exactly 9 digits. Please try again.");
            return;
        }

        int[] cpfArray = new int[11];

        for (int i = 0; i < 9; i++) {
            cpfArray[i] = Character.getNumericValue(cpfInput.charAt(i));
        }

        int d1 = calculateVerifierDigit(cpfArray, 10);
        int d2 = calculateVerifierDigit(cpfArray, 11);

        cpfArray[9] = d1;
        cpfArray[10] = d2;

        String formattedCPF = formatCPF(cpfArray);
        System.out.println("Formatted CPF: " + formattedCPF);
    }

    public static int calculateVerifierDigit(int[] cpfArray, int multiplier) {
        int sum = 0;
        for (int i = 0; i < cpfArray.length; i++) {
            sum += cpfArray[i] * multiplier;
            multiplier--;
        }
        int remainder = sum % 11;
        return (remainder < 2) ? 0 : (11 - remainder);
    }

    public static String formatCPF(int[] cpfArray) {
        return String.format("%03d.%03d.%03d-%02d",
                cpfArray[0] * 100 + cpfArray[1] * 10 + cpfArray[2],
                cpfArray[3] * 100 + cpfArray[4] * 10 + cpfArray[5],
                cpfArray[6] * 100 + cpfArray[7] * 10 + cpfArray[8],
                cpfArray[9] * 10 + cpfArray[10]);
    }
}