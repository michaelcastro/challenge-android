package com.lodjinha.data.model

data class Banner(val id: String, val linkUrl: String, val urlImagem: String){
    override fun toString(): String {
        return "Banner(id='$id', linkUrl=$linkUrl, urlImagem='$urlImagem')"
    }
}