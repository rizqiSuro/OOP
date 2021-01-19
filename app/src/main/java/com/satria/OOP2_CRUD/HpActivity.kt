package com.satria.OOP2_CRUD

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.satria.OOP2_CRUD.Database.AppRoomDB
import com.satria.OOP2_CRUD.Database.Constant
import com.satria.OOP2_CRUD.Database.hp
import kotlinx.android.synthetic.main.activity_hp.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HpActivity : AppCompatActivity() {

    val db by lazy { AppRoomDB(this) }
    lateinit var hpAdapter: HpAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hp)
        setupListener()
        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        loadHp()

    }

    fun loadHp(){
        CoroutineScope(Dispatchers.IO).launch {
            val allhp = db.hpDao().getAllhp()
            Log.d("hpActivity", "dbResponse: $allhp")
            withContext(Dispatchers.Main) {
                hpAdapter.setData(allhp)
            }
        }


    }

    fun setupListener() {
        btn_createhp.setOnClickListener {
            intentEdit(0, Constant.TYPE_CREATE)
        }

    }

    fun setupRecyclerView() {
        hpAdapter = HpAdapter(arrayListOf(), object: HpAdapter.OnAdapterListener {
            override fun onClick(hp: hp) {
                intentEdit(hp.id, Constant.TYPE_READ)
            }

            override fun onDelete(hp: hp ) {
                deleteDialog(hp)
            }
            override fun onUpdate(hp: hp) {
                // edit data
                intentEdit(hp.id, Constant.TYPE_UPDATE)
            }
        })
        list_hp.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = hpAdapter
        }
    }

    fun intentEdit(hpId: Int, intentType: Int ) {
        startActivity(
            Intent(applicationContext, EdithpActivity::class.java)
                .putExtra("intent_id", hpId)
                .putExtra("intent_type", intentType)
        )
    }

    private fun deleteDialog(hp: hp) {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.apply {
            setTitle("Konfirmasi")
            setMessage("Yakin ingin menghapus data ini?")
            setNegativeButton("Batal") { dialogInterface, i ->
                dialogInterface.dismiss()
            }
            setPositiveButton("Hapus") { dialogInterface, i ->
                dialogInterface.dismiss()
                CoroutineScope(Dispatchers.IO).launch {
                    db.hpDao().deleteHp(hp)
                    loadHp()
                }
            }
        }
        alertDialog.show()
    }
}