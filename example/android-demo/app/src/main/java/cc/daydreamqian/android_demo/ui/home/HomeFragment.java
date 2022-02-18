package cc.daydreamqian.android_demo.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.Locale;
import java.util.Objects;
import java.util.Random;

import cc.daydreamqian.android_demo.MainActivity;
import cc.daydreamqian.android_demo.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        Random ran = new Random();
        final Button btn = binding.changeLanguageButton;
        Locale targetLocale = Locale.getDefault();
        while (targetLocale == Locale.getDefault()) {
            targetLocale = new Locale[]{Locale.CHINESE, Locale.JAPANESE, Locale.ENGLISH}[ran.nextInt(3)];
        }
        Locale finalTargetLocale = targetLocale;
        btn.setOnClickListener(view -> (Objects.requireNonNull((MainActivity) getActivity())).updateLocale(finalTargetLocale));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}