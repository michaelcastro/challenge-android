package com.lodjinha.data.model

class Product(
    val id: String = "",
    val nome: String = "",
    val descricao: String = "",
    val urlImagem: String = "",
    val precoDe: Float = 0f,
    val precoPor: Float = 0f,
    val categoria: Category? = null
) {

    override fun toString(): String {
        return "Product(id='$id', nome='$nome', descricao='$descricao', urlImagem='$urlImagem', precoDe=$precoDe, precoPor=$precoPor, categoria=$categoria)"
    }
}