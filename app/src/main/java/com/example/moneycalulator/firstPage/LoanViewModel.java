package com.example.moneycalulator.firstPage;
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
}