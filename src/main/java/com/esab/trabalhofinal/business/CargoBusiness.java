package com.esab.trabalhofinal.business;

import com.esab.trabalhofinal.dao.CargoDAO;
import com.esab.trabalhofinal.dao.FuncionarioDAO;
import com.esab.trabalhofinal.model.Cargo;
import com.esab.trabalhofinal.model.Funcionario;
import com.esab.trabalhofinal.utilidades.GerenciadorDeLog;
import com.esab.trabalhofinal.utilidades.Notificador;
import java.math.BigInteger;
import java.util.List;




public class CargoBusiness {
	private static CargoBusiness INSTANCE;

	private CargoBusiness() {

	}

	public static CargoBusiness getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new CargoBusiness();
			return INSTANCE;
		} else {
			return INSTANCE;
		}
	}
	
	public List<Cargo> getAll(){
		return CargoDAO.getCargoDAOInstance().getAll();
	}
	
	public Cargo get(BigInteger id) {
		return CargoDAO.getCargoDAOInstance().get(id);
	}
	
	public Cargo get(String nome) {
		return CargoDAO.getCargoDAOInstance().get(nome);
	}
	
	public void cadastrarCargo(String nome) {
		if (null != CargoDAO.getCargoDAOInstance().get(nome)) {
			String mensagem = "Cargo " + nome + "  já existe!!!";
			System.out.println(mensagem);
			Notificador.getInstance().disparaAviso(mensagem);
			GerenciadorDeLog.getInstance().getLogger().warning(mensagem);

		} else {
			Cargo cargo = new Cargo();
			cargo.setNome(nome);
			save(cargo);
			String mensagem = "Cargo " + nome + " cadastrado com sucesso";
			System.out.println(mensagem);
			Notificador.getInstance().disparaInfo(mensagem);
			GerenciadorDeLog.getInstance().getLogger().fine(mensagem);
		}
	}
	
	public void update(BigInteger id, String nome) {
		Cargo cargo = get(id);
		cargo.setNome(nome);
		save(cargo);
	}
	
	public void save(Cargo cargo) {
		if (validate(cargo)) {
			if (null != cargo.getId()) {
				if (validaUpdate(cargo)) {
					CargoDAO.getCargoDAOInstance().save(cargo);
				}
			} else {
				CargoDAO.getCargoDAOInstance().save(cargo);
			}
		}
	}

	private Boolean validaUpdate(Cargo cargo) {
		Cargo cargoBanco = get(cargo.getNome());
		if (null != cargoBanco && !cargoBanco.getId().equals(cargo.getId())) {
			String mensagem = "Já existe Cargo com nome " + cargo.getNome() + "!!!";
			System.out.println(mensagem);
			Notificador.getInstance().disparaAviso(mensagem);
			GerenciadorDeLog.getInstance().getLogger().warning(mensagem);
			return Boolean.FALSE;
		} else {
			return Boolean.TRUE;
		}
	}
	
	private Boolean validate(Cargo cargo) {
		if(null == cargo.getNome() || "".equals(cargo.getNome())) {
			String mensagem = "Nome do Cargo não pode Estar com nome Vazio";
			System.out.println(mensagem);
			Notificador.getInstance().disparaAviso(mensagem);
			GerenciadorDeLog.getInstance().getLogger().warning(mensagem);
			return Boolean.FALSE;
		} else {
			return Boolean.TRUE;
		}
	}
        
        private Boolean validateDelete(BigInteger id) {
		Cargo cargo = CargoDAO.getCargoDAOInstance().get(id);
		List<Funcionario> funcionarios = FuncionarioDAO.getFuncionarioDAOInstance().getAll();
		for(Funcionario funcionario : funcionarios) {
			if(cargo.getId().equals(funcionario.getCargo().getId())) {
				String mensagem = "Cargo não pode ser deletado, há funcionários com este cargo cadastrados!!!";
				System.out.println(mensagem);
				Notificador.getInstance().disparaErro(mensagem);
				GerenciadorDeLog.getInstance().getLogger().severe(mensagem);
				return Boolean.FALSE;			
			}
		}
		return Boolean.TRUE;
	}
	
	public void delete(BigInteger id) {
		if (validateDelete(id)) {
			CargoDAO.getCargoDAOInstance().delete(id);
		}
	}
}
