package com.example.jetpackpractice;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyViewHolder> {

    private List<StudentData> studentData;
    private Context context;
    private RoomDB database;

    public StudentAdapter(Context context, List<StudentData> studentData){
        this.context = context;
        this.studentData = studentData;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_list_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        StudentData data = studentData.get(position );
        database = RoomDB.getInstance(context);
        holder.tv_text.setText(data.getFirst_name());
        holder.btn_edit.setOnClickListener(v -> {
            StudentData sData = studentData.get(holder.getAdapterPosition());
            int sID = sData.getId();
            String sText = sData.getFirst_name();

            //Create Dialog for Editing
            Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.update_dialog);
            int width = WindowManager.LayoutParams.MATCH_PARENT;
            int height = WindowManager.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width, height);
            dialog.show();

            //Set Data on the Layout
            TextInputLayout txt_first_name = dialog.findViewById(R.id.txt_first_name);
            TextInputLayout txt_last_name = dialog.findViewById(R.id.txt_last_name);
            TextInputLayout txt_email = dialog.findViewById(R.id.txt_email);
            TextInputLayout txt_phone_no = dialog.findViewById(R.id.txt_phone_no);
            TextInputLayout txt_matric = dialog.findViewById(R.id.txt_matric);
            Button btn_update = dialog.findViewById(R.id.btn_update);
            txt_first_name.getEditText().setText(sText);
            btn_update.setOnClickListener(v1 -> {
                dialog.dismiss();
                // Get update text from edit text
                String firstName = txt_first_name.getEditText().getText().toString().trim();
                String lastName = txt_last_name.getEditText().getText().toString().trim();
                String email = txt_email.getEditText().getText().toString().trim();
                String phone_no = txt_phone_no.getEditText().getText().toString().trim();
                Long matric = Long.parseLong(txt_matric.getEditText().getText().toString().trim());
                //Update text in database
                database.studentDao().update(sID, firstName, lastName, email, phone_no, matric);
                studentData.clear();
                studentData.addAll(database.studentDao().getAll());
                notifyDataSetChanged();
            });
        });
        holder.btn_delete.setOnClickListener(v -> {
            StudentData sData = studentData.get(holder.getAdapterPosition());
            database.studentDao().delete(sData);
            int pos = holder.getAdapterPosition();
            studentData.remove(pos);
            notifyItemRemoved(pos);
            notifyItemRangeChanged(pos, studentData.size());

        });
    }


    @Override
    public int getItemCount() {
        return studentData.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_text;
        ImageButton btn_edit, btn_delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_text = itemView.findViewById(R.id.tv_text);
            btn_edit = itemView.findViewById(R.id.btn_edit);
            btn_delete = itemView.findViewById(R.id.btn_delete);

        }
    }


}
