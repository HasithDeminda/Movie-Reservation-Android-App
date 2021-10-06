package com.example.blueskycinema.Hasith;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blueskycinema.DB_Handler;
import com.example.blueskycinema.MainActivity;
import com.example.blueskycinema.R;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

    private Context context;
    private ArrayList<Model> arrayList;
    private DB_Handler db_handler;

    //Database Object
    DB_Handler databaseHelper;

    public Adapter(Context context, ArrayList<Model> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        db_handler = new DB_Handler(context);

        //Initialize here
        databaseHelper = new DB_Handler(context);

    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.test1, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

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

                Intent intent = new Intent(context, EditMovie.class);
                intent.putExtra("ID", String.valueOf(model.getMovieId()));
                context.startActivity(intent);
            }
        });

        //Delete Button Listener
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener(){

            @Override
            public boolean onLongClick(View v) {

                deleteDialog(
                        ""+ id
                );
                return false;
            }
        });

    }

    //Delete function
    private void deleteDialog(String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete");
        builder.setMessage("Are you sure want to Delete?");
        builder.setCancelable(false);
        builder.setIcon(R.drawable.ic_launcher_del_foreground);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                databaseHelper.deleteInfo(id);
                ((displayMovies)context).onResume();
                Toast.makeText(context, "Successfully Deleted!", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }
//
//    //Edit code
//    private void editDialog(String position, String id, String name, String duration, String year, String description) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setTitle("Update");
//        builder.setMessage("Do You want to Update?");
//        builder.setCancelable(true);
//        builder.setIcon(R.drawable.ic_baseline_edit_24);
//
//        builder.setPositiveButton("Yes", (dialog, which) -> {
//            Intent intent = new Intent(context, EditMovie.class);
//
//            intent.putExtra("ID", id);
//            intent.putExtra("NAME", name);
//            intent.putExtra("DURATION", duration);
//            intent.putExtra("YEAR", year);
//            intent.putExtra("DESCRIPTION", description);
//
//            intent.putExtra("editMode", true);
//            context.startActivity(intent);
//
//        });
//
//        builder.setPositiveButton("No", (dialog, which) -> dialog.cancel());
//        builder.create().show();
//    }

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

            profileIv = itemView.findViewById(R.id.editBtn);
            MName = itemView.findViewById(R.id.MName);
            MDuration = itemView.findViewById(R.id.MDuration);
            MYear = itemView.findViewById(R.id.MYear);
//            editButton = itemView.findViewById(R.id.editBtn);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);
        }
    }


}
