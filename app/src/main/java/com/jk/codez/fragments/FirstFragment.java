package com.jk.codez.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;

import com.jk.codez.AcAdapter;
import com.jk.codez.ButtonClickListener;
import com.jk.codez.CodezViewModel;
import com.jk.codez.Item;
import com.jk.codez.ItemAdapter;
import com.jk.codez.ItemClickSupport;
import com.jk.codez.Network;
import com.jk.codez.R;
import com.jk.codez.ad.AestheticDialog;
import com.jk.codez.ad.DialogAnimation;
import com.jk.codez.ad.DialogStyle;
import com.jk.codez.ad.DialogType;
import com.jk.codez.databinding.FragmentFirstBinding;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.Date;
import java.util.Objects;

import cz.msebera.android.httpclient.Header;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private ItemAdapter mAdapter;
    private CodezViewModel mViewModel;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(CodezViewModel.class);

        mViewModel.getCodes().observe(getViewLifecycleOwner(), items -> {
            mAdapter = new ItemAdapter(items, null);
            binding.rvCodes.setAdapter(mAdapter);
            binding.searchView.setAdapter(
                    new AcAdapter(requireContext(), R.layout.item_search, items)
            );
            binding.rvCodes.setVisibility(View.VISIBLE);
            binding.pb.setVisibility(View.GONE);
        });

        ItemClickSupport.addTo(binding.rvCodes)
                .setOnItemClickListener((recyclerView, position, view1) -> {

                })
                .setOnItemLongClickListener((recyclerView, position, view1) -> {
                    System.out.println(mAdapter.get(position).toString());
                    showDialog(mAdapter.get(position));
                    return true;
                });

        DividerItemDecoration div = new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL);

        div.setDrawable(getResources().getDrawable(R.drawable.divider_blue, requireActivity().getTheme()));

        binding.rvCodes.addItemDecoration(div);

        binding.tvDate.setText(new Date().toString());

        binding.fab1.setOnClickListener(view1 -> showDialog(null));
    }

    private void showDialog(@Nullable final Item item) {
        AestheticDialog.Builder builder = new AestheticDialog.Builder(requireActivity(), DialogStyle.CODEZ, DialogType.CODEZ);
        // set title
        builder.setTitle("Title");
        // set message
        builder.setMessage("Message");

        builder.setItem(item == null ? new Item() : item);
        builder.setIsEdit(item != null);

        builder.setButtonClickListener(new ButtonClickListener() {
            @Override
            public void onSave(@NonNull AestheticDialog.Builder dialog) {
                System.out.println("ff - onsave");
                Item i = dialog.getItem();
                if (item == null) {
                    mViewModel.addCode(i, dialog);
                } else {
                    mViewModel.editCode(i, dialog);
                }
            }

            @Override
            public void onDelete(@NonNull AestheticDialog.Builder dialog) {
                mViewModel.deleteCode(dialog);
            }
        });

        // set animation
        builder.setAnimation(DialogAnimation.SHRINK);
        // show dialog
        builder.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}