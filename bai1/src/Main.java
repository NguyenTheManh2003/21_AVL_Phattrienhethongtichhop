import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("nhap a: ");
        int a = sc.nextInt();
        System.out.println("nhap b:");
        int b = sc.nextInt();
        System.out.print("Nhap c: ");
        int c = sc.nextInt();
        int max = a;
        int min = a;

        if (b > max) {
            max = b;
        }

        if (c > max) {
            max = c;
        }

        if (b < min) {
            min = b;
        }

        if (c < min) {
            min = c;
        }

        System.out.println("Gia tri lon nhat: " + max);
        System.out.println("Gia tri nho nhat: " + min);

        sc.close();
    }
}