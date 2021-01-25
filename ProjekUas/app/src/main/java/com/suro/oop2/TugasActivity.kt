package com.suro.oop2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.suro.oop2.db.TugasRoomDB
import com.suro.oop2.model.Tugas
import kotlinx.android.synthetic.main.activity_tugas.*

class TugasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tugas)

        getTugassData()

        floatingActionButton.setOnClickListener {
            startActivity(Intent(this, UbahTugasActivity::class.java))
        }
    }

    private fun getTugassData(){
        val database = TugasRoomDB.getDatabase(applicationContext)
        val dao = database.getTugasDao()
        val listItems = arrayListOf<Tugas>()
        listItems.addAll(dao.getAll())
        setupRecyclerView(listItems)
        if (listItems.isNotEmpty()){
            text_view_note_empty.visibility = View.GONE
        }
        else{
            text_view_note_empty.visibility = View.VISIBLE
        }
    }

    private fun setupRecyclerView(listItems: ArrayList<Tugas>){
        recycler_view_main.apply {
            adapter = TugasAdapter(listItems, object : TugasAdapter.TugasListener{
                override fun OnItemClicked(tugas: Tugas) {
                    val intent = Intent(this@TugasActivity, UbahTugasActivity::class.java)
                    intent.putExtra(UbahTugasActivity().UBAH_TUGAS_EXTRA, tugas)
                    startActivity(intent)
                }
            })

            layoutManager = LinearLayoutManager(this@TugasActivity)
        }
    }

    override fun onResume() {
        super.onResume()
        getTugassData()
    }
}