package com.example.moneycalulator.Main.view_model;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
public class LoanViewModel extends ViewModel {
    private final MutableLiveData<String> loanAmount = new MutableLiveData<>("");
    private final MutableLiveData<String> benefitPercentage = new MutableLiveData<>("");
    private final MutableLiveData<String> repaymentPeriod = new MutableLiveData<>("");
    public LiveData<String> getLoanAmount() {
        return loanAmount;
    }
    public LiveData<String> getBenefitPercentage() {
        return benefitPercentage;
    }
    public LiveData<String> getRepaymentPeriod() {
        return repaymentPeriod;
    }
    public void setLoanAmount(String amount) {
        loanAmount.setValue(amount);
    }
    public void setBenefitPercentage(String percentage) {
        benefitPercentage.setValue(percentage);
    }
    public void setRepaymentPeriod(String period) {
        repaymentPeriod.setValue(period);
    }
    public double getMonthlyPayment() {
        double p = Double.parseDouble(loanAmount.getValue());
        double benefitPercentage = Double.parseDouble(this.benefitPercentage.getValue());
        double n = Double.parseDouble(repaymentPeriod.getValue());
        double r=benefitPercentage/(100*12);
        return (p * r * Math.pow(1 + r, n)) / (Math.pow(1 + r, n) - 1);
    }
    public double getTotalPayment() {
        return getMonthlyPayment() * Double.parseDouble(repaymentPeriod.getValue());
    }
    public double getTotalInterest() {
        return getTotalPayment() - Double.parseDouble(loanAmount.getValue());
    }
}