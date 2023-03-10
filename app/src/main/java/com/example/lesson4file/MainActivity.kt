package com.example.lesson4file

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.lesson4file.databinding.ActivityMainBinding
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.io.path.Path




//Домашка отобрази список файлов в rv и при нажати айтема удалять файл
class MainActivity : AppCompatActivity() {



    lateinit var binding:ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener {
            saveData()
        }
        binding.getButton.setOnClickListener {
            getData()
        }

    }

    private fun getData() {

        val file= File(getExternalFilesDir(null),"/Новая/"+Constants.FILE_TEXT_NAME)




        val data=FileInputStream(file).use {


            String((it.readBytes()))
        }

        Log.d("ololo",data)


        val files=File(getExternalFilesDir(null),"Новая/")


        val list=files.listFiles()


        if (list!=null){

            for (myFiles in list){
                if (myFiles.isFile){

                    Log.d("myList",myFiles.name)


                }


            }


        }






    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun saveData() {
    val text=binding.editText.text.toString()



        //fileDir системные папки



        val file= File(getExternalFilesDir(null),"/Новая/"+Constants.FILE_TEXT_NAME)

    //    file.exists() проверка существует ли такой путь

            Files.createDirectories(Paths.get(getExternalFilesDir(null)?.path + "/Новая/"))




//Log.d("ololo",getExternalFilesDir(null).path.toString())

   FileOutputStream(file).use {



             val bytes=text.toByteArray()
          it.write(bytes)


        it.flush()
        it.close()

    }










    }
}