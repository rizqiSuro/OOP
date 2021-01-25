package com.suro.oop2


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.suro.oop2.db.TugasDao
import com.suro.oop2.db.TugasRomDB
import com.suro.oop2.model.Tugas
import kotlinx.android.synthetic.main.activity_ubah_tugas.*

class UbahTugasActivity : AppCompatActivity() {

    val UBAH_TUGAS_EXTRA = "ubah_tugas_extra"
    private lateinit var tugas: Tugas
    private var isUpdate = false
    private lateinit var database: TugasRomDB
    private lateinit var dao: TugasDao


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ubah_tugas)

        database = TugasRomDB.getDatabase(applicationContext)
        dao = database.getTugasDao()

        if (intent.getParcelableExtra<Tugas>(UBAH_TUGAS_EXTRA) != null){
            button_delete.visibility = View.VISIBLE
            isUpdate = true
            tugas = intent.getParcelableExtra(UBAH_TUGAS_EXTRA)!!
            edit_text_name.setText(tugas.title)
            edit_text_operator.setText(tugas.body)

            edit_text_name.setSelection(tugas.title.length)

        }

        button_save.setOnClickListener {
            val title = edit_text_name.text.toString()
            val body = edit_text_operator.text.toString()

            if (title.isEmpty() && body.isEmpty()){
                Toast.makeText(applicationContext, "Tugas tidak boleh ksosong", Toast.LENGTH_SHORT).show()
            }
            else{
                if (isUpdate){
                    saveTugas(Tugas(id = tugas.id, title = title, body = body))
                }
                else{
                    saveTugas(Tugas(title = title, body = body))
                }
            }

            finish()
        }

        button_delete.setOnClickListener {
            deleteTugas(tugas)
            finish()
        }

    }

    private fun saveTugas(tugas: Tugas){

        if (dao.getById(tugas.id).isEmpty()){

            dao.insert(tugas)
        }
        else{

            dao.update(tugas)
        }

        Toast.makeText(applicationContext, "Berhasil di simpan", Toast.LENGTH_SHORT).show()

    }

    private fun deleteTugas(tugas: Tugas){
        dao.delete(tugas)
        Toast.makeText(applicationContext, "berhasil di hapus", Toast.LENGTH_SHORT).show()
    }
}