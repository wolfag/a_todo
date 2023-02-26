package com.example.todoapp

import android.content.Context
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class FileHelper {
    val FILE_NAME: String = "listinfo.dat"

    fun write(item: ArrayList<String>, context: Context) {
        val fos = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE)
        val oas = ObjectOutputStream(fos)

        oas.writeObject(item)
        oas.close()
    }

    fun read(context: Context): ArrayList<String> {
        var itemList: ArrayList<String>
        try {
            val fis = context.openFileInput(FILE_NAME)
            val ois = ObjectInputStream(fis)

            itemList = ois.readObject() as ArrayList<String>

        } catch (e: FileNotFoundException) {
            itemList = ArrayList()
        }
        return  itemList
    }
}