package com.example.moneycalulator.Main.ui;
import android.graphics.Color;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.moneycalulator.Main.util.FactoryViewModel;
import com.example.moneycalulator.R;
import com.example.moneycalulator.Main.view_model.LoanViewModel;
import com.example.moneycalulator.databinding.ActivityMainBinding;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private FactoryViewModel factory;
    private LoanViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        factory = new FactoryViewModel();
        viewModel = new ViewModelProvider(this, factory).get(LoanViewModel.class);
        setupBindings();
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        binding.btnShowExpenses.setOnClickListener(v -> {
            if (binding.etLoanAmount.getText().toString().isEmpty() ||
                    binding.etBenefitPercentage.getText().toString().isEmpty() ||
                    binding.etRepaymentPeriod.getText().toString().isEmpty()) {
                if (binding.etLoanAmount.getText().toString().isEmpty()) {
                    binding.tilLoanAmount.setError("Loan amount is required");
                } else {
                    binding.tilLoanAmount.setError(null);
                }
                if (binding.etBenefitPercentage.getText().toString().isEmpty()) {
                    binding.tilBenefitPercentage.setError("Benefit percentage is required");
                } else {
                    binding.tilBenefitPercentage.setError(null);
                }
                if (binding.etRepaymentPeriod.getText().toString().isEmpty()) {
                    binding.tilRepaymentPeriod.setError("Repayment period is required");
                } else {
                    binding.tilRepaymentPeriod.setError(null);
                }
                return;
            }
            binding.tilLoanAmount.setError(null);
            binding.tilBenefitPercentage.setError(null);
            binding.tilRepaymentPeriod.setError(null);
            viewModel.setLoanAmount(binding.etLoanAmount.getText().toString());
            viewModel.setBenefitPercentage(binding.etBenefitPercentage.getText().toString());
            viewModel.setRepaymentPeriod(binding.etRepaymentPeriod.getText().toString());
            String result = "Monthly: " + String.format("%.2f", viewModel.getMonthlyPayment()) +
                    "\nTotal: " + String.format("%.2f", viewModel.getTotalPayment()) +
                    "\nInterest: " + String.format("%.2f", viewModel.getTotalInterest());
            binding.tvResult.setText(result);
            showPieChart(viewModel.getMonthlyPayment(), viewModel.getTotalInterest());
        });
    }
    private void setupBindings() {
        viewModel.getLoanAmount().observe(this, value -> binding.etLoanAmount.setText(value));
        viewModel.getBenefitPercentage().observe(this, value -> binding.etBenefitPercentage.setText(value));
        viewModel.getRepaymentPeriod().observe(this, value -> binding.etRepaymentPeriod.setText(value));
    }
    private void showPieChart(double principal, double interest) {
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry((float) principal, "Principal"));
        entries.add(new PieEntry((float) interest, "Interest"));
        PieDataSet dataSet = new PieDataSet(entries, "Loan Breakdown");
        dataSet.setColors(new int[]{
                android.R.color.holo_blue_light,
                android.R.color.holo_red_light
        }, this);
        PieData data = new PieData(dataSet);
        data.setValueTextSize(14f);
        data.setValueTextColor(Color.WHITE);
        binding.pieChart.setData(data);
        binding.pieChart.getDescription().setEnabled(false);
        binding.pieChart.setCenterText("Loan vs Interest");
        binding.pieChart.animateY(1000);
        binding.pieChart.invalidate(); // refresh
    }

}