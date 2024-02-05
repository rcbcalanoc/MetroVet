    package com.example.metrovet_frontend;

    import android.content.Intent;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.ImageView;
    import android.widget.TextView;

    import androidx.appcompat.app.AppCompatActivity;

    public class AdminActivity extends AppCompatActivity {

        private ImageView addButton;
        private ViewDogsFragment viewDogsFragment;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.admin_activity_main);

            addButton = findViewById(R.id.add_button);
            viewDogsFragment = new ViewDogsFragment();

            // Load the ViewDogsFragment
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, viewDogsFragment)
                    .commit();

            TextView headerName = findViewById(R.id.header_name);
            headerName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Navigate back to ViewDogsFragment
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentContainer, viewDogsFragment)
                            .commit();

                    // Make the buttons visible again when returning to the list
                    setButtonsVisibility(View.VISIBLE);
                }
            });

            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Start the AddDogActivity
                    Intent intent = new Intent(AdminActivity.this, AddDogActivity.class);
                    startActivity(intent);
                }
            });
        }

        // Method to set the visibility of buttons
        public void setButtonsVisibility(int visibility) {
            addButton.setVisibility(visibility);
        }

        // Override onActivityResult to pass the result to ViewDogsFragment
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            viewDogsFragment.onActivityResult(requestCode, resultCode, data);
        }
    }
