package SplitWise.models;

import java.util.List;

public class Expense {
    public static int nextExpenseId = 0;
    public String expenseId;
    public String description;
    public double totalAmount;
    public String paidByUserId;
    public List<Split> splits;
    public String groupId;
    
    public Expense(String desc, double amount, String paidBy,
            List<Split> splits, String group) {
        this.expenseId = "expense" + (++nextExpenseId);
        this.description = desc;
        this.totalAmount = amount;
        this.paidByUserId = paidBy;
        this.splits = splits;
        this.groupId = group;
    }
    
    public Expense(String desc, double amount, String paidBy, List<Split> splits) {
        this(desc, amount, paidBy, splits, "");
    }
}
