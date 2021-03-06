package ua.kpi.skilift.skipass.types;

public enum LiftsType {
    TEN(10), TWENTY(20), FIFTY(50), HUNDRED(100);

    private final Integer amount;

    LiftsType(Integer amount) {
        this.amount = amount;
    }

    public Integer getAmount() {
        return amount;
    }
}
