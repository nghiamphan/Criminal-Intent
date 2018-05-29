package com.nphan.android.criminalintent;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

public class PhotoDialogFragment extends DialogFragment {

    private static final String ARG_IMAGE = "imageFile";

    private ImageView mImageView;

    public static PhotoDialogFragment newInstance(String imageFile) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_IMAGE, imageFile);

        PhotoDialogFragment fragment = new PhotoDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_photo, null);

        String imageFile = (String) getArguments().getSerializable(ARG_IMAGE);
        Bitmap bitmap = PictureUtils.getScaledBitmap(imageFile, getActivity());

        mImageView = v.findViewById(R.id.crime_photo);
        mImageView.setImageBitmap(bitmap);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .create();
    }
}
