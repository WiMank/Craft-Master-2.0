package com.wimank.craftmaster.tz

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.google.gson.GsonBuilder
import com.wimank.craftmaster.tz.postgres.AppDataBase
import com.wimank.craftmaster.tz.postgres.RecipesEntity
import com.wimank.craftmaster.tz.request.ListApi
import com.wimank.craftmaster.tz.request.RecipePrimaryKey
import com.wimank.craftmaster.tz.request.RequestList
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val DATA_BASE_VERSION = "RecipesDataBase14.db"

class MainActivity : AppCompatActivity() {

    val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.NONE
    }

    val okhttp = OkHttpClient()
        .newBuilder()
        .addInterceptor(interceptor)
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .build()

    val gfac = GsonConverterFactory.create(GsonBuilder().create())

    val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.30.2:8080")
        .client(okhttp)
        .addConverterFactory(gfac)
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val room = Room.databaseBuilder(this, AppDataBase::class.java, DATA_BASE_VERSION)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

        val recipesDAO = room.recipesDAO()
        recipesDAO.insert(RecipesEntity())

        Observable.fromIterable(recipesDAO.getAll())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .filter { t -> t._id <= 297 }
            .subscribeBy(
                onNext = {
                    //uploadList(it)
                },
                onError = {
                    Log.e("TEST RX", "rx:", it)
                },
                onComplete = {
                    Toast.makeText(this, "onComplete", Toast.LENGTH_SHORT).show()
                }
            )
    }

    private fun uploadList(recipesEntity: RecipesEntity) {
        val api = retrofit.create(ListApi::class.java)


        val firtst = try {
            val resName = resources.getIdentifier(recipesEntity.name, "string", packageName)
            Log.i("TEST RX", "NAME: ${getString(resName)}")
            getString(resName)
        } catch (e: Resources.NotFoundException) {
            Log.i("TEST RX", "NAME: NULL")
            ""
        }

        val resp =
            api.postList(RequestList(RecipePrimaryKey(firtst, recipesEntity.imageIcon ?: "")))
        resp.enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("TEST RX", "API RESP: FAIIIIL!", t)
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Log.i("TEST RX", "API RESP: ${response.body()}!")
            }
        })
    }
}

/* val file = getDatabasePath(DATA_BASE_VERSION)

         if (!file.exists()) {
             if (!file.parentFile.exists()) {
                 file.parentFile.mkdir()
             }
         }

         val inputStream = assets.open("RecipesDataBase14adadasdasdasd.db")

         inputStream.use { input ->
             val outputStream = FileOutputStream(file)
             outputStream.use { output ->
                 val buffer = ByteArray(4 * 1024)
                 while (true) {
                     val byteCount = input.read(buffer)
                     if (byteCount < 0) break
                     output.write(buffer, 0, byteCount)
                 }
                 output.flush()
             }
         }*/