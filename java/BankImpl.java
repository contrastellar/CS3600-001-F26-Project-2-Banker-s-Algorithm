import java.util.Arrays;

public class BankImpl implements BankInterface {

    private int numberOfCustomers;
    private int numberOfResources;

    // Banker's Algorithm data structures
    private int[] available;
    private int[][] maximum;
    private int[][] allocation;
    private int[][] need;

    /**
     * Creates a new bank with the specified resources.
     */
    public BankImpl(int numberOfCustomers, int[] resources) {

        // TODO:
        // Initialize numberOfCustomers and numberOfResources.
        this.numberOfCustomers = numberOfCustomers;
        this.numberOfResources = resources.length;

        // TODO:
        // Initialize available, maximum,
        // allocation, and need.
        available = new int[numberOfResources];
        for (int j = 0; j < numberOfResources; j++) {
            available[j] = resources[j];
        }

        maximum = new int[numberOfCustomers][numberOfResources];
        allocation = new int[numberOfCustomers][numberOfResources];
        need = new int[numberOfCustomers][numberOfResources];
    }

    /**
     * Adds a customer and records the customer's
     * maximum resource demand.
     */
    @Override
    public void addCustomer(int customerNum, int[] maxDemand) {

        // TODO
        for (int j = 0; j < numberOfResources; j++) {
            maximum[customerNum][j] = maxDemand[j];
            allocation[customerNum][j] = 0;
            need[customerNum][j] = maxDemand[j];
        }
    }

    /**
     * Displays the current state of the bank:
     *
     * Available
     * Maximum
     * Allocation
     * Need
     */
    @Override
    public void getState() {

        // TODO
        System.out.println("Available:");
        System.out.println(Arrays.toString(available));
 
        System.out.println("\nMaximum:");
        for (int i = 0; i < numberOfCustomers; i++) {
            System.out.println("Customer " + i + ": " + Arrays.toString(maximum[i]));
        }
 
        System.out.println("\nAllocation:");
        for (int i = 0; i < numberOfCustomers; i++) {
            System.out.println("Customer " + i + ": " + Arrays.toString(allocation[i]));
        }
 
        System.out.println("\nNeed:");
        for (int i = 0; i < numberOfCustomers; i++) {
            System.out.println("Customer " + i + ": " + Arrays.toString(need[i]));
        }
    }

    /**
     * Determines whether the system would remain
     * in a safe state if the request were granted.
     */
    private boolean isSafeState(int customerNum, int[] request) {

        // TODO:
        // Implement the Banker's Safety Algorithm.
                int[] work = new int[numberOfResources];
        int[][] tempAllocation = new int[numberOfCustomers][numberOfResources];
        int[][] tempNeed = new int[numberOfCustomers][numberOfResources];
 
        for (int j = 0; j < numberOfResources; j++) {
            work[j] = available[j] - request[j];
        }
 
        for (int i = 0; i < numberOfCustomers; i++) {
            for (int j = 0; j < numberOfResources; j++) {
                tempAllocation[i][j] = allocation[i][j];
                tempNeed[i][j] = need[i][j];
            }
        }
 
        for (int j = 0; j < numberOfResources; j++) {
            tempAllocation[customerNum][j] += request[j];
            tempNeed[customerNum][j] -= request[j];
        }
 
        boolean[] finish = new boolean[numberOfCustomers];
 
        boolean found = true;
 
        while (found) {
            found = false;
 
            for (int i = 0; i < numberOfCustomers; i++) {
 
                if (!finish[i]) {
 
                    boolean canFinish = true;
                    for (int j = 0; j < numberOfResources; j++) {
                        if (tempNeed[i][j] > work[j]) {
                            canFinish = false;
                        }
                    }
 
                    if (canFinish) {
                        for (int j = 0; j < numberOfResources; j++) {
                            work[j] += tempAllocation[i][j];
                        }
                        finish[i] = true;
                        found = true;
                    }
                }
            }
        }
 
        for (int i = 0; i < numberOfCustomers; i++) {
            if (!finish[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Attempts to allocate resources to a customer.
     *
     * @return true if approved
     *         false if denied
     */
    @Override
    public synchronized boolean requestResources(
            int customerNum, int[] request) {

        // TODO:
        // 1. Validate the request.
        // 2. Check whether the request exceeds Need.
        // 3. Check whether the resources are available.
        // 4. Check whether the resulting state is safe.
        // 5. Update the bank if the request is approved.

        // 1. Validate the request.
        if (customerNum < 0 || customerNum >= numberOfCustomers) {
            System.out.println("Invalid customer number.");
            return false;
        }
 
        for (int j = 0; j < numberOfResources; j++) {
            if (request[j] < 0) {
                System.out.println("Resource values cannot be negative.");
                return false;
            }
        }
 
        // 2. Check whether the request exceeds Need.
        for (int j = 0; j < numberOfResources; j++) {
            if (request[j] > need[customerNum][j]) {
                return false;
            }
        }
 
        // 3. Check whether the resources are available.
        for (int j = 0; j < numberOfResources; j++) {
            if (request[j] > available[j]) {
                return false;
            }
        }
 
        // 4. Check whether the resulting state is safe.
        if (!isSafeState(customerNum, request)) {
            return false;
        }
 
        // 5. Update the bank if the request is approved.
        for (int j = 0; j < numberOfResources; j++) {
            available[j] -= request[j];
            allocation[customerNum][j] += request[j];
            need[customerNum][j] -= request[j];
        }
 
        return true;
    }

    /**
     * Releases resources held by a customer.
     */
    @Override
    public synchronized void releaseResources(
            int customerNum, int[] release) {

        // TODO:
        // Validate and release the resources.

        //Ans: validate the whole release before changing anything.
        if (customerNum < 0 || customerNum >= numberOfCustomers) {
            System.out.println("Invalid customer number.");
            return;
        }
 
        for (int j = 0; j < numberOfResources; j++) {
            if (release[j] < 0) {
                System.out.println("Resource values cannot be negative.");
                return;
            }
            if (release[j] > allocation[customerNum][j]) {
                System.out.println("Release denied: customer does not hold that many resources.");
                return;
            }
        }
 
        // Release the resources.
        for (int j = 0; j < numberOfResources; j++) {
            available[j] += release[j];
            allocation[customerNum][j] -= release[j];
            need[customerNum][j] += release[j];
        }
 
        System.out.println("Released");
    }
}
