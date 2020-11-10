package com.example.myApplication.View.Diary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.fragment.app.Fragment;
import com.example.myApplication.Model.Diary.DiaryItem;
import com.example.myApplication.ModelView.Diary.DiaryViewModel;
import com.example.navigationdrawer.R;
import com.google.android.material.snackbar.Snackbar;
import java.util.List;

public class DiaryFragment extends Fragment implements DiaryAdapter.OnListItemClickListener {

    View rootView;
    RecyclerView recycleViewList;
    DiaryAdapter diaryAdapter;
    Button addDiaryItem;
    TextView newDiaryDate;
    TextView newDiaryDateDescription;
    ViewModel diaryViewModel;
    Button updateDiaryItem;
    Button deleteDiaryItem;
    CoordinatorLayout diaryCoordinatorLayout;
    int diaryItemPosition;
    boolean isItemSelected;


    //create GUI for fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //call set up methods
        rootView = inflater.inflate(R.layout.diary_activity, container, false);
        addViewModel();
        setUpLocalGUIVariables();
        setUpDiaryAdapter();
        addListenerOn_AddDiary_Button();
        addListenerOn_UpdateDiary_Button();
        addListenerOn_DeleteDiary_Button();
        return rootView;
    }


    //add viewmodel
    private void addViewModel() {
        diaryViewModel = new ViewModelProvider(this).get(DiaryViewModel.class);
    }


    //set up local GUI variables
    private void setUpLocalGUIVariables() {
        recycleViewList = rootView.findViewById(R.id.recycleView);
        recycleViewList.hasFixedSize();
        recycleViewList.setLayoutManager(new LinearLayoutManager(getActivity()));
        diaryAdapter = new DiaryAdapter(this, (DiaryViewModel) diaryViewModel);
        addDiaryItem = rootView.findViewById(R.id.addDiaryItem);
        updateDiaryItem = rootView.findViewById(R.id.updateDiaryItem);
        deleteDiaryItem = rootView.findViewById(R.id.deleteDiaryItem);
        newDiaryDate = rootView.findViewById(R.id.newDiaryDate);
        newDiaryDateDescription = rootView.findViewById(R.id.newDiaryDescription);
        isItemSelected = false;
    }

    //set up diary adapter, set up listener on diaryItems in DiaryViewModel
    private void setUpDiaryAdapter() {
        recycleViewList.setAdapter(diaryAdapter);
        ((DiaryViewModel) diaryViewModel).getAllItems().observe(getViewLifecycleOwner(), new Observer<List<DiaryItem>>() {
            @Override
            public void onChanged(List<DiaryItem> diaryItems) {
                diaryAdapter.updateList(diaryItems);
            }
        });
    }

    //set up listener on AddNewDiary item button
    public void addListenerOn_AddDiary_Button() {
        addDiaryItem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String newDiaryDateText = newDiaryDate.getText().toString();
                String newDiaryDateDescriptionText = newDiaryDateDescription.getText().toString();
                DiaryItem diaryItem = new DiaryItem(newDiaryDateText, newDiaryDateDescriptionText);
                diaryAdapter.addItem(diaryItem);
            }
        });
    }


    //update button listener
    public void addListenerOn_UpdateDiary_Button() {
        updateDiaryItem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (isItemSelected) {
                    Snackbar snackbar = Snackbar
                            .make(rootView, getActivity().getApplicationContext().getString(R.string.reallyUpdate), Snackbar.LENGTH_LONG)
                            .setAction(getActivity().getApplicationContext().getString(R.string.Yes), new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    String newDiaryDateText = newDiaryDate.getText().toString();
                                    String newDiaryDateDescriptionText = newDiaryDateDescription.getText().toString();
                                    DiaryItem diaryItem = new DiaryItem(diaryItemPosition, newDiaryDateText, newDiaryDateDescriptionText);
                                    diaryAdapter.updateItem(diaryItemPosition, diaryItem);
                                    isItemSelected = false;
                                }
                            });
                    snackbar.show();
                }
            }
        });
    }


    //delete button listener
    public void addListenerOn_DeleteDiary_Button() {
        deleteDiaryItem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (isItemSelected) {
                    Snackbar snackbar = Snackbar
                            .make(rootView, getActivity().getApplicationContext().getString(R.string.reallyDelete), Snackbar.LENGTH_LONG)
                            .setAction(getActivity().getApplicationContext().getString(R.string.Yes), new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    String newDiaryDateText = newDiaryDate.getText().toString();
                                    String newDiaryDateDescriptionText = newDiaryDateDescription.getText().toString();
                                    DiaryItem diaryItem = new DiaryItem(diaryItemPosition, newDiaryDateText, newDiaryDateDescriptionText);
                                    diaryAdapter.deleteItem(diaryItemPosition, diaryItem);
                                    isItemSelected = false;
                                }
                            });
                    snackbar.show();
                }
            }
        });
    }


    //click diary item callback from Diary Adapter so the update/delete buttons would get item id
    @Override
    public void onClick(int itemPosition) {
        diaryItemPosition = itemPosition;
        isItemSelected = true;
    }
}