import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        Scanner scanner = new Scanner(System.in);
        System.out.println("What's your name?");
        String str = scanner.nextLine();
        System.out.println("Hi, I am "+str);
        System.out.print("Vui lòng nhập số hạng thứ nhất: ");
        int soA = scanner.nextInt();
        System.out.print("Vui lòng nhập số hạng thứ hai: ");
        int soB = scanner.nextInt();
        int kq = soA + soB;
        System.out.println("Tính tổng [" + soA + " + " + soB + " = " + kq);
    }
}