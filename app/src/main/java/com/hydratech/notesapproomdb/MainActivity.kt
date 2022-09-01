package com.hydratech.notesapproomdb

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.Settings
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.hydratech.notesapproomdb.Adapters.AdapterClass
import com.hydratech.notesapproomdb.databinding.ActivityMain2Binding
import com.hydratech.notesapproomdb.databinding.DialogBoxBinding
import com.hydratech.notesapproomdb.db.DatabaseNotes
import com.hydratech.notesapproomdb.db.Notes
import kotlinx.coroutines.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    var fab: FloatingActionButton? = null
/*    lateinit var idd: EditText
    lateinit var age: EditText
    lateinit var enterName: EditText
    lateinit var enterAdress: EditText
    lateinit var saveBtn: Button
    lateinit var edit: Button*/

    var arraylist = ArrayList<Notes>()
    lateinit var database: DatabaseNotes
    //lateinit var mRecyclerView: RecyclerView
    lateinit var adapter: AdapterClass
    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        database = DatabaseNotes.getDatabaseNotes(this)



       // mRecyclerView = findViewById(R.id.recylerView)
        val layoutManager: RecyclerView.LayoutManager =
            StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        binding.recylerView.setLayoutManager(layoutManager)

        CoroutineScope(Dispatchers.Main).launch {
            populateRecylerView()
        }


        binding.mAddNewNote!!.setOnClickListener {
            showDialog(this)
        }

/*        idd = findViewById(R.id.enterId)
        enterName = findViewById(R.id.enterName)
        enterAdress = findViewById(R.id.enterAdress)
        age = findViewById(R.id.enterAge)
        saveBtn = findViewById(R.id.save)
        edit = findViewById(R.id.editBtn)*/
/*        saveBtn.setOnClickListener {

            Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show()

            var name = enterName.text
            var age1 :Int = age.text.toString().toInt()
            var adress = enterAdress.text


//            GlobalScope.launch {
//                database.getNotesDao().insert(Notes(name.toString(),age1,adress.toString()))
//
//
//            }

    }
        edit.setOnClickListener {
            var name = enterName.text
            var age1 :Int = age.text.toString().toInt()
            var adress = enterAdress.text

            GlobalScope.launch {
                list = database.getNotesDao().getNotes()

                val id = idd.text.toString().toInt()

//                for ( i in list){
//                    if (id.equals(i.id)) {
//                        database.getNotesDao()
//                            .updateNotes(Notes(name.toString(), age1, adress.toString()))
//
//                    }
//                }

            }
        }


//     GlobalScope.launch {
//         database.getNotesDao().insert(Notes("abdul", 25, "kohat"))
//
//     }*/

    }

    @SuppressLint("NotifyDataSetChanged")
    fun showDialog(activity: Activity?) {
        val dialog = Dialog(activity!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        val binding : DialogBoxBinding
        binding = DialogBoxBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)
//        val text = dialog.findViewById<View>(R.id.text_dialog) as TextView
//        text.text = msg
        val saveButton = binding.dialogSaveBtn
        val cancel = binding.dialogCancelBtn
        val title = binding.dialogTitle
        val desc = binding.dialogDescription

        saveButton.setOnClickListener {
            if (TextUtils.isEmpty(title.text.toString())) {
                title.setError("Enter title")
            } else if (TextUtils.isEmpty(desc.text.toString())) {
                desc.setError("please enter field")
            } else {
                val notetitle = title.text.toString().trim()
                val notedesc = desc.text.toString().trim()
                val notes = Notes(notetitle, notedesc)
                CoroutineScope(Dispatchers.IO).launch {
                    kotlinCoroutine(notes)
                }
                dialog.dismiss()
            }
        }
        cancel.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    suspend fun populateRecylerView() {
        arraylist.clear()
        val list = database.getNotesDao().getNotes()
        arraylist = ArrayList(list)
        adapter = AdapterClass(this@MainActivity, arraylist)
        binding.recylerView.adapter = adapter
    }

    private suspend fun kotlinCoroutine(notes: Notes) {
        Log.e("AllVideos", "kakaCalled")
        database.getNotesDao().insert(notes)
        withContext(Dispatchers.Main){
            populateRecylerView()

        }


    }
//save / insert  Note using Async Task
/* fun saveNotes(notes: Notes){
     class SaveNotes : AsyncTask<Void,Void,Void>(){
         override fun doInBackground(vararg p0: Void?): Void?{
             database.getNotesDao().insert(notes)
             return null
         }

         override fun onPostExecute(result: Void?) {
             super.onPostExecute(result)
             Toast.makeText(this@MainActivity, "DataInserted", Toast.LENGTH_SHORT).show()
         }
     }
     SaveNotes().execute()
 }*/

}