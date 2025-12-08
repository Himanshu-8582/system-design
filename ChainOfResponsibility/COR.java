import java.util.Scanner;

public class COR {

    public static abstract class MoneyHandler {       // Handler Class
        protected MoneyHandler nextHandler;

        public MoneyHandler() {
            this.nextHandler = null;
        }

        public void setnextHandler(MoneyHandler next) {
            this.nextHandler = next;
        }

        public abstract void dispense(int amount);
    }
    
    public static class ThousandHandler extends MoneyHandler {
        private int numNotes;

        ThousandHandler(int notes) {
            this.numNotes = notes;
        }

        @Override
        public void dispense(int amount) {
            int notesNeeded = amount / 1000;
            if (notesNeeded > numNotes) {
                notesNeeded = numNotes;
                notesNeeded = 0;
            } else {
                numNotes -= notesNeeded;
            }

            if (notesNeeded > 0) {
                System.out.println("Dispensing " + notesNeeded + " x 1000 notes");
            }

            int remainingAmount = amount - (notesNeeded * 1000);
            if (remainingAmount > 0) {
                if (nextHandler != null)
                    nextHandler.dispense(remainingAmount);
                else {
                    System.out.println("Remaining amount of " + remainingAmount + " cannot be fullfilled.");
                }
            }
        }
    }
    

    public static class FiveHundredHandler extends MoneyHandler {
        private int numNotes;

        FiveHundredHandler(int notes) {
            this.numNotes = notes;
        }

        @Override
        public void dispense(int amount) {
            int notesNeeded = amount / 500;
            if (notesNeeded > numNotes) {
                notesNeeded = numNotes;
                notesNeeded = 0;
            } else {
                numNotes -= notesNeeded;
            }

            if (notesNeeded > 0) {
                System.out.println("Dispensing " + notesNeeded + " x 500 notes");
            }

            int remainingAmount = amount - (notesNeeded * 500);
            if (remainingAmount > 0) {
                if (nextHandler != null)
                    nextHandler.dispense(remainingAmount);
                else {
                    System.out.println("Remaining amount of " + remainingAmount + " cannot be fullfilled.");
                }
            }
        }
    }

    public static class TwoHundredHandler extends MoneyHandler {
        private int numNotes;

        TwoHundredHandler(int notes) {
            this.numNotes = notes;
        }

        @Override
        public void dispense(int amount) {
            int notesNeeded = amount / 200;
            if (notesNeeded > numNotes) {
                notesNeeded = numNotes;
                notesNeeded = 0;
            } else {
                numNotes -= notesNeeded;
            }

            if (notesNeeded > 0) {
                System.out.println("Dispensing " + notesNeeded + " x 200 notes");
            }

            int remainingAmount = amount - (notesNeeded * 200);
            if (remainingAmount > 0) {
                if (nextHandler != null)
                    nextHandler.dispense(remainingAmount);
                else {
                    System.out.println("Remaining amount of " + remainingAmount + " cannot be fullfilled.");
                }
            }
        }
    }

    public static class HundredHandler extends MoneyHandler {
        private int numNotes;

        HundredHandler(int notes) {
            this.numNotes = notes;
        }

        @Override
        public void dispense(int amount) {
            int notesNeeded = amount / 100;
            if (notesNeeded > numNotes) {
                notesNeeded = numNotes;
                notesNeeded = 0;
            } else {
                numNotes -= notesNeeded;
            }

            if (notesNeeded > 0) {
                System.out.println("Dispensing " + notesNeeded + " x 100 notes");
            }

            int remainingAmount = amount - (notesNeeded * 100);
            if (remainingAmount > 0) {
                if (nextHandler != null)
                    nextHandler.dispense(remainingAmount);
                else {
                    System.out.println("Remaining amount of " + remainingAmount + " cannot be fullfilled.");
                }
            }
        }
    }
    

    public static void main(String[] args) {
        MoneyHandler thousandHandler = new ThousandHandler(3);
        MoneyHandler fiveHundredHandler = new FiveHundredHandler(5);
        MoneyHandler twoHundredHandler = new TwoHundredHandler(10);
        MoneyHandler hundredHandler = new HundredHandler(20);

        thousandHandler.setnextHandler(fiveHundredHandler);
        fiveHundredHandler.setnextHandler(twoHundredHandler);
        twoHundredHandler.setnextHandler(hundredHandler);

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter amount to dispense: ");
        int amountToDispense = sc.nextInt();
        System.out.println("Requesting amount: " + amountToDispense);
        System.out.println("----------------------------");
        System.out.println("Dispensing notes:");
        thousandHandler.dispense(amountToDispense);
    }
}