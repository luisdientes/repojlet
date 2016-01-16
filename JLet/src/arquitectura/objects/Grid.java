
package arquitectura.objects;

import java.util.ArrayList;
import java.util.Collection;

public class Grid {
	
	ArrayList cols = new ArrayList();
	ArrayList rows = new ArrayList();
	
	public Grid(){	
	}
	
	public Grid(Collection cols) { 
        this.cols.addAll(cols);
    }
	
	public int columnCount(){
		return cols.size();
	}
	
	public int rowCount(){
		return rows.size();
	}
	
	public void addRow(Collection row){
		rows.add(row);
	}
	
	public void setCell(int rowIndex, int colIndex, Object obj){
		ArrayList row = (ArrayList)rows.get(rowIndex);
		row.set(colIndex,obj); 
	}
	
	public Collection getRow(int rowIndex){
		return (Collection)rows.get(rowIndex);
	}
	
	public Object getCell(int rowIndex, int colIndex){
		ArrayList row = (ArrayList)rows.get(rowIndex);
		return row.get(colIndex);
	}
	
	public String getStringCell(int rowIndex, int colIndex){
		ArrayList row = (ArrayList)rows.get(rowIndex);
		return row.get(colIndex).toString();
	}
	
	public Object getCell(int rowIndex, String colName){
		ArrayList row = (ArrayList)rows.get(rowIndex);
		int colIndex = cols.indexOf(colName);
		return row.get(colIndex);
	}
	
	public String getStringCell(int rowIndex, String colName){
		String value = "";
		ArrayList row = (ArrayList)rows.get(rowIndex);
		
		int colIndex = cols.indexOf(colName);
		
		try {
			value = row.get(colIndex).toString();
		} catch (Exception e) {
			
		}
		
		return value;
	}
	
	public void addColumn(String columnName){
		cols.add(columnName);
		
		int rowSize = rows.size();
		if (rowSize > 0){
			for (int i=0; i<rowSize; i++){
				ArrayList row = (ArrayList)rows.get(i);
				row.add(null);
			}
		}
	}
	
	public void addGrid(Grid grid){
		int count=grid.rowCount();
		for (int i=0; i < count; i++){
			this.addRow(grid.getRow(i));
		}
	}
	
	public boolean setColumnName(String oldColumnName, String newColumnName){
		boolean encontrado = false;
		for (int i=0;i<cols.size()&&!encontrado; i++){
			if (((String)cols.get(i)).equals(oldColumnName)){
				encontrado = true;
				cols.set(i,newColumnName);
			}
		}
		return encontrado;
	}
	
	public boolean setColumnName(int index,String columnName){
		if (cols.size() >= index){
			cols.set(index,columnName);
			return true;
		} else {
			return false;
		}
	}
	
	public void addColumns(Collection columns){
		cols.add(columns);
	}
	
	public void addGrid(String var, String grid){		
		//DESARROLLAR
	}
	
	public Collection getColumns(){
		return cols;
	}
	
	public String getColumnName(int colIndex){
		return (String)cols.get(colIndex);
	}
	
}
