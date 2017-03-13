package com.example.a3reyea63.fileio;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == R.id.save)
        {
            EditText notepadEditText = (EditText)findViewById(R.id.notepad);
            String notepadText = notepadEditText.getText().toString();
            try
            {
                PrintWriter pw =
                        new PrintWriter( new FileWriter(Environment.getExternalStorageDirectory().getAbsolutePath() + "/textedit.txt"));
                pw.println(notepadText);
                pw.close(); // close the file to ensure data is flushed to file
            }
            catch(IOException e)
            {
                new AlertDialog.Builder(this).setMessage("ERROR: " + e).
                        setPositiveButton("OK", null).show();
            }

            return true;
        }else if(item.getItemId() == R.id.load){
            EditText notepadEditText = (EditText)findViewById(R.id.notepad);
            try
            {
                FileReader fr = new FileReader(Environment.getExternalStorageDirectory().getAbsolutePath() + "/textedit.txt");
                BufferedReader reader = new BufferedReader(fr);
                String line = "";
                String loadedText = "";
                while((line = reader.readLine()) != null)
                {
                    loadedText = loadedText + line + '\n';
                }
                reader.close();
                notepadEditText.setText(loadedText);
            }
            catch(IOException e)
            {
                new AlertDialog.Builder(this).setMessage("ERROR: " + e).
                        setPositiveButton("OK", null).show();

            }
            return true;
        }
        return false;
    }


}

