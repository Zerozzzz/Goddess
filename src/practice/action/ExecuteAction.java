package practice.action;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import practice.dao.ExecuteGodess;
import practice.model.Godess;

public class ExecuteAction {

	public void add(Godess godess) {
		ExecuteGodess eg = new ExecuteGodess();
		eg.addGodess(godess);
	}
	
	public List<Godess> query() throws SQLException{
		ExecuteGodess eg = new ExecuteGodess();
		List<Godess> list = eg.query();
		return list;
	}
	
	public List<Godess> query(List<Map<String,Object>> params) throws SQLException{
		ExecuteGodess eg = new ExecuteGodess();
		List<Godess> list = eg.query(params);
		return list;
	}
	
	public void update(Godess godess) {
		ExecuteGodess eg = new ExecuteGodess();
		eg.upGodess(godess);
	}
	
	public void del(Integer id) {
		ExecuteGodess eg = new ExecuteGodess();
		eg.delGodess(id);
	}

	public Godess queOne(Integer id) throws SQLException {
		ExecuteGodess eg = new ExecuteGodess();
		Godess godess = eg.queOneGodess(id);
		return godess;
	}
	
}
