package repository

import entity.ContactEntity

class ContactRepository {

    companion object {

        private val lstContato = mutableListOf<ContactEntity>()

        fun salvar(contato: ContactEntity) {
            lstContato.add(contato)

        }

        fun deletar(contato: ContactEntity) {

            var index =0;
            for (item in lstContato.withIndex())
            {
                if(item.value.nome == contato.nome && item.value.telefone == contato.telefone)
                {
                    index = item.index;
                    break;
                }

            }
            lstContato.removeAt(index);
        }

        fun obterLista(): List<ContactEntity> {
            return lstContato;
        }

    }


}