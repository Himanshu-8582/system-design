import java.util.HashMap;
import java.util.Map;

public class MementoPattern {

    // Memento
    public static class DatabaseMemento {
        private final Map<String, String> data;

        DatabaseMemento(Map<String, String> dbData) {
            this.data = new HashMap<>(dbData);
        }

        public Map<String, String> getState() {
            return new HashMap<>(data);
        }

    }

    // Originator
    public static class Database {
        private Map<String, String> records;

        public Database() {
            this.records = new HashMap<>();
        }

        public void insert(String key, String value) {
            records.putIfAbsent(key, value);
            System.out.println("Inserted key: " + key + " Value: " + value);
        }

        public void update(String key, String value) {
            if (records.containsKey(key)) {
                records.put(key, value);
                System.out.println("Updated " + key + " = " + value);
            } else {
                System.out.println("Key not found for update: " + key);
            }
        }

        public void delete(String key) {
            if (records.containsKey(key)) {
                records.remove(key);
                System.out.println("Deleted: " + key);
            } else {
                System.out.println("Key not found for deletion.");
            }
        }

        public DatabaseMemento createMemento() {
            System.out.println("Creating database BackUp...");
            return new DatabaseMemento(records);
        }

        public void restoreFromMemento(DatabaseMemento memento) {
            records = new HashMap<>(memento.getState());
            System.out.println("Databse restored from backup!");
        }

        public void displayRecords() {
            System.out.println("-----Current Database State-----");
            if (records.isEmpty()) {
                System.out.println("Datbase is empty.");
            } else {
                for (String s : records.keySet()) {
                    System.out.println(s + " = " + records.get(s));
                }
            }
            System.out.println("---------------------------------");
        }

    }

    // CareTaker
    public static class TransactionManager {
        private DatabaseMemento backup;

        public TransactionManager() {
            backup = null;
        }

        public void beingTransaction(Database db) {
            System.out.println("=== Being Transaction ===");
            backup = db.createMemento();
        }

        public void commitTransaction() {
            System.out.println("=== Commit Transaction ===");
            if (backup != null) {
                backup = null;
            }
            System.out.println("Transaction Commited Successfully.");
        }

        public void rollBackTransaction(Database db) {
            System.out.println("=== RollBack Transaction ===");
            if (backup != null) {
                db.restoreFromMemento(backup);
                backup = null;
                System.out.println("Transaction Rolled Back.");
            } else {
                System.out.println("No backUp available for rollback.");
            }
        }

    }

    public static void main(String[] args) {
        Database db = new Database();
        TransactionManager tnxManager = new TransactionManager();

        // Success Senario
        tnxManager.beingTransaction(db);
        db.insert("user1", "Rohan");
        db.insert("user2", "Mohan");
        tnxManager.commitTransaction();
        System.out.println();
        System.out.println();

        db.displayRecords();
        System.out.println();
        System.out.println();

        // Fail senario
        tnxManager.beingTransaction(db);
        db.insert("user3", "Sohan");
        db.insert("user4", "Rahul");

        db.displayRecords();
        System.out.println();
        System.out.println();

        // Some error -> rollback
        System.out.println("Error: Something went wrong during transaction.");
        tnxManager.rollBackTransaction(db);
        db.displayRecords();

    }
}
