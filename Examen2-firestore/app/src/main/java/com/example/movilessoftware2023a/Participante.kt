package com.example.movilessoftware2023a

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi
import java.time.LocalDate

class Participante (
    var nickname: String?,
    var equipo: String?,
    var temasInteres: String?,
    var edad: Int,
    val id: Int?,
    var debate: Debate?
): Parcelable {
    @RequiresApi(Build.VERSION_CODES.Q)
    constructor(parcel: Parcel): this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readParcelable<Debate>()
    )


    constructor(nickname: String, equipo: String?, temasInteres: String?, edad: Int):
            this(nickname, equipo, temasInteres, edad)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nickname)
        parcel.writeValue(equipo)
        parcel.writeValue(temasInteres)
        parcel.writeValue(edad)
        parcel.writeValue(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Participante> {
        @RequiresApi(Build.VERSION_CODES.Q)
        override fun createFromParcel(parcel: Parcel): Participante {
            return Participante(parcel)
        }

        override fun newArray(size: Int): Array<Participante?> {
            return arrayOfNulls(size)
        }
    }


    override fun toString(): String {
        return "${this.nickname}" +
                "\n       Equipo: ${this.equipo}" +
                "\n       Temas de interés: ${this.temasInteres}" +
                "\n       Edad: ${this.edad} años\n"
    }
}