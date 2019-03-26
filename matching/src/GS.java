import java.io.File;
import java.util.Scanner;

class GS {
    static int n;
    static String[] menNames;
    static String[] womenNames;
    static int[][] menPref;
    static int[][] womenPref;

    public static void main(String[] args) {
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

    static public void build(Scanner scanner) {
        while(scanner.hasNext()){
            String line = scanner.nextLine();
            System.out.println(line);

            // skip empty lines
            if (line.isEmpty()) {
                continue;
            }

            char firstChar = line.charAt(0);
            switch (firstChar) {
                case '#':
                    System.out.println("REMOVED COMMENT!");
                    break;
                case 'n':
                    n = Character.getNumericValue(firstChar);
                    System.out.println("n set to " + n);
                    menNames = new String[n];
                    womenNames = new String[n];
                    menPref = new int[n][n];
                    womenPref = new int[n][n];
                    break;
                default:
                    char secondChar = line.charAt(1);
                    if (secondChar == ' ') {
                        processName(line, firstChar);
                    } else {
                        processPreferences(line, firstChar);
                    }
                    break;
            }
        }
    }

    public static void processPreferences(String line, char firstChar) {
        int index = Character.getNumericValue(firstChar);
        String[] tokens = line.substring(3, line.length()).split(" ");
        int[] preferences = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            preferences[i] = Integer.parseInt(tokens[i]);
        }
        if (index % 2 == 0) {
            womenPref[(index-1) / 2] = preferences;
        } else {
            menPref[(index-1) / 2] = preferences;
        }
        System.out.println("Added preferences: " + line);

    }

    public static void processName(String line, char firstChar) {
        int index = Character.getNumericValue(firstChar);
        String name = line.substring(2, line.length());
        if (index % 2 == 0) {
            womenNames[(index-1) / 2] = name;
        } else {
            menNames[(index-1) / 2] = name;
        }
        System.out.println("Added " + name + " at " + index);
    }
}
