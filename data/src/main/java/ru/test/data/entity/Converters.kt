package ru.test.data.entity

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.test.data.model.Address
import ru.test.data.model.Button
import ru.test.data.model.Experience
import ru.test.data.model.Salary

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromButton(button: Button?): String? {
        return gson.toJson(button)
    }

    @TypeConverter
    fun toButton(json: String?): Button? {
        return gson.fromJson(json, object : TypeToken<Button?>() {}.type)
    }

    @TypeConverter
    fun fromAddress(address: Address?): String? {
        return gson.toJson(address)
    }

    @TypeConverter
    fun toAddress(json: String?): Address? {
        return gson.fromJson(json, object : TypeToken<Address?>() {}.type)
    }

    @TypeConverter
    fun fromExperience(experience: Experience?): String? {
        return gson.toJson(experience)
    }

    @TypeConverter
    fun toExperience(json: String?): Experience? {
        return gson.fromJson(json, object : TypeToken<Experience?>() {}.type)
    }

    @TypeConverter
    fun fromSalary(salary: Salary?): String? {
        return gson.toJson(salary)
    }

    @TypeConverter
    fun toSalary(json: String?): Salary? {
        return gson.fromJson(json, object : TypeToken<Salary?>() {}.type)
    }

    @TypeConverter
    fun fromStringList(list: List<String>?): String? {
        return gson.toJson(list)
    }

    @TypeConverter
    fun toStringList(json: String?): List<String>? {
        return gson.fromJson(json, object : TypeToken<List<String>?>() {}.type)
    }
}