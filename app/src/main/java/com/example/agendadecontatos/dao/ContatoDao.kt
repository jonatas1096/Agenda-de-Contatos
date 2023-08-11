package com.example.agendadecontatos.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.agendadecontatos.model.Contato

@Dao
interface ContatoDao {

    @Insert
    fun salvar(ListaContatos : MutableList<Contato>)

    @Query("SELECT * FROM tabela_contatos ORDER BY nome ASC")
    fun listar() : MutableList<Contato>

    @Query("UPDATE tabela_contatos SET nome = :novoNome, sobrenome = :novoSobrenome, idade = :novaIdade, numero = :novoNumero WHERE usuarioID = :id")
    fun atualizar(id: Int, novoNome: String, novoSobrenome: String, novaIdade: Int, novoNumero: Int)


    @Query("DELETE FROM tabela_contatos WHERE usuarioID = :id")
    fun deletar(id:Int)
}