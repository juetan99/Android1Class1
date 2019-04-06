package com.example.extstudent.mylistapp;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView list = findViewById(R.id.aw_List);

        TextLisAdapter adapter = new TextLisAdapter();
        //list.setLayoutManager();
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(this));

        //adapter.setItems(Arrays.asList("Item 1", "Item 2", "Item 3", "Item 4"));
        adapter.setItems(generateitems());
    }

    private List<ListItemData> generateitems() {
        List<ListItemData> items = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            ListItemData itemData = new ListItemData("Title " + String.valueOf(i),
                    "Subtitle" + String.valueOf(i),
                    Color.rgb(new Random().nextInt(255),
                            new Random().nextInt(255),
                            new Random().nextInt(255)));
            items.add(itemData);
        }

        return items;
    }


    private class ListItemData{
        private String title;
        private String subTitle;
        private int backgroundColor;

        public ListItemData(String title, String subtitle, int color){
            this.title= title;
            this.subTitle = subtitle;
            this.backgroundColor = color;
        }

        public String getTitle() {
            return title;
        }

        public String getSubTitle() {
            return subTitle;
        }

        public int getBackgroundColor() {
            return backgroundColor;
        }

    }

    private static class TextLisAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        private List<ListItemData> items = new ArrayList<>();

        public void setItems(List<ListItemData> items){
            this.items = items;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {

            return new TextViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_text, viewGroup, false));

        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
                if(viewHolder instanceof TextViewHolder){

                    TextViewHolder textViewHolder = ((TextViewHolder)viewHolder);
                    ListItemData item = items.get(position);
                    textViewHolder.setText(item.getTitle());
                }
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        @Override
        public int getItemViewType(int position){
            return super.getItemViewType(position);
        }
    }

    private static class TextViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        private TextView textViewSubTitle;

        public TextViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.lit_text_view);
            textViewSubTitle = itemView.findViewById(R.id.lit_sub_title);
        }

        public void setText(String text){
            textView.setText(text);
        }

        public void setBackground(int color){
            itemView.setBackgroundColor(color);
        }
    }

}
