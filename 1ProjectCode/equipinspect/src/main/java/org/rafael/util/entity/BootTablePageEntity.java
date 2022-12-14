package org.rafael.util.entity;

import java.io.Serializable;
import java.util.List;
/**
 * bootstrap-table结合使用
 * @author Rafael
 *
 * @param <T>
 */
public class BootTablePageEntity<T> implements Serializable {
	private static final long serialVersionUID = -4161680222399055222L;

    private long total; //对应页面总条数 
    private List<T> rows; //对应页面结果集
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
