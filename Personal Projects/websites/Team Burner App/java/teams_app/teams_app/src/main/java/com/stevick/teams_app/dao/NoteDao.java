package com.stevick.teams_app.dao;

import com.stevick.teams_app.model.Note;
import java.util.List;

public interface NoteDao {
    Note getNoteById(int noteId);
    List<Note> getNotesByTeamId(int teamId);

    Note createNote(Note note);
    Note updateNote(Note note);
}
