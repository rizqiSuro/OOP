package com.suro.oop2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.suro.oop2.db.MkRoomDB
import com.suro.oop2.model.Mk
import kotlinx.android.synthetic.main.activity_mk.floatingActionButton
import kotlinx.android.synthetic.main.activity_mk.recycler_view_main
import kotlinx.android.synthetic.main.activity_mk.text_view_note_empty
import kotlinx.android.synthetic.main.activity_mk.*


class MkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mk)

        getMksData()

        floatingActionButton.setOnClickListener {
            startActivity(Intent(this, UbahMkActivity::class.java))
        }
    }

    private fun getMksData(){
        val database = MkRoomDB.getDatabase(applicationContext)
        val dao = database.getMkDao()
        val listItems = arrayListOf<Mk>()
        listItems.addAll(dao.getAll())
        setupRecyclerView(listItems)
        if (listItems.isNotEmpty()){
            text_view_note_empty.visibility = View.GONE
        }
        else{
            text_view_note_empty.visibility = View.VISIBLE
        }
    }

    private fun setupRecyclerView(listItems: ArrayList<Mk>){
        recycler_view_main.apply {
            adapter = MkAdapter(listItems, object : MkAdapter.MkListener{
                override fun OnItemClicked(mk: Mk) {
                    val intent = Intent(this@MkActivity, UbahMkActivity::class.java)
                    intent.putExtra(UbahMkActivity().UBAH_MK_EXTRA, mk)
                    startActivity(intent)
                }
            })

            layoutManager = LinearLayoutManager(this@MkActivity)
        }
    }

    override fun onResume() {
        super.onResume()
        getMksData()
    }
}