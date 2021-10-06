package com.example.blueskycinema.Hasith;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blueskycinema.DB_Handler;
import com.example.blueskycinema.R;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.Holder> {
    private Context context;
    private ArrayList<Model> arrayList;
    private DB_Handler db_handler;

    //Database Object
    DB_Handler databaseHelper;

    public UserAdapter(Context context, ArrayList<Model> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        db_handler = new DB_Handler(context);

        //Initialize here
        databaseHelper = new DB_Handler(context);

    }

    @NonNull
    @Override
    public UserAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.row, parent, false);
        return new UserAdapter.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.Holder holder, int position) {

        Model model = arrayList.get(position);

        //Get for view
        String id = model.getMovieId();
        String name = model.getMovieName();
        String duration = model.getDuration();
        String year = model.getYear();
        String description = model.getTickets();

        //Set view
        holder.MName.setText(name);
        holder.MDuration.setText(duration);
        holder.MYear.setText(year);


        //update movie edit button
        holder.profileIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, MovieDetails.class);
                intent.putExtra("ID", String.valueOf(model.getMovieId()));
                context.startActivity(intent);
            }
        });

    }



    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        ImageView profileIv;
        TextView MName, MDuration, MYear;
        //        ImageButton editButton;
        Button buttonDelete;

        public Holder(@NonNull View itemView) {
            super(itemView);

            profileIv = itemView.findViewById(R.id.profilePic);
            MName = itemView.findViewById(R.id.MName2);
            MDuration = itemView.findViewById(R.id.MDuration2);
            MYear = itemView.findViewById(R.id.MYear2);
//            editButton = itemView.findViewById(R.id.editBtn);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);
        }
    }

}
