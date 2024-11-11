package store.model.payment;

public class NonPromoTotalPayment {


    private int totalPayment;

    public void addToTotal(int amount) {
        this.totalPayment += amount;
    }

    public void setTotalPayment(int totalPayment) {
        this.totalPayment = totalPayment;
    }

    public int getTotalPayment() {
        return totalPayment;
    }

    @Override
    public String toString() {
        return "NonPromoTotalPayment{" +
                "totalPayment=" + totalPayment +
                '}';
    }
}
