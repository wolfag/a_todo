package com.example.todoapp

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var itemList = ArrayList<String>()
    var fileHelper = FileHelper()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        itemList = fileHelper.read(this)
        var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, itemList)
        lvTask.adapter = adapter

        btnAdd.setOnClickListener {
            val task = edtTask.text.toString()
            if (task.isEmpty()) {
                return@setOnClickListener
            }

            itemList.add(task)
            edtTask.setText("")

            adapter.notifyDataSetChanged()
            fileHelper.write(itemList, applicationContext)

        }

        lvTask.setOnItemClickListener { parent, view, position, id ->
            var alert = AlertDialog.Builder(this)
            alert.setTitle("Delete")
            alert.setMessage("Are you sure?")
            alert.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()
            })
            alert.setPositiveButton("Delete", DialogInterface.OnClickListener { dialog, which ->
                itemList.removeAt(position)
                adapter.notifyDataSetChanged()
                fileHelper.write(itemList, applicationContext)
            })
            alert.create().show()
        }
    }
}