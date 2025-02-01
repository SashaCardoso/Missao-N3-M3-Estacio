package test;

import model.PessoaFisica;
import model.PessoaFisicaDAO;
import model.PessoaJuridicaDAO;
import model.util.ConectorBD;
import model.util.SequenceManager;

public class CadastroBDTeste {
    
    public static void main(String[] args) {
        
        ConectorBD connector = new ConectorBD();
        
        PessoaFisicaDAO fisicaDao = new PessoaFisicaDAO(connector);
        PessoaJuridicaDAO juridicaDao = new PessoaJuridicaDAO(connector);
        
        SequenceManager sm = new SequenceManager();
        
        PessoaFisica pessoaNova = new PessoaFisica("asdf123", "shitsville", "123456789", "asnldnasd@fuckass.com", "123000456");
        fisicaDao.incluir(pessoaNova);
        
    }
    
}
