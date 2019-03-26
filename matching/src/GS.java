import java.io.File;
import java.util.Scanner;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

class GS {
    int n;
    String[] menNames;
    String[] womenNames;
    int[][] menPref;
    int[][] womenPref;
    int[] nbrOfProposes;

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
        Map<Integer, Integer> solution = solve();
        printSolution(solution);
    }

    private void printSolution(Map<Integer, Integer> map) {
        System.out.println("\n-- PRINTING --");

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            String manName = menNames[entry.getValue()];
            String womanName = womenNames[entry.getKey()];

            System.out.println(manName + " -- " + womanName);
        }
    }

    public Map<Integer, Integer> solve() {
        /*
            add each man m in M to a list p
            while p != null
                m <- take out the first element of p
                w <- the woman m prefers the most AND has not yet proposed to.
                IF w has no partner THEN
                    (m,w) becomes a pair
                ELSE IF w prefers m over her current partner m2 THEN
                    remove the pair (w, m2)
                    (m, w) becomes a pair
                    add m2 back to p
        */

        System.out.println("\n-- SOLVING -- ");

        // (woman -> man)
        Map<Integer, Integer> pairs = new HashMap<Integer, Integer>();

        Deque<Integer> unmarriedMen = new LinkedList<Integer>();
        for (int i = 0; i < n; i++) {
            unmarriedMen.add(i);
        }

        while(!unmarriedMen.isEmpty()) {
            int proposingMan = unmarriedMen.removeFirst();
            int woman = menPref[proposingMan][nbrOfProposes[proposingMan]];
            nbrOfProposes[proposingMan] = nbrOfProposes[proposingMan] + 1;

            System.out.println(menNames[proposingMan] + " proposes to " + womenNames[woman]);

            if (!pairs.containsKey(woman)) {
                System.out.println("They became a pair.");
                pairs.put(woman, proposingMan);

            // This if statement is fucking up everything cuz the list of preferences is not inverted!
            // If the list was inverted this would work, I think..
            } else if (womenPref[woman][proposingMan] < womenPref[woman][pairs.get(woman)]){

                int dumpedMan = pairs.get(woman);
                System.out.println("They became a pair and she dumped " + menNames[dumpedMan] +
                    " because value of proposing man is " + womenPref[woman][proposingMan] + " and the value " +
                    "of the dumped man was " + womenPref[woman][pairs.get(woman)]);
                unmarriedMen.addLast(dumpedMan);
                pairs.put(woman, proposingMan);
            }
        }
        System.out.println("Solved!");
        return pairs;
    }

    public void build(Scanner scanner) {
        System.out.println("-- BUILDING --");

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
        nbrOfProposes = new int[n];

        System.out.println("N = " + n);

        for (int i = 1; i <= 2 * n; i++) {
            line = scanner.nextLine();
            if (i % 2 == 1) {
                menNames[(i-1) / 2] = line.split(" ")[1];
                System.out.println("Added man " + (i-1) / 2 + " named " + line.split(" ")[1]);
            } else {
                womenNames[(i-1) / 2] = line.split(" ")[1];
                System.out.println("Added woman " + (i-1) / 2 + " named " + line.split(" ")[1]);
            }

        }
        line = scanner.nextLine();

        for (int i = 0; i < 2 * n; i++) {
            line = scanner.nextLine();
            String[] tokens = line.split(":")[1].substring(1).split(" ");
            int[] preferences = new int[n];
            for (int j = 0; j < tokens.length; j++) {
                preferences[j] = (Integer.parseInt(tokens[j]) - 1) / 2;
                System.out.println("Adding " + (Integer.parseInt(tokens[j]) - 1) / 2 + " to " + (i));
            }

            if (i % 2 == 0) {
                menPref[(i) / 2] = preferences;
                System.out.println(menNames[(i)/2] + " gets the above preferences.");
            } else {
                womenPref[(i) / 2] = preferences;
                System.out.println(womenNames[(i)/2] + " gets the above preferences.");
            }
        }
    }
}
