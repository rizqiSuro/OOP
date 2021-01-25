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
                Toast.makeText(applicationContext, "Bimbingan tidak boleh ksosong", Toast.LENGTH_SHORT).show()
            }
            else{
                if (isUpdate){
                    saveBimbingan(Mk(id = mk.id, title = title, body = body))
                }
                else{
                    saveBimbingan(Mk(title = title, body = body))
                }
            }

            finish()
        }

        button_delete.setOnClickListener {
            deleteBimbingan(mk)
            finish()
        }

    }

    private fun saveBimbingan(bimbingan: Mk){

        if (dao.getById(bimbingan.id).isEmpty()){

            dao.insert(bimbingan)
        }
        else{

            dao.update(bimbingan)
        }

        Toast.makeText(applicationContext, "Berhasil di simpan", Toast.LENGTH_SHORT).show()

    }

    private fun deleteBimbingan(bimbingan: Mk){
        dao.delete(bimbingan)
        Toast.makeText(applicationContext, "berhasil di hapus", Toast.LENGTH_SHORT).show()
    }
}