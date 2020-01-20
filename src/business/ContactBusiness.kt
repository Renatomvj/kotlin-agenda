package business

import entity.ContactEntity
import repository.ContactRepository
import java.lang.Exception

class ContactBusiness{

    private fun validar(nome:String, telefone:String)
    {
        if(nome == "") {
            throw  Exception("Nome é obrigatório!")
        }
        if(telefone == "") {
            throw  Exception("Telefone é obrigatório!")
        }
    }

    private fun validaDeletar(nome:String, telefone:String)
    {
        if(nome == "" || telefone == "") {
            throw  Exception("É necessário selecionar um contato antes de remover")
        }
    }

    fun salvar(nome:String, telefone: String )
    {
        validar(nome, telefone)

        var contato = ContactEntity(nome, telefone);
        ContactRepository.salvar(contato)

    }

    fun deletar(nome:String, telefone: String )
    {
        validaDeletar(nome, telefone)
        var contato = ContactEntity(nome, telefone);
        ContactRepository.deletar(contato)
    }

    fun obterLista(): List<ContactEntity> {
        return ContactRepository.obterLista();
    }
    fun obterQuantidadeContato(): String{
        var lst = obterLista();

        return when {
            lst.isEmpty() -> "0 Contatos"
            lst.size == 1 -> "1 Contato"
            else -> "${lst.size} Contatos"
        }
    }
}