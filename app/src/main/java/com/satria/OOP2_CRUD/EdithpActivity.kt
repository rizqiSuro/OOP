package com.satria.OOP2_CRUD

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.satria.OOP2_CRUD.Database.AppRoomDB
import com.satria.OOP2_CRUD.Database.Constant
import com.satria.OOP2_CRUD.Database.hp
import kotlinx.android.synthetic.main.activity_edit_hp.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EdithpActivity : AppCompatActivity() {

    val db by lazy { AppRoomDB(this) }
    private var hpId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_hp)
        setupListener()
        setupView()
    }

    fun setupListener(){
        btn_savehp.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.hpDao().addHp(
                    hp(0, txt_merk.text.toString(), Integer.parseInt(txt_stok.text.toString()), Integer.parseInt(txt_harga.text.toString()))
                )
                finish()
            }
        }
        btn_updatehp.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.hpDao().updateHp(
                    hp(hpId, txt_merk.text.toString(), Integer.parseInt(txt_stok.text.toString()), Integer.parseInt(txt_harga.text.toString()) )
                )
                finish()
            }
        }

    }

    fun setupView() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val intentType = intent.getIntExtra("intent_type", 0)
        when (intentType) {
            Constant.TYPE_CREATE -> {

                btn_updatehp.visibility = View.GONE
            }
            Constant.TYPE_READ -> {
                btn_savehp.visibility = View.GONE
                btn_updatehp.visibility = View.GONE
                getHp()
            }
            Constant.TYPE_UPDATE -> {
                btn_savehp.visibility = View.GONE
                getHp()
            }
        }
    }

    fun getHp() {
        hpId = intent.getIntExtra("intent_id", 0)
        CoroutineScope(Dispatchers.IO).launch {
            val hps =  db.hpDao().getHp( hpId )[0]
            txt_merk.setText( hps.merk )
            txt_stok.setText( hps.stok.toString() )
            txt_harga.setText( hps.harga.toString() )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}