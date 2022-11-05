package com.esab.trabalhofinal.business;

import com.esab.trabalhofinal.dao.FuncionarioDAO;
import com.esab.trabalhofinal.model.Funcionario;
import com.esab.trabalhofinal.utilidades.GerenciadorDeLog;
import com.esab.trabalhofinal.utilidades.Notificador;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;




public class FuncionarioBusiness {
	private static FuncionarioBusiness INSTANCE;

	private FuncionarioBusiness() {

	}

	public static FuncionarioBusiness getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new FuncionarioBusiness();
			return INSTANCE;
		} else {
			return INSTANCE;
		}
	}
	
	public List<Funcionario> getAll(){
		return FuncionarioDAO.getFuncionarioDAOInstance().getAll();
	}
	
	public Funcionario get(BigInteger id) {
		return FuncionarioDAO.getFuncionarioDAOInstance().get(id);
	}
	
	public List<Funcionario> get(String nome) {
		return FuncionarioDAO.getFuncionarioDAOInstance().get(nome);
	}
	
	public List<Funcionario> getLikeName(String nome) {
		return FuncionarioDAO.getFuncionarioDAOInstance().getLikeName(nome);
	}
	
	public void cadastrarFuncionario(String nome, BigInteger idade, BigDecimal salario, String cargo, String mes, String ano) {

		Funcionario funcionario = new Funcionario();
		funcionario.setNome(nome);
		funcionario.setIdade(idade);
		funcionario.setSalario(salario);
		funcionario.setCargo(CargoBusiness.getInstance().get(cargo));
		funcionario.setMes(mes);
                funcionario.setAno(ano);
		funcionario.setFaltas(BigInteger.ZERO);
		FuncionarioDAO.getFuncionarioDAOInstance().save(funcionario);
                String mensagem = "Funcionario " + nome + " cadastrado com sucesso";
                System.out.println(mensagem);
		Notificador.getInstance().disparaInfo(mensagem);
		GerenciadorDeLog.getInstance().getLogger().fine(mensagem);
	}
	
	public void salvarFuncionario(BigInteger id, String nome, BigInteger idade, BigDecimal salario, String cargo, String mes, String ano) {
        Funcionario funcionario = get(id);
		funcionario.setNome(nome);
		funcionario.setIdade(idade);
		funcionario.setSalario(salario);
		funcionario.setCargo(CargoBusiness.getInstance().get(cargo));
		funcionario.setMes(mes);
                funcionario.setAno(ano);
		FuncionarioDAO.getFuncionarioDAOInstance().save(funcionario);
                String mensagem = "Funcionario " + nome + " salvo com sucesso";
                System.out.println(mensagem);
		Notificador.getInstance().disparaInfo(mensagem);
		GerenciadorDeLog.getInstance().getLogger().fine(mensagem);
	}
	
	private void save(Funcionario funcionario) {
		if (validate(funcionario)) {
			FuncionarioDAO.getFuncionarioDAOInstance().save(funcionario);
		}
	}
	
	public void delete(BigInteger id) {
		FuncionarioDAO.getFuncionarioDAOInstance().delete(id);
                
	}

	private Boolean validate(Funcionario funcionario) {
            if(null == funcionario.getKm()) {
			funcionario.setKm(BigInteger.ZERO);
            }
		if(null == funcionario.getNome() || "".equals(funcionario.getNome())) {
			System.out.println("Nome do Funcionário não pode estar vazio");
			Notificador.getInstance().disparaAviso("Nome do Funcionário não pode Estar com nome Vazio");
			return Boolean.FALSE;
		} else {
			return Boolean.TRUE;
		}
                
	}

}
