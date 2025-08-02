package com.example.moneycalulator.secondPage;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.moneycalulator.R;
@SuppressLint("MissingInflatedId")
public class ShowingFigures extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_showing_figures);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent intent = getIntent();
        double p = Double.parseDouble(intent.getStringExtra("loanAmount"));
        double benefitPercentage = Double.parseDouble(intent.getStringExtra("benefitPercentage"));
        double n = Double.parseDouble(intent.getStringExtra("repaymentPeriod"));
        double r=benefitPercentage/(100*12);
        double monthlyPayment = (p * r * Math.pow(1 + r, n)) / (Math.pow(1 + r, n) - 1);
        double totalPayment = monthlyPayment * n;
        double totalInterest = totalPayment - p;
        TextView tvmonthlyPayment = findViewById(R.id.tvmonthlyPayment);
        TextView tvtotalPayment = findViewById(R.id.tvtotalPayment);
        TextView tvtotalInterest = findViewById(R.id.tvtotalInterest);
        tvmonthlyPayment.setText(String.format("%.2f", monthlyPayment));
        tvtotalPayment.setText(String.format("%.2f", totalPayment));
        tvtotalInterest.setText(String.format("%.2f", totalInterest));
    }
}