import java.util.Scanner;

public class pr_1 {
    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // 0 = Clean, 1 = Dirty
        System.out.print("Enter status of Room A (0/1): ");
        int A = sc.nextInt();

        System.out.print("Enter status of Room B (0/1): ");
        int B = sc.nextInt();

        char location = 'A';

        while (A == 1 || B == 1) {

            if (location == 'A') {
                if (A == 1) {
                    System.out.println("Room A is Dirty -> Suck");
                    A = 0;
                } else {
                    System.out.println("Room A is Clean -> Move Right");
                    location = 'B';
                }
            } 
            else {
                if (B == 1) {
                    System.out.println("Room B is Dirty -> Suck");
                    B = 0;
                } else {
                    System.out.println("Room B is Clean -> Move Left");
                    location = 'A';
                }
            }
        }

        System.out.println("\nBoth rooms are clean!");
        System.out.println("Final Status: A = " + A + ", B = " + B);

        sc.close();
    }
}

