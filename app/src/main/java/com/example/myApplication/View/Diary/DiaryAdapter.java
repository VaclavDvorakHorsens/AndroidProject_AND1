package com.example.myApplication.View.Diary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myApplication.Model.Diary.DiaryItem;
import com.example.myApplication.R;
import java.util.ArrayList;
import java.util.List;

//class that servers as a translator between recycle view and diary items from view
public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.ViewHolder> {

    private List<DiaryItem> diaryItems;
    private OnListItemClickListener itemListener;



    //constructor that accepts diary items listeners
    public DiaryAdapter(OnListItemClickListener itemListener) {
        this.diaryItems = new ArrayList<>();
        this.itemListener = itemListener;
    }

    //returns size of diary items
    @Override
    public int getItemCount() {
        return diaryItems.size();
    }

    //return list of diary items
    public List<DiaryItem> getDiaryItems() {return diaryItems;}

    //create and return view holder that holds view of diary item
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
        viewHolder.diaryDay.setText(diaryItems.get(position).getDiaryDate());
        viewHolder.diaryDateDescription.setText(diaryItems.get(position).getDiaryDateDescription());
    }

    //update diary item via view model
    public void updateList(List<DiaryItem> diaryItems) {
        this.diaryItems = diaryItems;
        notifyDataSetChanged();
    }

    //view holder that connects diary items and recycle view
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView diaryDay;
        TextView diaryDateDescription;
        ImageView diaryDay_icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //listeners on individual diary items that inform DiaryFragment about item position when user clicks on them
            itemView.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                itemListener.onClick(getAdapterPosition());
                                            }
                                        }

            );
            diaryDay = itemView.findViewById(R.id.diaryDay);
            diaryDateDescription = itemView.findViewById(R.id.diaryDayDescription);
            diaryDay_icon = itemView.findViewById(R.id.diaryDay_icon);
        }
    }


    //interface on diary items in View Holder.Is used as onClick listener on individual diary items in View Holder
    public interface OnListItemClickListener {
        void onClick(int position);
    }

}
