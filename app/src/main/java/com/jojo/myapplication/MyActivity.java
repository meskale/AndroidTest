package com.jojo.myapplication;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.jojo.myapplication.com.jojo.myapplication.model.Item;
import com.jojo.myapplication.com.jojo.myapplication.model.Maison;




public class MyActivity extends Activity
        implements
            ToggleFragment.OnFragmentInteractionListener,
            SlideFragment.OnFragmentInteractionListener,
            AdapterView.OnItemSelectedListener,
            AdapterView.OnItemClickListener
{

    public final static String EXTRA_MESSAGE = "com.jojo.myapplication.MESSAGE";
    public final static String EXTRA_SIZE ="extra.size" ;
    public final static String text = "backupTextEdit";
    public Maison[] quartier ;
    public Item[] items;

    public MyActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        quartier = new Maison[10];
        for(int i=0;i<10;i++){
            quartier[i]=new Maison("Maison "+ Integer.toString(i),Integer.toString(i)+" Rue du quartier","telephone 00"+Integer.toString(i));
        }

        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().hide(fm.findFragmentById(R.id.SlideFrag)).commit();

        Integer[] nb=new Integer[80];
        int a=0;
        for(int i=20;i<100;i++) {
            nb[a] = i;
            a += 1;
        }
        items = new Item[3];
        items[0] = new Item();
        items[1] = new Item();
        items[2]= new Item();

        items[0].setNom("Vert");
        items[0].setImage(R.drawable.ic_launcher);
        items[1].setNom("Bleu");
        items[1].setImage(R.drawable.ic_launcher);
        items[2].setNom("Rouge");
        items[2].setImage(R.drawable.ic_launcher);

        ListView listView = (ListView) findViewById(R.id.ListeMaison);

        ArrayAdapter<Maison> maisonArrayAdapter =new ArrayAdapter<Maison>(this,android.R.layout.simple_list_item_1,quartier);
        maisonArrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        listView.setAdapter(maisonArrayAdapter);
        listView.setOnItemClickListener(this);

        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, nb);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        Spinner spinner = (Spinner) findViewById(R.id.spinnerSize);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(this);

        spinner = (Spinner) findViewById(R.id.spinnerColor);
        CustomAdapter customAdapter =new CustomAdapter(this,R.layout.item,items);
        spinner.setAdapter(customAdapter);


        if(savedInstanceState !=null) {
            EditText editText = (EditText) findViewById(R.id.edit_message);
            editText.setText(savedInstanceState.getString(text));
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.action_close:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void sendMessage(View view){

        Intent intent = new Intent(this,DisplayMessageActivity.class);

        EditText editText = (EditText) findViewById(R.id.edit_message);
        Spinner spinner = (Spinner)findViewById(R.id.spinnerSize);
        Integer integer = (Integer)spinner.getSelectedItem();
        String message = editText.getText().toString();

        intent.putExtra(EXTRA_MESSAGE,message);
        intent.putExtra(EXTRA_SIZE,integer);
        startActivity(intent);
    }

    public void sayHello(View view){
        Intent intent = new Intent(this,DisplayMessageActivity.class);
        intent.putExtra(EXTRA_MESSAGE,"Hello");
        startActivity(intent);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void actionBignou(View view) {
        ToggleButton toggleButton = (ToggleButton) view.findViewById(R.id.toggleBignou);
        FragmentManager fm = getFragmentManager() ;
        Fragment sf = (Fragment)fm.findFragmentById(R.id.SlideFrag);
        fm.beginTransaction().setCustomAnimations(android.R.animator.fade_in,android.R.animator.fade_out).hide(sf);

        Context context = this.getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        if (!toggleButton.isChecked()) {
            CharSequence text = "Bignou desenclenché";
            Toast.makeText(context, text, duration).show();
            fm.beginTransaction().setCustomAnimations(android.R.animator.fade_in,android.R.animator.fade_out).hide(sf).commit();

        } else {
            CharSequence text = "Bignou enclenché";
            Toast.makeText(context, text, duration).show();
            fm.beginTransaction().setCustomAnimations(android.R.animator.fade_in,android.R.animator.fade_out).show(sf).commit();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        Intent intent = new Intent(this,DisplayMessageActivity.class);

        Context context = this.getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        CharSequence text = "Valeur selectionné "+ adapterView.getItemAtPosition(i).toString();
        Integer integer = (Integer )adapterView.getItemAtPosition(i);
        Toast.makeText(context, text, duration).show();

        TextView textView = (TextView) findViewById(R.id.edit_message);
        textView.setTextSize(integer.floatValue());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this,DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        Maison maison = (Maison)adapterView.getItemAtPosition(i);

        intent.putExtra("MAISON",maison);
        intent.putExtra(EXTRA_MESSAGE,message);

        startActivity(intent);
    }

    public void openMap(View view){
        Intent intent = new Intent(this,MapsActivity.class);
        startActivity(intent);

    }

    public class CustomAdapter extends ArrayAdapter<Item> {

        public CustomAdapter(Context context, int textViewResourceId, Item[] objects) {
            super(context, textViewResourceId, objects);
        }

        @Override
        public View getDropDownView(int position,View convertView,ViewGroup parent){
            return getCustomView(position,convertView,parent);
        }

        @Override
        public View getView(int pos, View cnvtView, ViewGroup prnt) {
            return getCustomView(pos, cnvtView, prnt);
        }

        public View getCustomView(int position,View convertView,ViewGroup parent){
            LayoutInflater inflater= getLayoutInflater();
            View row = inflater.inflate(R.layout.item,parent,false);
            TextView textView = (TextView) row.findViewById(R.id.itemText);
            ImageView imageView = (ImageView) row.findViewById(R.id.itemImage);

            textView.setText(items[position].getNom());
            imageView.setImageResource(items[position].getImage());

            return row;
        }
    }

}

