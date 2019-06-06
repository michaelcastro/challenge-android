package com.lodjinha.data.model

import android.os.Parcel
import android.os.Parcelable

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class Product(
    val id: String = "",
    val nome: String = "",
    val urlImagem: String = "",
    val descricao: String = "",
    val precoDe: Float = 0f,
    val precoPor: Float = 0f,
    val categoria: Category? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readFloat(),
        parcel.readFloat(),
        parcel.readParcelable(Category::class.java.classLoader)
    ) {
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(id)
        dest.writeString(nome)
        dest.writeString(urlImagem)
        dest.writeString(descricao)
        dest.writeFloat(precoDe)
        dest.writeFloat(precoPor)
        dest.writeParcelable(categoria, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "Product(id='$id', nome='$nome', descricao='$descricao', urlImagem='$urlImagem', precoDe=$precoDe, precoPor=$precoPor, categoria=$categoria)"
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }
}