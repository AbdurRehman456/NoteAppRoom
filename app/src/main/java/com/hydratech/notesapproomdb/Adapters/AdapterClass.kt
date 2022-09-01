package com.hydratech.notesapproomdb.Adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.hydratech.notesapproomdb.R
import com.hydratech.notesapproomdb.ShowActivity
import com.hydratech.notesapproomdb.db.Notes
import com.hydratech.notesapproomdb.toast

class AdapterClass (private val context: Context?,private val list: ArrayList<Notes>) :
    RecyclerView.Adapter<AdapterClass.ViewHolder>() {

    class ViewHolder (val view: View) :RecyclerView.ViewHolder(view) {

        val layout  = view.findViewById<RelativeLayout>(R.id.mRelativerecycler)
        val textviewTitle: TextView = view.findViewById(R.id.recyler_list_Title)
        val textviewDescription: TextView = view.findViewById(R.id.recyler_list_Description)
        val textViewid: TextView =view.findViewById(R.id.id)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyler_list,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textviewTitle.text = list[position].title
        holder.textviewDescription.text = list[position].note
        holder.textViewid.text = list[position].id.toString()
        holder.layout.setOnClickListener {

            val title = holder.textviewTitle.text.toString()
            val desc = holder.textviewDescription.text.toString()
            val  mId : Int = list[position].id
            val intentt = Intent(context, ShowActivity::class.java).apply {

            }
            intentt.putExtra("Title", title)
            intentt.putExtra("Desc", desc)
            intentt.putExtra("id", mId)

            context!!.startActivity(intentt)

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}