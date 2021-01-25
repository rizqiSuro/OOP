package com.suro.oop2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.suro.oop2.db.MkDao
import com.suro.oop2.db.MkRoomDB
import com.suro.oop2.model.Mk
import kotlinx.android.synthetic.main.activity_ubah_tugas.*

class UbahMkActivity : AppCompatActivity() {

    val UBAH_MK_EXTRA = "ubah_tugas_extra"
    private lateinit var mk : Mk
    private var isUpdate = false
    private lateinit var database: MkRoomDB
    private lateinit var dao: MkDao


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ubah_mk)

        database = MkRoomDB.getDatabase(applicationContext)
        dao = database.getMkDao()

        if (intent.getParcelableExtra<Mk>(UBAH_MK_EXTRA) != null){
            button_delete.visibility = View.VISIBLE
            isUpdate = true
            mk = intent.getParcelableExtra(UBAH_MK_EXTRA)!!
            edit_text_name.setText(mk.title)
            edit_text_operator.setText(mk.body)

            edit_text_name.setSelection(mk.title.length)

        }

        button_save.setOnClickListener {
            val title = edit_text_name.text.toString()
            val body = edit_text_operator.text.toString()

            if (title.isEmpty() && body.isEmpty()){
                Toast.makeText(applicationContext, "Matkul tidak boleh ksosong", Toast.LENGTH_SHORT).show()
            }
            else{
                if (isUpdate){
                    saveMk(Mk(id = mk.id, title = title, body = body))
                }
                else{
                    saveMk(Mk(title = title, body = body))
                }
            }

            finish()
        }

        button_delete.setOnClickListener {
            deleteMk(mk)
            finish()
        }

    }

    private fun saveMk(mk: Mk){

        if (dao.getById(mk.id).isEmpty()){

            dao.insert(mk)
        }
        else{

            dao.update(mk)
        }

        Toast.makeText(applicationContext, "Berhasil di simpan", Toast.LENGTH_SHORT).show()

    }

    private fun deleteMk(mk: Mk){
        dao.delete(mk)
        Toast.makeText(applicationContext, "berhasil di hapus", Toast.LENGTH_SHORT).show()
    }
}