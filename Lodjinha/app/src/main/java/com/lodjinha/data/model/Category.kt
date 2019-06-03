package com.lodjinha.data.model

data class Category (val id: String, val descricao: String, val urlImagem: String){
    override fun toString(): String {
        return "Category(id='$id', descricao=$descricao, urlImagem='$urlImagem')"
    }
}