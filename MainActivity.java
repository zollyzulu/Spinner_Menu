package edu.wctc.petsandfood;

import android.content.Intent;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AlertDialog;
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

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    protected int[] petImages = {R.drawable.frenchie, R.drawable.pug, R.drawable.golden};
    //protected int[] foodImages = {R.drawable.bingsu, R.drawable.icecream, R.drawable.pizza};
    protected ImageView theImage;
    protected int picNum = 0;
    protected double xaxis = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        theImage = (ImageView) findViewById(R.id.petPicture);
        Spinner spinner = (Spinner)
                findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        List<String> categories = new
                ArrayList<String>();
        categories.add("French Bull Dog");
        categories.add("Pug");
        categories.add("Golden Retriever");
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
                theImage.setImageResource(R.drawable.frenchie);
                break;
            case 1:
                theImage.setImageResource(R.drawable.pug);
                break;
            case 2:
                theImage.setImageResource(R.drawable.golden);
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
        if (picNum < 0) picNum = petImages.length - 1;
        theImage.setImageResource(petImages[picNum]);
    }

    public void rightClicked(View view) {
        rightSwipe();

    }

    private void rightSwipe() {
        picNum++;
        if (picNum >= petImages.length) picNum = 0;
        theImage.setImageResource(petImages[picNum]);
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
        Intent intent = new Intent(this, Food.class);
        startActivity(intent);
    }

    public void foodActivity() {
        Intent intent = new Intent(this, Food.class);
        startActivity(intent);
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

                return true;
            case R.id.activity_food:
                foodActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
