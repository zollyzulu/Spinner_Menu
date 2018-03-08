package edu.wctc.petsandfood;

import android.content.Intent;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Food extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    protected int[] foodImages = {R.drawable.bingsu, R.drawable.icecream, R.drawable.pizza};
    protected ImageView newImage;
    protected int picNum = 0;
    protected double xaxis = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        newImage = (ImageView) findViewById(R.id.imageView);
        Spinner spinner = (Spinner)
                findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        List<String> categories = new
                ArrayList<String>();
        categories.add("Bingsu");
        categories.add("Ice Cream");
        categories.add("Pizza");
        ArrayAdapter<String> dataAdapter = new
                ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                categories);
        dataAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent,
                               View view, int position, long id) {
        String item =
                parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),
                "Selected: " + item, Toast.LENGTH_LONG).show();

        switch (position){
            case 0:
                newImage.setImageResource(R.drawable.bingsu);
                break;
            case 1:
                newImage.setImageResource(R.drawable.icecream);
                break;
            case 2:
                newImage.setImageResource(R.drawable.pizza);
                break;
        }
    }
    public void onNothingSelected(AdapterView<?> arg0) {

    }
    public void leftClicked(View view) {

        leftSwipe();
    }
    private void leftSwipe() {
        picNum--;
        if (picNum < 0) picNum = foodImages.length - 1;
        newImage.setImageResource(foodImages[picNum]);
    }

    public void rightClicked(View view) {
        rightSwipe();

    }
    private void rightSwipe() {
        picNum++;
        if (picNum >= foodImages.length) picNum = 0;
        newImage.setImageResource(foodImages[picNum]);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = MotionEventCompat.getActionMasked(event);
        switch (action) {
            case (MotionEvent.ACTION_DOWN):
                xaxis = event.getRawX();
                return true;

            case (MotionEvent.ACTION_UP):
                if (xaxis > event.getRawX()) rightSwipe();
                else leftSwipe();
                return true;

            default:
                return super.onTouchEvent(event);
        }
    }
    public void newCategory(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void petActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void done(View view) {
        super.onBackPressed();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
// Handle item selection
        switch (item.getItemId()) {
            case R.id.Main_activity:
                petActivity();
                return true;
            case R.id.activity_food:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
