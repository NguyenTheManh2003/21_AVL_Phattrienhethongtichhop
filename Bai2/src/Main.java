import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int n;
        Scanner nhap = new Scanner(System.in);
        System.out.print("Nhap vao so phan tu: ");
        n=nhap.nextInt();
        int dayso[]=new int[n];
        for (int i=0;i<n;i++) {
            System.out.print("Nhap phan tu thu "+(i+1)+":");
            dayso[i] = nhap.nextInt();
        }
        System.out.println("Day so vua nhap vao la:");
        for (int i=0;i<n;i++) System.out.print(dayso[i]+" ");
        int max=dayso[0];
        int min=dayso[0];
        int Sum=dayso[0];
        for (int i=1;i<n;i++) {
            if (dayso[i]>max) max = dayso[i];
            if (dayso[i]<min) min = dayso[i];
            Sum = Sum + dayso[i];
        }
        System.out.println();
        System.out.println("Max = "+max+" Min = "+min+" Average = "+Sum*1.0/n);
        System.out.println("Day so sau khi sap xep tang dan:");
        for (int i=0;i<n-1;i++) {
            for (int j=i+1;j<n;j++) {
                if (dayso[i]>dayso[j]) {
                    int temp = dayso[i];
                    dayso[i] = dayso[j];
                    dayso[j] = temp;
                }
            }
        }
        for (int i=0;i<n;i++) System.out.print(dayso[i]+" ");
        nhap.close();


    }
}