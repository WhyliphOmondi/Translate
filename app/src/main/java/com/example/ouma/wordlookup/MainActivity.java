package com.example.carol.wordlookup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.TranslationAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    EditText editor;
    Button translate, exit;
    RecyclerView recyclerView;
    List<String> setOfWords;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editor = findViewById(R.id.edit);
        translate = findViewById(R.id.translate);
        exit = findViewById(R.id.exit);

        setOfWords = new ArrayList<>();

        recyclerView = findViewById(R.id.recView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final TranslationAdapter translationAdapter = new TranslationAdapter(this, setOfWords);
        recyclerView.setAdapter(translationAdapter);

        exit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();

            }
        });

        translate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String input = editor.getText().toString();

                if(TextUtils.isEmpty(input))
                {
                    Toast.makeText(MainActivity.this, "Enter a word for translation", Toast.LENGTH_LONG).show();
                    return;
                }

                List<String> listofwords = translationSearch(input);
                translationAdapter.addList(listofwords);

            }
        });
        }

    private List<String> translationSearch(String word)
    {
        String lineLocationOfWord = "";
        List<String> wordList = new ArrayList<>();

        try
        {
            InputStream inputStream = getAssets().open("db.csv");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            while((lineLocationOfWord = bufferedReader.readLine()) != null)
            {
                List<String> words = Arrays.asList(lineLocationOfWord.split(","));

                if(words.size() == 2)
                {
                    if(word.toLowerCase().equals(words.get(1).toLowerCase()))
                    {
                        wordList.add(words.get(0));
                    }
                }

                if(wordList.size() == 10)
                {
                    break;
                }
            }
            bufferedReader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return wordList;
    }
}
