package com.example.noteapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import com.example.noteapplication.databinding.ActivityMainBinding
import com.google.type.Date
import java.text.SimpleDateFormat

class AddNote : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var note: Note
    private lateinit var old_note : Note
    var isUpdate = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val ActivityAddNoteBinding = null
        binding = ActivityAddNoteBinding.inflate()
        setContentView(binding.root)

        try{

            old_note = intent.getSerializableExtra("current_note") as Note
            binding.etTitle.setText(old_note.title)
            binding.etNote.setText(old_note.note)
            isUpdate = true

        }catch (e : Exception){

            e.printStackTrace()
        }

        binding.imgCheck.setOnClickListener {

            val title = binding.etTitle.text.toString()
            val note_desc = binding.etNote.tect.toString()

            if (title.isNotEmpty() || note_desc.isNotEmpty()){

                val formatter = SimpleDateFormat("EEE, d MMM YYYY HH mm a")

                if(isUpdate){

                    note = Note(
                        old_note.id,title,note_desc,formatter.format(Date())
                    )
                }else{

                    note = Note(
                        null,title,note_desc,formatter.format(Date())
                    )
                }
                val intent = Intent()
                intent.putExtra("note",note)
                setResult(RESULT_OK,intent)
                finish()
            }
        }

        binding.img_back_arrow.setOnClickListener{

            onBackPressed()
        }
    }
}