package com.example.lesiadspro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    List<Model> modelList = new ArrayList<>();
    RecyclerView mRecyclerview;
    RecyclerView.LayoutManager layoutManager;

    FirebaseFirestore db;

    CustomerAdapter adapter;
    ProgressDialog pd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mRecyclerview = findViewById(R.id.recycler_view);

        mRecyclerview.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        mRecyclerview.setLayoutManager(layoutManager);

        showData();



    }

    private void showData() {

        pd.setTitle("Loading Data..");
        pd.show();

        db.collection("Feedback").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                pd.dismiss();

                for (DocumentSnapshot doc : task.getResult()){
                    Model model = new Model(doc.getString("id"));
                    doc.getString("name");
                    doc.getString("email");
                    doc.getString("feedback");
                    modelList.add(model);
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(ListActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}