import java.io.*;
import java.util.*;

public class TestBankers {

    public static final int NUMBER_OF_CUSTOMERS = 5;

    public static void main(String[] args) throws IOException {

        /*
         * Usage:
         *
         * java TestBankers <input-file> <R0> <R1> ... <Rm-1>
         *
         * Example:
         *
         * java TestBankers inPutFile.txt 10 5 7 8
         */

        if (args.length < 2) {
            System.err.println(
                "Usage: java TestBankers "
                + "<input-file> <R0> <R1> ... <Rm-1>"
            );
            System.exit(1);
        }

        /*
         * TODO:
         *
         * 1. Read the input filename.
         *
         * 2. Determine the number of resource types.
         *
         * 3. Read the initial resources from
         *    the command-line arguments.
         *
         * 4. Create the BankImpl object.
         *
         * 5. Open the input file.
         *
         * 6. Read the maximum demand for each customer.
         *
         * 7. Call addCustomer() for each customer.
         */

        // 1. Read the input filename
        String fileName = args[0];

        // 2. Determine the number of resource types.
        int numberOfResources = args.length - 1;

        // 3. Read the initial resources from the command-line arguments.
        int[] resources = new int[numberOfResources];
 
        try {
            for (int j = 0; j < numberOfResources; j++) {
                resources[j] = Integer.parseInt(args[j + 1]);
                if (resources[j] < 0) {
                    System.err.println("Error: resource values cannot be negative.");
                    System.exit(1);
                }
            }
        } catch (NumberFormatException e) {
            System.err.println("Error: resource values must be integers.");
            System.exit(1);
        }

        // 4. 4. Create the BankImpl object.
        BankInterface bank = new BankImpl(NUMBER_OF_CUSTOMERS, resources);

        // 5. Open the input file.
        BufferedReader fileReader = null;
        try {
            fileReader = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            System.err.println("Error: cannot open file " + fileName);
            System.exit(1);
        }

        // 6. & 7. 
        try {
            for (int i = 0; i < NUMBER_OF_CUSTOMERS; i++) {
 
                String line = fileReader.readLine();
                if (line == null) {
                    System.err.println("Error: the input file must have "
                            + NUMBER_OF_CUSTOMERS + " lines.");
                    System.exit(1);
                }
 
                String[] parts = line.trim().split(",");
                if (parts.length != numberOfResources) {
                    System.err.println("Error: line " + (i + 1) + " of the input file must have "
                            + numberOfResources + " values.");
                    System.exit(1);
                }
 
                int[] maxDemand = new int[numberOfResources];
                for (int j = 0; j < numberOfResources; j++) {
                    maxDemand[j] = Integer.parseInt(parts[j].trim());
                    if (maxDemand[j] < 0) {
                        System.err.println("Error: line " + (i + 1)
                                + " of the input file has a negative value.");
                        System.exit(1);
                    }
                }
 
                bank.addCustomer(i, maxDemand);
            }
        } catch (NumberFormatException e) {
            System.err.println("Error: the input file must contain only integers.");
            System.exit(1);
        }
 
        fileReader.close();


        /*
         * TODO:
         *
         * Repeatedly read commands from the user.
         *
         * Required commands:
         *
         * RQ <customer> <R0> <R1> ... <Rm-1>
         *
         * RL <customer> <R0> <R1> ... <Rm-1>
         *
         * *
         *
         *
         * RQ:
         * Call requestResources()
         *
         * RL:
         * Call releaseResources()
         *
         * *:
         * Call getState()
         */

        // Repeatedly read commands from the user.
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
 
        while (true) {
 
            System.out.print("> ");
            String line = input.readLine();
 
            // Stop when input ends.
            if (line == null) {
                break;
            }
 
            String[] tokens = line.trim().split("\\s+");
            String command = tokens[0];
 
            if (command.isEmpty()) {
                continue;
            }
 
            if (command.equals("*")) {
 
                bank.getState();
 
            } else if (command.equals("RQ") || command.equals("RL")) {
 
                if (tokens.length != numberOfResources + 2) {
                    System.out.println("Error: enter " + command + " <customer> followed by "
                            + numberOfResources + " resource values.");
                    continue;
                }
 
                int customerNum;
                int[] values = new int[numberOfResources];
 
                try {
                    customerNum = Integer.parseInt(tokens[1]);
                    for (int j = 0; j < numberOfResources; j++) {
                        values[j] = Integer.parseInt(tokens[j + 2]);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: customer number and resource values must be integers.");
                    continue;
                }
 
                if (command.equals("RQ")) {
                    if (bank.requestResources(customerNum, values)) {
                        System.out.println("Approved");
                    } else {
                        System.out.println("Denied");
                    }
                } else {
                    bank.releaseResources(customerNum, values);
                }
 
            } else {
                System.out.println("Error: invalid command. Use RQ, RL, or *.");
            }
        }
    }
}
