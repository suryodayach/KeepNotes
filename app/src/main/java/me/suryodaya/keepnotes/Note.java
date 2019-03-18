package me.suryodaya.keepnotes;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private  int id;

    private String title;

    private String description;

    private int priority;

    private String date;

    private String time;

    public Note(String title, String description, int priority, String date, String time) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.date = date;
        this.time = time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
