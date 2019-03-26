import java.io.File;
import java.util.Scanner;

class GS {
    int n;
    String[] menNames;
    String[] womenNames;
    int[][] menPref;
    int[][] womenPref;

    public static void main(String[] args) {
        new GS().run(args);
    }

    public void run(String[] args) {
        File file = new File(args[0]);
        Scanner scanner = new Scanner(System.in);
        try {
            scanner = new Scanner(file);
        } catch(Exception e) {
            System.out.println("No such file exists.");
            System.exit(0);
        }
        build(scanner);
        // solve();
        // printSolution();
    }

    public void build(Scanner scanner) {
        String line = scanner.nextLine();

        // skip comments.
        while (line.charAt(0) == '#') {
            line = scanner.nextLine();
        }

        n = Integer.parseInt(line.split("=")[1]);
        menNames = new String[n];
        womenNames = new String[n];
        menPref = new int[n][n];
        womenPref = new int[n][n];

        System.out.println("N = " + n);

        for (int i = 0; i < 2 * n; i++) {
            line = scanner.nextLine();
            if (i % 2 == 1) {
                menNames[(i-1) / 2] = line.split(" ")[1];
            } else {
                womenNames[(i-1) / 2] = line.split(" ")[1];
            }
        }
        line = scanner.nextLine();

        for (int i = 0; i < 2 * n; i++) {
            line = scanner.nextLine();
            String[] tokens = line.split(":")[1].substring(1).split(" ");
            int[] preferences = new int[n];
            for (int j = 0; j < tokens.length; j++) {
                preferences[j] = Integer.parseInt(tokens[j]);
            }

            if (i % 2 == 1) {
                menPref[(i-1) / 2] = preferences;
            } else {
                womenPref[(i-1) / 2] = preferences;
            }
        }
    }
}
