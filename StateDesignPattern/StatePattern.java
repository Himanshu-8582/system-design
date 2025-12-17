public class StatePattern {

    public interface VendingState {
        public VendingState insertCoin(VendingMachine machine, int coin);

        public VendingState selectItem(VendingMachine machine);

        public VendingState dispense(VendingMachine machine);

        public VendingState returnCoin(VendingMachine machine);

        public VendingState refill(VendingMachine machine, int quantity);

        public String getStateName();
    }

    public static class NoCoinState implements VendingState {

        @Override
        public VendingState insertCoin(VendingMachine machine, int coin) {
            machine.setInsertedCoin(coin);
            System.out.println("Coin Inserted Current balance is: " + coin);
            return machine.getHasCoinState();
        }

        @Override
        public VendingState selectItem(VendingMachine machine) {
            System.out.println("Please insert coin first: ");
            return machine.getNoCoinState();
        }

        public VendingState dispense(VendingMachine machine) {
            System.out.println("Please insert coin and select item first.");
            return machine.getNoCoinState();
        }

        @Override
        public VendingState returnCoin(VendingMachine machine) {
            System.out.println("No coin to return!");
            return machine.getNoCoinState();
        }

        @Override
        public VendingState refill(VendingMachine machine, int quantity) {
            System.out.println("Items refilling.");
            machine.incrementItemCount(quantity);
            return machine.getNoCoinState();
        }

        @Override
        public String getStateName() {
            return "No-Coin";
        }
    }
    


    public static class HasCoinState implements VendingState {

        @Override
        public VendingState insertCoin(VendingMachine machine, int coin) {
            machine.addCoin(coin);
            System.out.println("Additional coin Inserted. Current balance is: " + machine.getInsertedCoin());
            return machine.getHasCoinState();
        }

        @Override
        public VendingState selectItem(VendingMachine machine) {
            if (machine.getInsertedCoin() >= machine.getPrice()) {
                System.out.println("Items selected Dispensing...");
                int change = machine.getInsertedCoin() - machine.getPrice();
                if (change > 0) {
                    System.out.println("Change returned");
                }
                machine.setInsertedCoin(0);
                return machine.getDispenseState();
            }
            else {
                int needed = machine.getPrice() - machine.getInsertedCoin();
                System.out.println("Insufficient funds. Need Rs : "+needed+" more.");
                return machine.getHasCoinState();
            }
        }

        public VendingState dispense(VendingMachine machine) {
            System.out.println("Please select an item first!");
            return machine.getHasCoinState();
        }

        @Override
        public VendingState returnCoin(VendingMachine machine) {
            System.out.println("No coin to return!");
            return machine.getNoCoinState();
        }

        @Override
        public VendingState refill(VendingMachine machine, int quantity) {
            System.out.println("Can't refill in this state.");
            machine.incrementItemCount(quantity);
            return machine.getHasCoinState();
        }

        @Override
        public String getStateName() {
            return "Has-Coin";
        }
    }



    public static class DispenseState implements VendingState {

        @Override
        public VendingState insertCoin(VendingMachine machine, int coin) {
            machine.setInsertedCoin(coin);
            System.out.println("Please wait, already dispensing item. Coin returned Rs: " + coin);
            return machine.getDispenseState();
        }

        @Override
        public VendingState selectItem(VendingMachine machine) {
            System.out.println("Already dispensing item. Please wait. ");
            return machine.getDispenseState();
        }

        public VendingState dispense(VendingMachine machine) {
            System.out.println("Item dispensed!");
            machine.decrementItemCount();

            if (machine.getItemCount() > 0) {
                return machine.getNoCoinState();
            } else {
                System.out.println("Machine is now sold out!");
                return machine.getSoldOutState();
            }

        }

        @Override
        public VendingState returnCoin(VendingMachine machine) {
            System.out.println("Cant return coin while dispensing items!");
            return machine.getDispenseState();
        }

        @Override
        public VendingState refill(VendingMachine machine, int quantity) {
            System.out.println("Cant refil in this state");
            return machine.getDispenseState();
        }

        @Override
        public String getStateName() {
            return "Dispensing";
        }
    }

    

    public static class SoldOutState implements VendingState {

        @Override
        public VendingState insertCoin(VendingMachine machine, int coin) {
            machine.setInsertedCoin(coin);
            System.out.println("Machine is sold out. Coin returned Rs: " + coin);
            return machine.getSoldOutState();
        }

        @Override
        public VendingState selectItem(VendingMachine machine) {
            System.out.println("Machine is sold out!");
            return machine.getSoldOutState();
        }

        public VendingState dispense(VendingMachine machine) {
            System.out.println("Machine is sold out!");
            return machine.getSoldOutState();
        }

        @Override
        public VendingState returnCoin(VendingMachine machine) {
            System.out.println("Machine is sold out, No coin is inserted.");
            return machine.getSoldOutState();
        }

        @Override
        public VendingState refill(VendingMachine machine, int quantity) {
            System.out.println("Items refilling.");
            machine.incrementItemCount(quantity);
            return machine.getNoCoinState();
        }

        @Override
        public String getStateName() {
            return "Sold-Out";
        }
    }


    // Context Class Client Interacts with this only
    public static class VendingMachine {
        private VendingState currentState;
        private int itemCount, itemPrice, insertedCoin;

        VendingState noCoinState;
        VendingState hasCoinState;
        VendingState dispenseState;
        VendingState soldOutState;

        public VendingMachine(int itemCount, int itemPrice) {
            this.itemCount = itemCount;
            this.itemPrice = itemPrice;

            // Creating State Object
            noCoinState = new NoCoinState();
            hasCoinState = new HasCoinState();
            dispenseState = new DispenseState();
            soldOutState = new SoldOutState();

            // Set Initial state
            if (itemCount > 0)
                currentState = noCoinState;
            else
                currentState = soldOutState;

        }

        VendingState getNoCoinState() {
            return noCoinState;
        }

        VendingState getHasCoinState() {
            return hasCoinState;
        }

        VendingState getDispenseState() {
            return dispenseState;
        }

        VendingState getSoldOutState() {
            return soldOutState;
        }

        public int getItemCount() {
            return itemCount;
        }

        public void decrementItemCount() {
            itemCount--;
        }

        public void incrementItemCount(int count) {
            itemCount += count;
        }

        public int getInsertedCoin() {
            return insertedCoin;
        }

        public void setInsertedCoin(int coin) {
            insertedCoin = coin;
        }

        public void addCoin(int coin) {
            insertedCoin += coin;
        }

        public int getPrice() {
            return this.itemPrice;
        }

        public void setPrice(int itemPrice) {
            this.itemPrice = itemPrice;
        }

        public void insertCoin(int coin) {
            currentState = currentState.insertCoin(this, coin);
        }

        public void selectItem() {
            currentState = currentState.selectItem(this);
        }

        public void dispense() {
            currentState = currentState.dispense(this);
        }

        public void returnCoin() {
            currentState = currentState.returnCoin(this);
        }

        public void refill(int quantity) {
            currentState = currentState.refill(this, quantity);
        }

        public void printStatus() {
            System.out.println("-----Vending Machine Status-----");
            System.out.println("Items remaining : " + itemCount);
            System.out.println("Inserted Coin Rs : " + insertedCoin);
            System.out.println("Current status : " + currentState.getStateName());
        }
    }
    

    public static void main(String[] args) {
        System.out.println("=== Water Bottle Vending Machine ===");
        int itemCount=2;
        int itemPrice = 20;

        VendingMachine machine = new VendingMachine(itemCount, itemPrice);
        machine.printStatus();

        //Test scenario - each operations potentially changes state
        System.out.println();
        System.out.println();
        System.out.println("1-> Trying to select item without coin: ");
        machine.selectItem();
        machine.printStatus();
        System.out.println();
        System.out.println();

        System.out.println("2-> Inserting Coins: ");
        machine.insertCoin(10);
        machine.printStatus();
        System.out.println();
        System.out.println();

        System.out.println("3-> Selecting item with insufficient funds:");
        machine.selectItem();   // Fund is insufficient
        machine.printStatus();
        System.out.println();
        System.out.println();

        System.out.println("4-> Adding more coin: ");
        machine.insertCoin(10);
        machine.printStatus();
        System.out.println();
        System.out.println();

        System.out.println("5-> Selecting item now.");
        machine.selectItem();
        machine.printStatus();
        System.out.println();
        System.out.println();

        System.out.println("6-> Dispensing item: ");
        machine.dispense();
        machine.printStatus();
        System.out.println();
        System.out.println();

        System.out.println("7-> Buying last item: ");
        machine.insertCoin(20);
        machine.selectItem();
        machine.dispense();
    }
}
