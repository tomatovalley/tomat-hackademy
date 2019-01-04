package prueba.hackademi.creareventosuperadmin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.mongodb.BasicDBObject
import com.mongodb.MongoClient
import org.bson.Document


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mongo = MongoClient ("192.168.1.74", 27017)
        val db = mongo.getDatabase("tomato")
        val collection = db.getCollection("users")

        val document = Document()
        document.put("name", "Ivan")
        document.put("age", "20")
        collection.insertOne(document)

        val searchQuery = BasicDBObject()
        searchQuery.put("name", "Ivan")

        val cursor = collection.find(searchQuery)





    }
}
