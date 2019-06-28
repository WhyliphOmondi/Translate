package com;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.carol.wordlookup.R;

import java.util.List;

public class TranslationAdapter extends RecyclerView.Adapter<TranslationAdapter.ViewHolder>
{
    private Context context;
    private List<String> setOfWords;

    public TranslationAdapter(Context context, List<String> setOfWords)
    {
        this.context = context;
        this.setOfWords = setOfWords;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.the_words_set, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int j)
    {
        viewHolder.words.setText(setOfWords.get(j));

    }

    @Override
    public int getItemCount() {
        return setOfWords.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView words;

        public ViewHolder(@NonNull View item)
        {
            super(item);

            words = item.findViewById(R.id.wordSet);
        }
    }

    public void addList(List<String> setOfWords)
    {
        this.setOfWords = setOfWords;
        notifyDataSetChanged();
    }
}
