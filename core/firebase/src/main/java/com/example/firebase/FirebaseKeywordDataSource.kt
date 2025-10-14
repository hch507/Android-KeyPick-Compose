package com.example.firebase

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject


class FirebaseKeywordDataSource @Inject constructor(
    private val db: FirebaseFirestore
) {

    suspend fun fetchTodayKeyword(): String? {
        return try {
            val today = SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(Date())
            val snapshot = db.collection("keywordDB") // TODO: 컬렉션 이름 수정
                .whereEqualTo("date", today)
                .get()
                .await()

            if (!snapshot.isEmpty) {
                val keyword = snapshot.documents.firstOrNull()?.getString("keyword")
                if (!keyword.isNullOrBlank()) {
                    keyword
                } else {
                    null
                }
            } else {
                null
            }
        } catch (e: Exception) {
            Log.e("firebase_catch", "Error fetching keyword", e)
            null
        }
    }
}