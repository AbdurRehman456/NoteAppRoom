package com.hydratech.notesapproomdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import com.hydratech.notesapproomdb.db.DatabaseNotes
import com.hydratech.notesapproomdb.db.Notes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class ShowActivity : AppCompatActivity() {
    private lateinit var nNote: Notes
    lateinit var eTitleEditText: EditText
    lateinit var edescEditText: EditText
    private var notes :Notes? = null
    var id  : String? = null
    lateinit var database: DatabaseNotes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)
        database = DatabaseNotes.getDatabaseNotes(this)

        eTitleEditText=findViewById(R.id.titleAfterClick)
        edescEditText=findViewById(R.id.descriptionAfterClick)

        val intent = intent
        val title = intent.getStringExtra("Title")
        val desc =  intent.getStringExtra("Desc")
        id = intent.getStringExtra("id")

        eTitleEditText.setText(title)
        edescEditText.setText(desc)


       nNote = Notes(eTitleEditText.text.toString(),edescEditText.text.toString())



    }
   suspend fun update(){
       if (notes!!.id == id!!.toInt()){
           database.getNotesDao().updateNotes(nNote)

       }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        CoroutineScope(Dispatchers.IO).launch {
            update()
        }
    }
}