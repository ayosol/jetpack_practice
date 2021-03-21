package com.example.jetpackpractice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    TextInputEditText textField;
    Button btn_add, btn_reset;
    RecyclerView recyclerView;

    List<StudentData> studentData = new ArrayList<>();
    RoomDB database;
    StudentAdapter studentAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        textField = view.findViewById(R.id.textField);
        btn_add = view.findViewById(R.id.btn_add);
        btn_reset = view.findViewById(R.id.btn_reset);

        initializeDatabase();

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        studentAdapter = new StudentAdapter(getContext(), studentData);
        recyclerView.setAdapter(studentAdapter);

        btn_add.setOnClickListener(v -> {
            String sText = textField.getText().toString().trim();
            if (!sText.equals("")){
                StudentData sData = new StudentData();
                sData.setFirst_name(sText);
                database.studentDao().insert(sData);
                textField.setText("");
                studentData.clear();
                studentData.addAll(database.studentDao().getAll());
                studentAdapter.notifyDataSetChanged();
            }
        });

        btn_reset.setOnClickListener(v -> {
            database.studentDao().reset(studentData);
            studentData.clear();
            studentData.addAll(database.studentDao().getAll());
            studentAdapter.notifyDataSetChanged();
        });

        return  view;
    }

    private void initializeDatabase() {
        database = RoomDB.getInstance(getContext());
        studentData = database.studentDao().getAll();
    }
}