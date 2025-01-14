package vttp.paf.workshop24.model;

public class BankAccount {
    private int id;
    private String fullName;
    private boolean isActive;
    private float balance;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
    public float getBalance() {
        return balance;
    }
    public void setBalance(float balance) {
        this.balance = balance;
    }
    @Override
    public String toString() {
        return "BankAccount [id=" + id + ", fullName=" + fullName + ", isActive=" + isActive + ", balance=" + balance
                + "]";
    }
    
}
