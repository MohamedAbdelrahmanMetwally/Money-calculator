package com.example.moneycalulator.firstPage;
import android.content.Intent;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import com.example.moneycalulator.R;
import com.example.moneycalulator.secondPage.ShowingFigures;
import com.example.moneycalulator.databinding.ActivityMainBinding;
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private LoanViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(LoanViewModel.class);
        setupBindings();
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        binding.btnShowExpenses.setOnClickListener(v -> {
            viewModel.setLoanAmount(binding.etLoanAmount.getText().toString());
            viewModel.setBenefitPercentage(binding.etBenefitPercentage.getText().toString());
            viewModel.setRepaymentPeriod(binding.etRepaymentPeriod.getText().toString());
            Intent intent = new Intent(MainActivity.this, ShowingFigures.class);
            intent.putExtra("loanAmount", viewModel.getLoanAmount().getValue());
            intent.putExtra("benefitPercentage", viewModel.getBenefitPercentage().getValue());
            intent.putExtra("repaymentPeriod", viewModel.getRepaymentPeriod().getValue());
            startActivity(intent);
        });
    }
    private void setupBindings() {
        viewModel.getLoanAmount().observe(this, value -> binding.etLoanAmount.setText(value));
        viewModel.getBenefitPercentage().observe(this, value -> binding.etBenefitPercentage.setText(value));
        viewModel.getRepaymentPeriod().observe(this, value -> binding.etRepaymentPeriod.setText(value));
    }
}