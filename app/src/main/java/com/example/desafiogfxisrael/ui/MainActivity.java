package com.example.desafiogfxisrael.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desafiogfxisrael.DesafioApplication;
import com.example.desafiogfxisrael.R;
import com.example.desafiogfxisrael.domain.Category;
import com.example.desafiogfxisrael.domain.Product;
import com.example.desafiogfxisrael.repository.RepositoryError;
import com.example.desafiogfxisrael.viewmodel.ProductViewModel;
import com.example.desafiogfxisrael.viewmodel.ProductViewModelFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    private ProductViewModel viewModel;
    private ProductAdapter productAdapter;
    private ProgressBar progressBar;
    private Spinner categorySpinner;
    private TextView emptyStateTextView;
    private final List<Product> currentProducts = new ArrayList<>();
    private boolean isLoading = false;
    private boolean hasRequestedProducts = false;
    @Inject
    ProductViewModelFactory productViewModelFactory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        injectDependencies();
        initViews();
        viewModel = createViewModel();
        setupRecyclerView();
        setupCategoryFilter();
        observeViewModelState();

        loadProductsWithNetworkGuard();
    }

    private void initViews() {
        progressBar = findViewById(R.id.progressBar);
        categorySpinner = findViewById(R.id.spinnerCategory);
        emptyStateTextView = findViewById(R.id.textEmptyState);
    }

    private ProductViewModel createViewModel() {
        return new ViewModelProvider(this, productViewModelFactory).get(ProductViewModel.class);
    }

    private void injectDependencies() {
        ((DesafioApplication) getApplication()).getAppComponent().inject(this);
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerProducts);
        productAdapter = new ProductAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(productAdapter);
    }

    private void setupCategoryFilter() {
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
                viewModel.applyCategoryFilter(Category.fromFilterPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                viewModel.applyCategoryFilter(null);
            }
        });
    }

    private void observeViewModelState() {
        observeProducts();
        observeLoading();
        observeError();
    }

    private void observeProducts() {
        viewModel.getProducts().observe(this, products -> {
            productAdapter.updateData(products);
            currentProducts.clear();
            if (products != null) {
                currentProducts.addAll(products);
            }
            renderEmptyState();
        });
    }

    private void observeLoading() {
        viewModel.getLoading().observe(this, loading -> {
            isLoading = Boolean.TRUE.equals(loading);
            progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            renderEmptyState();
        });
    }

    private void observeError() {
        viewModel.getError().observe(this, error -> {
            if (error != null) {
                showError(error);
            }
        });
    }

    private void loadProductsWithNetworkGuard() {
        if (!NetworkStatusChecker.isConnected(this)) {
            showError(RepositoryError.NO_INTERNET);
            return;
        }
        hasRequestedProducts = true;
        viewModel.loadProducts();
    }

    private void renderEmptyState() {
        boolean showEmptyState = hasRequestedProducts && !isLoading && currentProducts.isEmpty();
        emptyStateTextView.setVisibility(showEmptyState ? View.VISIBLE : View.GONE);
    }

    private void showError(RepositoryError error) {
        Toast.makeText(this, getString(ErrorMessageMapper.toMessageRes(error)), Toast.LENGTH_SHORT).show();
    }

}
