package com.example.desafiogfxisrael.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desafiogfxisrael.R;
import com.example.desafiogfxisrael.domain.Category;
import com.example.desafiogfxisrael.network.ApiService;
import com.example.desafiogfxisrael.network.RetrofitClient;
import com.example.desafiogfxisrael.repository.ProductRepository;
import com.example.desafiogfxisrael.viewmodel.ProductViewModel;
import com.example.desafiogfxisrael.viewmodel.ProductViewModelFactory;

public class MainActivity extends AppCompatActivity {
    private ProductViewModel viewModel;
    private ProductAdapter productAdapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUi();
        setupViewModel();
        observeViewModel();

        viewModel.loadProducts();
    }

    private void setupUi() {
        RecyclerView recyclerView = findViewById(R.id.recyclerProducts);
        Spinner categorySpinner = findViewById(R.id.spinnerCategory);
        progressBar = findViewById(R.id.progressBar);

        productAdapter = new ProductAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(productAdapter);

        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.category_filter_items,
                android.R.layout.simple_spinner_item
        );
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(spinnerAdapter);
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                viewModel.applyCategoryFilter(mapPositionToCategory(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                viewModel.applyCategoryFilter(null);
            }
        });
    }

    private void setupViewModel() {
        ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
        ProductRepository repository = new ProductRepository(apiService);
        ProductViewModelFactory factory = new ProductViewModelFactory(repository);
        viewModel = new ViewModelProvider(this, factory).get(ProductViewModel.class);
    }

    private void observeViewModel() {
        viewModel.getProducts().observe(this, products -> productAdapter.updateData(products));
        viewModel.getLoading().observe(this, loading ->
                progressBar.setVisibility(Boolean.TRUE.equals(loading) ? View.VISIBLE : View.GONE));
        viewModel.getError().observe(this, error -> {
            if (error != null && !error.isEmpty()) {
                Toast.makeText(this, getString(R.string.error_loading_products), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Category mapPositionToCategory(int position) {
        switch (position) {
            case 1:
                return Category.MENS_CLOTHING;
            case 2:
                return Category.WOMENS_CLOTHING;
            case 3:
                return Category.JEWELERY;
            case 4:
                return Category.ELECTRONICS;
            default:
                return null;
        }
    }
}
