import java.io.File;
import java.util.Scanner;

class GS {
    public static void main(String[] args) {
        File file = new File(args[0]);
        Scanner scanner = new Scanner(System.in);
        try {
            scanner = new Scanner(file);
        } catch(Exception e) {
            System.out.println("No such file exists.");
            System.exit(0);
        }
        while (scanner.hasNext()) {
            System.out.println(scanner.nextLine());
        }
    }
}
