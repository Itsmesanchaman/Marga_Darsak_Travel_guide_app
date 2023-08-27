package com.example.autoimageslider.ForAdmin;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.InputType;
import android.text.Layout;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.autoimageslider.R;
import com.example.autoimageslider.SeasonActivity;

import java.util.ArrayList;
import java.util.List;

public class AdminDashboard extends AppCompatActivity {

    private static final int REQUEST_IMAGE_PICK_IMAGEVIEW = 1;
    private static final int REQUEST_IMAGE_PICK_VIEWPAGER = 2;
    private static final int MAX_IMAGES = 10;


    private final ArrayList<String> dayDescriptions = new ArrayList<>();

    private int dayCounter = 1;
    private FrameLayout frameLayout;

    private ViewPager2 viewPager;
    private ImageSliderAdapter imageSliderAdapter;

    private final List<Uri> imagesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);


        frameLayout = findViewById(R.id.frameLayoutId);

        viewPager = findViewById(R.id.viewPager);
        imageSliderAdapter = new ImageSliderAdapter(this, imagesList);
        viewPager.setAdapter(imageSliderAdapter);
        viewPager.postDelayed(new Runnable() {
            @Override
            public void run() {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                viewPager.postDelayed(this, 4000);
            }
        }, 4000);

        TextView imageCountTextView = findViewById(R.id.imageCountTextView);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                String imageCount = (position + 1) + "/" + imagesList.size();
                imageCountTextView.setText(imageCount);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        EditText desDayOneId = findViewById(R.id.desDayOneId);
        EditText imgDes = findViewById(R.id.imgDes);

        imgDes.setSingleLine(false);
        imgDes.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE | InputType.TYPE_CLASS_TEXT);

        desDayOneId.setSingleLine(false);
        desDayOneId.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE | InputType.TYPE_CLASS_TEXT);

        imgDes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                insertNewLineIfNeeded(imgDes);
            }
        });

        desDayOneId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                insertNewLineIfNeeded(desDayOneId);
            }
        });



        Button addImagesButton = findViewById(R.id.addImagesId);
        addImagesButton.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_IMAGE_PICK_IMAGEVIEW);
            } else {
                openImagePickerForImageView();
            }
        });

        Button addButton = findViewById(R.id.addDayId);
        addButton.setOnClickListener(v -> addNewDay());

        AppCompatButton addImageButton = findViewById(R.id.imageAddId);
        addImageButton.setOnClickListener(v -> {
            if (imagesList.size() < MAX_IMAGES) {
                requestImageFromGallery();
            } else {
                Toast.makeText(this, "Maximum image limit reached", Toast.LENGTH_SHORT).show();
            }
        });



        AppCompatButton addTOId = findViewById(R.id.addTOId);
        addTOId.setOnClickListener(v -> {
            convertEditTextToTextView();
            showSeasonSelectionDialog();
            finish();
        });
    }

    private void requestImageFromGallery() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_IMAGE_PICK_VIEWPAGER);
        } else {
            openImagePickerForViewPager();
        }
    }


    private void openImagePickerForImageView() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_IMAGE_PICK_IMAGEVIEW);
    }

    private void openImagePickerForViewPager() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_IMAGE_PICK_VIEWPAGER);
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_IMAGE_PICK_VIEWPAGER || requestCode == REQUEST_IMAGE_PICK_IMAGEVIEW) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (requestCode == REQUEST_IMAGE_PICK_IMAGEVIEW) {
                    openImagePickerForImageView();
                } else if (requestCode == REQUEST_IMAGE_PICK_VIEWPAGER) {
                    openImagePickerForViewPager();
                }
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();

            if (selectedImageUri != null) {
                if (requestCode == REQUEST_IMAGE_PICK_VIEWPAGER) {
                    if (imagesList.size() < MAX_IMAGES) {
                        imagesList.add(selectedImageUri);
                        imageSliderAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(this, "Maximum image limit reached", Toast.LENGTH_SHORT).show();

                    }
                } else if (requestCode == REQUEST_IMAGE_PICK_IMAGEVIEW) {
                    ImageView imageView = findViewById(R.id.trekMapId);
                    imageView.setImageURI(selectedImageUri);
                    Button addImagesButton = findViewById(R.id.addImagesId);
                    addImagesButton.setVisibility(View.GONE);
                }
            }
        }
    }



    @SuppressLint("SetTextI18n")
    private void addNewDay() {

        if (frameLayout == null) {
            Log.e("AddPlaces", "frameLayout is null");
            return;
        }

        int numDayLayouts = frameLayout.getChildCount() - 1;

        FrameLayout dayLayout = new FrameLayout(this);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
        );

        EditText desDayOneId = findViewById(R.id.desDayOneId);
        int editTextHeight = desDayOneId.getHeight();
        int topMargin = dpToPx(20 + editTextHeight + numDayLayouts * 10);
        layoutParams.topMargin = topMargin;
        dayLayout.setLayoutParams(layoutParams);

        TextView dayTextView = new TextView(this);
        dayTextView.setLayoutParams(findViewById(R.id.dayOneId).getLayoutParams());
        dayTextView.setText("Day " + (dayCounter + 1));
        dayTextView.setId(View.generateViewId());
        dayLayout.addView(dayTextView);

        EditText descriptionEditText = new EditText(this);
        descriptionEditText.setLayoutParams(findViewById(R.id.desDayOneId).getLayoutParams());
        descriptionEditText.setTextColor(ContextCompat.getColor(this, R.color.black));
        descriptionEditText.setHint("Description");
        descriptionEditText.setMaxLines(3);
        descriptionEditText.setSingleLine(false);
        descriptionEditText.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE | InputType.TYPE_CLASS_TEXT);
        descriptionEditText.setId(View.generateViewId());
        dayLayout.addView(descriptionEditText);



        Button deleteButton = new Button(this);
        deleteButton.setLayoutParams(findViewById(R.id.deleteDayOneId).getLayoutParams());
        deleteButton.setText("Delete");
        deleteButton.setVisibility(View.VISIBLE);
        deleteButton.setOnClickListener(v -> {
            frameLayout.removeView(dayLayout);
            dayCounter--;
        });
        deleteButton.setId(View.generateViewId());
        dayLayout.addView(deleteButton);


        frameLayout.addView(dayLayout, frameLayout.indexOfChild(findViewById(R.id.addDayId)) + dayCounter);
        dayCounter++;
    }




    private void convertEditTextToTextView() {
        for (int i = 0; i < frameLayout.getChildCount(); i++) {
            View view = frameLayout.getChildAt(i);
            if (view instanceof EditText) {
                EditText editText = (EditText) view;
                String description = editText.getText().toString().trim();
                dayDescriptions.add(description);
                TextView textView = new TextView(this);
                textView.setLayoutParams(editText.getLayoutParams());
                textView.setTextColor(ContextCompat.getColor(this,R.color.black));
                textView.setTextSize(15);
                textView.setText(description);
                frameLayout.removeView(editText);
                frameLayout.addView(textView, i);
            }
        }
    }



    private void showSeasonSelectionDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_season_selection);

        RadioButton radioSpring = dialog.findViewById(R.id.radioSpring);
        RadioButton radioSummer = dialog.findViewById(R.id.radioSummer);
        RadioButton radioAutumn = dialog.findViewById(R.id.radioAutumn);
        RadioButton radioWinter = dialog.findViewById(R.id.radioWinter);
        Button buttonConfirm = dialog.findViewById(R.id.buttonConfirm);

        buttonConfirm.setOnClickListener(v -> {
            int selectedSeason = -1;
            if (radioSpring.isChecked()) {
                selectedSeason = SeasonActivity.SPRING;
            } else if (radioSummer.isChecked()) {
                selectedSeason = SeasonActivity.SUMMER;
            } else if (radioAutumn.isChecked()) {
                selectedSeason = SeasonActivity.AUTUMN;
            } else if (radioWinter.isChecked()) {
                selectedSeason = SeasonActivity.WINTER;
            }

            dialog.dismiss();


            Intent intent = new Intent();
            intent.putExtra(SeasonActivity.EXTRA_SEASON, selectedSeason);
            intent.putStringArrayListExtra("dayDescriptions", dayDescriptions);
            setResult(RESULT_OK, intent);
            finish();
        });

        dialog.show();
    }




    private int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }

    private void insertNewLineIfNeeded(EditText editText) {
        Layout layout = editText.getLayout();
        if (layout != null) {
            int lineCount = layout.getLineCount();
            int maxLines = editText.getMaxLines();

            if (lineCount >= maxLines) {
                int lastLineStart = layout.getLineStart(lineCount - 1);
                int lastLineEnd = layout.getLineEnd(lineCount - 1);

                if (lastLineEnd - lastLineStart == editText.getSelectionEnd() - editText.getSelectionStart()) {
                    editText.getText().insert(editText.getSelectionEnd(), "\n");
                }
            }
        }
    }
}