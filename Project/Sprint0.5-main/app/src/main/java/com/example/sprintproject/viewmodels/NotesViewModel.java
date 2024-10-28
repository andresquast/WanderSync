package com.example.sprintproject.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sprintproject.model.NotesModel;
import com.example.sprintproject.views.MainActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class NotesViewModel extends ViewModel {

    private MutableLiveData<String> noteMessage;
    private NotesModel notesModel;

    private String userId = MainActivity.getUserId();
    private DatabaseReference db = new DBViewModel().getDB();

    public NotesViewModel() {
        noteMessage = new MutableLiveData<>();
        notesModel = new NotesModel("");

        db.child("users").child(userId).child("destinations").child("notes").child(userId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {

                            NotesModel note = snapshot.getValue(NotesModel.class);

                            if (note != null) {

                                noteMessage.setValue(note.getNotes());
                                notesModel.setNotes(note.getNotes());

                            } else {

                                notesModel.setNotes("");
                                noteMessage.setValue("");
                                db.child("users").child(userId).child("destinations")
                                        .child("notes").child(userId).setValue("");

                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        //your words are as empty, empty
                    }
                });

    }

    public LiveData<String> getMessage() {

        return noteMessage;

    }

    public DatabaseReference getDatabaseReference() {
        return db;
    }
    public String getUserID() {
        return userId;
    }

    public void updateMessage(String note) {

        notesModel.setNotes(note);
        noteMessage.setValue(notesModel.getNotes());

        db.child("users").child(userId).child("destinations")
                .child("notes").child(userId).setValue(note);

    }

}
