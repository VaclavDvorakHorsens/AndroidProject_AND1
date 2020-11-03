package com.example.myApplication.View.Diary;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myApplication.Model.Diary.DiaryItem;
import com.example.myApplication.ModelView.Diary.DiaryViewModel;
import com.example.navigationdrawer.R;

import java.util.ArrayList;
import java.util.List;

public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.ViewHolder> {

    private List<DiaryItem> diaryItems;
    private OnListItemClickListener itemListener;
    private DiaryViewModel viewModel;


    public DiaryAdapter(OnListItemClickListener itemListener, DiaryViewModel viewModel)
    {
        /*this.diaryItems= MainActivity.DiaryItemList.getDiaryItems();*/
        this.diaryItems=new ArrayList<>();
        this.itemListener=itemListener;
        this.viewModel=viewModel;
    }

    @Override
    public int getItemCount() {
        return diaryItems.size();
       /* return viewModel.getItemCount();*/
    }



    //create and return view holder that holds view of diary_item
    @NonNull
    @Override
    public DiaryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.diary_item, parent, false);
        return new ViewHolder(view);
    }

    //bind data(diary items) with view holder
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {
        /*viewHolder.diaryDay.setText(diaryItems.get(position).getDiaryDate());
        viewHolder.diaryDateDescription.setText(diaryItems.get(position).getDiaryDateDescription());
        viewHolder.diaryDay_icon.setImageResource(diaryItems.get(position).getDiaryIconId());*/

        viewHolder.diaryDay.setText(diaryItems.get(position).getDiaryDate());
        viewHolder.diaryDateDescription.setText(diaryItems.get(position).getDiaryDateDescription());

    /*   viewModel.getAllItems().observe((LifecycleOwner) this,new Observer<List<DiaryItem>>() {
            @Override
            public void onChanged(List<DiaryItem> diaryItems) {
              if(!diaryItems.isEmpty())
              {
                  viewHolder.diaryDay.setText(viewModel.getAllItems().getValue().get(position).getDiaryDate());
                  viewHolder.diaryDateDescription.setText(viewModel.getAllItems().getValue().get(position).getDiaryDateDescription());
              }

             //  Log.d("xxx","my value: "+o);
            }


        });*/

        //viewHolder.diaryDay_icon.setImageResource(viewModel.getAllItems().getValue().get(position).getDiaryIconId());

    }


    //add diary item via model
    public void addItem(DiaryItem diaryItem) {
        /*diaryItems.add(diaryItem);*/
        viewModel.addDiaryItem(diaryItem);
        notifyDataSetChanged();
        viewModel.getAllItems();
    }



    public void updateList(List<DiaryItem> diaryItems) {
        this.diaryItems = diaryItems;
        notifyDataSetChanged();
    }


    //update diary item via model
    public void updateItem(int number,DiaryItem diaryItem) {
        /*diaryItems.add(diaryItem);*/

        DiaryItem toUpdate=diaryItem;
        diaryItem.setId(diaryItems.get(number).getId());
        viewModel.updateDiaryItem(toUpdate);
        notifyDataSetChanged();
    }

//delete
    public void deleteItem(int number,DiaryItem diaryItem) {
        /*diaryItems.add(diaryItem);*/
        DiaryItem toUpdate=diaryItem;
        diaryItem.setId(diaryItems.get(number).getId());
        viewModel.deleteDiaryItem(diaryItem);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView diaryDay;
        TextView diaryDateDescription;
        ImageView diaryDay_icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view)
                {
                    itemListener.onClick(getAdapterPosition());
                }}

            );
            diaryDay=itemView.findViewById(R.id.diaryDay);
            diaryDateDescription=itemView.findViewById(R.id.diaryDayDescription);
            diaryDay_icon=itemView.findViewById(R.id.diaryDay_icon);
        }
    }






    //callback na itemech, tohle mozna nepouziju
    public interface OnListItemClickListener{
        void onClick(int position);

    }

}
